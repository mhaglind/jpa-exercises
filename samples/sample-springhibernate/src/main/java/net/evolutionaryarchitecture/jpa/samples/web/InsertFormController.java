package net.evolutionaryarchitecture.jpa.samples.web;

import javax.servlet.http.HttpServletRequest;

import net.evolutionaryarchitecture.jpa.samples.domain.PublicCourse;


public class InsertFormController extends FormController {

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		PublicCourse ev = new PublicCourse();
		ev.setTitle("(Enter title here)");
		return ev;
	}

	@Override
	protected void doSubmitAction(Object managedResource) throws Exception {
		System.out.println("InsertFormController:doSubmitAction");
		eventManager.save((PublicCourse) managedResource);
	}

}
