package com.imran.springsecurity.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.imran.springsecurity.dto.User;

public class UserDetailsImpl implements UserDetails
{
	private User user;
	
	public UserDetailsImpl(User user)
	{
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		// TODO Auto-generated method stub
		System.out.println("user role : " + user.getRole());
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		System.out.println("password : " + user.getPassword());
		return user.getPassword();
	}

	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		System.out.println("username : " + user.getUserName());
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return true;
	}

}
