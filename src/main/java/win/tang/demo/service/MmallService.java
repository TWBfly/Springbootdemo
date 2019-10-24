package win.tang.demo.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import win.tang.demo.domain.MmallCategory;
import win.tang.demo.domain.MmallUser;
import win.tang.demo.utils.FormatResponseUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Create by Tang on 2019/10/23
 */
public interface MmallService {

    /**********************  user  **************************************/

    /**
     * check用户名
     */
    int checkUserName(String userName);

    /**
     * check email
     */
    int checkEmail(String email);

    /**
     * check email 整个数据库中唯一
     */
    int checkOnlyEmail(String email, int id);

    /**
     * check phone
     */
    int checkPhone(String phone);


    /**
     * login
     */
    MmallUser login(String username, String password);

    /**
     * register
     */
    int register(MmallUser mmallUser);

    /**
     * 忘记问题
     */
    String forgetGetQuestion(String username);

    /**
     * 校验答案
     */
    int checkAnswer(String username, String question, String answer);

    /**
     * 忘记密码 重置密码 未登录
     */
    int resetPassword(String username, String newPassword, String token);

    /**
     * 校验旧密码
     */
    int checkPassword(String password, int id);

    /**
     * 忘记密码 重置密码 已登录
     */
    int loginResetPassword(String oldPassword, String newPassword);

    /**
     * update 用户信息 username不能更新
     */
    int updateUserInfo(MmallUser user);


    /**********************  category  **************************************/
    /**
     * select role
     */
    int selectRole(int id);

    /**
     * insert
     */
    int addCategory(MmallCategory category);

    /**
     * update
     */
    int updateCategory(MmallCategory category);

    /**
     * 查询子节点的category信息,并且不递归,保持平级
     */
    List<MmallCategory> getChildrenParallelCategory(Integer parentId);

    /**
     * id查询
     */
    MmallCategory selectByPrimaryKey(int id);

}
