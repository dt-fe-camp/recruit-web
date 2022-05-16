package recruit.model;

public class Welfare {
    private Integer id;

    private Integer recruitId;

    private String welfareValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public String getWelfareValue() {
        return welfareValue;
    }

    public void setWelfareValue(String welfareValue) {
        this.welfareValue = welfareValue == null ? null : welfareValue.trim();
    }
}