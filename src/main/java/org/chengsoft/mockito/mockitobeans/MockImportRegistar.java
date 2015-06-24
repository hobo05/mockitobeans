package org.chengsoft.mockito.mockitobeans;

import org.mockito.Mockito;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
/**
 * Register mock and/or spy classes as beans by annotation
 * 
 * @author tcheng
 *
 */
public class MockImportRegistar implements ImportBeanDefinitionRegistrar {

	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		if (importingClassMetadata.isAnnotated(MockedBeans.class.getName())) {
			try {
				// Scope
				String scope = importingClassMetadata.getAnnotationAttributes(MockedBeans.class.getName()).get("scope").toString();
				
				// Register mocks
				Object mockedBeanTypesValue = importingClassMetadata.getAnnotationAttributes(MockedBeans.class.getName()).get("mockClasses");
				if (mockedBeanTypesValue instanceof Class<?>[]) {
					Class<?>[] mockedBeanTypes = (Class<?>[]) mockedBeanTypesValue;
					if (mockedBeanTypes != null && mockedBeanTypes.length > 0) {
						mockSpecifiedBeanTypes(registry, mockedBeanTypes, MockType.MOCK, scope);
					}
				}
				// Register spies
				Object spyBeanTypesValue = importingClassMetadata.getAnnotationAttributes(MockedBeans.class.getName()).get("spyClasses");
				if (spyBeanTypesValue instanceof Class<?>[]) {
					Class<?>[] spyBeanTypes = (Class<?>[]) spyBeanTypesValue;
					if (spyBeanTypes != null && spyBeanTypes.length > 0) {
						mockSpecifiedBeanTypes(registry, spyBeanTypes, MockType.SPY, scope);
					}
				}
			} catch (Exception ex) {
				throw new RuntimeException("Error while registering mocks: ", ex);
			}
		}
	}

	/**
	 * Mock specified bean types.
	 *
	 * @param registry the bean registry
	 * @param mockedBeanTypes the mocked bean types
	 * @param mockType the mock type (spy or mock)
	 * @param scope the bean scope
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	private void mockSpecifiedBeanTypes(BeanDefinitionRegistry registry, Class<?>[] mockedBeanTypes, MockType mockType, String scope) throws InstantiationException, IllegalAccessException {
		for (Class<?> mockedType : mockedBeanTypes) {
			// Choose different argument value
			// based on mock type
			Object argValue = null;
			switch (mockType) {
				case MOCK:
					argValue = mockedType.getName();
					break;
				case SPY:
					argValue = mockedType.newInstance();
					break;
			}
			// Create and register bean as mock or spy
			registry.registerBeanDefinition(mockType.getMethodName() + mockedType.getSimpleName(),
			BeanDefinitionBuilder
					.rootBeanDefinition(Mockito.class)
					.setFactoryMethod(mockType.getMethodName())
					.addConstructorArgValue(argValue)
					.setScope(scope)
					.getBeanDefinition()
			);
		}
	}
}
