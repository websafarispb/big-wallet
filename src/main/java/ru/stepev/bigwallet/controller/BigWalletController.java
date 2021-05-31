package ru.stepev.bigwallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BigWalletController {

	@RequestMapping("/")
	public String demoPage(Model model) {
		return "index";
	}
}
