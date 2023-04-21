
package com.example.webreact.entity.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.webreact.entity.basecat.UserInfo;

import java.util.Date;

/**
 * defaultParamDescription
 * @author lucky
 * @date 2023/4/17 13:28
 */
public class TokenUtil {


    //token到期时间60s
    private static final long EXPIRE_TIME= 60000*1000;
    //密钥盐
    private static final String TOKEN_SECRET="123456qwertyuiop789";

    /**
     * 创建一个token
     * @param user
     * @return
     */
    public static String sign(UserInfo user){
        String token=null;
        try {
            Date expireAt=new Date(System.currentTimeMillis()+EXPIRE_TIME);
            token = JWT.create()
                    //发行人
                    .withIssuer("auth0")
                    //存放数据
                    .withClaim("username",user.getUsername())
                    .withClaim("password",user.getPassword())
                    .withClaim("id",user.getId())
                    .withClaim("Email",user.getEmail())
                    //过期时间
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException|JWTCreationException ignored) {

        }
        return token;
    }
    /**
     * 对token进行验证
     * @param token
     * @return
     */
    public static Boolean verify(String token){
        try {
            //创建token验证器
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + TokenUtil.getUserName(token));
            System.out.println("id: " + TokenUtil.getUserID(token));
            System.out.println("过期时间：    " + decodedJWT.getExpiresAt());
        } catch (IllegalArgumentException |JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }

    /**
     * 获取用户名
     */
    public static String getUserName(String token){
        try{
            DecodedJWT jwt=JWT.decode(token);
            return  jwt.getClaim("username").asString();
        }catch (JWTDecodeException e)
        {
            return null;
        }
    }
    /**
     * 获取用户id
     */
    public static Integer getUserID(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            return jwt.getClaim("id").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}