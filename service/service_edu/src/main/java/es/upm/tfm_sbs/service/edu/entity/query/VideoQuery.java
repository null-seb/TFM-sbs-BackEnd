package es.upm.tfm_sbs.service.edu.entity.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private Integer sort;

    private String videoSourceId;
}
