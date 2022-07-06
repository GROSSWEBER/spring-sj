package inc.monster.app.hello.client;

import inc.monster.app.hello.client.domain.Hello;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloClientSystemTest {

    @Autowired
    private HelloClient client;

    @Test
    @SneakyThrows
    public void getHello() {
        Hello hello = client.getHello();

        assertThat(hello).isEqualTo(new Hello("Hello World!"));
    }
}
