package com.dimaneg.common.jsf;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

	private ExceptionHandler wrapped;

	CustomExceptionHandler(ExceptionHandler exception) {
		this.wrapped = exception;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {

		final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			// get the exception from context
			Throwable t = context.getException();

			final FacesContext fc = FacesContext.getCurrentInstance();
			final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
			final NavigationHandler nav = fc.getApplication().getNavigationHandler();

			try {
				LOG.error("Critical Exception!", t);

				// redirect error page
				requestMap.put("exceptionMessage", t.getMessage());
				nav.handleNavigation(fc, null, "/error");
				fc.renderResponse();

				// JsfUtil.addErrorMessage(t.getMessage());

			} finally {
				i.remove();
			}
		}
		getWrapped().handle();
	}
}
