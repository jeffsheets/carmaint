package com.sheetsj.test;

import static org.hamcrest.Matchers.*
import static spock.util.matcher.HamcrestSupport.*

import static org.hamcrest.Matchers.*
import static org.junit.Assert.assertThat;
import static spock.util.matcher.HamcrestSupport.*

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.transaction.annotation.Transactional

import spock.lang.Ignore;
import spock.lang.Specification
import spock.lang.Unroll

import com.sheetsj.car.Car
import com.sheetsj.car.CarRepository;
import com.sheetsj.config.PersistenceConfig
import com.sheetsj.config.RootConfig
import com.sheetsj.manufacturer.Manufacturer
import com.sheetsj.manufacturer.ManufacturerRepository;
import com.sheetsj.provider.Provider
import com.sheetsj.provider.ProviderRepository


@ContextConfiguration(loader=AnnotationConfigContextLoader, classes=[RootConfig, PersistenceConfig])
@Transactional
abstract class IntegrationSpecBaseClass extends Specification {
	
}
