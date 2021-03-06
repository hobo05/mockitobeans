package org.chengsoft.mockito.mockitobeans.withlib;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.chengsoft.mockito.mockitobeans.MockedBeans;
import org.chengsoft.mockito.mockitobeans.model.Person;
import org.chengsoft.mockito.mockitobeans.service.PersonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test will pass because {@link PersonDao} has no additional dependencies
 * and can be easily mocked with mockito
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PassingNoDependencyMocksTest.class)
@Configuration
@MockedBeans(mockClasses=PersonDao.class)
public class PassingNoDependencyMocksTest {
	
	/**
	 * Since we are using {@link MockedBeans}, we don't need to specify
	 * another {@link Bean} annotation
	 */
	@Autowired
	private PersonDao personDao;

	@Test
	public void testFindPerson() {
		// Mock return value
		when(personDao.createFakePerson()).thenReturn(new Person("Jim", 25));
		
		Person person = personDao.createFakePerson();
		assertNotNull(person);
	}

}
