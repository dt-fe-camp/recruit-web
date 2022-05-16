/**
 * @file ResponseResult.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseResult<T> {
  private int code;

  private T data;

  private String message;

  public ResponseResult(T data) {
    this.code = 0;
    this.data = data;
    this.message = "success";
  }

  public ResponseResult(int code, String message) {
    this.code = code;
    this.data = null;
    this.message = message;
  }
}
