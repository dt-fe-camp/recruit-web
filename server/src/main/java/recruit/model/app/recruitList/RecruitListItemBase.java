package recruit.model.app.recruitList;

import lombok.Data;

@Data
public class RecruitListItemBase {
  private String title;

  private String regionCode;

  private String regionDetail;

  private String minSalary;

  private String maxSalary;

  private String salaryMonth;

  private String education;

  private String jobType;

  private String experience;

  private String description;

  private String shortTip;

  /**
   * 公司名称
   */
  private String companyName;

  /**
   * 公司描述信息
   */
  private String companyDescription;

  /**
   * 公司规模
   */
  private String companyScale;

  /**
   * 公司类型
   */
  private String companyType;

  /**
   * 公司logo
   */
  private String companyLogo;
}
