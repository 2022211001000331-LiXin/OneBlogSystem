package com.shanzhu.blog.cms.service;

import com.shanzhu.blog.cms.domain.CmsBlogTag;

import java.util.List;


/**
 * 博客标签service
 * */
public interface ICmsBlogTagService {

    /**
     * 批量新增
     */
    int batchBlogTag(List<CmsBlogTag> blogTagList);

    /**
     * 通过blogID删除blog标签关联
     */
    int deleteBlogTagByBlogId(Long blogId);

    /**
     * 批量删除blog标签关联
     */
    int deleteBlogTag(Long[] ids);

    /**
     * 查询标签列表
     */
    List<CmsBlogTag> selectBlogTagList(Long blogId);
}
