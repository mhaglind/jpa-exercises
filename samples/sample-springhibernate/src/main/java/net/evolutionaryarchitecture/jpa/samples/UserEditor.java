package net.evolutionaryarchitecture.jpa.samples;

import java.beans.PropertyEditorSupport;

import net.evolutionaryarchitecture.jpa.samples.service.User;
import net.evolutionaryarchitecture.jpa.samples.service.UserImpl;



public class UserEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        User user = new UserImpl();
        user.setName(text);
        setValue(user);
    }
}
