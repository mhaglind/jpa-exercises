package net.evolutionaryarchitecture.jpa.samples.domain;

import java.io.Serializable;

public class Country implements Comparable, Serializable {

	private static final long serialVersionUID = 2557214928726166902L;
	private String code;
	
    private String name;

    public Country() {
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }


    public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Object o) {
        return this.code.compareTo(((Country) o).code);
    }

    public String toString() {
    	return getName();
    }
}
