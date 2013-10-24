package net.evolutionaryarchitecture.jpa.samples.service;

import java.util.Locale;


import org.springframework.context.MessageSource;



public class UserImpl implements User {

	String name = "";
	String description = "";
	String email = "";
	
	MessageSource messages;
	
	public void setDefaultUser(User user) {
		this.setName(user.getName());
		this.setDescription(user.getDescription());
	}
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desciption) {
		this.description = desciption;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		Locale locale = new Locale("no", "NO");
		//Locale locale = Locale.UK;
		//Locale locale = null;
		return messages.getMessage("user", null, "", locale)  + getName() + "  " +
				messages.getMessage("description", null, "", locale)  + getDescription() + "  " + 
				messages.getMessage("email", null, "", locale) + getEmail();
	}
}
