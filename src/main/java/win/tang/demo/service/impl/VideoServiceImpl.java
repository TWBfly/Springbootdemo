package win.tang.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.tang.demo.domain.Video;
import win.tang.demo.mapper.VideoMapper;
import win.tang.demo.service.VideoService;

import java.util.List;

/**
 * Create by Tang on 2019/10/21
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Override
    public int add(Video video) {
        return videoMapper.add(video);
    }

    @Override
    public int delete(int id) {
        return videoMapper.delete(id);
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public Video findById(int id) {
        return videoMapper.findById(id);
    }

    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }
}
