package es.upm.tfm_sbs.service.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("cms_ad")
public class Ad implements Serializable {

    private static final long serialVersionUID=1L;

    private String title;

    private String typeId;

    private String imageUrl;

    private String color;

    private String linkUrl;

    private Integer sort;


}
