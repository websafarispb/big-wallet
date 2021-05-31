package ru.stepev.bigwallet.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.stepev.bigwallet.model.Owner;

@Component
public class OwnerRowMapper implements RowMapper<Owner> {

	@Override
	public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Owner(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
	}
}
