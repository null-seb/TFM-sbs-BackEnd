package es.upm.tfm_sbs.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.edu.entity.Video;

public interface VideoService extends IService<Video> {
    void removeMediaVideoById(String id);
}
