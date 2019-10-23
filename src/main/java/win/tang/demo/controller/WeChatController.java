package win.tang.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import win.tang.demo.config.WeChatConfig;
import win.tang.demo.domain.Video;
import win.tang.demo.service.VideoService;
import win.tang.demo.utils.FormatResponseUtil;

import java.util.Date;
import java.util.List;

/**
 * Create by Tang on 2019/10/21
 */
@RestController
public class WeChatController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");
    @Autowired
    WeChatConfig weChatConfig;
    @Autowired
    VideoService videoService;

    @RequestMapping(value = "/test")
    public String test() {
        System.out.println(weChatConfig.getAppId());
        return "test===" + weChatConfig.getAppsecret();
    }

    @RequestMapping(value = "/testdb")
    public Object testdb() {
        return FormatResponseUtil.formatResponse(videoService.findAll());
    }

//    @PostMapping("add")
//    public Object add(String title, String summary, String coverImg, int viewNum, int price, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date createTime, int online, double point) {
//        Video video = new Video();
//        video.setTitle(title);
//        video.setSummary(summary);
//        video.setCoverImg(coverImg);
//        video.setViewNum(viewNum);
//        video.setPrice(price);
//        video.setCreateTime(createTime);
//        video.setOnline(online);
//        video.setPoint(point);
//        return FormatResponseUtil.formatResponse(videoService.add(video));
//    }

    @PostMapping("add")
    public Object add(@RequestBody Video video) {
        return FormatResponseUtil.formatResponse(videoService.add(video));
    }

    @PostMapping("delete")
    public Object delete(int id) {
        return FormatResponseUtil.formatResponse(videoService.delete(id));
    }

    @PostMapping("update")
    public Object update(@RequestBody Video video) {
        return FormatResponseUtil.formatResponse(videoService.update(video));
    }

    @PostMapping("findById")
    public Object findById(int id) {
        return FormatResponseUtil.formatResponse(videoService.findById(id));
    }

    @GetMapping("findAll")
    public Object findAll(@RequestParam(value = "page",defaultValue = "1")int page,
                          @RequestParam(value = "size",defaultValue = "2")int size){
        dataLogger.info("findAll==page="+page);
        PageHelper.startPage(page,size);
        List<Video> all = videoService.findAll();
        PageInfo<Video> videoPageInfo = new PageInfo<>(all);
        return FormatResponseUtil.formatResponse(videoPageInfo);
    }


}
