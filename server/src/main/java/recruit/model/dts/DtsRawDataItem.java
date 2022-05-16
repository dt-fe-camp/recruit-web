/**
 * @file DtsRawDataItem.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.dts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtsRawDataItem {
  private String l1Name;
  private String l1Value;

  private String l2Name;
  private String l2Value;

  private String l3Name;
  private String l3Value;
}
