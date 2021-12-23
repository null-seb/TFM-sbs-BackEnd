package es.upm.tfm_sbs.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_college")
public class College implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
      private String id;

      private String name;

      private String intro;

      private String career;

      private Integer level;

      private String avatar;

      private Integer sort;

      private Date joinDate;

      @TableLogic
    private Integer isDeleted;

        @TableField(fill = FieldFill.INSERT)
      private Date gmtCreate;

        @TableField(fill = FieldFill.INSERT_UPDATE)
      private Date gmtModified;


}
