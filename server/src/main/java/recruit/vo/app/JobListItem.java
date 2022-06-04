/**
 * @file JobDaoItem.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.vo.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recruit.model.app.job_list.JobListItemBase;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobListItem extends JobListItemBase {
  private String[] welfare;
}
