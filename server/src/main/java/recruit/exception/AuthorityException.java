package recruit.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限校验异常类
 * 使用时请用异常捕获类捕获
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityException extends RuntimeException {

  private Integer code;

  private String msg;
}