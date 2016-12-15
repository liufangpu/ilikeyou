package site.jjilikeyou.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import site.jjilikeyou.www.service.DataService;

@Controller
public class DataController {
	@Autowired
	private DataService dataService;
	
	
	@RequestMapping(value="/data",method=RequestMethod.GET)
	
	public String data(){
		
		
		return "data/data";
	}
}
