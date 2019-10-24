package win.tang.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import win.tang.demo.common.Const;
import win.tang.demo.common.TokenCache;
import win.tang.demo.domain.MmallUser;
import win.tang.demo.service.MmallService;
import win.tang.demo.utils.FormatResponseUtil;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Create by Tang on 2019/10/23
 */
@RestController
@RequestMapping("/user")
public class UserContoller {

    @Autowired
    MmallService mmallService;

    @PostMapping("login")
    @ResponseBody
    public Object login(String username, String password, HttpSession session) {
        int resultCount = mmallService.checkUserName(username);
        if (resultCount <= 0) {
            return FormatResponseUtil.error("用户名不存在");
        }
        MmallUser login = mmallService.login(username, password);
        if (login == null) {
            return FormatResponseUtil.error("密码错误");
        }
        login.setPassword("");
        session.setAttribute(Const.CURRENT_USER, login);
        return FormatResponseUtil.formatResponse("登录成功", login);
    }

    @GetMapping("logout")
    @ResponseBody
    public Object logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return FormatResponseUtil.formatResponse("退出成功");
    }

    @PostMapping("register")
    @ResponseBody
    public Object register(@RequestBody MmallUser user) {
        int resultCount = mmallService.checkUserName(user.getUsername());
        if (resultCount > 0) {
            return FormatResponseUtil.error("用户名已存在");
        }
        //注册校验email phone
        int emailCount = mmallService.checkEmail(user.getEmail());
        if (emailCount > 0) {
            return FormatResponseUtil.error("邮箱已存在");
        }
        int phoneCount = mmallService.checkPhone(user.getPhone());
        if (phoneCount > 0) {
            return FormatResponseUtil.error("手机号已存在");
        }
        int register = mmallService.register(user);
        if (register <= 0) {
            return FormatResponseUtil.error("注册失败");
        }
        return FormatResponseUtil.formatResponse("注册成功");
    }

    @PostMapping("get_user_info")
    @ResponseBody
    public Object getUserInfo(HttpSession session) {
        MmallUser login = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (login == null) {
            return FormatResponseUtil.error("获取用户信息失败");
        }
        return FormatResponseUtil.formatResponse(login);
    }

    /**
     * 忘记问题
     */
    @PostMapping("forget_get_question")
    @ResponseBody
    public Object forgetGetQuestion(String username) {
        int resultCount = mmallService.checkUserName(username);
        if (resultCount <= 0) {
            return FormatResponseUtil.error("用户名不存在");
        }
        String question = mmallService.forgetGetQuestion(username);
        if (StringUtils.isNotBlank(question)) {
            return FormatResponseUtil.formatResponse(question);
        }
        return FormatResponseUtil.error("找回密码的问题是空的");
    }

    /**
     * 校验答案
     */
    @PostMapping("forget_check_answer")
    @ResponseBody
    public Object checkAnswer(String username, String question, String answer) {
        int resultCount = mmallService.checkUserName(username);
        if (resultCount <= 0) {
            return FormatResponseUtil.error("用户名不存在");
        }
        int checkCount = mmallService.checkAnswer(username, question, answer);
        if (checkCount <= 0) {
            return FormatResponseUtil.error("问题的答案错误");
        }

        //说明问题及问题答案是这个用户的,并且是正确的
        String forgetToken = UUID.randomUUID().toString();
        TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
        return FormatResponseUtil.formatResponse(forgetToken);
    }

    /**
     * 忘记密码 重置密码 未登录
     */
    @PostMapping("reset_password")
    @ResponseBody
    public Object resetPassword(String username, String new_password, String token) {
        if (StringUtils.isBlank(token)) {
            return FormatResponseUtil.error("传参错误,token不能为空");
        }

        int resultCount = mmallService.checkUserName(username);
        if (resultCount <= 0) {
            return FormatResponseUtil.error("用户名不存在");
        }

        String cach_token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(cach_token)) {
            return FormatResponseUtil.error("token无效或过期");
        }

        if (StringUtils.equals(token, cach_token)) {
            int resetToken = mmallService.resetPassword(username, new_password, token);
            if (resetToken > 0) {
                return FormatResponseUtil.formatResponse("修改成功");
            } else {
                return FormatResponseUtil.error("修改失败");
            }
        } else {
            return FormatResponseUtil.error("token错误");
        }
    }

    /**
     * 忘记密码 重置密码 已登录
     */
    @PostMapping("login_reset_password")
    @ResponseBody
    public Object loginResetPassword(HttpSession session, String old_password, String new_password) {
        MmallUser user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return FormatResponseUtil.error("用户未登录");
        }
        //防止横向越权 校验旧密码
        int checkPasswordCount = mmallService.checkPassword(old_password, user.getId());
        if (checkPasswordCount <= 0) {
            return FormatResponseUtil.error("旧密码错误");
        }
        int loginResetPasswordCount = mmallService.loginResetPassword(old_password, new_password);
        if (loginResetPasswordCount <= 0) {
            return FormatResponseUtil.error("密码更新失败");
        }
        return FormatResponseUtil.formatResponse("密码更新成功");
    }

    /**
     * 更新用户信息
     */
    @PostMapping("update_user_info")
    @ResponseBody
    public Object update_user_info(HttpSession session, @RequestBody MmallUser user) {
        MmallUser session_user = (MmallUser) session.getAttribute(Const.CURRENT_USER);
        if (session_user == null) {
            return FormatResponseUtil.error("用户未登录");
        }

        //id 和 username 不可更改
        Integer id = session_user.getId();
        String email = session_user.getEmail();
        user.setId(id);
        user.setUsername(session_user.getUsername());

        //校验唯一性 email
        int emailCount = mmallService.checkOnlyEmail(email, id);
        if (emailCount > 0) {
            return FormatResponseUtil.error("email被占用,请更换");
        }
        int updateUserCount = mmallService.updateUserInfo(user);
        if (updateUserCount > 0) {
            session.setAttribute(Const.CURRENT_USER, user);
            return FormatResponseUtil.formatResponse("更新个人信息成功", user);
        }
        return FormatResponseUtil.error("更新个人信息失败");
    }


}
