package com.shanzhu.blog.cms.mapper;

import com.shanzhu.blog.cms.domain.SysFileInfo;

import java.util.List;


/**
 * 文件管理Mapper接口
 * */
public interface SysFileInfoMapper {

    /**
     * 查询文件管理
     *
     * @param fileObjectName
     * @return 文件管理
     */
    SysFileInfo selectSysFileInfoByFileObjectName(String fileObjectName);

    /**
     * 查询文件管理
     *
     * @param fileId 文件管理主键
     * @return 文件管理
     */
    SysFileInfo selectSysFileInfoByFileId(Long fileId);

    /**
     * 查询文件管理列表
     *
     * @param sysFileInfo 文件管理
     * @return 文件管理集合
     */
    List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo);

    /**
     * 新增文件管理
     *
     * @param sysFileInfo 文件管理
     * @return 结果
     */
    int insertSysFileInfo(SysFileInfo sysFileInfo);

    /**
     * 修改文件管理
     *
     * @param sysFileInfo 文件管理
     * @return 结果
     */
    int updateSysFileInfo(SysFileInfo sysFileInfo);

    /**
     * 删除文件管理
     *
     * @param fileId 文件管理主键
     * @return 结果
     */
    int deleteSysFileInfoByFileId(Long fileId);

    /**
     * 批量删除文件管理
     *
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysFileInfoByFileIds(Long[] fileIds);

    /**
     * 删除文件管理信息
     *
     * @param fileObjectName
     * @return 结果
     */
    int deleteSysFileInfoByFileObjectName(String fileObjectName);
}
