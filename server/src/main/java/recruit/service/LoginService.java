/**
 * @file LoginService.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.service;

import recruit.model.SysUser;
import recruit.utils.ResponseResult;

public interface LoginService {
  /**
   * ajax登录接口
   * @param user
   * @return 登录信息
   */
  ResponseResult<SysUser> login(SysUser user);

  // /**
  //  * ajax退出接口
  //  * @return
  //  */
  // ResponseResult<Object> logout();
}
