package recruit.model;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Job {
    private Integer id;

    @NonNull
    private String title;

    @NonNull
    private String regionCode;

    @NonNull
    private String regionDetail;

    @NonNull
    private String minSalary;

    @NonNull
    private String maxSalary;

    @NonNull
    private String salaryMonth;

    @NonNull
    private String education;

    @NonNull
    private String jobType;

    @NonNull
    private String experience;

    @NonNull
    private String description;

    @NonNull
    private String shortTip;

    @NonNull
    private String welfare;

    private Integer companyId;

    private Date createTime;

    private Date modifyTime;

    private Byte isDel;
}