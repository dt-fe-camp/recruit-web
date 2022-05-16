/**
 * @file AppDtsItemOneLevel.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.app;

import java.util.List;

public class AppFilterResultItem {
  private String filterName;

  private String filterValue;

  private String filterValueType;

  public AppFilterResultItem(String filterName, String filterValue, String filterValueType, List<AppFilterResultChildrenItem> children) {
    this.filterName = filterName;
    this.filterValue = filterValue;
    this.filterValueType = filterValueType;
    this.children = children;
  }

  private List<AppFilterResultChildrenItem> children;

  public String getFilterName() {
    return this.filterName;
  }

  public void setFilterName(String filterName) {
    this.filterName = filterName;
  }

  public String getFilterValueType() {
    return this.filterValueType;
  }

  public void setFilterValueType(String filterValueType) {
    this.filterValueType = filterValueType;
  }

  public String getFilterValue() {
    return this.filterValue;
  }

  public void setFilterValue(String filterValue) {
    this.filterValue = filterValue;
  }

  public List<AppFilterResultChildrenItem> getChildren() {
    return this.children;
  }

  public void setChildren(List<AppFilterResultChildrenItem> children) {
    this.children = children;
  }


  public static class AppFilterResultChildrenItem {
    private String name;
    private String value;

    public AppFilterResultChildrenItem(String name, String value) {
      this.name = name;
      this.value = value;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getValue() {
      return this.value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }
}
