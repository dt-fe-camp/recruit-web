/**
 * @file AppListQueryBody.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.app.job_list;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AppListQueryBody {
  @NotNull
  private Integer pageSize;

  @NotNull
  private Integer pageIndex;

  AppListFilter filter;
}
