package com.shanzhu.blog.cms.domain;

import java.util.List;

/**
 * 情感分析结果接收对象
 */
public class SentimentResultDto {
    private String sentiment;    // 正面/负面
    private Double confidence;   // 置信度
    private List<String> keywords; // 关键词列表

    // Getter and Setter
    public String getSentiment() { return sentiment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }

    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }

    public List<String> getKeywords() { return keywords; }
    public void setKeywords(List<String> keywords) { this.keywords = keywords; }
}