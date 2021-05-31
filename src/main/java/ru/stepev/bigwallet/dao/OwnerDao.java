package ru.stepev.bigwallet.dao;

import java.util.List;
import java.util.Optional;

import ru.stepev.bigwallet.model.Owner;

public interface OwnerDao {

	List<Owner> findAll();
	Optional<Owner> findByWalletId(int id);
	Optional<Owner> findById(Integer ownerId);
	void delete(Integer id);
	void create(Owner owner);
	void update(Owner owner);
}
