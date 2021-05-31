package ru.stepev.bigwallet.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.WalletDao;
import ru.stepev.bigwallet.dao.jdbc.rowmapper.WalletRowMapper;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenCreatedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenDeletedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenUpdatedException;
import ru.stepev.bigwallet.model.Wallet;

@Component
public class JdbcWalletDao implements WalletDao {

	private static final String GET_ALL = "SELECT * FROM wallets";
	private static final String FIND_WALLET_BY_ID = "SELECT * FROM wallets WHERE id = ?";
	private static final String CREATE_WALLET_QUERY = "INSERT INTO wallets (owner_id, currency_id, create_date, create_time) VALUES (?, ?, ?, ?)";
	private static final String DELETE_WALLET_BY_ID = "DELETE FROM wallets WHERE id = ?";
	private static final String UPDATE_WALLET_BY_ID = "UPDATE wallets SET currentcy_id = ?, owner_id = ?, create_date = ?, create_time = ?";
	private static final String GET_ALL_BY_OWNER_ID = "SELECT * FROM wallets WHERE owner_id = ?";

	private WalletRowMapper walletRowMapper;
	private JdbcTemplate jdbcTemplate;

	public JdbcWalletDao(WalletRowMapper walletRowMapper, JdbcTemplate jdbcTemplate) {
		this.walletRowMapper = walletRowMapper;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Wallet> findAll() {
		return jdbcTemplate.query(GET_ALL, walletRowMapper);
	}

	@Override
	public Optional<Wallet> findById(int id) {
		try {
			return Optional.of(jdbcTemplate.queryForObject(FIND_WALLET_BY_ID, walletRowMapper, id));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public void create(Wallet wallet) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(CREATE_WALLET_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, wallet.getOwnerId());
			statement.setInt(2, wallet.getCurrencyId());
			statement.setObject(3, wallet.getCreateDate());
			statement.setObject(4, wallet.getCreateTime());
			return statement;
		}, keyHolder) == 0) {
			throw new EntityCouldNotBeenCreatedException(
					String.format("Wallet with CurrencyId %s could not been created", wallet.getCurrencyId()));
		}
		wallet.setId((int) keyHolder.getKeys().get("id"));
	}

	@Override
	public void delete(Integer walletId) {
		if (jdbcTemplate.update(DELETE_WALLET_BY_ID, walletId) == 0) {
			throw new EntityCouldNotBeenDeletedException(
					String.format("Wallet with ID %s could not been deleted", walletId));
		}
	}

	@Override
	public void update(Wallet wallet) {
		if (jdbcTemplate.update(UPDATE_WALLET_BY_ID, wallet.getCurrencyId(), wallet.getOwnerId(),
				wallet.getCreateDate(), wallet.getCreateTime()) == 0) {
			throw new EntityCouldNotBeenUpdatedException(
					String.format("Wallet with ID %s could not been updated", wallet.getId()));
		}
	}

	@Override
	public List<Wallet> findAllByOwnerId(Integer ownerId) {
		return jdbcTemplate.query(GET_ALL_BY_OWNER_ID, walletRowMapper, ownerId);
	}
}
