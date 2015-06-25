package org.chengsoft.mockito.mockitobeans.service;

import org.chengsoft.mockito.mockitobeans.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Simple person service that returns a person
 * 
 * @author tcheng
 *
 */
@Service
public class PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	public Person findAnyPerson() {
		return personDao.createFakePerson();
	}

}
