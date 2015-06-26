package rematricula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	
	@RequestMapping( value = "/404", method = RequestMethod.GET )
	public String notFound( Model model){
		return "404";
	}
	
	@RequestMapping( value = "/500", method = RequestMethod.GET )
	public String serverError( Model model){
		return "500";
	}
}