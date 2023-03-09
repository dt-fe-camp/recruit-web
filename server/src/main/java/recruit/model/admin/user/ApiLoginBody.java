/**
 * @file ApiLoginBody.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.admin.user;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiLoginBody {
  @NotEmpty
  private Boolean rememberMe;

  @NotEmpty
  @Min(3)
  private String userName;

  @Min(3)
  private String password;
}
