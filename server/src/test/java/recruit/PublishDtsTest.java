/**
 * @file PublishDtsTest.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import recruit.dao.DtsDao;
import recruit.model.admin.AdminPublishResultItem;
import recruit.model.dts.DtsItem;
import recruit.model.dts.DtsRawDataItem;
import recruit.service.PublishService;
import recruit.utils.TreeDtsUtil;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PublishDtsTest {
  @Autowired
  PublishService adminPublishService;

  @Autowired
  private DtsDao dtsDao;

   /**
   * 查询所有发布选项
   * @throws JsonProcessingException
   */
  @Test
  public void testFindAllPublishDts() throws JsonProcessingException, IllegalAccessException, InvocationTargetException {
    Map<String, AdminPublishResultItem> adminPublishDtsMap = adminPublishService.getPublishDataSource();
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(Include.NON_NULL);
    String data = mapper.writeValueAsString(adminPublishDtsMap);
    System.out.print("adminPublishDtsItem: " + data);
    // assertEquals(5, dtsItems.size());
    // assertEquals("1", dtsItems.get(0).getValue());
  }
}
