package com.dimaneg.common;

import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import com.dimaneg.common.api.Failure;
import com.dimaneg.common.api.FailureSelector;
import com.dimaneg.common.api.Qualified;

@Named("eventsBean")
@Stateful
public class EventsBean {

	@Inject
	private Event<MyEvent> eventProducer;

	public void fireMyEventA() {
		eventProducer.fire(new MyEvent());
	}
	
	public void fireMyEventB() {
		eventProducer.select(new FailureSelector("abc")).fire(new MyEvent());
	}
	
	public void fireMyEventC() {
		eventProducer.select(new FailureSelector()).fire(new MyEvent());
	}
	
//	public void observesEventA(@Observes MyEvent event) {
//		System.out.println("Received a event");
//	}
	
	public void observesEventB(@Observes @Failure("abc") MyEvent event) {
		System.out.println("Received a event - abc");
	}
	
//	public void observesEventC(@Observes @Qualified MyEvent event) {
//		System.out.println("Received a event - ()");
//	}
}
