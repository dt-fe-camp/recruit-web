package recruit;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import recruit.dao.RecruitDao;
import recruit.model.Recruit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RecruitTest {
  @Autowired
  private RecruitDao recruitDao;

  @Test
  public void testFindAll() throws JsonProcessingException {
    List<Recruit> list = recruitDao.findAll();
    String listData = new ObjectMapper().writeValueAsString(list);
    System.out.println("结果： " + listData);
  }
}
