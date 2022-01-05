package es.upm.tfm_sbs.service.base.exception;

import es.upm.tfm_sbs.common.base.result.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SbsException extends RuntimeException {

    private Integer code;

    //接受状态码和消息
    public SbsException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    //接受枚举类型
    public SbsException(ResultCode resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "SbsException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}