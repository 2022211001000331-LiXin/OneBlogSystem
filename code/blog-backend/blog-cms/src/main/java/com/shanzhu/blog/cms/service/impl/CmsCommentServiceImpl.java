package com.shanzhu.blog.cms.service.impl;

import com.shanzhu.blog.cms.domain.CmsBlog;
import com.shanzhu.blog.cms.domain.CmsComment;
import com.shanzhu.blog.cms.domain.CmsCommentLike;
import com.shanzhu.blog.cms.mapper.CmsBlogMapper;
import com.shanzhu.blog.cms.mapper.CmsCommentLikeMapper;
import com.shanzhu.blog.cms.mapper.CmsCommentMapper;
import com.shanzhu.blog.cms.service.ICmsCommentService;
import com.shanzhu.blog.common.core.domain.entity.SysUser;
import com.shanzhu.blog.common.utils.DateUtils;
import com.shanzhu.blog.system.mapper.SysUserMapper;
import com.shanzhu.blog.cms.domain.SentimentResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 评论管理Service业务层处理
 * */
@Service
public class CmsCommentServiceImpl implements ICmsCommentService {

    @Resource
    private CmsCommentMapper cmsCommentMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private CmsCommentLikeMapper cmsCommentLikeMapper;

    @Resource
    private CmsBlogMapper cmsBlogMapper;

    @Resource
    private SentimentServiceImpl sentimentService;

    private static final Logger log = LoggerFactory.getLogger(CmsCommentServiceImpl.class);

    /**
     * 首页查询评论列表
     */
    @Override
    public List<CmsComment> selectCommentList(CmsComment cmsComment) {
        //判断是否登录
        Long logUserUserId = null;
        String createBy = cmsComment.getCreateBy();
        if (createBy != null && !"".equals(createBy)) {
            SysUser logUser = sysUserMapper.selectUserByUserName(createBy);
            if (logUser != null) {
                logUserUserId = logUser.getUserId();
            }
        }
        cmsComment.setCreateBy(null);
        cmsComment.setType("0");
        cmsComment.setDelFlag("0");
        List<CmsComment> cmsCommentList = cmsCommentMapper.selectCmsCommentList(cmsComment);
        for (CmsComment comment : cmsCommentList) {
            //添加头像
            Long userId = comment.getUserId();
            if (userId != null) {
                SysUser user = sysUserMapper.selectUserById(userId);
                comment.setAvatar(user.getAvatar());
            }
            //添加是否被点赞
            if (logUserUserId != null) {
                CmsCommentLike commentLike = new CmsCommentLike();
                commentLike.setCommentId(comment.getId());
                commentLike.setUserId(logUserUserId);
                List<CmsCommentLike> likeList = cmsCommentLikeMapper.selectCmsCommentLikeList(commentLike);
                if (likeList.size() > 0) {
                    comment.setIsLike(true);
                } else {
                    comment.setIsLike(false);
                }
            }
            //添加子评论(回复)
            CmsComment childComment = new CmsComment();
            childComment.setType("1");
            childComment.setMainId(comment.getId());
            List<CmsComment> childCommentList = cmsCommentMapper.selectChildCommentList(childComment);
            if (childCommentList.size() > 0) {
                for (CmsComment childListComment : childCommentList) {
                    //添加头像
                    Long childUserId = childListComment.getUserId();
                    if (childUserId != null) {
                        SysUser user = sysUserMapper.selectUserById(childUserId);
                        if (user != null) {
                            childListComment.setAvatar(user.getAvatar());
                        }
                    }
                    //添加是否被点赞
                    if (logUserUserId != null) {
                        CmsCommentLike commentLike = new CmsCommentLike();
                        commentLike.setCommentId(comment.getId());
                        commentLike.setUserId(logUserUserId);
                        List<CmsCommentLike> likeList = cmsCommentLikeMapper.selectCmsCommentLikeList(commentLike);
                        if (likeList.size() > 0) {
                            comment.setIsLike(true);
                        } else {
                            comment.setIsLike(false);
                        }
                    }
                    //添加父评论信息
                    CmsComment byId = cmsCommentMapper.selectCmsCommentById(childListComment.getParentId());
                    if (byId != null) {
                        childListComment.setPCreateBy(byId.getCreateBy());
                    }
                }
                comment.setChildren(childCommentList);
            }
        }
        return cmsCommentList;
    }

