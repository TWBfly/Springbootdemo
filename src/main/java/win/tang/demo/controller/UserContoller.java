package win.tang.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.tang.demo.domain.MmallUser;
import win.tang.demo.service.MmallService;
import win.tang.demo.utils.FormatResponseUtil;

/**
 * Create by Tang on 2019/10/23
 */
@RestController
@RequestMapping("/user")
public class UserContoller {

    @Autowired
    MmallService mmallService;

    @PostMapping("login")
    public Object login(String username, String password) {
        int resultCount = mmallService.checkUserName(username);
        if (resultCount <= 0) {
            return FormatResponseUtil.error("用户名不存在");
        }
        MmallUser login = mmallService.login(username, password);
        if (login == null) {
            return FormatResponseUtil.error("密码错误");
        }
        login.setPassword("");
        return FormatResponseUtil.formatResponse("登录成功", login);
    }


}
