package win.tang.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import win.tang.demo.common.Const;
import win.tang.demo.domain.MmallUser;
import win.tang.demo.service.MmallService;
import win.tang.demo.utils.FormatResponseUtil;

import javax.servlet.http.HttpSession;

/**
 * Create by Tang on 2019/10/23
 */
@RestController
@RequestMapping("/user")
public class UserContoller {

    @Autowired
    MmallService mmallService;

    @PostMapping("login")
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


}
