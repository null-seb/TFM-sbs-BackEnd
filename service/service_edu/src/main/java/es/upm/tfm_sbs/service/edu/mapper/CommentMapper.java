package es.upm.tfm_sbs.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import es.upm.tfm_sbs.service.edu.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
