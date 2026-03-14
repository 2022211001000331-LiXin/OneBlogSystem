package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.FileBlogInfo;

import java.util.List;


/**
 * blog文件关联 数据层
 * */
public interface FileBlogInfoMapper {

    /**
     * 批量新增
     */
    int batchFileBlog(List<FileBlogInfo> fileBlogList);

    /**
     * 通过blogID删除blog文件关联
     */
    int deleteFileBlogByBlogId(Long blogId);

    /**
     * 批量删除blog文件关联
     */
    int deleteFileBlog(Long[] ids);

    /**
     * 查询文件列表
     */
    List<FileBlogInfo> selectFileBlogList(Long blogId);
}
