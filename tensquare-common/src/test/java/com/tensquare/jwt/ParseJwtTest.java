package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-10-28 15:16
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("itecast").parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1NzIyNDY5NzZ9.wQwEUiwBASzAR7g91-8d5FWkwU6gFHvtBGHMie-I9-I")
                .getBody();
        System.out.println("用户id：" + claims.getId());
        System.out.println("用户名：" + claims.getSubject());
        System.out.println("登录时间：" + new SimpleDateFormat("yyyy-MM-dd").format(claims.getIssuedAt()) );
    }
}
