/**
 * @file JobListItemBase.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.app.job_list;

import lombok.Data;

@Data
public class JobListItemBase {
   /**
   * 岗位名称
  */
  private String title;

  /**
   * 职位描述
   */
  private String description;

  /**
   * 岗位地址
   */
  private String location;

  /**
   * 职位亮点
   */
  private String shortTip;

  /**
   * 最低工资
   */
  private String minSalary;

  /**
   * 最高工资
   */
  private String maxSalary;

  /**
   * 薪酬月份
   */
  private String salaryMonth;

  /**
   * 工作类型
   */
  private String jobType;

  /**
   * 学历
   */
  private String education;

  /**
   * 经验
   */
  private String experience;

  /**
   * 公司名称
   */
  private String companyName;

  /**
   * 公司描述信息
   */
  private String companyDescription;

  /**
   * 公司logo
   */
  private String companyLogo;

  /**
   * 公司规模
   */
  private String companyScale;

  /**
   * 公司类型
   */
  private String companyType;

  /**
   * 公司地址
   */
  private String companyLocation;
}
