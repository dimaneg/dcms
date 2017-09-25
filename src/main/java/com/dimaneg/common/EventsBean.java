package com.dimaneg.common;

import java.io.IOException;

import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dimaneg.common.api.Failure;
import com.dimaneg.common.api.FailureSelector;
import com.dimaneg.common.api.InterceptMe;
import com.dimaneg.common.api.Log;
import com.dimaneg.common.api.PerfMetrics;
import com.dimaneg.common.api.PerformanceLog;
import com.dimaneg.common.api.PerformanceLogger;
import com.dimaneg.common.api.Qualified;

@Named("eventsBean")
@Stateful
public class EventsBean {

	//Logger log = LoggerFactory.getLogger(EventsBean.class);
	
	@Inject
	@Log
	private Logger log;
	
	@Inject
	@PerformanceLog
	private PerformanceLogger perfLog;
    
	@Inject
	private Event<MyEvent> eventProducer;

	@InterceptMe
	public void fireMyEventA() {
		log.info("A disparar o evento A");
		
		perfLog.debug("A perfLog message ---");
		
		perfLog.opTime("OP_X01", 901);
		perfLog.opTime("OP_X02", 2345);
		perfLog.methodTime(1001);
		
		eventProducer.fire(new MyEvent());
	}
	
	//@PerfMetrics
	public void fireMyEventB() {
		perfLog.methodTime(4091);
		
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
	
	public void provocarErro1() throws Exception {
		log.info("Vamos lançar uma Exception !!!");
		throw new IOException();
	}
}
