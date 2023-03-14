package com.ue.termi.exception;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ue.termi.enums.ResultCodeMsgEnum;
import com.ue.termi.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 注意这里可能需要根据不同的sessionType去返回不同的异常包装对象
 *
 * @author huwei01_cd@keruyun.com
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class WebExceptionInterceptor {

    @ExceptionHandler
    public ResponseResult<?> handleException(MethodArgumentNotValidException e) {
        log.warn("WebExceptionHandler.handleMethodArgumentNotValidException,msg={}", e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        ResponseResult<Object> result = ResponseResult.error(ResultCodeMsgEnum.PARAM_ERR.getCode(), ResultCodeMsgEnum.PARAM_ERR.getMsg());
        handleBindingResult(bindingResult, result);
        log.warn("WebExceptionHandler.handleMethodArgumentNotValidException,res={}", JSONUtil.parse(result));
        return result;
    }


    private void handleBindingResult(BindingResult bindingResult, ResponseResult response) {
        List<ObjectError> errors = bindingResult.getAllErrors();
        if (errors == null) {
            response.setCode(ResultCodeMsgEnum.PARAM_ERR.getCode());
            return;
        }
        Map<String, String> errorDesc = new HashMap<>(20);
        for (ObjectError error : errors) {
            if (error == null) {
                continue;
            }
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorDesc.put(error.getObjectName() + "." + fieldError.getField(), fieldError.getDefaultMessage());
            } else {
                errorDesc.put(error.getObjectName(), error.getCode());
            }
        }
        response.setCode(ResultCodeMsgEnum.PARAM_ERR.getCode());
        response.setMsg(ResultCodeMsgEnum.PARAM_ERR.getMsg() + ":" + errorDesc.toString());
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseResult<?> handleNotReadableException(Exception e) {
        // 处理参数反序列化异常
        log.warn("WebExceptionHandler.handleNotReadableException", e);
        ResponseResult<Object> ResponseResult = handleNotReadable(e);
        log.warn("WebExceptionHandler.handleNotReadableException,res={}", JSONUtil.parse(ResponseResult));
        return ResponseResult;
    }

    private ResponseResult<Object> handleNotReadable(Throwable exception) {
        ResponseResult<Object> response = ResponseResult.error(ResultCodeMsgEnum.PARAM_ERR.getCode(), ResultCodeMsgEnum.PARAM_ERR.getMsg());
        response.setMsg(exception.getMessage());
        String message = "";
        Throwable cause = exception.getCause();
        if (cause instanceof JsonMappingException) {
            JsonMappingException jsonMappingException = (JsonMappingException) cause;
            List<JsonMappingException.Reference> paths = jsonMappingException.getPath();
            StringBuilder sb = new StringBuilder();
            Iterator<JsonMappingException.Reference> iterator = paths.iterator();
            JsonMappingException.Reference element = iterator.next();
            appendReference(sb, element);
            while (iterator.hasNext()) {
                element = iterator.next();
                if (element.getIndex() < 0) {
                    sb.append(".");
                }
                appendReference(sb, element);
            }
            message = sb.toString();
        }
        response.setCode(ResultCodeMsgEnum.PARAM_ERR.getCode());
        response.setMsg(message + ResultCodeMsgEnum.PARAM_ERR.getMsg());
        return response;
    }

    private void appendReference(StringBuilder stringBuilder, JsonMappingException.Reference ref) {
        String fieldName = ref.getFieldName();
        int index = ref.getIndex();
        if (StringUtils.isEmpty(fieldName)) {
            stringBuilder.append("[").append(index).append("]");
        } else {
            stringBuilder.append(fieldName);
        }
    }

	/*@ExceptionHandler
	public ResponseResult<?> handleException(UniteAllNullException e){
		log.error("WebExceptionHandler.handleException",e);
		return ResponseResult.parameterError();

	}

	@ExceptionHandler
	public ResponseResult<?> handleException(UnauthorizedException e){
		log.error("WebExceptionHandler.handleException",e);
		return ResponseResult.unauthorized();

	}
*/

	/*@ExceptionHandler
	public ResponseResult<?> handleException(NotloginException e){
		log.error("WebExceptionHandler.handleException",e);
		return ResponseResult.notlogin();
	}*/

    @ExceptionHandler
    public ResponseResult<?> handleException(Exception e) {
        log.error("WebExceptionHandler.handleException msg={},", e.getMessage(), e);
        return new ResponseResult<>(ResultCodeMsgEnum.SYS_ERR.getCode(), null, ResultCodeMsgEnum.SYS_ERR.getMsg());
    }


    @ExceptionHandler(value = IException.class)
    public ResponseResult<?> handleException(IException e) {
        log.warn("WebExceptionHandler.handleIException", e);
        ResponseResult<String> stringRestResponse = new ResponseResult<>(e.getCode(), null, e.getMessage());
        log.warn("WebExceptionHandler.handleIException,res={}", JSONUtil.parse(stringRestResponse));
        return stringRestResponse;
    }

    @ExceptionHandler(value = RpcException.class)
    public ResponseResult<?> handleException(RpcException e) {
        ResponseResult<String> stringRestResponse = new ResponseResult<>(e.getCode(), null, e.getMessage());
        log.warn("RpcException,res={}", JSONUtil.parse(stringRestResponse), e);
        return stringRestResponse;
    }
}
