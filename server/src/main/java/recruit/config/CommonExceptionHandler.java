package recruit.config;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import recruit.utils.Result;
import recruit.utils.ResultState;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class CommonExceptionHandler {
  @ExceptionHandler({ MethodArgumentNotValidException.class })
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();
    StringBuilder sb = new StringBuilder("校验失败，");
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
    }
    String msg = sb.toString();
    return Result.fail(ResultState.PARAMS_VALIDATION_FAIL_CODE, msg);
  }

  @ExceptionHandler({ ConstraintViolationException.class })
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Result handleConstraintViolationException(ConstraintViolationException ex) {
    return Result.fail(ResultState.PARAMS_VALIDATION_FAIL_CODE, ex.getMessage());
  }

  @ExceptionHandler({ Exception.class })
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Result handleCommonException(Exception ex) {
    return Result.fail(ResultState.UNKNOWN_FAIL_CODE, ex.getMessage());
  }
}