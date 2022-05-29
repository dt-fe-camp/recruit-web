package recruit.utils;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String responseData(HttpServletResponse response, ResponseResult result) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            ObjectMapper mapper = new ObjectMapper();
            String dataString = mapper.writeValueAsString(result);
            response.getWriter().print(dataString);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String responseData(HttpServletResponse response, ResponseResult result, Boolean ignoreNull) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            ObjectMapper mapper = new ObjectMapper();
            if (ignoreNull.equals(true)) {
                mapper.setSerializationInclusion(Include.NON_NULL);
            }
            String dataString = mapper.writeValueAsString(result);
            response.getWriter().print(dataString);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}