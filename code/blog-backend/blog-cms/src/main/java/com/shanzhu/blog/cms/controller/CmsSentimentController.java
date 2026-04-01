package com.shanzhu.blog.cms.controller;

import com.shanzhu.blog.common.core.domain.AjaxResult;
import com.shanzhu.blog.cms.service.ICmsCommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/cms/sentiment")
public class CmsSentimentController {

    @Resource
    private ICmsCommentService cmsCommentService;

    /**
     * 获取情感分析看板数据
     */
    @GetMapping("/dashboard")
    public AjaxResult getDashboard() {
        return AjaxResult.success(cmsCommentService.getSentimentDashboardData());
    }
}