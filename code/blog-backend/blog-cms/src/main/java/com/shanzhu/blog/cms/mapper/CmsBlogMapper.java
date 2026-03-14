package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.CmsBlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 文章管理Mapper接口
 * */
public interface CmsBlogMapper {

    /**
     * 查询文章管理
     *
     * @param id 文章管理主键
     * @return 文章管理
     */
    CmsBlog selectCmsBlogById(Long id);

    /**
     * 查询文章管理列表
     *
     * @param cmsBlog 文章管理
     * @return 文章管理集合
     */
    List<CmsBlog> selectCmsBlogList(CmsBlog cmsBlog);

    List<CmsBlog> selectCmsBlogListBetweenCreateTime(
            @Param("title") String title, @Param("type") String type,
            @Param("top") String top, @Param("status") String status,
            @Param("createTimeBegin") String createTimeBegin, @Param(
            "createTimeEnd") String createTimeEnd, @Param(
            "createBy") String createBy
    );

    /**
     * 查询推荐文章列表
     */
    List<CmsBlog> selectCmsBlogListRecommend(CmsBlog cmsBlog);

    /**
     * 按分类查询文章管理列表
     */
    List<CmsBlog> selectCmsBlogListByTypeId(Long id);

    /**
     * 按标签查询文章管理列表
     */
    List<CmsBlog> selectCmsBlogListByTagId(Long id);

    /**
     * 新增文章管理
     *
     * @param cmsBlog 文章管理
     * @return 结果
     */
    int insertCmsBlog(CmsBlog cmsBlog);

    /**
     * 修改文章管理
     *
     * @param cmsBlog 文章管理
     * @return 结果
     */
    int updateCmsBlog(CmsBlog cmsBlog);

    /**
     * 删除文章管理
     *
     * @param id 文章管理主键
     * @return 结果
     */
    int deleteCmsBlogById(Long id);

    /**
     * 批量删除文章管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCmsBlogByIds(Long[] ids);
}
