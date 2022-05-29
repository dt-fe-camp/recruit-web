/**
 * @file AppPublishMap.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.admin;

import lombok.Data;

@Data
public class AdminPublishFields {
  /**
   * 选择项名称
   */
  private String publishName;

  /**
   * 选项值
   */
  private String publishValue;

  /**
   * 是否必填
   */
  private Integer required;

  /**
   * 筛选类型: single | multiple
   */
  private String publishValueType;
}
