package win.tang.demo.mapper;

import org.apache.ibatis.annotations.*;
import win.tang.demo.domain.MmallCategory;
import win.tang.demo.domain.MmallUser;
import win.tang.demo.provider.MmallProvider;

import java.util.List;

/**
 * Create by Tang on 2019/10/23
 * 电商
 */
public interface MmallMapper {
    /**********************  user  **************************************/
    /**
     * check username
     */
    @Select("select count(1) from mmall_user where username = #{username}")
    int checkUserName(String userName);

    @Select("select * from mmall_user where username=#{username} and password=#{password}")
    MmallUser login(String username, String password);

    @Insert("insert into `mmall_user`(`username`,`password`,`email`,`phone`,`question`,`answer`,`role`,`create_time`,`update_time`)"
            + "values" + "(#{username},#{password},#{email},#{phone},#{question},#{answer},#{role},now(),now());")
    int register(MmallUser mmallUser);

    @Select("select role from mmall_user where id =#{id}")
    int selectRole(int id);

    /**
     * check email
     */
    @Select("select count(1) from mmall_user where email = #{email}")
    int checkEmail(String email);

    /**
     * check email 整个数据库中唯一
     */
    @Select("select count(1) from mmall_user where email = #{email} and id !=#{id}")
    int checkOnlyEmail(String email,int id);

    /**
     * check phone
     */
    @Select("select count(1) from mmall_user where phone = #{phone}")
    int checkPhone(String phone);


    /**
     * 忘记问题
     */
    @Select("select question from mmall_user where username = #{username}")
    String forgetGetQuestion(String username);

    /**
     * 校验答案
     */
    @Select("select count(1) from mmall_user where username = #{username} and question=#{question} and answer=#{answer}")
    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    /**
     * 忘记密码 重置密码
     */
    @Update("update mmall_user set password=#{newPassword},update_time = now() where username = #{username} ")
    int resetPassword(@Param("username") String username, @Param("newPassword") String newPassword, @Param("token") String token);


    /**
     * 校验旧密码
     */
    @Select("select count(1) from mmall_user where password=#{password} and id=#{id}")
    int checkPassword(String password, int id);

    /**
     * 忘记密码 重置密码 已登录
     */
//    @UpdateProvider(type = MmallProvider.class, method = "updateProvider")
    @Update("update mmall_user set password=#{newPassword},update_time = now()")
    int loginResetPassword(String oldPassword, String newPassword);

    /**
     * update 用户信息 username不能更新
     */
    @UpdateProvider(type = MmallProvider.class, method = "updateProvider")
    int updateUserInfo(MmallUser user);


    /**********************  category  **************************************/
    @Insert("insert into `mmall_category`(`parent_id`,`name`,`status`,`sort_order`,`create_time`,`update_time`)"
            + "values" + "(#{parentId},#{name},#{status},#{sortOrder},now(),now());")
    int addCategory(MmallCategory category);

    @UpdateProvider(type = MmallProvider.class, method = "updateCategoryProvider")
    int updateCategory(MmallCategory category);

    @Select("select * from mmall_category where parent_id=#{parentId}")
    List<MmallCategory> getChildrenParallelCategory(Integer parentId);

    @Select("select * from mmall_category where id=#{id}")
    MmallCategory selectByPrimaryKey(int id);
}
