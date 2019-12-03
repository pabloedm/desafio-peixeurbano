package com.peixeurbano.pablo.desafio;

import static com.wix.mysql.distribution.Version.v8_0_17;

import java.io.IOException;
import java.time.ZoneId;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.flywaydb.core.Flyway;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class BaseControllerEnviroment {

    @LocalServerPort
    protected int port;
    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    private Flyway flyway;

    private static EmbeddedMysql embeddedMysql;

    @BeforeClass
    public static void _setupBeforeClass() throws IOException {
        MysqldConfig config = MysqldConfig.aMysqldConfig(v8_0_17)
                .withPort(3309)
                .withTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")))
                .withUser("test", "test")
                .build();

        SchemaConfig schemaConfig = SchemaConfig.aSchemaConfig("hub").build();

        embeddedMysql = EmbeddedMysql.anEmbeddedMysql(config)
                .addSchema(schemaConfig)
                .start();
    }

    @AfterClass
    public static void _tearDownAfterClass() {
        if (null != embeddedMysql) {
            embeddedMysql.stop();
        }
    }

    @Rule
    public BeforeExecution beforeExecution = new BeforeExecution();

    public class BeforeExecution implements TestRule {

        @Override
        public Statement apply(final Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    flyway.migrate();
                    try {
                        base.evaluate();
                    } finally {
                        flyway.clean();
                    }
                }
            };
        }
    }

}
