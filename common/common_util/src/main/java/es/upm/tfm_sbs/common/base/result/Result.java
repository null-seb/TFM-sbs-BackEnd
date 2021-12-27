package es.upm.tfm_sbs.common.base.result;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(value = "Returns results uniformly across the board.")
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data= new HashMap<>();

    public static Result ok(){
        Result result=new Result();
        result.setSuccess(ResultCode.SUCCESS.getSuccess());
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result error(){
        Result result=new Result();
        result.setSuccess(ResultCode.UNKNOWN_REASON.getSuccess());
        result.setCode(ResultCode.UNKNOWN_REASON.getCode());
        result.setMessage(ResultCode.UNKNOWN_REASON.getMessage());
        return result;
    }

    public static Result setResult(ResultCode resultCode){
        Result result=new Result();
        result.setSuccess(resultCode.getSuccess());
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
}
