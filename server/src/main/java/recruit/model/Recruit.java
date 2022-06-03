package recruit.model;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Recruit {
    private Integer id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String regionCode;

    @NotEmpty
    private String regionDetail;

    @NotEmpty()
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

    @NotEmpty
    private String shortTip;

    private Integer companyId;

    private Date createTime;

    private Date modifyTime;

    private Byte isDel;
}