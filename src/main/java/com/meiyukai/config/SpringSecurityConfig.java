package com.meiyukai.config;

import com.meiyukai.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true , prePostEnabled = true,jsr250Enabled = true)
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Resource(name = "sellerService")
    private SellerService sellerService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sellerService)
                .passwordEncoder(passwordEncoder());

       /* auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("xiaomei")
                .password(passwordEncoder().encode("8888"))
                .roles("ADMIN");*/

    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**" , "/assets/**" , "/login/**").permitAll()
                .antMatchers("/seller/**").hasRole("ADMIN")
                .antMatchers("/buyer/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .anonymous();

        http
                .formLogin()
                .loginPage("/seller/login/toLogin").permitAll()
                 .loginProcessingUrl("/login")    // 由于springboot 自动补上项目路径导致与前端的action 路径不一致！
                .defaultSuccessUrl("/seller/order/list")
                .failureUrl("/seller/login/toFailure")
                .and()
                .logout().logoutSuccessUrl("/seller/login/toLogin");

    }


}
