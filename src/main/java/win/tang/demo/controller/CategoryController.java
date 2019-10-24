package win.tang.demo.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import win.tang.demo.common.Const;
import win.tang.demo.domain.MmallCategory;
import win.tang.demo.domain.MmallUser;
import win.tang.demo.service.MmallService;
import win.tang.demo.utils.FormatResponseUtil;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * Create by Tang on 2019/10/24
 * 分类管理模块
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    MmallService mmallService;

    @PostMapping("addCategory")
    public Object addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") Integer parentId) {
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return FormatResponseUtil.error("用户未登录");
        }

        if (StringUtils.isBlank(categoryName) || parentId == null) {
            return FormatResponseUtil.error("参数错误");
        }
        //校验是否是管理员
        int role = mmallService.selectRole(user.getId());
        if (role == Const.Role.ROLE_ADMIN) {
            MmallCategory category = new MmallCategory();
            category.setName(categoryName);
            category.setParentId(parentId);
            category.setStatus(1);
            int addCategoryCount = mmallService.addCategory(category);
            if (addCategoryCount > 0) {
                return FormatResponseUtil.formatResponse("添加品类成功");
            }
            return FormatResponseUtil.error("添加品类失败");
        } else {
            return FormatResponseUtil.error("无权限操作");
        }
    }

    @PostMapping("update_category")
    public Object updateCategory(HttpSession session, @RequestBody MmallCategory category) {
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return FormatResponseUtil.error("用户未登录");
        }
        //校验是否是管理员
        int role = mmallService.selectRole(user.getId());
        if (role == Const.Role.ROLE_ADMIN) {
            int categoryCount = mmallService.updateCategory(category);
            if (categoryCount > 0) {
                return FormatResponseUtil.error("更新成功");
            } else {
                return FormatResponseUtil.error("更新失败");
            }
        } else {
            return FormatResponseUtil.error("无权限操作");
        }
    }

    @PostMapping("get_category")
    public Object getChildrenParallelCategory(HttpSession session,@RequestParam(value = "parentId" ,defaultValue = "0") Integer parentId){
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return FormatResponseUtil.error("用户未登录");
        }
        //校验是否是管理员
        int role = mmallService.selectRole(user.getId());
        if (role == Const.Role.ROLE_ADMIN) {
            List<MmallCategory> childrenParallelCategoryList = mmallService.getChildrenParallelCategory(parentId);
            if (CollectionUtils.isEmpty(childrenParallelCategoryList)){
                //查询子节点的category信息,并且不递归,保持平级
                dataLogger.info("module=CategoryController,"+"method = getChildrenParallelCategory,"+"查询category信息为空");
            }
            return FormatResponseUtil.formatResponse(childrenParallelCategoryList);
        }else {
            return FormatResponseUtil.error("无权限操作");
        }
    }
    @PostMapping("get_deep_category")
    public Object getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return FormatResponseUtil.error("用户未登录");
        }
        //校验是否是管理员
        int role = mmallService.selectRole(user.getId());
        if (role == Const.Role.ROLE_ADMIN) {
            //查询当前节点的id和递归子节点的id
            return selectCategoryAndChildrenById(categoryId);
        }else {
            return FormatResponseUtil.error("无权限操作");
        }
    }

    /**
     * 递归查询本节点的id及孩子节点的id
     * @param categoryId
     * @return
     */
    public Object selectCategoryAndChildrenById(Integer categoryId){
        Set<MmallCategory> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);


        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(MmallCategory categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return FormatResponseUtil.formatResponse(categoryIdList);
    }

    //递归算法,算出子节点
    private Set<MmallCategory> findChildCategory(Set<MmallCategory> categorySet ,Integer categoryId){
        MmallCategory category = mmallService.selectByPrimaryKey(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<MmallCategory> categoryList =  mmallService.getChildrenParallelCategory(categoryId);
        for(MmallCategory categoryItem : categoryList){
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }
}
