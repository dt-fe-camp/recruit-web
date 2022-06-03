/**
 * @file RecruitListItem.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.vo.app;

import lombok.Data;
import lombok.NoArgsConstructor;
import recruit.model.app.recruitList.RecruitListItemBase;

@Data
@NoArgsConstructor
public class RecruitListItem extends RecruitListItemBase {
  /**
   * 岗位地址
   */
  private String location;

  /**
   * 公司地址
   */
  private String recruitLocation;
}
