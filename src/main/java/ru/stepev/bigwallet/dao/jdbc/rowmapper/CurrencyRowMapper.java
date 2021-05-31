package ru.stepev.bigwallet.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.model.Currency;

@Component
public class CurrencyRowMapper implements RowMapper<Currency> {

	@Override
	public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Currency(rs.getInt("id"), rs.getString("name"));
	}
}
