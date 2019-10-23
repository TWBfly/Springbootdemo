package win.tang.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import win.tang.demo.domain.MmallUser;
import win.tang.demo.domain.User;

/**
 * Create by Tang on 2019/10/23
 * 电商
 */
public interface MmallMapper {
    @Select("select count(1) from mmall_user where username = #{username}")
    int checkUserName(String userName);

    @Select("select * from mmall_user where username = #{username} and password = #{password}")
    MmallUser login();

    @Insert("insert into `mmall_user`(`username`,`password`,`email`,`phone`,`question`,`answer`,`role`)"
            + "values" + "(#{username},#{password},#{email},#{phone},#{question},#{answer},#{role});")
    String register(MmallUser mmallUser);


}
