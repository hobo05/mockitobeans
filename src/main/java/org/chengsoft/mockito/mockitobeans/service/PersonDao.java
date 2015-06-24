package org.chengsoft.mockito.mockitobeans.service;

import java.util.Random;

import org.chengsoft.mockito.mockitobeans.model.Person;
import org.springframework.stereotype.Repository;

/**
 * Person Repository
 * 
 * @author tcheng
 *
 */
@Repository
public class PersonDao {
	
	private static final String[] anyNameArray = new String[]{"Tom", "Dick", "Harry"};
	
	public Person createFakePerson() {
		Random rand = new Random();
		String name = anyNameArray[rand.nextInt(anyNameArray.length)];
		Integer age = rand.nextInt(101);
		
		return new Person(name, age);
	}

}
