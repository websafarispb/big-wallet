package ru.stepev.bigwallet.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.OwnerDao;
import ru.stepev.bigwallet.dao.jdbc.rowmapper.OwnerRowMapper;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenCreatedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenDeletedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenUpdatedException;
import ru.stepev.bigwallet.model.Owner;

@Component
public class JdbcOwnerDao implements OwnerDao {

	private static final String GET_ALL = "SELECT * FROM owners";
	private static final String FIND_OWNER_BY_WALLET_ID = "SELECT * FROM owners WHERE id = ?";
	private static final String FIND_OWNER_BY_ID = "SELECT * FROM owners WHERE id = ?";
	private static final String DELETE_OWNER_BY_ID = "DELETE FROM owners WHERE id = ?";
	private static final String CREATE_OWNER_QUERY = "INSERT INTO owners (first_name, last_name) VALUES (?, ?)";
	private static final String UPDATE_BY_OWNER_ID = "UPDATE owners SET first_name = ? , last_name = ? WHERE id = ?";

	private OwnerRowMapper ownerRowMapper;
	private JdbcTemplate jdbcTemplate;

	public JdbcOwnerDao(JdbcTemplate jdbcTemplate, OwnerRowMapper ownerRowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.ownerRowMapper = ownerRowMapper;
	}

	@Override
	public List<Owner> findAll() {
		return jdbcTemplate.query(GET_ALL, ownerRowMapper);
	}

	@Override
	public Optional<Owner> findByWalletId(int walletId) {
		try {
			return Optional.of(jdbcTemplate.queryForObject(FIND_OWNER_BY_WALLET_ID, ownerRowMapper, walletId));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Owner> findById(Integer ownerId) {
		try {
			return Optional.of(jdbcTemplate.queryForObject(FIND_OWNER_BY_ID, ownerRowMapper, ownerId));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public void delete(Integer ownerId) {
		if (jdbcTemplate.update(DELETE_OWNER_BY_ID, ownerId) == 0) {
			throw new EntityCouldNotBeenDeletedException(
					String.format("Owner with name %s could not been deleted", ownerId));
		}
	}

	@Override
	public void create(Owner owner) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(CREATE_OWNER_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, owner.getFirstName());
			statement.setString(2, owner.getLastName());
			return statement;
		}, keyHolder) == 0) {
			throw new EntityCouldNotBeenCreatedException(String.format("Owner with name %s could not been created",
					owner.getFirstName() + " " + owner.getLastName()));
		}
		owner.setId((int) keyHolder.getKeys().get("id"));
	}

	@Override
	public void update(Owner owner) {
		if (jdbcTemplate.update(UPDATE_BY_OWNER_ID, owner.getFirstName(), owner.getLastName(), owner.getId()) == 0) {
			throw new EntityCouldNotBeenUpdatedException(String.format("Ovner with name %s could not been updated",
					owner.getFirstName() + " " + owner.getLastName()));
		}
	}
}
