/**
 * @file AccountRealm.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import recruit.dao.admin.UserDao;
import recruit.model.SysUser;

public class AccountRealm extends AuthorizingRealm {

  @Autowired
  private UserDao userDao;
  /**
   * 认证，获取用户对应的角色
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) {
    UsernamePasswordToken token = (UsernamePasswordToken)authToken;
    SysUser user = userDao.findUserByUserName(token.getUsername());
    if (user != null) {
      System.out.println("user" + user);
      return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
    return null;
  }


  /**
   * 授权，角色的权限信息
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
    return null;
  }
}
