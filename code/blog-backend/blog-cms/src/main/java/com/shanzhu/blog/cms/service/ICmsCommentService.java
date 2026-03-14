package com.shanzhu.blog.cms.service;

import com.shanzhu.blog.cms.domain.CmsComment;
import com.shanzhu.blog.cms.domain.CmsCommentLike;

import java.util.List;


/**
 * 评论管理Service接口
 * */
public interface ICmsCommentService {

    /**
     * 首页查询留言列表
     */
    List<CmsComment> selectCommentList(CmsComment cmsComment);

    /**
     * 首页新增点赞
     */
    int addCmsCommentLike(CmsCommentLike cmsCommentLike);

    /**
     * 首页删除点赞
     */
    int delCmsCommentLike(CmsCommentLike cmsCommentLike);

    /**
     * 查询评论管理
     *
     * @param id 评论管理主键
     * @return 评论管理
     */
    CmsComment selectCmsCommentById(Long id);

    /**
     * 查询评论管理列表
     *
     * @param cmsComment 评论管理
     * @return 评论管理集合
     */
    List<CmsComment> selectCmsCommentList(CmsComment cmsComment);

    /**
     * 新增评论管理
     *
     * @param cmsComment 评论管理
     * @return 结果
     */
    int insertCmsComment(CmsComment cmsComment);

    /**
     * 修改评论管理
     *
     * @param cmsComment 评论管理
     * @return 结果
     */
    int updateCmsComment(CmsComment cmsComment);

    /**
     * 批量删除评论管理
     *
     * @param ids 需要删除的评论管理主键集合
     * @return 结果
     */
    int deleteCmsCommentByIds(Long[] ids);

    /**
     * 删除评论管理信息
     *
     * @param id 评论管理主键
     * @return 结果
     */
    int deleteCmsCommentById(Long id);
}
