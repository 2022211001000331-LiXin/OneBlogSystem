package com.shanzhu.blog.cms.service.impl;

import com.shanzhu.blog.cms.domain.CmsBlogType;
import com.shanzhu.blog.cms.mapper.CmsBlogTypeMapper;
import com.shanzhu.blog.cms.service.ICmsBlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 博客类型service实现类
 * */
@Service
public class CmsBlogTypeServiceImpl implements ICmsBlogTypeService {

    @Resource
    CmsBlogTypeMapper cmsBlogTypeMapper;

    @Override
    public int batchBlogType(List<CmsBlogType> blogTypeList) {
        return cmsBlogTypeMapper.batchBlogType(blogTypeList);
    }

    @Override
    public int deleteBlogTypeByBlogId(Long blogId) {
        return cmsBlogTypeMapper.deleteBlogTypeByBlogId(blogId);
    }

    @Override
    public int deleteBlogType(Long[] ids) {
        return cmsBlogTypeMapper.deleteBlogType(ids);
    }

    @Override
    public List<CmsBlogType> selectBlogTypeList(Long blogId) {
        return cmsBlogTypeMapper.selectBlogTypeList(blogId);
    }
}
