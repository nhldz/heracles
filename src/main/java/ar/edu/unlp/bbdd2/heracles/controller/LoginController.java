package ar.edu.unlp.bbdd2.heracles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlp.bbdd2.heracles.util.RootUtil;

@Controller
public class LoginController {

	@Autowired
	private RootUtil rootUtil;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		this.rootUtil.rootLoad();
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@RequestMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

}
