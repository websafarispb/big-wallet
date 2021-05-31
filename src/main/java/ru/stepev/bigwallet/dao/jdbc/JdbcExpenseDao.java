package ru.stepev.bigwallet.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.ExpenseDao;
import ru.stepev.bigwallet.dao.jdbc.rowmapper.ExpenseRowMapper;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenCreatedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenDeletedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenUpdatedException;
import ru.stepev.bigwallet.model.Expense;

@Component
public class JdbcExpenseDao implements ExpenseDao {

	private static final String GET_ALL = "SELECT * FROM expenses";
	private static final String GET_BY_WALLETID = "SELECT * FROM expenses WHERE wallet_id = ?";
	private static final String GET_BY_ID = "SELECT * FROM expenses WHERE id = ?";
	private static final String CREATE_EXPENSE_QUERY = "INSERT INTO expenses (price, group_id, wallet_id, date, time) VALUES (?, ?, ?, ?, ?)";
	private static final String DELETE_EXPENSE_BY_ID = "DELETE FROM expenses WHERE id = ?";
	private static final String UPDATE_EXPENSE_BY_ID = "UPDATE expenses SET price = ? ,  group_id = ? , wallet_id = ? ,  date = ? ,  time = ? WHERE id = ?";

	private ExpenseRowMapper expenseRowMapper;
	private JdbcTemplate jdbcTemplate;

	public JdbcExpenseDao(ExpenseRowMapper expenseRowMapper, JdbcTemplate jdbcTemplate) {
		this.expenseRowMapper = expenseRowMapper;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Expense> findAll() {
		return jdbcTemplate.query(GET_ALL, expenseRowMapper);
	}

	@Override
	public List<Expense> findByWalletId(Integer walletId) {
		return jdbcTemplate.query(GET_BY_WALLETID, expenseRowMapper, walletId);
	}

	@Override
	public Optional<Expense> findById(int expenseId) {
		try {
			return Optional.of(jdbcTemplate.queryForObject(GET_BY_ID, expenseRowMapper, expenseId));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public void delete(Integer expenseId) {
		if (jdbcTemplate.update(DELETE_EXPENSE_BY_ID, expenseId) == 0) {
			throw new EntityCouldNotBeenDeletedException(
					String.format("Expense with Id %s could not been deleted", expenseId));
		}
	}

	@Override
	public void create(Expense expense) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(CREATE_EXPENSE_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, expense.getPrice());
			statement.setInt(2, expense.getGroupId());
			statement.setInt(3, expense.getWalletId());
			statement.setObject(4, expense.getDate());
			statement.setObject(5, expense.getTime());
			return statement;
		}, keyHolder) == 0) {
			throw new EntityCouldNotBeenCreatedException(
					String.format("Expense with price %s could not been created", expense.getPrice()));
		}
		expense.setId((int) keyHolder.getKeys().get("id"));
	}

	@Override
	public void update(Expense expense) {
		if (jdbcTemplate.update(UPDATE_EXPENSE_BY_ID, expense.getPrice(), expense.getGroupId(), expense.getWalletId(),
				expense.getDate(), expense.getTime(), expense.getId()) == 0) {
			throw new EntityCouldNotBeenUpdatedException(
					String.format("Expense with ID %s could not been updated", expense.getId()));
		}
	}
}
