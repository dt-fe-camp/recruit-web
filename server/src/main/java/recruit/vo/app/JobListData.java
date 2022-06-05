/**
 * @file JobListData.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.vo.app;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobListData {

  private Integer total;

  private Integer pageIndex;

  private Integer pageSize;

  private List<JobListItem> list;
}
