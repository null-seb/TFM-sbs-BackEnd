package es.upm.tfm_sbs.service.edu.entity.query;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CourseCollectVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String courseId;
    private String title;
    private BigDecimal price;
    private Integer lessonNum;//课时数
    private String cover;//封面
    private String gmtCreate;//收藏时间
    private String collegeName;
}
