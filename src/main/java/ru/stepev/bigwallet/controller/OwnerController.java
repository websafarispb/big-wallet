package ru.stepev.bigwallet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.stepev.bigwallet.model.Owner;
import ru.stepev.bigwallet.model.Wallet;
import ru.stepev.bigwallet.service.OwnerService;
import ru.stepev.bigwallet.service.WalletService;

@Controller
@RequestMapping("/owners")
public class OwnerController {

	private OwnerService ownerService;
	private WalletService walletService;

	public OwnerController(OwnerService ownerService, WalletService walletService) {
		this.ownerService = ownerService;
		this.walletService = walletService;
	}

	@GetMapping
	public String showAllOwners(Model model) {
		List<Owner> owners = ownerService.getAll();
		model.addAttribute("owners", owners);
		return "owners-page";
	}

	@GetMapping("{id}")
	public String getOwner(@PathVariable int id, Model model) {
		Owner owner = ownerService.getById(id).orElseThrow();
		List<Wallet> wallets = walletService.getAllByOwnerId(owner.getId());
		model.addAttribute("owner", owner);
		model.addAttribute("wallets", wallets);
		return "show/show-owner";
	}

	@GetMapping("/delete/{id}")
	public String deleteOwner(@PathVariable int id, Model model) {
		Owner owner = ownerService.getById(id).orElseThrow();
		ownerService.delete(owner);
		return "redirect:/owners";
	}

	@GetMapping("/update/{id}")
	public String updateOwner(@PathVariable int id, Model model) {
		Owner owner = ownerService.getById(id).orElseThrow();
		model.addAttribute("owner", owner);
		return "update/update-owner";
	}

	@GetMapping("/add")
	public String addGroup(Model model) {
		Owner owner = new Owner();
		model.addAttribute("owner", owner);
		return "add/add-owner";
	}

	@PostMapping("/create")
	public String createOwner(@ModelAttribute Owner owner, Model model) {
		ownerService.add(owner);
		return "redirect:/owners";
	}

	@PostMapping("/save")
	public String saveGroup(@ModelAttribute Owner owner, Model model) {
		ownerService.update(owner);
		return "redirect:/owners";
	}
}