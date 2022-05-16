/**
 * @file AppDataSource.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.app;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import recruit.model.dts.DtsItem;

@Data
@AllArgsConstructor
public class AppDataSource {
  private List<AppFilterResultItem> filters;

  private List<DtsItem> region;
}
