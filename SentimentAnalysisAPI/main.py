from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field
from typing import List
import uvicorn
from core.model_processor import SentimentEngine

app = FastAPI(title="智能博客情感分析微服务")

# 1. 初始化引擎
engine = SentimentEngine()


# 2. 定义请求体结构 (使用 Pydantic，自带数据校验)
class AnalyzeRequest(BaseModel):
    content: str = Field(..., description="需要分析的评论文本", min_length=1, max_length=1000)


# 3. 定义返回体结构
class AnalyzeResponseData(BaseModel):
    sentiment: str
    confidence: float
    keywords: List[str]


class AnalyzeResponse(BaseModel):
    code: int
    msg: str
    data: AnalyzeResponseData = None


# 4. 路由定义
@app.post("/analyze", response_model=AnalyzeResponse)
async def analyze_api(request: AnalyzeRequest):
    try:
        # 执行分析
        result = engine.analyze(request.content)

        return AnalyzeResponse(
            code=200,
            msg="分析成功",
            data=AnalyzeResponseData(**result)
        )
    except Exception as e:
        # 捕获可能的模型异常，防止服务崩溃
        print(f"分析出错: {e}")
        return AnalyzeResponse(code=500, msg="服务器内部推理错误", data=None)


if __name__ == "__main__":
    # 启动在 8000 端口
    uvicorn.run(app, host="127.0.0.1", port=8000)