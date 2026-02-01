package com.renancatini.Muralize.service.Autenticacao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/login",
                        "/logar",
                        "/registrar",        // ADICIONE ESTA LINHA (para abrir a tela)
                        "/registrar/criar",  // ADICIONE ESTA LINHA (para conseguir salvar)
                        "/error",
                        "/vendor/**",
                        "/js/**",
                        "/favicon.ico",
                        "/css/**"
                );
    }
}
