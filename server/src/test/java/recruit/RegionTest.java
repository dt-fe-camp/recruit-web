package recruit;

import java.util.List;
import java.lang.reflect.InvocationTargetException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import recruit.model.dts.DtsItem;
import recruit.service.RegionService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RegionTest {
  @Autowired
  private RegionService regionSrv;


  /**
   * 行业
   * @throws JsonProcessingException
   */
  @Test
  public void testGetRegionDts() throws JsonProcessingException, IllegalAccessException, InvocationTargetException {
    List<DtsItem> dts = regionSrv.getRegionDts();

    String regionData = new ObjectMapper().writeValueAsString(dts);
    System.out.print("regionData: " + regionData);
    // assertEquals(19, dtsItems.size());
    // assertEquals("1000000000000", dtsItems.get(0).getValue());
  }
}
