package ru.stepev.bigwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.OwnerDao;
import ru.stepev.bigwallet.exeption.EntityAlreadyExistException;
import ru.stepev.bigwallet.exeption.EntityNotFoundException;
import ru.stepev.bigwallet.model.Owner;

@Component
public class OwnerService {

	private OwnerDao ownerDao;

	public OwnerService(OwnerDao ownerDao) {
		this.ownerDao = ownerDao;
	}

	public List<Owner> getAll() {
		return ownerDao.findAll();
	}

	public Optional<Owner> getByWalletId(int id) {
		return ownerDao.findByWalletId(id);
	}

	public Optional<Owner> getById(Integer ownerId) {
		return ownerDao.findById(ownerId);
	}

	public void delete(Owner owner) {
		verifyOwnerExist(owner);
		ownerDao.delete(owner.getId());
	}

	public void add(Owner owner) {
		verifyOwnerNotExist(owner);
		ownerDao.create(owner);
	}

	public void verifyOwnerExist(Owner owner) {
		if (ownerDao.findById(owner.getId()).isEmpty()) {
			throw new EntityNotFoundException(String.format("Owner with ID %s doesn't exist", owner.getId()));
		}
	}

	public void verifyOwnerNotExist(Owner owner) {
		if (ownerDao.findById(owner.getId()).isPresent()) {
			throw new EntityAlreadyExistException(String.format("Owner with ID %s already exist", owner.getId()));
		}
	}

	public void update(Owner owner) {
		verifyOwnerExist(owner);
		ownerDao.update(owner);
	}
}
