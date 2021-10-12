package tipka.qa.properties;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class OwnerTests {

    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    @Test
    @Tag("properties")
    void readCredentialsTest() {

        String login = credentials.login();
        String password = credentials.password();

        String message = format("I login as %s with password %s.",
                login, password);
        System.out.println(message);
    }

}
