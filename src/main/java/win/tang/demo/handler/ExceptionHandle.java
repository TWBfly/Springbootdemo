package win.tang.demo.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import win.tang.demo.utils.FormatResponseUtil;
import win.tang.demo.utils.ResponseResult;

/**
 * Create by Tang on 2019/10/21
 */
@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    private ResponseResult exceptionHandle(Exception e){
        return FormatResponseUtil.error(e);
    }
}
