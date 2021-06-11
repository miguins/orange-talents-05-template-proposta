package br.com.zupedu.lucasmiguins.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/**/propostas").hasAuthority("SCOPE_propostas:write")
                .antMatchers(HttpMethod.POST,"/**/biometrias/**").hasAuthority("SCOPE_propostas:write")
                .antMatchers(HttpMethod.POST,"/**/bloqueios/**").hasAuthority("SCOPE_propostas:write")
                .antMatchers(HttpMethod.POST,"/**/viagens/**").hasAuthority("SCOPE_propostas:write")
                .antMatchers(HttpMethod.POST,"/**/carteiras/**").hasAuthority("SCOPE_propostas:write")
                .antMatchers(HttpMethod.GET,"/**/propostas/**").hasAuthority("SCOPE_propostas:read")
                .antMatchers(HttpMethod.GET,"/actuator/prometheus").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        ;
    }
}
