package com.shanzhu.blog.cms.service;

import com.shanzhu.blog.cms.domain.CmsBlogType;

import java.util.List;


/**
 * 博客类型service接口
 * */
public interface ICmsBlogTypeService {

    /**
     * 批量新增
     */
    int batchBlogType(List<CmsBlogType> blogTypeList);

    /**
     * 通过blogID删除blog分类关联
     */
    int deleteBlogTypeByBlogId(Long blogId);

    /**
     * 批量删除blog分类关联
     */
    int deleteBlogType(Long[] ids);

    /**
     * 查询分类列表
     */
    List<CmsBlogType> selectBlogTypeList(Long blogId);
}
