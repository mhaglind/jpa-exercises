package net.evolutionaryarchitecture.jpa.samples.web;

import java.beans.PropertyEditorSupport;

import net.evolutionaryarchitecture.jpa.samples.domain.Country;
import net.evolutionaryarchitecture.jpa.samples.service.EventManager;


public class CountryEditor extends PropertyEditorSupport {

	private EventManager eventManager;

	public CountryEditor(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		setValue(this.eventManager.findCountry(text));
	}

	public String getAsText() {
		if (getValue() == null) {
			return "";
		}
		return ((Country) getValue()).getCode();
	}

}
