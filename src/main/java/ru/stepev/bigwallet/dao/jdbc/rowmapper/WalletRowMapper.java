package ru.stepev.bigwallet.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.ExpenseDao;
import ru.stepev.bigwallet.model.Wallet;

@Component
public class WalletRowMapper implements RowMapper<Wallet> {
	
	private ExpenseDao expenseDao;
	
	public WalletRowMapper(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	@Override
	public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
		Wallet wallet = new Wallet();
		wallet.setId(rs.getInt("id"));
		wallet.setOwnerId(rs.getInt("owner_id"));
		wallet.setCurrencyId(rs.getInt("currency_id"));
		wallet.setCreateDate(rs.getObject("create_date", LocalDate.class));
		wallet.setCreateTime(rs.getObject("create_time", LocalTime.class));
		wallet.setExpenses(expenseDao.findByWalletId(rs.getInt("id")));
		return wallet;
	}
}
