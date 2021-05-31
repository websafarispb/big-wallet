package ru.stepev.bigwallet.dao;

import java.util.List;
import java.util.Optional;

import ru.stepev.bigwallet.model.Currency;

public interface CurrencyDao {

	public List<Currency> findAll();
	public Optional<Currency> findById(int currencyId);
	public void create(Currency currency);
	public Optional<Currency> findByName(String name);
	public void update(Currency currency);
	public void delete(Integer currencyId);
}
