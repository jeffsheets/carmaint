package com.sheetsj.test;

import static com.sheetsj.test.matchers.CustomMatchers.isStringInjected;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * Tests needed most for properties that are in the database.
 * 
 * But also good for checking for badly spelled keys.
 * 
 * And spring could be configured to ignore missing properties!
 */
public class PropertiesIT extends IntegrationTestBaseClass {
	
	@Value("${another.property}")
	private String anotherProperty;
	
	@Test
	public void testAnotherProperty() {
		assertThat(anotherProperty, isStringInjected());
	}
}
