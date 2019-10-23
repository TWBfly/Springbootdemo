package win.tang.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import win.tang.demo.domain.MmallUser;

/**
 * Create by Tang on 2019/10/23
 * 电商
 */
public interface MmallMapper {
    /**
     * check username
     */
    @Select("select count(1) from mmall_user where username = #{username}")
    int checkUserName(String userName);

    @Select("select * from mmall_user where username=#{username} and password=#{password}")
    MmallUser login(String username,String password);

    @Insert("insert into `mmall_user`(`username`,`password`,`email`,`phone`,`question`,`answer`,`role`,`create_time`,`update_time`)"
            + "values" + "(#{username},#{password},#{email},#{phone},#{question},#{answer},#{role},now(),now());")
    int register(MmallUser mmallUser);

    /**
     * check email
     */
    @Select("select count(1) from mmall_user where email = #{email}")
    int checkEmail(String email);

    /**
     * check phone
     */
    @Select("select count(1) from mmall_user where phone = #{phone}")
    int checkPhone(String phone);



}
