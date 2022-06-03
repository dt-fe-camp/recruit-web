/**
 * @file AppDtsItemOneLevel.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.app;

import java.util.List;

import io.micrometer.core.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import recruit.model.dts.DtsItem;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AppFilterResultItem {
  @NonNull
  private String filterName;

  @NonNull
  private String filterValue;

  @NonNull
  private String filterValueType;

  private List<DtsItem> dataSource;
}
