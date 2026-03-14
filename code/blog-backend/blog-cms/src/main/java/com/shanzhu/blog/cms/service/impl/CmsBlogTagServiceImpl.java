package com.shanzhu.blog.cms.service.impl;

import com.shanzhu.blog.cms.domain.CmsBlogTag;
import com.shanzhu.blog.cms.mapper.CmsBlogTagMapper;
import com.shanzhu.blog.cms.service.ICmsBlogTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 博客标签service实现类
 * */
@Service
public class CmsBlogTagServiceImpl implements ICmsBlogTagService {

    @Resource
    CmsBlogTagMapper cmsBlogTagMapper;

    @Override
    public int batchBlogTag(List<CmsBlogTag> blogTagList) {
        return cmsBlogTagMapper.batchBlogTag(blogTagList);
    }

    @Override
    public int deleteBlogTagByBlogId(Long blogId) {
        return cmsBlogTagMapper.deleteBlogTagByBlogId(blogId);
    }

    @Override
    public int deleteBlogTag(Long[] ids) {
        return cmsBlogTagMapper.deleteBlogTag(ids);
    }

    @Override
    public List<CmsBlogTag> selectBlogTagList(Long blogId) {
        return cmsBlogTagMapper.selectBlogTagList(blogId);
    }
}
