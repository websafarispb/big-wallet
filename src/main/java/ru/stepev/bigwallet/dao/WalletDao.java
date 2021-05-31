package ru.stepev.bigwallet.dao;

import java.util.List;
import java.util.Optional;

import ru.stepev.bigwallet.model.Wallet;

public interface WalletDao {

	public List<Wallet> findAll();
	public Optional<Wallet> findById(int id);
	public void create(Wallet wallet);
	public void delete(Integer walletId);
	public void update(Wallet wallet);
	public List<Wallet> findAllByOwnerId(Integer ownerId);
}
