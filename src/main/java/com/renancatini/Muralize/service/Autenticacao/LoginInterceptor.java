package com.renancatini.Muralize.service.Autenticacao;


import com.renancatini.Muralize.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(CookieService.getCookie(request,"usuarioId") != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String nome = CookieService.getCookie(request, "nomeUsuario");
            String username = CookieService.getCookie(request, "usernameUsuario");
            String id = CookieService.getCookie(request, "usuarioId");

            modelAndView.addObject("nomeUsuario", nome);
            modelAndView.addObject("usernameUsuario", username);
            modelAndView.addObject("usuarioId", id);
        }
    }

}
