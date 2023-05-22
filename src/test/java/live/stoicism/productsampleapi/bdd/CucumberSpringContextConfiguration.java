package live.stoicism.productsampleapi.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringContextConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);

    @BeforeAll
    public static void setUp() {
        LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    }
}
