package com.shanzhu.blog.cms.service.impl;

import com.shanzhu.blog.cms.domain.FileNoticeInfo;
import com.shanzhu.blog.cms.mapper.FileNoticeInfoMapper;
import com.shanzhu.blog.cms.service.IFileNoticeInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 文件公告通知信息service实现类
 * */
@Service
public class FileNoticeInfoServiceImpl implements IFileNoticeInfoService {

    @Resource
    private FileNoticeInfoMapper fileNoticeInfoMapper;

    @Override
    public int batchFileNotice(List<FileNoticeInfo> fileNoticeList) {
        return fileNoticeInfoMapper.batchFileNotice(fileNoticeList);
    }

    @Override
    public int deleteFileNoticeByNoticeId(Long noticeId) {
        return fileNoticeInfoMapper.deleteFileNoticeByNoticeId(noticeId);
    }

    @Override
    public int deleteFileNotice(Long[] ids) {
        return fileNoticeInfoMapper.deleteFileNotice(ids);
    }

    @Override
    public List<FileNoticeInfo> selectFileNoticeList(Long noticeId) {
        return fileNoticeInfoMapper.selectFileNoticeList(noticeId);
    }
}