    @Override
    public int addCmsCommentLike(CmsCommentLike cmsCommentLike) {
        int result = -1;
        String createBy = cmsCommentLike.getCreateBy();
        if (!"".equals(createBy) && createBy != null) {
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user != null) {
                cmsCommentLike.setUserId(user.getUserId());
                cmsCommentLikeMapper.addCmsCommentLike(cmsCommentLike);
            }
        }
        //修改点赞数量
        CmsComment cmsComment = new CmsComment();
        cmsComment.setId(cmsCommentLike.getCommentId());
        cmsComment.setLikeNum(cmsCommentLike.getLikeNum());
        result = cmsCommentMapper.updateCmsComment(cmsComment);
        return result;
    }

    @Override
    public int delCmsCommentLike(CmsCommentLike cmsCommentLike) {
        int result = -1;
        String createBy = cmsCommentLike.getCreateBy();
        if (!"".equals(createBy) && createBy != null) {
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user != null) {
                cmsCommentLike.setUserId(user.getUserId());
                cmsCommentLikeMapper.deleteCmsCommentLike(cmsCommentLike);
            }
        }
        //修改点赞数量
        CmsComment cmsComment = new CmsComment();
        cmsComment.setId(cmsCommentLike.getCommentId());
        cmsComment.setLikeNum(cmsCommentLike.getLikeNum());
        result = cmsCommentMapper.updateCmsComment(cmsComment);
        return result;
    }

    /**
     * 查询评论管理
     *
     * @param id 评论管理主键
     * @return 评论管理
     */
    @Override
    public CmsComment selectCmsCommentById(Long id) {
        return cmsCommentMapper.selectCmsCommentById(id);
    }

    /**
     * 查询评论管理列表
     *
     * @param cmsComment 评论管理
     * @return 评论管理
     */
    @Override
    public List<CmsComment> selectCmsCommentList(CmsComment cmsComment) {
        List<CmsComment> cmsCommentList = new ArrayList<>();
        //判断用户权限
        String createBy = cmsComment.getCreateBy();
        if (createBy != null && !"".equals(createBy)) {
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user != null) {
                List<CmsComment> CommentList = cmsCommentMapper.selectCmsCommentList(cmsComment);
                for (CmsComment comment : CommentList) {
                    //查询子评论(回复)
                    CmsComment childComment = new CmsComment();
                    childComment.setType("1");
                    childComment.setParentId(comment.getId());
                    List<CmsComment> childCommentList = cmsCommentMapper.selectCmsCommentList(childComment);
                    if (childCommentList.size() > 0) {
                        cmsCommentList.addAll(childCommentList);
                    }
                }
                cmsCommentList.addAll(CommentList);
            }
        } else {
            cmsCommentList = cmsCommentMapper.selectCmsCommentList(cmsComment);
        }
        for (CmsComment comment : cmsCommentList) {
            //添加头像
            Long userId = comment.getUserId();
            if (userId != null) {
                SysUser user = sysUserMapper.selectUserById(userId);
                if (user != null) {
                    comment.setAvatar(user.getAvatar());
                }
            }
            //添加父评论信息
            Long parentId = comment.getParentId();
            if (parentId != null) {
                CmsComment parentComment = cmsCommentMapper.selectCmsCommentById(parentId);
                if (parentComment != null) {
                    comment.setPCreateBy(parentComment.getCreateBy());
                }
            }
            //添加博客信息
            Long blogId = comment.getBlogId();
            if (blogId != null) {
                CmsBlog blog = cmsBlogMapper.selectCmsBlogById(blogId);
                if (blog != null) {
                    comment.setBlogTitle(blog.getTitle());
                }
            }
        }
        //排序
