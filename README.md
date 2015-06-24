## Overview
mockitobeans is a supercharged [@InjectMocks](http://docs.mockito.googlecode.com/hg/org/mockito/InjectMocks.html) that will allow you to have all your mocked beans autowire seamlessly by just using Spring's own @Autowire syntax.

## History
mockitobeans started when I was looking for a way to use [mockito](http://mockito.org/) to mock complex objects that had autowired dependencies which had additional autowired dependencies and so on...

## Problems
1. @InjectMocks only injects the dependencies of the class that has been annotated. It does not inject the dependencies of that
* @Autowire by default sets it's annotation attribute "required" to "true". This is a huge pain especially when you only want to inject 2 out of the 12 dependencies for your tests

## Solution
### Add mocks as beans

```java
@Configuration
@MockedBeans(mockClasses={PersonDao.class}, spyClasses={PersonService.class})
public class AppConfig {}
```

### Reference mocks as beans

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class MyTestCase {
	
	// Since the @MockedBeans adds PersonService as a spring bean
	// we can autowire it here
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonDao personDao;
}
```

### Stop default @Autowire require=true behavior
Traditionally, if you try to create a bean with the following:
```java
public class PersonService {
	@Autowired
	private PersonDao personDao;

	@Autowired
	private AddressDao addressDao;
}
```
you will be forced to declare both personDao and addressDao. To circumvent this, but still retain the autowiring capabilities, you can add the DisableAutowireRequireInitializer to stop this behavior

```java
@ContextConfiguration(initializers=DisableAutowireRequireInitializer.class)
```

## Reference
Props to [knes1](https://github.com/knes1) for this blogpost <http://knes1.github.io/blog/2014/2014-08-18-concise-integration-tests-that-contain-mocks-in-spring-framework.html> which inspired this project
