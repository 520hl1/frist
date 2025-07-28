package com.emergency.drill.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    // 使用 JJWT 工具类生成 HS512 所需的 512 位（64 字节）密钥
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRE_TIME = 86400000; // 24小时有效期（毫秒）

    // 生成 Token（使用 Key 对象签名）
    public static String generateToken(Integer userId, String role) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(KEY) // 直接传入 Key 对象（无需手动处理密钥字符串）
                .compact();
    }

    // 校验 Token
    public static boolean verifyToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
            return false;
        }
    }

    // 获取用户 ID
    public static Integer getUserId(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
            return Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            return null;
        }
    }

    // 获取用户角色
    public static String getRole(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
            return (String) claims.get("role");
        } catch (Exception e) {
            return null;
        }
    }
}