package com.loan.approve.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> {

    @Schema(description = "success or not")
    private boolean isSuccess;

    @Schema(description = "if not success, this is the error code. example=001, if success, it is 000")
    private String errorCode;

    @Schema(description = "if not success, this is the error message, else is null")
    private String errorMsg;

    @Schema(description = "if success, this is the response data")
    private T data;

    public static <T> BaseResult<T> success(T data) {
        BaseResult<T> r = new BaseResult<>();
        r.setSuccess(true);
        r.setData(data);
        return r;
    }

    public static <T> BaseResult<T> fail(String code, String msg) {
        BaseResult<T> r = new BaseResult<>();
        r.setSuccess(false);
        r.setErrorCode(code);
        r.setErrorMsg(msg);
        return r;
    }
}
