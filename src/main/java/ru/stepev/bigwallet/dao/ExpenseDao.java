package ru.stepev.bigwallet.dao;

import java.util.List;
import java.util.Optional;

import ru.stepev.bigwallet.model.Expense;

public interface ExpenseDao {
	
	public List <Expense> findAll();
	public List <Expense> findByWalletId(Integer walletId);
	public Optional<Expense> findById(int expenseId);
	public void delete(Integer id);
	public void create(Expense group);
	public void update(Expense expense);
}
