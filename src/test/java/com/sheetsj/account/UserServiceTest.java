package com.sheetsj.account;

import static com.sheetsj.test.matchers.BusinessValidationExceptionMatchers.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserService();

	@Mock
	private AccountRepository accountRepositoryMock;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldInitializeWithTwoDemoUsers() {
		// act
		userService.initialize();
		// assert
		verify(accountRepositoryMock, times(2)).save(any(Account.class));
	}

	@Test
	public void shouldThrowExceptionWhenUserNotFound() {
		// arrange
		thrown.expect(UsernameNotFoundException.class);
		thrown.expectMessage("not found"); //NOTE: This uses containsString() not equalTo(). Can be confusing!!

		when(accountRepositoryMock.findByEmail("user@example.com")).thenReturn(null);
		// act
		userService.loadUserByUsername("user@example.com");
	}
	
	@Test
	public void shouldThrowExceptionWhenUserNotFound_oldSchool() {
		when(accountRepositoryMock.findByEmail("user@example.com")).thenReturn(null);
		
		try {
			userService.loadUserByUsername("user@example.com");
			fail();
		} catch (UsernameNotFoundException e) {
			assertThat(true);
		}
	}

	@Test
	public void shouldThrowBusinessExceptionWhenUsernameTooShort() {
		//Uses custom expect matcher using equalTo() instead of containsString()
		expect("username", "username.too.short", thrown, "ab");

		userService.loadUserByUsername("ab");
	}

	/**
	 * Shows Fest style assertions
	 */
	@Test
	public void shouldReturnUserDetails() {
		// arrange
		Account demoUser = new Account("user@example.com", "demo", "ROLE_USER");
		when(accountRepositoryMock.findByEmail("user@example.com")).thenReturn(demoUser);

		// act
		UserDetails userDetails = userService.loadUserByUsername("user@example.com");

		// assert
		assertThat(demoUser.getEmail()).isEqualTo(userDetails.getUsername());
		assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
        assertThat(hasAuthority(userDetails, demoUser.getRole()));
	}

	private boolean hasAuthority(UserDetails userDetails, String role) {
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			if(authority.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
}
