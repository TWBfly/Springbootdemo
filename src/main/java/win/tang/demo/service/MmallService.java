package win.tang.demo.service;

import win.tang.demo.domain.MmallUser;

/**
 * Create by Tang on 2019/10/23
 */
public interface MmallService {

    /**
     * check用户名
     */
    int checkUserName(String userName);

    /**
     * check email
     */
    int checkEmail(String email);

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


}
