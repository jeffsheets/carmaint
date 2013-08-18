package com.sheetsj.provider;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sheetsj.test.IntegrationTestBaseClass;

public class ProviderRepositoryIT extends IntegrationTestBaseClass {
	@Autowired
	private ProviderRepository providerRepository;

	/**
	 * Using Hamcrest matchers via JUnit assertThat calls
	 */
	@Test
	public void testFindAllAndSave() {
		List<Provider> original = providerRepository.findAll();
		
		Provider provider = new Provider("Junit Tire Shop", "Shadow Lake");
		provider = providerRepository.save(provider);
		
		List<Provider> result = providerRepository.findAll();
		
		assertThat(result, not(nullValue()));
		assertThat(result, hasSize(original.size() + 1));
		
		//This requires overridden provider.equals method
		assertThat(result, hasItem(provider));
	}

}
