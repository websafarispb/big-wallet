package ru.stepev.bigwallet.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.CurrencyDao;
import ru.stepev.bigwallet.dao.jdbc.rowmapper.CurrencyRowMapper;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenCreatedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenDeletedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenUpdatedException;
import ru.stepev.bigwallet.model.Currency;

@Component
public class JdbcCurrencyDao implements CurrencyDao {

	private static final String GET_ALL = "SELECT * FROM currency";
	private static final String GET_BY_ID = "SELECT * FROM currency WHERE id = ?";
	private static final String GET_BY_NAME = "SELECT * FROM currency WHERE name = ?";
	private static final String CREATE_CURRENCY_QUERY = "INSERT INTO currency (name) VALUES (?)";
	private static final String UPDATE_BY_CURRENCY_ID = "UPDATE currency SET name = ? WHERE id = ?";
	private static final String DELETE_CURRENCY_BY_ID = "DELETE FROM currency WHERE id = ?";

	private CurrencyRowMapper currencyRowMapper;
	private JdbcTemplate jdbcTemplate;

	public JdbcCurrencyDao(CurrencyRowMapper currencyRowMapper, JdbcTemplate jdbcTemplate) {
		this.currencyRowMapper = currencyRowMapper;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Currency> findAll() {
		return jdbcTemplate.query(GET_ALL, currencyRowMapper);
	}

	@Override
	public Optional<Currency> findById(int currencyId) {
		try {
			return Optional.of(jdbcTemplate.queryForObject(GET_BY_ID, currencyRowMapper, currencyId));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	@Override
	public void create(Currency currency) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(CREATE_CURRENCY_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, currency.getName());
			return statement;
		}, keyHolder) == 0) {
			throw new EntityCouldNotBeenCreatedException(
					String.format("Currency with name %s could not been created", currency.getName()));
		}
		currency.setId((int) (keyHolder.getKeys().get("id")));	
	}

	@Override
	public Optional<Currency> findByName(String currencyName) {
		try {
			return Optional.of(jdbcTemplate.queryForObject(GET_BY_NAME, currencyRowMapper, currencyName));
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	@Override
	public void update(Currency currency) {
		if (jdbcTemplate.update(UPDATE_BY_CURRENCY_ID, currency.getName(), currency.getId()) == 0) {
			throw new EntityCouldNotBeenUpdatedException(
					String.format("Currency with name %s could not been updated", currency.getName()));
		}
	}

	@Override
	public void delete(Integer currencyId) {
		if (jdbcTemplate.update(DELETE_CURRENCY_BY_ID, currencyId) == 0) {
			throw new EntityCouldNotBeenDeletedException(
					String.format("Currency with name %s could not been deleted", currencyId));
		}
	}
}
