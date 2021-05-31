package ru.stepev.bigwallet.exeption;

public class EntityCouldNotBeenCreatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityCouldNotBeenCreatedException(String message) {
		super(message);
	}
}
