package com.shanzhu.blog.cms.service;

import com.shanzhu.blog.cms.domain.CmsBlog;
import com.shanzhu.blog.cms.domain.CmsComment;
import com.shanzhu.blog.cms.domain.CmsMessage;

import java.util.List;


/**
 * 图表服务层
 * */
public interface IChartService {

    List<CmsBlog> selectList(CmsBlog cmsBlog);

    List<CmsBlog> selectListBetweenCreateTime(
            CmsBlog cmsBlog, String createTimeBegin, String createTimeEnd);

    List<CmsComment> selectCmsCommentListBetweenCreateTime(
            CmsComment cmsComment,
            String createTimeBegin,
            String createTimeEnd
    );

    List<CmsMessage> selectCmsMessageListBetweenCreateTime(
            CmsMessage cmsMessage,
            String createTimeBegin,
            String createTimeEnd
    );
}
