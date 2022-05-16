/**
 * @file Test.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {
  private String name;

  private float value;

  ArrayList<Test> children = new ArrayList<>();

  public String getName() {
    return name;
  }

  public float getValue() {
    return value;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public void setValue( float value ) {
    this.value = value;
  }

  static void main() {
    Test test = new Test();

    test.setName("abc");
    test.setValue(1);
    List<Test> children = new ArrayList<>();

    Test testChild = new Test();
    testChild.setName("def");
    testChild.setValue(1);
    children.add(testChild);
    HashMap<String, Test> map = new HashMap<>(16);
    map.put("test", test);
  }
}



