package es.upm.tfm_sbs.service.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_course")
public class Course implements Serializable {

    private static final long serialVersionUID=1L;

    public static final String COURSE_DRAFT = "Draft";      //未发布
    public static final String COURSE_NORMAL = "Normal";    //已发布

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String collegeId;

    private String subjectId;

    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private Long version;

    private String status;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
