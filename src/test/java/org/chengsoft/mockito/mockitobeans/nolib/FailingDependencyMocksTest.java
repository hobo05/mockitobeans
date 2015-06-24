package org.chengsoft.mockito.mockitobeans.nolib;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.chengsoft.mockito.mockitobeans.model.Person;
import org.chengsoft.mockito.mockitobeans.service.PersonDao;
import org.chengsoft.mockito.mockitobeans.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test will fail on {@link ApplicationContext} load because {@link PersonService}
 * is trying to autowire {@link PersonDao}, but no beans match it
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=FailingDependencyMocksTest.class)
@Configuration
public class FailingDependencyMocksTest {
	
	@Autowired
	private PersonService personService;
	
	@Bean
	public PersonService personService() {
		return mock(PersonService.class);
	}

	@Test
	public void testFindPerson() {
		Person person = personService.findAnyPerson();
		assertNotNull(person);
	}

}
