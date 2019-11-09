package com.imran.SpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.imran.SpringBoot.dao.SmartphoneRepositry;
import com.imran.SpringBoot.dto.Smartphone;

public class SmartPhoneServiceImpl implements SmartPhoneService
{
	@Autowired
	private SmartphoneRepositry smartphoneRepo;

	@Override
	public String addSmartphone(Smartphone smartphone)
	{
		Smartphone smartPhone = smartphoneRepo.save(smartphone);
		
		if (smartPhone.getId() > 0)
			return smartPhone.getId() + " is sucessfully saved";
		else
			return "something went wrong please try again";
		
		
	}

	@Override
	public void deleteSmartPhone(int id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<Smartphone> findAllSmartPhone()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
