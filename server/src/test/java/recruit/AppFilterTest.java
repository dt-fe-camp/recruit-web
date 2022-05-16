package recruit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import recruit.dao.app.FilterDao;
import recruit.model.app.AppFilterListItem;
import recruit.model.app.AppFilterResultItem;
import recruit.model.app.AppFilterResultItem.AppFilterResultChildrenItem;
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

  /**
   * 查询所有App筛选器
   * @throws JsonProcessingException
   */
  @Test
  public void testFindAllAppFilters() throws JsonProcessingException {
    List<AppFilterListItem> appFilters = this.filterDao.findAppFilters();
    LinkedHashMap<String, AppFilterResultItem> filterValueMap = new LinkedHashMap<>();

    for (AppFilterListItem item: appFilters) {
      String key = item.getFilterValue();
      AppFilterResultItem resultItems = filterValueMap.get(key);
      List<AppFilterResultItem.AppFilterResultChildrenItem> children;
      AppFilterResultItem.AppFilterResultChildrenItem childrenItem = new AppFilterResultChildrenItem(item.getName(), item.getValue());
      children = resultItems == null ? new ArrayList<>() : resultItems.getChildren();
      children.add(childrenItem);
      resultItems = new AppFilterResultItem(item.getFilterName(), item.getFilterValue(), item.getFilterValueType(), children);
      filterValueMap.put(key, resultItems);
    }

    String data = new ObjectMapper().writeValueAsString(filterValueMap.values().toArray());
    System.out.print("appFileListItems: " + data);
    // assertEquals(5, dtsItems.size());
    // assertEquals("1", dtsItems.get(0).getValue());
  }
}
