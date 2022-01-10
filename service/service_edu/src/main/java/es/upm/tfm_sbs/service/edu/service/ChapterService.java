package es.upm.tfm_sbs.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.edu.entity.Chapter;
import es.upm.tfm_sbs.service.edu.entity.query.ChapterQuery;

import java.util.List;

public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterQuery> nestedList(String courseId);
}
