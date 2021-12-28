package es.upm.tfm_sbs.service.edu.entity.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class CollegeQuery implements Serializable {
    private static final long serialVersionUID=1L;

    private String name;
    private Integer level;
    private String joinDateBegin;
    private String joinDateEnd;
}
