package com.cedaniel200.practice.repository.user;

import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

public class UserRepositoryDefaultTest {

    private List<User> expectedUsers;
    private User expectedUser;
    @Mock
    private Response<List<User>> listResponse;
    @Mock
    private Call<List<User>> listCall;
    @Mock
    private Response<User> userResponse;
    @Mock
    private Call<User> userCall;
    @Mock
    private UserService service;
    @Mock
    private Retrofit retrofit;
    private UserRepository userRepository;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setup() throws IOException {
        MockitoAnnotations.openMocks(this);
        expectedUser = new User();
        expectedUser.setId(1);
        expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());

        Mockito.when(listResponse.body()).thenReturn(expectedUsers);
        Mockito.when(userResponse.body()).thenReturn(expectedUser);
        Mockito.when(listCall.execute()).thenReturn(listResponse);
        Mockito.when(userCall.execute()).thenReturn(userResponse);
        Mockito.when(service.listUsers()).thenReturn(listCall);
        Mockito.when(service.findById(anyInt())).thenReturn(userCall);
        Mockito.when(retrofit.create(UserService.class)).thenReturn(service);

        userRepository = new UserRepositoryDefault(retrofit);
    }

    @Test
    void mustBeSuccessfulIfReturnAnUserList() throws ServiceNotAvailableException {
        List<User> actualUsers = userRepository.list();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void mustBeSuccessfulIfListMethodThrowServiceNotAvailableException() throws IOException {
        Mockito.when(listCall.execute()).thenThrow(new IOException());

        assertThrows(ServiceNotAvailableException.class, () -> userRepository.list());
    }

    @Test
    void mustBeSuccessfulIfReturnAnUser() throws ServiceNotAvailableException {
        User actualUser = userRepository.findById(1);

        assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test
    void mustBeSuccessfulIfFindByIdMethodThrowServiceNotAvailableException() throws IOException {
        Mockito.when(userCall.execute()).thenThrow(new IOException());

        assertThrows(ServiceNotAvailableException.class, () -> userRepository.findById(1));
    }

}
