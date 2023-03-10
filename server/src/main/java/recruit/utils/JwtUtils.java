/**
 * @file JwtUtils.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.net.HttpRetryException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtils {

    // 有效期为: 1分钟就过期
    public static final Long JWT_EXPIRE = 60 * 1000L;

    /*
    * 设置秘钥明文
    */
    private static String authSecret;

    public static void setAuthSecret(String key) {
        JwtUtils.authSecret = key;
    }

    public static String getAuthSecret() {
        return JwtUtils.authSecret;
    }

    /**
     * 生成token字符串的方法
     */
    public static String getJwtToken(String name, String nickname) {
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")

                .setSubject("afcfzf-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRE))

                .claim("name", name) // 设置token主体部分 ，存储用户信息
                .claim("nickname", nickname)

                .signWith(SignatureAlgorithm.HS256, authSecret)
                .compact();

        return JwtToken;
    }

     /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(!StringUtils.hasLength(jwtToken)) {
            return false;
        }

        try {
            Jwts.parser().setSigningKey(authSecret).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

       /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(ObjectUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(authSecret).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

        /**
     * 根据token字符串获取会员id
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        if (!checkToken(request)){
           throw new RuntimeException();
        }
        String jwtToken = request.getHeader("token");
        if(ObjectUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }

}