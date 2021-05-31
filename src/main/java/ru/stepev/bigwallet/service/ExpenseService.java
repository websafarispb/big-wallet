package ru.stepev.bigwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.ExpenseDao;
import ru.stepev.bigwallet.exeption.EntityNotFoundException;
import ru.stepev.bigwallet.model.Expense;

@Component
public class ExpenseService {

	private ExpenseDao expenseDao;

	public ExpenseService(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	public List<Expense> getAll() {
		return expenseDao.findAll();
	}

	public Optional<Expense> getById(int expenseId) {
		return expenseDao.findById(expenseId);
	}

	public void delete(Expense expense) {
		verifyExpenseExist(expense);
		expenseDao.delete(expense.getId());
	}

	public void add(Expense expense) {
		verifyExpenseIsNotExist(expense);
		expenseDao.create(expense);
	}

	public void verifyExpenseExist(Expense expense) {
		if (expenseDao.findById(expense.getId()).isEmpty()) {
			throw new EntityNotFoundException(String.format("Expense with ID %s doesn't exist", expense.getId()));
		}
	}

	private void verifyExpenseIsNotExist(Expense expense) {
		if (expenseDao.findById(expense.getId()).isPresent()) {
			throw new EntityNotFoundException(String.format("Expense with ID %s already exist", expense.getId()));
		}
	}

	public void update(Expense expense) {
		verifyExpenseExist(expense);
		expenseDao.update(expense);
	}
}
