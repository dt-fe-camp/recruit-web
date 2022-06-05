package recruit.model.admin;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AdminPublishBody {
  private Integer id;

  @NotEmpty
  private String title;

  @NotEmpty
  private List<String> regionCode;

  @NotEmpty
  private String regionDetail;

  @NotEmpty
  private String minSalary;

  @NotEmpty
  private String maxSalary;

  @NotEmpty
  private String salaryMonth;

  @NotEmpty
  private String education;

  @NotEmpty
  private String jobType;

  @NotEmpty
  private String experience;

  @NotEmpty
  private String description;

  @NotNull
  private String shortTip;

  @NotNull
  private List<String> welfare;
}
