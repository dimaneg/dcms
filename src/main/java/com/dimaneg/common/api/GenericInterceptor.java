package com.dimaneg.common.api;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@InterceptMe
@Interceptor
public class GenericInterceptor {

   @AroundInvoke
   public Object manageTransaction(InvocationContext ctx) throws Exception {
	   
	   Object obj = ctx.proceed();
	   
	   return obj;
   }

}