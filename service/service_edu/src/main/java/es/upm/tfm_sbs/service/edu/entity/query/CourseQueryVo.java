package es.upm.tfm_sbs.service.edu.entity.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String collegeId;
    private String subjectParentId;
    private String subjectId;
}
