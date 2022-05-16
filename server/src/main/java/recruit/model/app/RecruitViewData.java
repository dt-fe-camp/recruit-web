
/**
 * @file AppDataSource.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.app;

import java.util.List;
import lombok.Data;

@Data
public class RecruitViewData {
  private String title;

  private String region;

  /**
   * 职位地址
   */
  private String jobLocation;

  private String minSalary;

  private String maxSalary;

  private String salaryMonth;

  /**
   * 经验
   */
  private String experience;

  /**
   * 学历
   */
  private String education;

  /**
   * 全职、兼职、校招
   */
  private String jobType;

  /**
   * 标签：五险一金等
   */
  private List<String> jobKeywords;

  /**
   * 福利
   */
  private List<String> welfares;

  /**
   * 行业：计算机软件/
   */
  private String industry;

  /**
   * 公司名称
   */
  private String companyName;

  /**
   * 公司性质
   */
  private String companyType;

  /**
   * 公司规模
   */
  private String companyScale;

  /**
   * 公司地址
   */
  private String companyLocation;

  /**
   * 职位信息
   */
  private String description;
}