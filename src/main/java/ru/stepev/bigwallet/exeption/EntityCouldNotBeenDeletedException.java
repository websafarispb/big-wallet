package ru.stepev.bigwallet.exeption;

public class EntityCouldNotBeenDeletedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityCouldNotBeenDeletedException(String message) {
		super(message);
	}
}
