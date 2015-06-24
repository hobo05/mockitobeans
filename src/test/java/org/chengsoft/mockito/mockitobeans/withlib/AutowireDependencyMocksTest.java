package org.chengsoft.mockito.mockitobeans.withlib;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.chengsoft.mockito.mockitobeans.DisableAutowireRequireInitializer;
import org.chengsoft.mockito.mockitobeans.MockedBeans;
import org.chengsoft.mockito.mockitobeans.model.Person;
import org.chengsoft.mockito.mockitobeans.service.PersonDao;
import org.chengsoft.mockito.mockitobeans.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * We now mock {@link PersonDao} and spy on {@link PersonService}. This way {@link PersonService}
 * will still have {@link PersonDao} autowired as its dependency, but we don't have to use
 * {@link MockitoAnnotations} at all. In addition, we are still using our {@link DisableAutowireRequireInitializer}
 * even though we don't need to since all of our autowire dependencies have been resolved, but it is useful to note
 * that in a real world context, there may still be dependencies in {@link PersonService} that we don't want to
 * allow {@link Autowired} to require to resolve
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AutowireDependencyMocksTest.class,
					initializers=DisableAutowireRequireInitializer.class)
@Configuration
@MockedBeans(mockClasses={PersonDao.class}, spyClasses={PersonService.class})
public class AutowireDependencyMocksTest {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonDao personDao;

	@Test
	public void testFindPerson() {
		// Mock return value for person dao
		// since PersonDao is autowired to PersonService
		// PersonService should return this value
		when(personDao.createFakePerson()).thenReturn(new Person("Stanley", 45));
		
		Person person = personService.findAnyPerson();
		assertEquals("Stanley", person.getName());
		assertThat(45, is(person.getAge()));
	}

}
