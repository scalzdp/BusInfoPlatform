package com.bip.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {

	@RequestMapping(value="/captchaError",method=RequestMethod.GET)
	private String getCaptchaError(Model model){
		model.addAttribute("pagename", "message/captcha.jsp");
		return "index";
	}
}
