package es.upm.tfm_sbs.service.edu.service.impl;

import es.upm.tfm_sbs.service.edu.entity.Video;
import es.upm.tfm_sbs.service.edu.feign.VodMediaService;
import es.upm.tfm_sbs.service.edu.mapper.VideoMapper;
import es.upm.tfm_sbs.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    private final VodMediaService vodMediaService;

    public VideoServiceImpl(VodMediaService vodMediaService) {
        this.vodMediaService = vodMediaService;
    }

    @Override
    public void removeMediaVideoById(String id) {

        log.warn("VideoServiceImpl：video id = " + id);
        //根据videoid找到视频id
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        log.warn("VideoServiceImpl：videoSourceId= " + videoSourceId);
        vodMediaService.removeVideo(videoSourceId);
    }
}