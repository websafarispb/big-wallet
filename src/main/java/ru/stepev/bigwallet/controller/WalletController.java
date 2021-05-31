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
import ru.stepev.bigwallet.model.Owner;
import ru.stepev.bigwallet.model.Wallet;
import ru.stepev.bigwallet.service.CurrencyService;
import ru.stepev.bigwallet.service.OwnerService;
import ru.stepev.bigwallet.service.WalletService;

@Controller
@RequestMapping("/wallets")
public class WalletController {

	private WalletService walletService;
	private OwnerService ownerService;
	private CurrencyService currencyService;

	public WalletController(WalletService walletService, OwnerService ownerService, CurrencyService currencyService) {
		this.walletService = walletService;
		this.ownerService = ownerService;
		this.currencyService = currencyService;
	}

	@GetMapping
	public String showAllWallets(Model model) {
		List<Wallet> wallets = walletService.getAll();
		model.addAttribute("wallets", wallets);
		return "wallets-page";
	}

	@GetMapping("{id}")
	public String getWallet(@PathVariable int id, Model model) {
		Wallet wallet = walletService.getById(id).orElseThrow();
		Owner owner = ownerService.getById(wallet.getOwnerId()).orElseThrow();
		model.addAttribute("wallet", wallet);
		model.addAttribute("owner", owner);
		return "show/show-wallet";
	}

	@GetMapping("/add")
	public String addWallet(Model model) {
		Wallet wallet = new Wallet();
		List<Currency> currencies = currencyService.getAll();
		List<Owner> owners = ownerService.getAll();
		model.addAttribute("wallet", wallet);
		model.addAttribute("currencies", currencies);
		model.addAttribute("owners", owners);
		return "add/add-wallet";
	}

	@PostMapping("/create")
	public String createWallet(@ModelAttribute Wallet wallet, Model model) {
		walletService.add(wallet);
		return "redirect:/wallets";
	}

	@GetMapping("/delete/{id}")
	public String deleteWallet(@PathVariable int id) {
		Wallet wallet = walletService.getById(id).orElseThrow();
		walletService.delete(wallet);
		return "redirect:/wallets";
	}

	@GetMapping("/update/{id}")
	public String updateWallet(@PathVariable int id, Model model) {
		Wallet wallet = walletService.getById(id).orElseThrow();
		Owner ow = ownerService.getById(wallet.getOwnerId()).orElseThrow();
		List<Currency> currencies = currencyService.getAll();
		List<Owner> owners = ownerService.getAll();
		model.addAttribute("wallet", wallet);
		model.addAttribute("currencies", currencies);
		model.addAttribute("owners", owners);
		model.addAttribute("ow", ow);
		return "update/update-wallet";
	}

	@PostMapping("/save")
	public String saveWallet(@ModelAttribute Wallet wallet, Model model) {
		walletService.update(wallet);
		return "redirect:/wallets";
	}
}