package es.upm.tfm_sbs.service.base.handler;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.common.base.result.ResultCode;
import es.upm.tfm_sbs.common.base.util.ExceptionUtils;
import es.upm.tfm_sbs.service.base.exception.SbsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public Result error(BadSqlGrammarException e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.setResult(ResultCode.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result error(HttpMessageNotReadableException e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.setResult(ResultCode.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(SbsException.class)
    @ResponseBody
    public Result error(SbsException e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.error().message(e.getMessage()).code(e.getCode());
    }
}
