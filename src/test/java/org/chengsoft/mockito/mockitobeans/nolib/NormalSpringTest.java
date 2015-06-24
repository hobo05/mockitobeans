package org.chengsoft.mockito.mockitobeans.nolib;

import static org.junit.Assert.assertNotNull;

import org.chengsoft.mockito.mockitobeans.model.Person;
import org.chengsoft.mockito.mockitobeans.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Normal Spring test that will work
 * 
 * @author tcheng
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=NormalSpringTest.class)
@Configuration
@ComponentScan("org.chengsoft.mockito.mockitobeans")
public class NormalSpringTest {
	
	@Autowired
	private PersonService personService;

	@Test
	public void testFindPerson() {
		Person person = personService.findAnyPerson();
		assertNotNull(person);
	}

}
