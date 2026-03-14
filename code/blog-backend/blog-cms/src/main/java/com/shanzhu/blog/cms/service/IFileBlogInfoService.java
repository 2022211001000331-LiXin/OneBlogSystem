package com.shanzhu.blog.cms.service;

import com.shanzhu.blog.cms.domain.FileBlogInfo;

import java.util.List;


/**
 * 博客文件信息service接口
 * */
public interface IFileBlogInfoService {

    /**
     * 批量新增
     */
    int batchFileBlog(List<FileBlogInfo> fileBlogList);

    /**
     * 通过通知公告ID删除通知公告和文件关联
     */
    int deleteFileBlogByBlogId(Long blogId);

    /**
     * 批量删除通知公告和文件关联
     */
    int deleteFileBlog(Long[] ids);

    /**
     * 查询文件列表
     */
    List<FileBlogInfo> selectFileBlogList(Long blogId);
}
