package win.tang.demo.mapper;

import org.apache.ibatis.annotations.*;
import win.tang.demo.domain.Video;
import win.tang.demo.provider.VideoProvider;

import java.util.List;

/**
 * Create by Tang on 2019/10/21
 */
public interface VideoMapper {

    @Select("select * from video")
    List<Video> findAll();

    @Insert("insert into `video`(`title`,`summary`,`cover_img`,`view_num`,`price`,`create_time`,`online`,`point`)"
            + "values" + "(#{title},#{summary},#{coverImg},#{viewNum},#{price},#{createTime},#{online},#{point});")
    int add(Video video);

    @Delete("delete from video where id=#{id}")
    int delete(int id);

//    @Update("update video set title=#{title} where id=#{id} ")
    @UpdateProvider(type = VideoProvider.class,method = "updateProvider")
    int update(Video video);

    @Select("select * from video where id=#{id}")
    Video findById(int id);
}
