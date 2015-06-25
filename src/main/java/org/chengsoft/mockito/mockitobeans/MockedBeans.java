package org.chengsoft.mockito.mockitobeans;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Annotation used on {@link Configuration} classes to register
 * mocks or spies as beans
 * 
 * @author tcheng
 *
 */
@Import(MockImportRegistrar.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MockedBeans {
	/**
	 * Types that need to be mocked.
	 * @return
	 */
	Class<?>[] mockClasses() default {};
	
	/**
	 * Types that need to be spied.
	 * @return
	 */
	Class<?>[] spyClasses() default {};
	
	/**
	 * Scope to scope the beans
	 * @return
	 */
	String scope() default "";
	
	/**
	 * Set the bean as a primary bean to override existing beans
	 * @return
	 */
	boolean primary() default true;
}