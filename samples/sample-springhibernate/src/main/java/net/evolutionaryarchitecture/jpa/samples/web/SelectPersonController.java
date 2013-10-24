package net.evolutionaryarchitecture.jpa.samples.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.evolutionaryarchitecture.jpa.samples.service.EventManager;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


public class SelectPersonController extends AbstractController {

	private EventManager eventManager;
	private String viewName;

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		return new ModelAndView(viewName).addObject("personList",
				this.eventManager.findAllPersons());
	}

}
