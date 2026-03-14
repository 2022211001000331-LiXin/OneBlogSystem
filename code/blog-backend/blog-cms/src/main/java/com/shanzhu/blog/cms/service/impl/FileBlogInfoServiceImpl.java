package com.shanzhu.blog.cms.service.impl;

import com.shanzhu.blog.cms.domain.FileBlogInfo;
import com.shanzhu.blog.cms.mapper.FileBlogInfoMapper;
import com.shanzhu.blog.cms.service.IFileBlogInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 博客文件信息service实现类
 * */
@Service
public class FileBlogInfoServiceImpl implements IFileBlogInfoService {

    @Resource
    private FileBlogInfoMapper fileBlogInfoMapper;

    @Override
    public int batchFileBlog(List<FileBlogInfo> fileBlogList) {
        return fileBlogInfoMapper.batchFileBlog(fileBlogList);
    }

    @Override
    public int deleteFileBlogByBlogId(Long blogId) {
        return fileBlogInfoMapper.deleteFileBlogByBlogId(blogId);
    }

    @Override
    public int deleteFileBlog(Long[] ids) {
        return fileBlogInfoMapper.deleteFileBlog(ids);
    }

    @Override
    public List<FileBlogInfo> selectFileBlogList(Long blogId) {
        return fileBlogInfoMapper.selectFileBlogList(blogId);
    }
}
