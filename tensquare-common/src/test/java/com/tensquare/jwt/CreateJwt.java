package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-10-28 14:40
 */
public class CreateJwt {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "itecast");
        System.out.println(builder.compact());
    }
}
