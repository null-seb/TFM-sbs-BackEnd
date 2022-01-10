package es.upm.tfm_sbs.service.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_chapter")
@Data
public class Chapter implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String courseId;

    private String title;

    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}