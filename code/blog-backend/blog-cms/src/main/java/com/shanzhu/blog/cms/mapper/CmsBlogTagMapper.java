package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.CmsBlogTag;

import java.util.List;


/**
 * blog标签关联
 * */
public interface CmsBlogTagMapper {

    /**
     * 批量新增
     */
    int batchBlogTag(List<CmsBlogTag> blogTagList);

    /**
     * 通过blogID删除blog文件关联
     */
    int deleteBlogTagByBlogId(Long blogId);

    /**
     * 批量删除blog文件关联
     */
    int deleteBlogTag(Long[] ids);

    /**
     * 查询文件列表
     */
    List<CmsBlogTag> selectBlogTagList(Long blogId);

    /**
     * 通过tagId删除blog文件关联
     */
    int deleteBlogTagByTagId(Long tagId);

    /**
     * 通过tagId统计blog数量
     */
    int countBlogByTagId(Long tagId);
}
