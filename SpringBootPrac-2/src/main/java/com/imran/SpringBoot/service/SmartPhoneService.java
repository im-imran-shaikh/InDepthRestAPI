package com.imran.SpringBoot.service;

import java.util.List;

import com.imran.SpringBoot.dto.Smartphone;

public interface SmartPhoneService
{
	String addSmartphone(Smartphone smartphone);
	void deleteSmartPhone(int id);
	List<Smartphone> findAllSmartPhone();
}
