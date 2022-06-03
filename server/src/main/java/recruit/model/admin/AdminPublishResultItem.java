/**
 * @file AppDtsItemOneLevel.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.admin;

import java.util.List;

import io.micrometer.core.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import recruit.model.dts.DtsItem;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdminPublishResultItem {
  @NonNull
  private String publishName;

  @NonNull
  private String publishValue;

  @NonNull
  private String publishValueType;

  @NonNull
  private Integer required;

  private List<DtsItem> dataSource;
}

