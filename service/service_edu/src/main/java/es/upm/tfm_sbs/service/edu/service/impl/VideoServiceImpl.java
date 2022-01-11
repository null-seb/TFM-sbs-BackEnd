package es.upm.tfm_sbs.service.edu.service.impl;

import es.upm.tfm_sbs.service.edu.entity.Video;
import es.upm.tfm_sbs.service.edu.mapper.VideoMapper;
import es.upm.tfm_sbs.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}