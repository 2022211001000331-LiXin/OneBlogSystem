import torch
import hanlp
from transformers import AutoTokenizer, AutoModelForSequenceClassification
import re


class SentimentEngine:
    def __init__(self):
        self.device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
        print(f"✅ 正在加载模型到设备: {self.device}")

        # 加载 HanLP 分词
        self.tokenizer_hanlp = hanlp.load(hanlp.pretrained.tok.COARSE_ELECTRA_SMALL_ZH)

        # 💡 优化点 1：扩充停用词表
        # 在实际毕设中，你可以去网上下载一个“哈工大停用词表.txt”来读取。
        # 这里我为你补充了常见的代词、副词、助词等无意义词汇
        self.stopwords = {
            "的", "了", "和", "是", "就", "都", "而", "及", "与", "着", "这", "那",
            "我", "你", "他", "她", "它", "也", "在", "有", "对", "个",
            "这个", "那个", "简直", "太", "非常", "很", "特别", "其实", "吧", "吗", "呢"
        }

        self.model_name = "IDEA-CCNL/Erlangshen-Roberta-110M-Sentiment"
        self.tokenizer_bert = AutoTokenizer.from_pretrained(self.model_name)
        self.model_bert = AutoModelForSequenceClassification.from_pretrained(self.model_name, num_labels=2)

        self.model_bert.to(self.device)
        self.model_bert.eval()
        print("✅ 模型加载完毕！")

    def clean_text(self, text):
        """文本清洗：去除特殊符号和URL"""
        text = re.sub(r"http\S+|www\S+|https\S+", '', text, flags=re.MULTILINE)
        text = re.sub(r'[^\w\s\u4e00-\u9fa5]', '', text)
        return text

    def analyze(self, text):
        cleaned_raw_text = self.clean_text(text)

        # HanLP 分词
        words = self.tokenizer_hanlp(cleaned_raw_text)

        # 💡 优化点 2：取消 len(w) > 1 的限制，保留情感单字（如：差、好）
        # 只要求去除停用词，并且去除纯粹的空格
        useful_words = [w for w in words if w not in self.stopwords and len(w.strip()) > 0]

        # 拼回字符串送入BERT进行推断
        bert_input_text = "".join(words)

        inputs = self.tokenizer_bert(
            bert_input_text,
            return_tensors="pt",
            truncation=True,
            max_length=128
        ).to(self.device)

        with torch.no_grad():
            outputs = self.model_bert(**inputs)
            probs = torch.nn.functional.softmax(outputs.logits, dim=-1)
            label_idx = torch.argmax(probs, dim=-1).item()
            confidence = torch.max(probs).item()

        label_str = "正面" if label_idx == 1 else "负面"

        return {
            "sentiment": label_str,
            "confidence": round(confidence, 4),
            "keywords": useful_words  # 现在提取出的关键词会精准很多！
        }