package win.tang.demo.common;

/**
 * Create by Tang on 2019/10/23
 */
public interface Const {
    String CURRENT_USER = "current_user";

    String EMAIL = "email";
    String USERNAME = "username";

    interface Role {
        int ROLE_CUSTOMER = 0;
        int ROLE_ADMIN = 1;
    }
}
