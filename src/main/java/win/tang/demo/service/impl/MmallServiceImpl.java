package win.tang.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.tang.demo.domain.MmallCategory;
import win.tang.demo.domain.MmallUser;
import win.tang.demo.mapper.MmallMapper;
import win.tang.demo.service.MmallService;

import java.util.List;

/**
 * Create by Tang on 2019/10/23
 */
@Service
public class MmallServiceImpl implements MmallService {

    @Autowired
    MmallMapper mmallMapper;

    @Override
    public int checkUserName(String userName) {
        return mmallMapper.checkUserName(userName);
    }

    @Override
    public int checkEmail(String email) {
        return mmallMapper.checkEmail(email);
    }

    @Override
    public int checkOnlyEmail(String email, int id) {
        return mmallMapper.checkOnlyEmail(email,id);
    }

    @Override
    public int checkPhone(String phone) {
        return mmallMapper.checkPhone(phone);
    }

    @Override
    public MmallUser login(String username, String password) {
        return mmallMapper.login(username,password);
    }

    @Override
    public int register(MmallUser mmallUser) {
        return mmallMapper.register(mmallUser);
    }

    @Override
    public String forgetGetQuestion(String username) {
        return mmallMapper.forgetGetQuestion(username);
    }

    @Override
    public int checkAnswer(String username, String question, String answer) {
        return mmallMapper.checkAnswer(username,question,answer);
    }

    @Override
    public int resetPassword(String username, String newPassword, String token) {
        return mmallMapper.resetPassword(username,newPassword,token);
    }

    @Override
    public int checkPassword(String password, int id) {
        return mmallMapper.checkPassword(password,id);
    }

    @Override
    public int loginResetPassword(String oldPassword, String newPassword) {
        return mmallMapper.loginResetPassword(oldPassword,newPassword);
    }

    @Override
    public int updateUserInfo(MmallUser user) {
        return mmallMapper.updateUserInfo(user);
    }

    @Override
    public int selectRole(int id) {
        return mmallMapper.selectRole(id);
    }

    @Override
    public int addCategory(MmallCategory category) {
        return mmallMapper.addCategory(category);
    }

    @Override
    public int updateCategory(MmallCategory category) {
        return mmallMapper.updateCategory(category);
    }

    @Override
    public List<MmallCategory> getChildrenParallelCategory(Integer parentId) {
        return mmallMapper.getChildrenParallelCategory(parentId);
    }

    @Override
    public MmallCategory selectByPrimaryKey(int id) {
        return mmallMapper.selectByPrimaryKey(id);
    }


}
