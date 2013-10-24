package net.evolutionaryarchitecture.jpa.samples.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.evolutionaryarchitecture.jpa.samples.domain.Country;
import net.evolutionaryarchitecture.jpa.samples.domain.Event;
import net.evolutionaryarchitecture.jpa.samples.service.EventManager;

import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;


public class FormController extends SimpleFormController {

	EventManager eventManager;

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		System.out.println("FormController:initBinder");
		binder.registerCustomEditor(Country.class, new CountryEditor(
				this.eventManager));
	}

	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors) throws Exception {
		System.out.println("FormController:referenceData");
		return new ModelMap("countryList", eventManager.findAllCountries())
				.addObject("subjects", getSubjects()).addObject("eventList",
						eventManager.findAllEvents());
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		System.out.println("FormController:formBackingObject");
		long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		return this.eventManager.findEventWithParticipants(new Long(id));
	}

	protected void doSubmitAction(Object managedResource) throws Exception {
		System.out.println("FormController:doSubmitAction");
		this.eventManager.update((Event) managedResource);
	}

	private String[] getSubjects() {
		return new String[] { "Java", "Spring", "Hibernate", "Agile Modelling",
				"Test-driven Develoment", "JEE 5" };
	}

}
