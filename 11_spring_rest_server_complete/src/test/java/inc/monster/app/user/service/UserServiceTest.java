package inc.monster.app.user.service;

import inc.monster.app.user.domain.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UsersJsonLoader loader;
    @InjectMocks
    private UserService service;

    private User userOne = new User(111L);
    private User userTwo = new User(222L);

    @BeforeEach
    public void setup() {
        Mockito.when(loader.load()).thenReturn(Arrays.asList(userOne, userTwo));
    }

    @Test
    public void getAllUsers() {
        MatcherAssert.assertThat(service.getAllUsers(), Matchers.contains(userOne, userTwo));
        MatcherAssert.assertThat(service.getAllUsers(), Matchers.contains(userOne, userTwo));

        Mockito.verify(loader).load();
    }

    @Test
    public void getUser() {
        MatcherAssert.assertThat(service.getUser(111L), Matchers.is(Optional.of(userOne)));
        MatcherAssert.assertThat(service.getUser(222L), Matchers.is(Optional.of(userTwo)));

        Mockito.verify(loader).load();
    }

}
