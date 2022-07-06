package inc.monster.app.hello.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import inc.monster.app.hello.client.config.HelloClientConfig;
import inc.monster.app.hello.client.domain.Hello;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(HelloClientConfig.class)
public class HelloClientMockTest {

    @Autowired
    private HelloClient client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @SneakyThrows
    public void getHello() {
        //given
        server.expect(requestTo("http://localhost:8080/hello"))
                .andRespond(
                        withSuccess(mapper.writeValueAsString(new Hello("Hello World!")), MediaType.APPLICATION_JSON));
        //when
        Hello hello = client.getHello();
        //then
        assertThat(hello).isEqualTo(new Hello("Hello World!"));
        server.verify();
    }
}
