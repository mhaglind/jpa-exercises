package net.evolutionaryarchitecture.jpa.samples.service;

public interface User {

	public abstract String getDescription();

	public abstract void setDescription(String desciption);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract String getName();

	public abstract void setName(String name);

}