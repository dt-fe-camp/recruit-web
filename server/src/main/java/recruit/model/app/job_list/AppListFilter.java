/**
 * @file AppListFilter.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.app.job_list;

import java.util.List;

import lombok.Data;


@Data
public class AppListFilter {
  private String title;

  private List<String> regionCode;
}
