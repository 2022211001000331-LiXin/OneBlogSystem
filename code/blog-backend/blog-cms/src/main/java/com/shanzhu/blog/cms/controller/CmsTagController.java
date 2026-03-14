package com.shanzhu.blog.cms.controller;

import com.shanzhu.blog.cms.domain.CmsTag;
import com.shanzhu.blog.cms.service.ICmsTagService;
import com.shanzhu.blog.common.annotation.Log;
import com.shanzhu.blog.common.core.controller.BaseController;
import com.shanzhu.blog.common.core.domain.AjaxResult;
import com.shanzhu.blog.common.core.page.TableDataInfo;
import com.shanzhu.blog.common.enums.BusinessType;
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
 * 标签控制层Controller
 * */
@RestController
@RequestMapping("/cms/tag")
public class CmsTagController extends BaseController {

    @Resource
    private ICmsTagService cmsTagService;

    @Resource
    private SysPermissionService permissionService;

    /**
     * 查询标签管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmsTag cmsTag) {
        startPage();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(getLoginUser().getUser());
        if (!SecurityUtils.isAdmin(getUserId()) && !roles.contains("admin") && !roles.contains("cms")) {
            cmsTag.setCreateBy(getUsername());
        }
        List<CmsTag> list = cmsTagService.selectCmsTagList(cmsTag);
        return getDataTable(list);
    }

    /**
     * 导出标签管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:tag:export')")
    @Log(title = "标签管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsTag cmsTag) {
        List<CmsTag> list = cmsTagService.selectCmsTagList(cmsTag);
        ExcelUtil<CmsTag> util = new ExcelUtil<CmsTag>(CmsTag.class);
        util.exportExcel(response, list, "标签管理数据");
    }

    /**
     * 获取标签管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:tag:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId) {
        return AjaxResult.success(cmsTagService.selectCmsTagByTagId(tagId));
    }

    /**
     * 新增标签管理
     */
    @PreAuthorize("@ss.hasPermi('cms:tag:add')")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsTag cmsTag) {
        cmsTag.setCreateBy(getUsername());
        return toAjax(cmsTagService.insertCmsTag(cmsTag));
    }

    /**
     * 修改标签管理
     */
    @PreAuthorize("@ss.hasPermi('cms:tag:edit')")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsTag cmsTag) {
        cmsTag.setUpdateBy(getUsername());
        return toAjax(cmsTagService.updateCmsTag(cmsTag));
    }

    /**
     * 删除标签管理
     */
    @PreAuthorize("@ss.hasPermi('cms:tag:remove')")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds) {
        return toAjax(cmsTagService.deleteCmsTagByTagIds(tagIds));
    }
}
