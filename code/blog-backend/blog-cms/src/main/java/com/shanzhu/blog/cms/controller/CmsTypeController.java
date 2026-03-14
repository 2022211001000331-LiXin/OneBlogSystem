package com.shanzhu.blog.cms.controller;

import com.shanzhu.blog.cms.domain.CmsType;
import com.shanzhu.blog.cms.service.ICmsTypeService;
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
 * 分类管理Controller
 * */
@RestController
@RequestMapping("/cms/type")
public class CmsTypeController extends BaseController {

    @Resource
    private ICmsTypeService cmsTypeService;

    @Resource
    private SysPermissionService permissionService;

    /**
     * 查询分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmsType cmsType) {
        startPage();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(getLoginUser().getUser());
        if (!SecurityUtils.isAdmin(getUserId()) && !roles.contains("admin") && !roles.contains("cms")) {
            cmsType.setCreateBy(getUsername());
        }
        List<CmsType> list = cmsTypeService.selectCmsTypeList(cmsType);
        return getDataTable(list);
    }

    /**
     * 导出分类管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:type:export')")
    @Log(title = "分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsType cmsType) {
        List<CmsType> list = cmsTypeService.selectCmsTypeList(cmsType);
        ExcelUtil<CmsType> util = new ExcelUtil<CmsType>(CmsType.class);
        util.exportExcel(response, list, "分类管理数据");
    }

    /**
     * 获取分类管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId) {
        return AjaxResult.success(cmsTypeService.selectCmsTypeByTypeId(typeId));
    }

    /**
     * 新增分类管理
     */
    @PreAuthorize("@ss.hasPermi('cms:type:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsType cmsType) {
        cmsType.setCreateBy(getUsername());
        return toAjax(cmsTypeService.insertCmsType(cmsType));
    }

    /**
     * 修改分类管理
     */
    @PreAuthorize("@ss.hasPermi('cms:type:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsType cmsType) {
        cmsType.setUpdateBy(getUsername());
        return toAjax(cmsTypeService.updateCmsType(cmsType));
    }

    /**
     * 删除分类管理
     */
    @PreAuthorize("@ss.hasPermi('cms:type:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds) {
        return toAjax(cmsTypeService.deleteCmsTypeByTypeIds(typeIds));
    }

    /**
     * 取消按钮-删除分类图片
     */
    @PreAuthorize("@ss.hasPermi('cms:type:edit')")
    @PostMapping("/cancel")
    public AjaxResult cancel(@RequestBody CmsType cmsType) {
        return toAjax(cmsTypeService.cancel(cmsType));
    }

}
