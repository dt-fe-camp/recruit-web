package recruit.model.app.recruitList;

import lombok.Data;

@Data
public class RecruitListItemQueryItem extends RecruitListItemBase {
  private String regionCode;

  private String regionDetail;

  private String companyRegionCode;

  private String companyRegionDetail;
}
