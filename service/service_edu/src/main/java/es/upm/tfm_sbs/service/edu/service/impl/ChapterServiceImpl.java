package es.upm.tfm_sbs.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import es.upm.tfm_sbs.service.edu.entity.Chapter;
import es.upm.tfm_sbs.service.edu.entity.Video;
import es.upm.tfm_sbs.service.edu.entity.query.ChapterQuery;
import es.upm.tfm_sbs.service.edu.entity.query.VideoQuery;
import es.upm.tfm_sbs.service.edu.mapper.ChapterMapper;
import es.upm.tfm_sbs.service.edu.mapper.VideoMapper;
import es.upm.tfm_sbs.service.edu.service.ChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    private final VideoMapper videoMapper;

    public ChapterServiceImpl(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeChapterById(String id) {

        //课时信息：video
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", id);
        videoMapper.delete(videoQueryWrapper);

        //章节信息：chapter
        return this.removeById(id);
    }

    @Override
    public List<ChapterQuery> nestedList(String courseId) {

        List<ChapterQuery> chapterVoList = new ArrayList<>();

        //获取章信息
        QueryWrapper<Chapter> queryWrapperChapter = new QueryWrapper<>();
        queryWrapperChapter.eq("course_id", courseId);
        queryWrapperChapter.orderByAsc("sort", "id");
        List<Chapter> chapterList = baseMapper.selectList(queryWrapperChapter);

        //获取课时信息
        QueryWrapper<Video> queryWrapperVideo = new QueryWrapper<>();
        queryWrapperVideo.eq("course_id", courseId);
        queryWrapperVideo.orderByAsc("sort", "id");
        List<Video> videoList = videoMapper.selectList(queryWrapperVideo);

        //填充列表数据：Chapter列表
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);

            //创建ChapterVo对象
            ChapterQuery chapterQuery = new ChapterQuery();
            BeanUtils.copyProperties(chapter, chapterQuery);
            chapterVoList.add(chapterQuery);

            //填充列表数据：Video列表
            List<VideoQuery> videoVoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                Video video = videoList.get(j);
                if(chapter.getId().equals(video.getChapterId())){

                    VideoQuery videoVo = new VideoQuery();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }

            chapterQuery.setChildren(videoVoList);
        }

        return chapterVoList;
    }
}