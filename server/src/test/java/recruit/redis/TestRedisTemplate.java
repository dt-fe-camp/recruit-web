/**
 * @file TestRedisTemplate.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedisTemplate {
  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  private String testKey = "hello spring boot!!";

  @Test
  public void testString() {
    redisTemplate.opsForValue().set("hello:redis", testKey);
    assertEquals(testKey, redisTemplate.opsForValue().get("hello:redis"));
  }
}
