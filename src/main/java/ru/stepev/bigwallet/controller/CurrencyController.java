package ru.stepev.bigwallet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.stepev.bigwallet.model.Currency;
import ru.stepev.bigwallet.service.CurrencyService;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {
	
	private CurrencyService currencyService;

	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	@GetMapping
	public String getAllCurrencies(Model model) {
		List<Currency> currencies = currencyService.getAll();
		model.addAttribute("currencies", currencies);
		return "currencies-page";
	}
	
	@GetMapping("{id}")
	public String getCurrency(@PathVariable int id, Model model) {
		Currency currency = currencyService.getById(id).orElseThrow();
		model.addAttribute("currency", currency);
		return "show/show-currency";
	}
	
	@GetMapping("/add")
	public String addCurrency(Model model) {
		Currency currency = new Currency();
		model.addAttribute("currency", currency);
		return "add/add-currency";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCurrency(@PathVariable int id, Model model) {
		Currency currency = currencyService.getById(id).orElseThrow();
		currencyService.delete(currency);
		return "redirect:/currencies";
	}
	
	@GetMapping("/update/{id}")
	public String updateCurrency(@PathVariable int id, Model model) {
		Currency currency = currencyService.getById(id).orElseThrow();
		model.addAttribute("currency", currency);
		return "update/update-currency";
	}
	
	@PostMapping("/save")
	public String saveCurrency(@ModelAttribute Currency currency, Model model) {
		currencyService.update(currency);
		return "redirect:/currencies";
	}
	
	@PostMapping("/create")
	public String createCurrency(@ModelAttribute Currency currency, Model model) {
		currencyService.add(currency);
		return "redirect:/currencies";
	}
	
}
