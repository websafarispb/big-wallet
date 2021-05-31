package ru.stepev.bigwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.WalletDao;
import ru.stepev.bigwallet.exeption.EntityAlreadyExistException;
import ru.stepev.bigwallet.exeption.EntityNotFoundException;
import ru.stepev.bigwallet.model.Wallet;

@Component
public class WalletService {

	private WalletDao walletDao;

	public WalletService(WalletDao walletDao) {
		this.walletDao = walletDao;
	}

	public List<Wallet> getAll() {
		return walletDao.findAll();
	}

	public Optional<Wallet> getById(int id) {
		return walletDao.findById(id);
	}

	public void add(Wallet wallet) {
		verifyWalletNotExist(wallet);
		walletDao.create(wallet);
	}

	public void verifyWalletExist(Wallet wallet) {
		if (walletDao.findById(wallet.getId()).isEmpty()) {
			throw new EntityNotFoundException(String.format("Wallet with ID %s doesn't exist", wallet.getId()));
		}
	}

	public void verifyWalletNotExist(Wallet wallet) {
		if (walletDao.findById(wallet.getId()).isPresent()) {
			throw new EntityAlreadyExistException(String.format("Wallet with ID %s already exist", wallet.getId()));
		}
	}

	public void delete(Wallet wallet) {
		verifyWalletExist(wallet);
		walletDao.delete(wallet.getId());
	}

	public void update(Wallet wallet) {
		verifyWalletExist(wallet);
		walletDao.update(wallet);
	}

	public List<Wallet> getAllByOwnerId(Integer ownerId) {
		return walletDao.findAllByOwnerId(ownerId);
	}
}
