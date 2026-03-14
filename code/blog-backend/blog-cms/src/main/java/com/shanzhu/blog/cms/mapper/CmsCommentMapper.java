package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.CmsComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 评论管理Mapper接口
 * */
public interface CmsCommentMapper {

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

    List<CmsComment> selectCmsCommentListBetweenCreateTime(
            @Param("type") String type,
            @Param("delFlag") String delFlag, @Param(
            "createTimeBegin") String createTimeBegin,
            @Param("createTimeEnd") String createTimeEnd
    );

    /**
     * 查询子留言列表
     */
    List<CmsComment> selectChildCommentList(CmsComment cmsComment);

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
     * 删除评论管理
     *
     * @param id 评论管理主键
     * @return 结果
     */
    int deleteCmsCommentById(Long id);

    /**
     * 批量删除评论管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCmsCommentByIds(Long[] ids);

    /**
     * 删除评论管理
     *
     * @param id 评论管理主键
     * @return 结果
     */
    int updateDelFlagById(Long id);

    /**
     * 批量删除评论管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int updateDelFlagByIds(Long[] ids);
}
