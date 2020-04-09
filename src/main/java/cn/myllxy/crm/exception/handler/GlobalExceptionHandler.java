package cn.myllxy.crm.exception.handler;

import cn.myllxy.crm.exception.BusinessException;
import cn.myllxy.crm.exception.code.BaseExceptionResponseEnum;
import cn.myllxy.crm.utils.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    public AjaxResult businessException(BusinessException e) {
        log.error("BusinessException:{}", e);
        return new AjaxResult(e.getMsg(), e.getCode());
    }

    @ExceptionHandler(value = Exception.class)
    public AjaxResult exceptionHandler(Exception e) {
        log.error("Exception:{}", e);
        return new AjaxResult(e.getMessage(), BaseExceptionResponseEnum.SYSTEMBUSY.getCode());
    }
}