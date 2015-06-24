package org.chengsoft.mockito.mockitobeans.withlib;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.chengsoft.mockito.mockitobeans.DisableAutowireRequireInitializer;
import org.chengsoft.mockito.mockitobeans.MockedBeans;
import org.chengsoft.mockito.mockitobeans.model.Person;
import org.chengsoft.mockito.mockitobeans.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Now that we add this DisableAutowireRequireInitializer, the {@link ApplicationContext}
 *  will no longer require autowiring of dependencies by default
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PassingDependencyMocksTest.class,
					initializers=DisableAutowireRequireInitializer.class)
@Configuration
@MockedBeans(mockClasses={PersonService.class})
public class PassingDependencyMocksTest {
	
	@Autowired
	private PersonService personService;

	@Test
	public void testFindPerson() {
		// Mock return value
		when(personService.findAnyPerson()).thenReturn(new Person("Jeremy", 25));
		
		Person person = personService.findAnyPerson();
		assertNotNull(person);
	}

}
