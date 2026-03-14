package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.CmsMessageLike;

import java.util.List;


/**
 * 评论消息点赞mapper
 * */
public interface CmsMessageLikeMapper {
    /**
     * 查询列表
     */
     List<CmsMessageLike> selectCmsMessageLikeList(CmsMessageLike cmsMessageLike);

    /**
     * 新增
     */
     int addCmsMessageLike(CmsMessageLike cmsMessageLike);

    /**
     * 删除关联
     */
     int deleteCmsMessageLike(CmsMessageLike cmsMessageLike);
}
