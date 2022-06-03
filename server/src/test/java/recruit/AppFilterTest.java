package recruit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import recruit.dao.app.FilterDao;
import recruit.model.dts.DtsItemOneLevel;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppFilterTest {
  @Autowired
  private FilterDao filterDao;

  /**
   * 行业
   * @throws JsonProcessingException
   */
  @Test
  public void testFindIndustryFilter() throws JsonProcessingException {
    List<DtsItemOneLevel> dtsItems = this.filterDao.findFilterByDtsValue("industry");
    String industryData = new ObjectMapper().writeValueAsString(dtsItems);
    System.out.print("industryData: " + industryData);
    assertEquals(19, dtsItems.size());
    assertEquals("1000000000000", dtsItems.get(0).getValue());
  }

  /**
   * 公司规模
   * @throws JsonProcessingException
   */
  @Test
  public void testCompanyScaleFilter() throws JsonProcessingException {
    List<DtsItemOneLevel> dtsItems = this.filterDao.findFilterByDtsValue("company_scale");
    String data = new ObjectMapper().writeValueAsString(dtsItems);
    System.out.print("companyScaleData: " + data);
    assertEquals(7, dtsItems.size());
    assertEquals("1", dtsItems.get(0).getValue());
  }

  /**
   * 公司属性
   * @throws JsonProcessingException
   */
  @Test
  public void testCompanyTypeFilter() throws JsonProcessingException {
    List<DtsItemOneLevel> dtsItems = this.filterDao.findFilterByDtsValue("company_type");
    String data = new ObjectMapper().writeValueAsString(dtsItems);
    System.out.print("companyTypeData: " + data);
    assertEquals(23, dtsItems.size());
    assertEquals("1", dtsItems.get(0).getValue());
  }

  /**
   * 发布时间
   * @throws JsonProcessingException
   */
  @Test
  public void testPublishDateFilter() throws JsonProcessingException {
    List<DtsItemOneLevel> dtsItems = this.filterDao.findFilterByDtsValue("publish_date");
    String data = new ObjectMapper().writeValueAsString(dtsItems);
    System.out.print("publishDate: " + data);
    assertEquals(4, dtsItems.size());
    assertEquals("2", dtsItems.get(0).getValue());
  }
}
