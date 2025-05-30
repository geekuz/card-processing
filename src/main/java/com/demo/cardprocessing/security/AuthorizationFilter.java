package com.demo.cardprocessing.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final Environment environment;

    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               Environment environment) {
        super(authenticationManager);
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));

        if (authorizationHeader == null
                || !authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String authorizationHeader = req.getHeader(environment.getProperty("authorization.token.header.name"));

        if (authorizationHeader == null) {
            return null;
        }

        String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix"), "").trim();
        String tokenSecret = environment.getProperty("token.secret");

        if (tokenSecret == null) return null;

//        byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());
//        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
//
//        JwtParser parser = Jwts.parser()
//                .verifyWith(secretKey)
//                .build();
//
//        Claims claims = parser.parseSignedClaims(token).getPayload();
//        String userId = (String) claims.get("sub");

        JwtClaimsParser jwtClaimsParser = new JwtClaimsParser(token, tokenSecret);
        String userId = jwtClaimsParser.getJwtSubject();
        if (userId == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userId, null, jwtClaimsParser.getUserAuthorities());

    }


}
