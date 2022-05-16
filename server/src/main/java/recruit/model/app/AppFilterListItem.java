/**
 * @file AppFilterMap.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.app;

public class AppFilterListItem {
  /**
   * 筛选项名称
   */
  private String name;

  /**
   * 筛选项名称
   */
  private String filterName;

  /**
   * 筛选项值
   */
  private String value;

  /**
   * 筛选值
   */
  private String filterValue;


  /**
   * 筛选类型: single | multiple
   */
  private String filterValueType;

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

  public String getFilterValue() {
    return this.filterValue;
  }

  public void setFilterValue(String filterValue) {
    this.filterValue = filterValue;
  }

  public String getFilterValueType() {
    return this.filterValueType;
  }

  public void setFilterValueType(String filterValueType) {
    this.filterValueType = filterValueType;
  }

  public String getFilterName() {
    return this.filterName;
  }

  public void setFilterName(String filterName) {
    this.filterName = filterName;
  }
}
