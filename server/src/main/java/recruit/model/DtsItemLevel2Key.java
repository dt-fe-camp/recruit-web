package recruit.model;

public class DtsItemLevel2Key {
    private String parentValue;

    private String value;

    public String getParentValue() {
        return parentValue;
    }

    public void setParentValue(String parentValue) {
        this.parentValue = parentValue == null ? null : parentValue.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}