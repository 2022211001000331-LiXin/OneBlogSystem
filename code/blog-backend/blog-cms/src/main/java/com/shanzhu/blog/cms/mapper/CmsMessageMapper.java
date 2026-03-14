package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.CmsMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 留言管理Mapper接口
 * */
public interface CmsMessageMapper {
    /**
     * 查询留言管理
     *
     * @param id 留言管理主键
     * @return 留言管理
     */
    CmsMessage selectCmsMessageById(Long id);

    /**
     * 查询留言管理列表
     *
     * @param cmsMessage 留言管理
     * @return 留言管理集合
     */
    List<CmsMessage> selectCmsMessageList(CmsMessage cmsMessage);

    List<CmsMessage> selectCmsMessageListBetweenCreateTime(
            @Param("type") String type,
            @Param("delFlag") String delFlag, @Param(
            "createTimeBegin") String createTimeBegin,
            @Param("createTimeEnd") String createTimeEnd
    );

    /**
     * 查询子留言列表
     */
    List<CmsMessage> selectChildMessageList(CmsMessage cmsMessage);

    /**
     * 新增留言管理
     *
     * @param cmsMessage 留言管理
     * @return 结果
     */
    int insertCmsMessage(CmsMessage cmsMessage);

    /**
     * 修改留言管理
     *
     * @param cmsMessage 留言管理
     * @return 结果
     */
    int updateCmsMessage(CmsMessage cmsMessage);

    /**
     * 删除留言管理
     *
     * @param id 留言管理主键
     * @return 结果
     */
    int deleteCmsMessageById(Long id);

    /**
     * 批量删除留言管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCmsMessageByIds(Long[] ids);

    /**
     * 删除留言管理
     *
     * @param id 留言管理主键
     * @return 结果
     */
    int updateDelFlagById(Long id);

    /**
     * 批量删除留言管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int updateDelFlagByIds(Long[] ids);
}
