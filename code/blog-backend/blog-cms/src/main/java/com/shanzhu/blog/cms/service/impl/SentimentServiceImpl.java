package com.shanzhu.blog.cms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanzhu.blog.cms.domain.SentimentResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SentimentServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(SentimentServiceImpl.class);

    @Value("${ai.sentiment.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 调用 Python AI 接口分析情感
     * @param content 评论内容
     * @return 分析结果对象
     */
    public SentimentResultDto analyzeContent(String content) {
        try {
            // 1. 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 2. 构建请求体 (对应 Python 的 AnalyzeRequest)
            Map<String, String> map = new HashMap<>();
            map.put("content", content);
            HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(map), headers);

            // 3. 发送 POST 请求
            log.info("正在调用 AI 情感分析接口，内容长度: {}", content.length());
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

            // 4. 解析结果
            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject jsonObject = JSON.parseObject(response.getBody());
                int code = jsonObject.getIntValue("code");
                
                if (code == 200) {
                    // 获取 data 部分并转为 DTO
                    JSONObject data = jsonObject.getJSONObject("data");
                    return data.toJavaObject(SentimentResultDto.class);
                }
            }
            log.error("AI 接口返回异常状态码: {}", response.getStatusCode());
        } catch (Exception e) {
            // 容错处理：如果 Python 服务没开或网络超时，记录错误但不中断 Java 业务
            log.error("调用 AI 情感分析失败，错误原因: {}", e.getMessage());
        }
        return null; // 返回 null 代表分析失败，由调用方处理默认值
    }
}