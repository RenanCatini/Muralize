package com.renancatini.Muralize.service.Autenticacao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") // Aplica a todas as rotas
                .excludePathPatterns( // Exceto arquivos que não são páginas
                        "/vendor/**",
                        "/js/**",
                        "/css/**",
                        "/assets/**",
                        "/favicon.ico",
                        "/error"
                );
    }
}
