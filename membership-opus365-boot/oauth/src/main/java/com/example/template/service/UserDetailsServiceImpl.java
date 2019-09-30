package com.example.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;
import com.example.template.entity.User;
import com.example.template.repository.UserRepository;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;


@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private WebApplicationContext applicationContext;
	private UserRepository repository;

	@PostConstruct
	public void completeSetup() {
		repository = applicationContext.getBean(UserRepository.class);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		log.info("UserDetailsServiceImpl.loadUserByUserId :::: {}",userName);
		
		User user = repository.findByUsername(userName);
		
		if(ObjectUtils.isEmpty(user)) {
			throw new UsernameNotFoundException("Invalid resource owner, please check resource owner info !");
		}
		
		user.setAuthorities(AuthorityUtils.createAuthorityList(String.valueOf(user.getRole())));
		
		return user;
	}

}
