/**
 * @file User.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.jsonwebtoken.Claims;
import recruit.dao.admin.UserDao;
import recruit.model.SysUser;
import recruit.utils.JwtUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
  @Autowired
  private UserDao userDao;

  @Test
  public void testFindUserById() {
    SysUser user = userDao.findUserByUserName("markjia");
    assertEquals("markjia", user.getUserName());
  }

  @Test
  public void testJWT() throws Exception {
    // String jwtToken = JwtUtils.createJWT("3");
    // System.out.println("jwtToken: " + jwtToken);
    // Claims jwtObj = JwtUtils.parseJWT(jwtToken);
    // System.out.println("decrypt_jwt: " + jwtObj.getSubject());
    // assertEquals("3", jwtObj.getSubject());
  }
}
