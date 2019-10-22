package win.tang.demo.provider;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;
import win.tang.demo.domain.Video;

/**
 * Create by Tang on 2019/10/22
 */
public class VideoProvider {

    public String updateProvider(Video video) {

        return new SQL() {
            {
                UPDATE("video");
                //条件写法
                if (video.getTitle() != null) {
                    SET("title=#{title}");
                }
                if (video.getSummary() != null) {
                    SET("summary=#{summary}");
                }
                if (video.getCoverImg() != null) {
                    SET("summary=#{coverImg}");
                }
                if (video.getViewNum() != null) {
                    SET("viewNum=#{viewNum}");
                }
                if (video.getPrice() != null) {
                    SET("price=#{price}");
                }
                if (video.getCreateTime() != null) {
                    SET("createTime=#{createTime}");
                }
                if (video.getOnline() != null) {
                    SET("online=#{online}");
                }
                if (video.getPoint() != null) {
                    SET("point=#{point}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }
}
