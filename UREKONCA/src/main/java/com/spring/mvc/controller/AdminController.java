package com.spring.mvc.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.entity.CA;
import com.spring.service.CAService;
/**
 * 
 * @author Soumyadip Chowdhury
 * @github soumyadip007
 *
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	

	private CAService amsService;
	@Autowired   
	public AdminController(CAService obj)
	{
		this.amsService=obj;
	
	}


	@PostMapping("/add-ca")
	public String Saveca(@ModelAttribute("ca") CA ca) {

	
		amsService.save(ca);
	
		return "redirect:/admin/add-ca";
	}
	
	@GetMapping("/all-ca")
	public String Allca(Model theModel) {

		List<CA> list=amsService.findAll();
		
		theModel.addAttribute("allca",list);
		
		return "dashboard/caList";
	}

	
	@GetMapping("/update-ca")
	public String Updateca(@RequestParam("id") int theId,Model theModel) {

		CA ca=amsService.findById(theId);
		
		theModel.addAttribute("ca",ca);
		
		return "dashboard/addca";
	}
	
	@GetMapping("/delete-ca")
	public String Deleteca(@RequestParam("id") int theId) {

		amsService.deleteById(theId);
	
		return "redirect:/admin/all-ca";
	}


}
