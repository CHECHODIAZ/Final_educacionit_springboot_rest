package ar.com.educacionit.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ar.com.educacionit.services.UserDetailSericeImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter{

	//userdetail
	@Autowired
	UserDetailSericeImpl userDetailServiceImpl;
	
	//jwt
	@Autowired
	JwtEntryPoint jtwEntryPoint;
	
	//bean
	@Bean
	public JwtTokenFIlter jwtTokenFilter() {
		return new JwtTokenFIlter(); 
	}
	
	//password encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//configure(Authenticationmanager)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailServiceImpl).passwordEncoder(this.passwordEncoder());
	}
	
	//authentication manager
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
		
	//configure (httpsecurity)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/**").permitAll() //los get no estan securazados
			.antMatchers("/auth/**").permitAll()
			//habilitamos los recurso de swagger swagger
			.antMatchers("/v2/api-docs/**","/configuration/**","swagger/**","/webjars/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(this.jtwEntryPoint)
			.and()
			//sin estados
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
		http.addFilterBefore(this.jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
