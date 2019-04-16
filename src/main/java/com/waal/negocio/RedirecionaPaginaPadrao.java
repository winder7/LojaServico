package com.waal.negocio;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Autor Winder Rezende
 * @Data  15/04/2019
 */
public class RedirecionaPaginaPadrao implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMINSTRATOR")) {
            response.sendRedirect("/LojaServico/index.xhtml");
        }else if (roles.contains("ROLE_CLIENTE")) {
            response.sendRedirect("/LojaServico/index.xhtml");
        }
    }
}
