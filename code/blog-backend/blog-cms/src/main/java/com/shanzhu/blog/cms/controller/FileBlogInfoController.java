package com.shanzhu.blog.cms.controller;

import com.shanzhu.blog.cms.domain.FileBlogInfo;
import com.shanzhu.blog.cms.domain.SysFileInfo;
import com.shanzhu.blog.cms.service.IFileBlogInfoService;
import com.shanzhu.blog.cms.service.ISysFileInfoService;
import com.shanzhu.blog.common.core.controller.BaseController;
import com.shanzhu.blog.common.core.domain.AjaxResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 博客文件相关Controller
 * */
@RestController
@RequestMapping("/fileBlogInfo")
public class FileBlogInfoController extends BaseController {

    @Resource
    private IFileBlogInfoService fileBlogInfoService;

    @Resource
    private ISysFileInfoService fileInfoService;

    /**
     * 新增blog文件关联
     */
    @PreAuthorize("@ss.hasPermi('cms:blog:add')")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody FileBlogInfo fileBlogInfo) {
        List<FileBlogInfo> fileBlogList = new ArrayList<>();
        Long blogId = fileBlogInfo.getBlogId();
        Long[] fileIds = fileBlogInfo.getFileIds();
        for (Long fileId : fileIds) {
            FileBlogInfo info = new FileBlogInfo();
            info.setFileId(fileId);
            info.setBlogId(blogId);
            fileBlogList.add(info);
        }
        fileBlogInfoService.batchFileBlog(fileBlogList);
        return toAjax(1);
    }

    /**
     * 删除blog文件关联
     */
    @PreAuthorize("@ss.hasPermi('cms:blog:remove')")
    @DeleteMapping("/{blogIds}")
    public AjaxResult remove(@PathVariable Long[] blogIds) {
        for (Long id : blogIds) {
            List<FileBlogInfo> fileBlogInfos = fileBlogInfoService.selectFileBlogList(id);
            fileBlogInfos.forEach((FileBlogInfo fileBlogInfo) -> {
                Long fileId = fileBlogInfo.getFileId();
                fileInfoService.deleteSysFileInfoByFileId(fileId);
            });
        }
        fileBlogInfoService.deleteFileBlog(blogIds);
        return toAjax(1);
    }

    /**
     * 根据blogId获取文件列表
     */
    @PreAuthorize("@ss.hasPermi('cms:blog:query')")
    @GetMapping(value = "/{blogId}")
    public AjaxResult getInfo(@PathVariable Long blogId) {
        List<SysFileInfo> sysFileInfoList = new ArrayList<>();
        List<FileBlogInfo> fileBlogInfos = fileBlogInfoService.selectFileBlogList(blogId);
        fileBlogInfos.forEach((FileBlogInfo fileBlogInfo) -> {
            Long fileId = fileBlogInfo.getFileId();
            SysFileInfo sysFileInfo = fileInfoService.selectSysFileInfoByFileId(fileId);
            if (sysFileInfo != null) {
                sysFileInfoList.add(sysFileInfo);
            }
        });
        return AjaxResult.success(sysFileInfoList);
    }

}
