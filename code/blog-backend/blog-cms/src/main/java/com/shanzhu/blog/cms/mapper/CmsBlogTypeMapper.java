package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.CmsBlogType;

import java.util.List;


/**
 * blog分类关联
 * */
public interface CmsBlogTypeMapper {

    /**
     * 批量新增
     */
    int batchBlogType(List<CmsBlogType> blogTypeList);

    /**
     * 通过blogID删除blog文件关联
     */
    int deleteBlogTypeByBlogId(Long blogId);

    /**
     * 批量删除blog文件关联
     */
    int deleteBlogType(Long[] ids);

    /**
     * 查询博客列表
     */
    List<CmsBlogType> selectBlogTypeList(Long blogId);

    /**
     * 通过typeId删除blog文件关联
     */
    int deleteBlogTypeByTypeId(Long typeId);

    /**
     * 通过typeId统计blog数量
     */
    int countBlogByTypeId(Long typeId);
}
