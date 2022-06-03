/**
 * @file TreeDts.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import recruit.model.dts.DtsItem;
import recruit.model.dts.DtsItemOneLevel;
import recruit.model.dts.DtsRawDataItem;

@Component
public class TreeDtsUtil {
  final int MAX_LEVEL = 10;

  public static class RegionDtsMapItem extends DtsItemOneLevel {
    private LinkedHashMap<String, RegionDtsMapItem> childrenMap;

    public LinkedHashMap<String, RegionDtsMapItem> getChildrenMap() {
      return childrenMap;
    }

    public void setChildren(LinkedHashMap<String, RegionDtsMapItem> childrenMap) {
      this.childrenMap = childrenMap;
    }
  }

  public static List<DtsItem> createDtsByListData(List<DtsRawDataItem> rawItems, Integer maxLevel)
  throws IllegalAccessException, InvocationTargetException {
    TreeDtsUtil treeDtsUtil = new TreeDtsUtil();

    RegionDtsMapItem root = new RegionDtsMapItem();

    RegionDtsMapItem node;
    for (DtsRawDataItem item : rawItems) {
      node = root;
      for (int i = 1; i < treeDtsUtil.MAX_LEVEL; i++) {

        Method getValue = null;
        Method getName = null;
        try {
          getValue = item.getClass().getMethod("getL" + i + "Value");
          getName = item.getClass().getMethod("getL" + i + "Name");
        } catch (NoSuchMethodException err) {
          // System.out.println("报错了表示没这样的值");
          break;
        }

        if (getValue == null) {
          break;
        }

        String value = (String)getValue.invoke(item);
        String name = (String)getName.invoke(item);

        treeDtsUtil.addChildren(value, name, node);

        if (maxLevel != null && i >= maxLevel) {
          break;
        }

        if (node.getChildrenMap() == null) {
          break;
        }

        node = node.getChildrenMap().get(value);
      }
    }

    return treeDtsUtil.convertLinkedMap2List(root.getChildrenMap());
  }

  private void addChildren(String childValue, String childName, RegionDtsMapItem map) {
    if (childValue == null) {
      return;
    }

    LinkedHashMap<String, RegionDtsMapItem> childrenMap = map.getChildrenMap();
    childrenMap = childrenMap == null ? new LinkedHashMap<>() : childrenMap;
    if (childrenMap.get(childValue) == null) {
      RegionDtsMapItem child = new RegionDtsMapItem();
      child.setName(childName);
      child.setValue(childValue);
      childrenMap.put(childValue, child);
    }
    map.setChildren(childrenMap);
  }

  private List<DtsItem> convertLinkedMap2List(LinkedHashMap<String, RegionDtsMapItem> target) {
    List<DtsItem> result = new ArrayList<>();

    for (RegionDtsMapItem item : target.values()) {
      DtsItem resItem = new DtsItem(item.getName(), item.getValue());
      if (item.childrenMap != null) {
        List<DtsItem> res = convertLinkedMap2List(item.childrenMap);
        resItem.setChildren(res);
      }

      result.add(resItem);
    }

    return result;
  }
}
