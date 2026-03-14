package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.FileNoticeInfo;

import java.util.List;


/**
 * 通知公告和文件关联 数据层
 * */
public interface FileNoticeInfoMapper {

    /**
     * 批量新增
     */
    int batchFileNotice(List<FileNoticeInfo> userRoleList);

    /**
     * 通过通知公告ID删除通知公告和文件关联
     */
    int deleteFileNoticeByNoticeId(Long noticeId);

    /**
     * 批量删除通知公告和文件关联
     */
    int deleteFileNotice(Long[] ids);

    /**
     * 查询文件列表
     */
    List<FileNoticeInfo> selectFileNoticeList(Long noticeId);
}
