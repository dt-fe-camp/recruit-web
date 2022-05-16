/**
 * @file ApiLoginUser.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.admin.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiLoginUser {
  private String userName;

  private String password;
}
