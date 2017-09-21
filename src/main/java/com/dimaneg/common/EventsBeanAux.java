package com.dimaneg.common;

import javax.ejb.Stateful;
import javax.enterprise.event.Observes;

import com.dimaneg.common.api.Failure;

@Stateful
public class EventsBeanAux {

	public void observesEventA(@Observes @Failure("abx") MyEvent event) {
		System.out.println("Aux Received a event - abx");
	}
	
	public void observesEventB(@Observes @Failure("abc") MyEvent event) {
		System.out.println("Aux Received a event - abc");
	}
	
	public void observesEventC(@Observes @Failure("abcd") MyEvent event) {
		System.out.println("Aux Received a event - abcd");
	}
}
