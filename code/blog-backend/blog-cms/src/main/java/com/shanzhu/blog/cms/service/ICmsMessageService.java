package com.shanzhu.blog.cms.service;

import com.shanzhu.blog.cms.domain.CmsMessage;
import com.shanzhu.blog.cms.domain.CmsMessageLike;

import java.util.List;


/**
 * 地址相关
 * */
public interface ICmsMessageService {

    /**
     * 首页查询留言列表
     */
    List<CmsMessage> selectMessageList(CmsMessage cmsMessage);

    /**
     * 首页新增点赞
     */
    int addCmsMessageLike(CmsMessageLike cmsMessageLike);

    /**
     * 首页删除点赞
     */
    int delCmsMessageLike(CmsMessageLike cmsMessageLike);

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
     * 批量删除留言管理
     *
     * @param ids 需要删除的留言管理主键集合
     * @return 结果
     */
    int deleteCmsMessageByIds(Long[] ids);

    /**
     * 删除留言管理信息
     *
     * @param id 留言管理主键
     * @return 结果
     */
    int deleteCmsMessageById(Long id);
}
