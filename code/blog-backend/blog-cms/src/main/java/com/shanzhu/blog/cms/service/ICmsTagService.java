package com.shanzhu.blog.cms.service;

import com.shanzhu.blog.cms.domain.CmsTag;

import java.util.List;


/**
 * 标签管理Service接口
 * */
public interface ICmsTagService {

    /**
     * 查询标签管理
     *
     * @param tagId 标签管理主键
     * @return 标签管理
     */
    CmsTag selectCmsTagByTagId(Long tagId);

    /**
     * 查询标签管理列表
     *
     * @param cmsTag 标签管理
     * @return 标签管理集合
     */
    List<CmsTag> selectCmsTagList(CmsTag cmsTag);

    /**
     * 新增标签管理
     *
     * @param cmsTag 标签管理
     * @return 结果
     */
    int insertCmsTag(CmsTag cmsTag);

    /**
     * 修改标签管理
     *
     * @param cmsTag 标签管理
     * @return 结果
     */
    int updateCmsTag(CmsTag cmsTag);

    /**
     * 批量删除标签管理
     *
     * @param tagIds 需要删除的标签管理主键集合
     * @return 结果
     */
    int deleteCmsTagByTagIds(Long[] tagIds);

    /**
     * 删除标签管理信息
     *
     * @param tagId 标签管理主键
     * @return 结果
     */
    int deleteCmsTagByTagId(Long tagId);
}
