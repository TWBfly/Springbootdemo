package win.tang.demo.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import win.tang.demo.domain.MmallUser;

/**
 * Create by Tang on 2019/10/23
 * SQLd动态注入
 */
public class MmallProvider {
    public String updateProvider(MmallUser user) {

        return new SQL() {
            {
                UPDATE("mmall_user");
                //条件写法
                if (user.getUsername() != null) {
                    SET("username=#{username}");
                }
                if (StringUtils.isNotBlank(user.getPassword())) {
                    SET("password=#{password}");
                }
                if (user.getEmail() != null) {
                    SET("email=#{email}");
                }
                if (user.getPhone() != null) {
                    SET("phone=#{phone}");
                }
                if (user.getQuestion() != null) {
                    SET("question=#{question}");
                }
                if (user.getAnswer() != null) {
                    SET("answer=#{answer}");
                }
                if (user.getRole() != null) {
                    SET("role=#{role}");
                }
                SET("update_time= now()");
                WHERE("id=#{id}");
            }
        }.toString();
    }
}