//        String[] sortNameArr1 = {"createTime"};
//        //true升序，false降序
//        boolean[] isAscArr1 = {false};
//        ListSortUtils.sort(cmsMessageList, sortNameArr1, isAscArr1);
//        cmsMessageList.sort((a,b)->a.getCreateBy().compareTo(b.getCreateBy()));
//        Collections.sort(cmsMessageList, new Comparator<CmsMessage>() {
//            @Override
//            public int compare(CmsMessage o1, CmsMessage o2) {
//                //升序
//                //return o1.getCreateBy().compareTo(o2.getCreateBy());
//                //降序
//                return o2.getCreateBy().compareTo(o1.getCreateBy());
//            }
//        });
        return cmsCommentList;
    }

    /**
     * 新增评论管理
     *
     * @param cmsComment 评论管理
     * @return 结果
     */
    @Override
    public int insertCmsComment(CmsComment cmsComment) {
        String createBy = cmsComment.getCreateBy();
        if (createBy != null && !"".equals(createBy)) {
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user != null) {
                cmsComment.setUserId(user.getUserId());
            }
        }
        cmsComment.setCreateTime(DateUtils.getNowDate());

        // 执行插入获取自增 ID
        int rows = cmsCommentMapper.insertCmsComment(cmsComment);
        
        // 如果插入成功，且评论有内容，则触发 AI 分析
        if (rows > 0 && cmsComment.getContent() != null) {
            // 开启一个新线程去跑分析，不要让用户在网页上“转圈圈”等 AI 结果
            new Thread(() -> {
                SentimentResultDto result = sentimentService.analyzeContent(cmsComment.getContent());
                if (result != null) {
                    // 回填 AI 数据到数据库
                    CmsComment updateVo = new CmsComment();
                    updateVo.setId(cmsComment.getId());
                    updateVo.setSentiment(result.getSentiment());
                    updateVo.setConfidence(result.getConfidence());
                    // 把关键词列表转为字符串存入
                    updateVo.setKeywords(String.join(",", result.getKeywords()));
                    
                    cmsCommentMapper.updateCmsComment(updateVo);
                    log.info("评论 ID: {} 情感分析完成: {}", cmsComment.getId(), result.getSentiment());
                }
            }).start();
        }
        
        return rows;
    }

    /**
     * 修改评论管理
     *
     * @param cmsComment 评论管理
     * @return 结果
     */
    @Override
    public int updateCmsComment(CmsComment cmsComment) {
        cmsComment.setUpdateTime(DateUtils.getNowDate());
        return cmsCommentMapper.updateCmsComment(cmsComment);
    }

    /**
     * 批量删除评论管理
     *
     * @param ids 需要删除的评论管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsCommentByIds(Long[] ids) {
        return cmsCommentMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除评论管理信息
     *
     * @param id 评论管理主键
     * @return 结果
     */
    @Override
    public int deleteCmsCommentById(Long id) {
        return cmsCommentMapper.updateDelFlagById(id);
    }

    // 在 ICmsCommentService 接口中定义方法，这里直接写实现
@Override
public Map<String, Object> getSentimentDashboardData() {
    Map<String, Object> result = new HashMap<>();
    
    // 1. 获取饼图数据
    result.put("pieData", cmsCommentMapper.selectSentimentPieData());
    
    // 2. 获取趋势图数据
    result.put("trendData", cmsCommentMapper.selectSentimentTrendData());
    
    // 3. 处理词云数据（逻辑：取出所有负面词，统计频率）
    List<String> allKeywords = cmsCommentMapper.selectNegativeKeywords();
    Map<String, Integer> wordFreq = new HashMap<>();
    for (String kws : allKeywords) {
        if (kws != null) {
            for (String word : kws.split(",")) {
                wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
            }
        }
    }
    // 转为前端词云需要的格式 [{name: '很差', value: 10}, ...]
    List<Map<String, Object>> cloudData = new ArrayList<>();
    wordFreq.forEach((k, v) -> {
        Map<String, Object> item = new HashMap<>();
        item.put("name", k);
        item.put("value", v);
        cloudData.add(item);
    });
    result.put("cloudData", cloudData);
    
    return result;
}
}
