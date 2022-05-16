package recruit;

import java.lang.reflect.InvocationTargetException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import recruit.model.app.AppDataSource;
import recruit.service.AppService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {
  @Autowired
  private AppService appSrv;

  @Test
  public void testGetAppDataSource() throws IllegalAccessException, InvocationTargetException, JsonProcessingException {
    AppDataSource dts = appSrv.getAppDataSource();
    String data = new ObjectMapper().writeValueAsString(dts);
    System.out.print("appDataSource: " + data);
  }

}
