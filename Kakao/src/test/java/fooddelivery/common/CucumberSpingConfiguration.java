package fooddelivery.common;

import fooddelivery.KakaoApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { KakaoApplication.class })
public class CucumberSpingConfiguration {}
