package com.cos.security.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

public class ApplicationContextUtil implements ApplicationContextAware {
		
		private static ApplicationContext applicationContext;
		
		@Override
		public void setApplicationContext( ApplicationContext context ) throws BeansException {
				
				applicationContext = context;
		}
		
		public static ApplicationContext getContext () {
				return applicationContext;
		}
		
		public static <T> Map<String,T> getBeanOfType ( Class<T> cls ) {
			
			return applicationContext.getBeansOfType( cls );
		}
		
}
