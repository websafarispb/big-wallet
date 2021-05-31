package ru.stepev.bigwallet.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.model.Expense;

@Component
public class ExpenseRowMapper implements RowMapper<Expense>{

	@Override
	public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {	
		Expense expense = new Expense();
		expense.setId(rs.getInt("id"));
		expense.setPrice(rs.getInt("price"));
		expense.setGroupId(rs.getInt("group_id"));
		expense.setWalletId(rs.getInt("wallet_id"));
		expense.setDate(rs.getObject("date", LocalDate.class));
		expense.setTime(rs.getObject("time", LocalTime.class));
		return expense;
	}
}
