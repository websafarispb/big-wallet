package ru.stepev.bigwallet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.stepev.bigwallet.model.Expense;
import ru.stepev.bigwallet.model.Group;
import ru.stepev.bigwallet.model.Wallet;
import ru.stepev.bigwallet.service.ExpenseService;
import ru.stepev.bigwallet.service.GroupService;
import ru.stepev.bigwallet.service.WalletService;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

	private ExpenseService expenseService;
	private GroupService groupService;
	private WalletService walletService;

	public ExpenseController(ExpenseService expenseService, GroupService groupService, WalletService walletService) {
		this.expenseService = expenseService;
		this.groupService = groupService;
		this.walletService = walletService;
	}

	@GetMapping
	public String getAllExpenses(Model model) {
		List<Expense> expenses = expenseService.getAll();
		model.addAttribute("expenses", expenses);
		return "expenses-page";
	}

	@GetMapping("{id}")
	public String getExpense(@PathVariable int id, Model model) {
		Expense expense = expenseService.getById(id).orElseThrow();
		Group group = groupService.getById(expense.getGroupId()).orElseThrow();
		model.addAttribute("expense", expense);
		model.addAttribute("group", group);
		System.out.println(expense);
		return "show/show-expense";
	}

	@GetMapping("/delete/{id}")
	public String deleteExpense(@PathVariable int id, Model model) {
		Expense expense = expenseService.getById(id).orElseThrow();
		expenseService.delete(expense);
		return "redirect:/expenses";
	}

	@GetMapping("/add")
	public String addExpense(Model model) {
		Expense expense = new Expense();
		List<Group> groups = groupService.getAll();
		List<Wallet> wallets = walletService.getAll();
		model.addAttribute("expense", expense);
		model.addAttribute("groups", groups);
		model.addAttribute("wallets", wallets);
		return "add/add-expense";
	}

	@PostMapping("/create")
	public String createExpense(@ModelAttribute Expense expense, Model model) {
		expenseService.add(expense);
		return "redirect:/expenses";
	}

	@GetMapping("/update/{id}")
	public String updateExpense(@PathVariable int id, Model model) {
		Expense expense = expenseService.getById(id).orElseThrow();
		List<Group> groups = groupService.getAll();
		List<Wallet> wallets = walletService.getAll();
		model.addAttribute("expense", expense);
		model.addAttribute("groups", groups);
		model.addAttribute("wallets", wallets);
		return "update/update-expense";
	}

	@PostMapping("/save")
	public String saveExpense(@ModelAttribute Expense expense, Model model) {
		expenseService.update(expense);
		return "redirect:/expenses";
	}
}
