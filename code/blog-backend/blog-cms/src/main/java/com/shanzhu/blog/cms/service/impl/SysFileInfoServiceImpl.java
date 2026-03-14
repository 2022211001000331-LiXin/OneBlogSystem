package com.shanzhu.blog.cms.service.impl;

import com.shanzhu.blog.cms.domain.SysFileInfo;
import com.shanzhu.blog.cms.mapper.SysFileInfoMapper;
import com.shanzhu.blog.cms.service.ISysFileInfoService;
import com.shanzhu.blog.common.config.BlogConfig;
import com.shanzhu.blog.common.constant.Constants;
import com.shanzhu.blog.common.utils.DateUtils;
import com.shanzhu.blog.common.utils.StringUtils;
import com.shanzhu.blog.common.utils.file.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 文件管理Service业务层处理
 * */
@Service
public class SysFileInfoServiceImpl implements ISysFileInfoService {

    @Resource
    private SysFileInfoMapper sysFileInfoMapper;

    @Override
    public SysFileInfo selectSysFileInfoByFileObjectName(String fileObjectName) {
        return sysFileInfoMapper.selectSysFileInfoByFileObjectName(fileObjectName);
    }

    /**
     * 查询文件管理
     *
     * @param fileId 文件管理主键
     * @return 文件管理
     */
    @Override
    public SysFileInfo selectSysFileInfoByFileId(Long fileId) {
        return sysFileInfoMapper.selectSysFileInfoByFileId(fileId);
    }

    /**
     * 查询文件管理列表
     *
     * @param sysFileInfo 文件管理
     * @return 文件管理
     */
    @Override
    public List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo) {
        return sysFileInfoMapper.selectSysFileInfoList(sysFileInfo);
    }

    /**
     * 新增文件管理
     *
     * @param sysFileInfo 文件管理
     * @return 结果
     */
    @Override
    public int insertSysFileInfo(SysFileInfo sysFileInfo) {
        sysFileInfo.setCreateTime(DateUtils.getNowDate());
        return sysFileInfoMapper.insertSysFileInfo(sysFileInfo);
    }

    /**
     * 修改文件管理
     *
     * @param sysFileInfo 文件管理
     * @return 结果
     */
    @Override
    public int updateSysFileInfo(SysFileInfo sysFileInfo) {
        sysFileInfo.setUpdateTime(DateUtils.getNowDate());
        return sysFileInfoMapper.updateSysFileInfo(sysFileInfo);
    }

    /**
     * 批量删除文件管理
     *
     * @param fileIds 需要删除的文件管理主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByFileIds(Long[] fileIds) {
        for (int i = 0; i < fileIds.length; i++) {
            Long fileId = fileIds[i];
            SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoByFileId(fileId);
            String filePath = BlogConfig.getProfile() + StringUtils.substringAfter(sysFileInfo.getFilePath(),
                    Constants.RESOURCE_PREFIX);
            FileUtils.deleteFile(filePath);
        }
        return sysFileInfoMapper.deleteSysFileInfoByFileIds(fileIds);
    }

    /**
     * 删除文件管理信息
     *
     * @param fileId 文件管理主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByFileId(Long fileId) {
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoByFileId(fileId);
        String filePath = BlogConfig.getProfile() + StringUtils.substringAfter(sysFileInfo.getFilePath(),
                Constants.RESOURCE_PREFIX);
        FileUtils.deleteFile(filePath);
        return sysFileInfoMapper.deleteSysFileInfoByFileId(fileId);
    }

    @Override
    public int deleteSysFileInfoByFileObjectName(String fileObjectName) {
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoByFileObjectName(fileObjectName);
        String filePath = BlogConfig.getProfile() + StringUtils.substringAfter(sysFileInfo.getFilePath(),
                Constants.RESOURCE_PREFIX);
        FileUtils.deleteFile(filePath);
        return sysFileInfoMapper.deleteSysFileInfoByFileObjectName(fileObjectName);
    }
}
