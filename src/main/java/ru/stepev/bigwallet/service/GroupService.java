package ru.stepev.bigwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.GroupDao;
import ru.stepev.bigwallet.exeption.EntityAlreadyExistException;
import ru.stepev.bigwallet.exeption.EntityNotFoundException;
import ru.stepev.bigwallet.model.Group;

@Component
public class GroupService {

	private GroupDao groupDao;

	public GroupService(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public List<Group> getAll() {
		return groupDao.findAll();
	}

	public Optional<Group> getById(int groupId) {
		return groupDao.findById(groupId);
	}

	public void delete(Group group) {
		verifyGroupIsExist(group);
		groupDao.delete(group.getId());
	}
	
	public void add(Group group) {
		verifyGroupIsUnique(group);
		groupDao.create(group);
	}

	private void verifyGroupIsUnique(Group group) {
		Optional<Group> existingGroup = groupDao.findByName(group.getName());
		if (existingGroup.isPresent() && (existingGroup.get().getId() != group.getId())) {	
				throw new EntityAlreadyExistException(
						String.format("Group with name %s already exist", group.getName()));
		} 		
	}

	private void verifyGroupIsExist(Group group) {
		if (groupDao.findById(group.getId()).isEmpty()) {
			throw new EntityNotFoundException(
					String.format("Group with name %s doesn't exist", group.getName()));
		}
	}

	public void update(Group group) {
		verifyGroupIsExist(group);
		verifyGroupIsUnique(group);
		groupDao.update(group);		
	}
}
