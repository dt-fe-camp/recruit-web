package recruit.utils;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Result implements Serializable {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private Integer code;

  private String message;

  private Object data;

  public static Result build(Integer code, String message, Object data) {
    return new Result(code, message, data);
  }

  public static Result success(Object data) {
    return new Result(data);
  }

  public static Result success() {
    return new Result(null);
  }

  public static Result fail(Integer code, String message) {
    return new Result(code, message, null);
  }

  public Result() {

  }

  public static Result build(Integer code, String message) {
    return new Result(code, message, null);
  }

  public Result(Integer code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public Result(Object data) {
    this.code = 0;
    this.message = "success";
    this.data = data;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  /**
   * 将json结果集转化为Result对象
   *
   * @param jsonData
   *                 json数据 传的是Result的对象的Json字符串
   * @param clazz
   *                 TaotaoResult中的object类型
   * @return
   */
  public static Result formatToPojo(String jsonData, Class<?> clazz) {
    try {
      if (clazz == null) {
        return MAPPER.readValue(jsonData, Result.class);
      }
      JsonNode jsonNode = MAPPER.readTree(jsonData);
      JsonNode data = jsonNode.get("data");
      Object obj = null;
      if (clazz != null) {
        if (data.isObject()) {
          obj = MAPPER.readValue(data.traverse(), clazz);
        } else if (data.isTextual()) {
          obj = MAPPER.readValue(data.asText(), clazz);
        }
      }
      return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 没有object对象的转化
   *
   * @param json
   * @return
   */
  public static Result format(String json) {
    try {
      return MAPPER.readValue(json, Result.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
	 * Object是集合转化
	 *
	 * @param jsonData  传的是Result的对象的Json字符串
	 *            json数据
	 * @param clazz
	 *            集合中的类型
	 * @return
	 */
	public static Result formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}
}
