package win.tang.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.tang.demo.domain.MmallUser;
import win.tang.demo.mapper.MmallMapper;
import win.tang.demo.service.MmallService;

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

}
