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

import ru.stepev.bigwallet.dao.GroupDao;
import ru.stepev.bigwallet.dao.jdbc.rowmapper.GroupRowMapper;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenCreatedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenDeletedException;
import ru.stepev.bigwallet.exeption.EntityCouldNotBeenUpdatedException;
import ru.stepev.bigwallet.model.Group;

@Component
public class JdbcGroupDao implements GroupDao {

	private static final String GET_ALL = "SELECT * FROM groups";
	private static final String GET_BY_ID = "SELECT * FROM groups WHERE id = ?";
	private static final String DELETE_GROUP_BY_ID = "DELETE FROM groups WHERE id = ?";
	private static final String GET_BY_NAME = "SELECT * FROM groups WHERE name = ?";
	private static final String CREATE_GROUP_QUERY = "INSERT INTO groups (name) VALUES (?)";
	private static final String UPDATE_BY_GROUP_ID = "UPDATE groups SET name = ? WHERE id = ?";

	private GroupRowMapper groupRowMapper;
	private JdbcTemplate jdbcTemplate;

	public JdbcGroupDao(JdbcTemplate jdbcTemplate, GroupRowMapper groupRowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.groupRowMapper = groupRowMapper;
	}

	@Override
	public List<Group> findAll() {
		return jdbcTemplate.query(GET_ALL, groupRowMapper);
	}

	@Override
	public Optional<Group> findById(int groupId) {
		try {
			return Optional.of(jdbcTemplate.queryForObject(GET_BY_ID, groupRowMapper, groupId));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public void delete(Integer groupId) {
		if (jdbcTemplate.update(DELETE_GROUP_BY_ID, groupId) == 0) {
			throw new EntityCouldNotBeenDeletedException(
					String.format("Group with name %s could not been deleted", groupId));
		}
	}

	@Override
	public Optional<Group> findByName(String name) {
		try {
			return Optional.of(jdbcTemplate.queryForObject(GET_BY_NAME, groupRowMapper, name));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public void create(Group group) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(CREATE_GROUP_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, group.getName());
			return statement;
		}, keyHolder) == 0) {
			throw new EntityCouldNotBeenCreatedException(
					String.format("Group with name %s could not been created", group.getName()));
		}
		group.setId((int) (keyHolder.getKeys().get("id")));
	}
	
	public void update(Group group) {
		if (jdbcTemplate.update(UPDATE_BY_GROUP_ID, group.getName(), group.getId()) == 0) {
			throw new EntityCouldNotBeenUpdatedException(
					String.format("Group with name %s could not been updated", group.getName()));
		}
	}
}
