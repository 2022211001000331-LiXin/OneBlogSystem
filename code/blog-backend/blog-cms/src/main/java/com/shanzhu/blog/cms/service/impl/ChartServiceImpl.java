package com.shanzhu.blog.cms.service.impl;

import com.shanzhu.blog.cms.domain.CmsBlog;
import com.shanzhu.blog.cms.domain.CmsComment;
import com.shanzhu.blog.cms.domain.CmsMessage;
import com.shanzhu.blog.cms.mapper.CmsBlogMapper;
import com.shanzhu.blog.cms.mapper.CmsCommentMapper;
import com.shanzhu.blog.cms.mapper.CmsMessageMapper;
import com.shanzhu.blog.cms.service.IChartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 图表服务层实现类
 * */
@Service
public class ChartServiceImpl implements IChartService {

    @Resource
    private CmsBlogMapper cmsBlogMapper;

    @Resource
    private CmsCommentMapper cmsCommentMapper;

    @Resource
    private CmsMessageMapper cmsMessageMapper;

    @Override
    public List<CmsBlog> selectList(CmsBlog cmsBlog) {
        return cmsBlogMapper.selectCmsBlogList(cmsBlog);
    }

    @Override
    public List<CmsBlog> selectListBetweenCreateTime(
            CmsBlog cmsBlog, String createTimeBegin, String createTimeEnd
    ) {
        return cmsBlogMapper.selectCmsBlogListBetweenCreateTime(
                cmsBlog.getTitle(), cmsBlog.getType(),
                cmsBlog.getTop(), cmsBlog.getStatus(),
                createTimeBegin, createTimeEnd,
                cmsBlog.getCreateBy()
        );
    }

    @Override
    public List<CmsComment> selectCmsCommentListBetweenCreateTime(
            CmsComment cmsComment, String createTimeBegin, String createTimeEnd) {
        return cmsCommentMapper.selectCmsCommentListBetweenCreateTime(
                cmsComment.getType(), cmsComment.getDelFlag(),
                createTimeBegin, createTimeEnd
        );
    }

    @Override
    public List<CmsMessage> selectCmsMessageListBetweenCreateTime(
            CmsMessage cmsMessage, String createTimeBegin, String createTimeEnd) {
        return cmsMessageMapper.selectCmsMessageListBetweenCreateTime(
                cmsMessage.getType(), cmsMessage.getDelFlag(),
                createTimeBegin, createTimeEnd
        );
    }
}
