package com.shanzhu.blog.cms.controller;

import com.shanzhu.blog.cms.domain.SysFileInfo;
import com.shanzhu.blog.cms.service.ISysFileInfoService;
import com.shanzhu.blog.common.annotation.Log;
import com.shanzhu.blog.common.core.controller.BaseController;
import com.shanzhu.blog.common.core.domain.AjaxResult;
import com.shanzhu.blog.common.core.page.TableDataInfo;
import com.shanzhu.blog.common.enums.BusinessType;
import com.shanzhu.blog.common.exception.ServiceException;
import com.shanzhu.blog.common.utils.SecurityUtils;
import com.shanzhu.blog.common.utils.poi.ExcelUtil;
import com.shanzhu.blog.framework.web.service.SysPermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;


/**
 * 文件管理Controller
 * */
@RestController
@RequestMapping("/fileInfo")
public class SysFileInfoController extends BaseController {

    @Resource
    private ISysFileInfoService sysFileInfoService;

    @Resource
    private SysPermissionService permissionService;

    /**
     * 查询文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:fileInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFileInfo sysFileInfo) {
        startPage();
        //判断用户权限
        String createBy = sysFileInfo.getCreateBy();
        if (createBy == null && "".equals(createBy)) {
            throw new ServiceException("获取列表createBy参数为空");
        }
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(getLoginUser().getUser());
        if (SecurityUtils.isAdmin(getUserId()) || roles.contains("admin") || roles.contains("cms")) {
            sysFileInfo.setCreateBy("");
        }
        List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoList(sysFileInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:fileInfo:export')")
    @Log(title = "文件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFileInfo sysFileInfo) {
        List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoList(sysFileInfo);
        ExcelUtil<SysFileInfo> util = new ExcelUtil<SysFileInfo>(SysFileInfo.class);
        util.exportExcel(response, list, "文件管理数据");
    }

    /**
     * 获取文件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:fileInfo:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId) {
        return AjaxResult.success(sysFileInfoService.selectSysFileInfoByFileId(fileId));
    }

    /**
     * 新增文件管理
     */
    @PreAuthorize("@ss.hasPermi('cms:fileInfo:add')")
    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFileInfo sysFileInfo) {
        return toAjax(sysFileInfoService.insertSysFileInfo(sysFileInfo));
    }

    /**
     * 修改文件管理
     */
    @PreAuthorize("@ss.hasPermi('cms:fileInfo:edit')")
    @Log(title = "文件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFileInfo sysFileInfo) {
        return toAjax(sysFileInfoService.updateSysFileInfo(sysFileInfo));
    }

    /**
     * 删除文件管理
     */
    @PreAuthorize("@ss.hasPermi('cms:fileInfo:remove')")
    @Log(title = "文件管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds) {
        return toAjax(sysFileInfoService.deleteSysFileInfoByFileIds(fileIds));
    }
}
