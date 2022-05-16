/**
 * @file UserDetailServiceImpl.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import recruit.dao.admin.UserDao;
import recruit.model.SysUser;
import recruit.model.admin.user.LoginedUserDetails;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
  @Autowired
  private UserDao userDao;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SysUser usr = userDao.findUserByUserName(username);

    if (usr == null) {
      throw new RuntimeException("用户名或密码错误!");
    }

    UserDetails userDetail = new LoginedUserDetails(usr);
    return userDetail;
  }
}
