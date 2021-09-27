package ru.adamDev.callCenter.springTest.abstractTest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test_application.properties")
public abstract class AbstractSpringBootTestSuperClass {
}
