package com.sheetsj.test;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.sheetsj.config.PersistenceConfig;
import com.sheetsj.config.RootConfig;

@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={RootConfig.class, PersistenceConfig.class})
@ActiveProfiles("test")
public abstract class IntegrationTestBaseClass extends AbstractTransactionalJUnit4SpringContextTests {

}
