package ru.stepev.bigwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ru.stepev.bigwallet.dao.CurrencyDao;
import ru.stepev.bigwallet.exeption.EntityAlreadyExistException;
import ru.stepev.bigwallet.exeption.EntityNotFoundException;
import ru.stepev.bigwallet.model.Currency;

@Component
public class CurrencyService {

	private CurrencyDao currencyDao;

	public CurrencyService(CurrencyDao currencyDao) {
		this.currencyDao = currencyDao;
	}

	public List<Currency> getAll() {
		return currencyDao.findAll();
	}

	public Optional<Currency> getById(int currencyId) {
		return currencyDao.findById(currencyId);
	}

	public void add(Currency currency) {
		verifyCurrencyIsUnique(currency);
		currencyDao.create(currency);
	}

	private void verifyCurrencyIsUnique(Currency currency) {
		Optional<Currency> existingCurrency = currencyDao.findByName(currency.getName());
		if (existingCurrency.isPresent() && (existingCurrency.get().getId() != currency.getId())) {
			throw new EntityAlreadyExistException(
					String.format("Currency with name %s already exist", currency.getName()));
		}
	}

	public void delete(Currency currency) {
		verifyCurrencyIsExist(currency);
		currencyDao.delete(currency.getId());
	}

	private void verifyCurrencyIsExist(Currency currency) {
		if (currencyDao.findById(currency.getId()).isEmpty()) {
			throw new EntityNotFoundException(String.format("Currency with name %s doesn't exist", currency.getName()));
		}
	}

	public void update(Currency currency) {
		verifyCurrencyIsExist(currency);
		verifyCurrencyIsUnique(currency);
		currencyDao.update(currency);
	}
}
