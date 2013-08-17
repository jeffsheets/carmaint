package com.sheetsj.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.sheetsj.config.PersistenceConfig;
import com.sheetsj.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={RootConfig.class, PersistenceConfig.class})
@ActiveProfiles("test")
public abstract class BaseIntegrationTestCase {

}
