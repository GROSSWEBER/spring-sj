package inc.monster.app.user.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import inc.monster.app.user.client.config.UserClientConfig;
import inc.monster.app.user.client.domain.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(UserClientConfig.class)
public class UserClientMockTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private UserClient userClient;

    private User userOne = new User();
    private User userTwo = new User();

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        userOne.setId(111L);
        userOne.setUsername("first user");
        userOne.setName("one");
        userOne.setEmail("one@somewhere.net");

        userTwo.setId(222L);
        userTwo.setUsername("second user");
        userTwo.setName("two");
        userTwo.setEmail("two@somewhere.net");
    }

    @Test
    @SneakyThrows
    public void getUsers() {
        server.expect(requestTo("http://localhost:8080/users"))
                .andRespond(
                        withSuccess(mapper.writeValueAsString(Arrays.asList(userOne, userTwo)), MediaType.APPLICATION_JSON));

        List<User> users = userClient.getAllUsers();
        assertThat(users).contains(userOne, userTwo);
    }

    @Test
    @SneakyThrows
    public void getUser() {
        server.expect(requestTo("http://localhost:8080/users/111"))
                .andRespond(
                        withSuccess(mapper.writeValueAsString(userOne), MediaType.APPLICATION_JSON));

        User user = userClient.getUser(111L);
        assertThat(user).isEqualTo(userOne);
    }
}
