/**
 * @file DtsItem.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.model.dts;
import java.util.List;
import lombok.Data;

@Data
public class DtsItem extends DtsItemOneLevel {

  private List<DtsItem> children;

  public List<DtsItem> getChildren() {
    return this.children;
  }

  public DtsItem(String name, String value) {
    super(name, value);
  }

  public void setChildren(List<DtsItem> children) {
    this.children = children;
  }
}