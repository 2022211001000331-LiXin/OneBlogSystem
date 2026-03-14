package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.CmsCommentLike;

import java.util.List;


/**
 * 评论点赞
 * */
public interface CmsCommentLikeMapper {

    /**
     * 查询列表
     */
    List<CmsCommentLike> selectCmsCommentLikeList(CmsCommentLike cmsCommentLike);

    /**
     * 新增
     */
    int addCmsCommentLike(CmsCommentLike cmsCommentLike);

    /**
     * 删除关联
     */
    int deleteCmsCommentLike(CmsCommentLike cmsCommentLike);
}
