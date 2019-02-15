package com.xzst.modi.app.gCommon;

import com.xzst.modi.app.dModel.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private Logger log = LoggerFactory.getLogger(JWTUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    //私密
    @Value("${JWT.secret}")
    private String secret;

    //到期时间 分钟
    @Value("${JWT.expiration}")
    private Long expiration;
    @Value("${JWT.header}")
    private String tokenHeader;

    @Value("${JWT.tokenHead}")
    private String tokenHead;


    public String getUsernameFromToken(String token) throws Exception{
        String username;
        final Claims claims = getClaimsFromToken(token);
        username = claims.getSubject();
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) throws Exception{
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    private Date generateExpirationDate() {
        Date date = new Date(System.currentTimeMillis() + expiration *60* 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(date));
        return date;
    }

    /**
     * 比较超时
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        boolean flag = expiration.before(new Date());
        System.out.println("time flag ==="+flag);
        return flag;
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        boolean flag = (lastPasswordReset != null && created.before(lastPasswordReset));
        System.out.println("isCreatedBeforeLastPasswordReset--"+flag);
        return flag;
    }

    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getYhbh());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证token的超时时间
     * @param token
     * @return
     */
    public Boolean validateToken(String token) {
        //final Date expiration = getExpirationDateFromToken(token);
        return !isTokenExpired(token);
    }

    public String getYhbhFromToken(HttpServletRequest request){
        String authHeader = request.getHeader(this.tokenHeader);
        log.debug("recvtoken:"+authHeader);
        String yhbh = null;
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            try {
                yhbh = getUsernameFromToken(authToken);
            } catch (Exception e) {

            }
        }
        return yhbh;
    }

    public String getYhbhFromToken(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        String authHeader = request.getHeader(this.tokenHeader);

        log.debug("recvtoken:"+authHeader);
        String yhbh = null;
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            try {
                yhbh = getUsernameFromToken(authToken);
            } catch (Exception e) {

            }
        }
        return yhbh;
    }
}

