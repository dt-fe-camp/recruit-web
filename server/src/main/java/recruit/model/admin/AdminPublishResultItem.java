/**
 * @file AppDtsItemOneLevel.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.admin;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import recruit.model.dts.DtsItem;

@Data
@AllArgsConstructor
public class AdminPublishResultItem {
  private String publishName;

  private String publishValue;

  private String publishValueType;

  private Integer required;

  private List<DtsItem> dataSource;
}

