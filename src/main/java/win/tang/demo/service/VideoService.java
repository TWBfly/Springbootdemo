package win.tang.demo.service;


import win.tang.demo.domain.Video;

import java.util.List;

/**
 * Create by Tang on 2019/10/21
 */
public interface VideoService {
    int add(Video video);

    int delete(int id);

    int update(Video video);

    Video findById(int id);

    List<Video> findAll();
}
