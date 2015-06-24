package org.chengsoft.mockito.mockitobeans;

import org.mockito.Mockito;

/**
 * The type of mocking for {@link Mockito}
 */
public enum MockType {
	MOCK("mock"),
	SPY("spy");
	
	private String methodName;
	
	private MockType(String methodName) {
		this.methodName = methodName;
	}
	
	public String getMethodName() {
		return methodName;
	}
}