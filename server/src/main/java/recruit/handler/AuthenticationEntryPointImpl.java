/**
 * @file AuthenticationEntryPointImpl.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import recruit.utils.ResponseResult;
import recruit.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败,请检查后重试");
        String callbackUri = request.getRequestURI();
        String redirectUrl = "/recruit/auth";
        if (callbackUri != null && !callbackUri.equals("")) {
            URLEncoder.encode(callbackUri, "utf-8");
            redirectUrl += ("?callback=" + callbackUri);
        }
        response.sendRedirect(redirectUrl);

        // String json = new ObjectMapper().writeValueAsString(result);
        // //处理异常
        // WebUtils.renderString(response,json);
    }
}
