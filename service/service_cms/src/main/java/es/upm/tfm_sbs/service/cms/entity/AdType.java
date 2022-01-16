package es.upm.tfm_sbs.service.cms.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("cms_ad_type")
@ApiModel(value="AdType对象", description="推荐位")
public class AdType implements Serializable {

    private static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private String title;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}