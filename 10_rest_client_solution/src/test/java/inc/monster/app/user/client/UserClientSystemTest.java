package inc.monster.app.user.client;

import inc.monster.app.user.client.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserClientSystemTest {
    @Autowired
    private UserClient userClient;

    @Test
    public void getUsers() {
        List<User> users = userClient.getAllUsers();
        assertThat(users).hasSize(10);
        assertThat(users.get(0)).isEqualTo(new User(1L, "Leanne Graham", "Bret", "Sincere@april.biz"));
    }

    @Test
    public void getUser() {
        User user = userClient.getUser(1L);
        assertThat(user).isEqualTo(new User(1L, "Leanne Graham", "Bret", "Sincere@april.biz"));
    }
}
