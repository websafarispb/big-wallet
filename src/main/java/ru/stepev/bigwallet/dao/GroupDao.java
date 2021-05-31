package ru.stepev.bigwallet.dao;

import java.util.List;
import java.util.Optional;

import ru.stepev.bigwallet.model.Group;

public interface GroupDao {

	public List<Group> findAll();
	public Optional<Group> findById(int groupId);
	public void delete(Integer id);
	public Optional<Group> findByName(String name);
	public void create(Group group);
	public void update(Group group);
}
