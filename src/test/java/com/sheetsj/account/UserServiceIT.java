package com.sheetsj.account;

import static com.sheetsj.test.matchers.CustomMatchers.isStringInjected;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sheetsj.test.IntegrationTestBaseClass;

public class UserServiceIT extends IntegrationTestBaseClass {
	@Autowired
	private UserService userService;
	
	@Test
	public void testPropertyIsInjected() {
		assertThat(userService.getAnotherProperty(), isStringInjected());
	}
}
