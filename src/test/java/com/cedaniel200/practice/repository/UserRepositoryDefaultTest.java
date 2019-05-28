package com.cedaniel200.practice.repository;

import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.model.UsersSummary;
import com.cedaniel200.practice.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

public class UserRepositoryDefaultTest {

    private UsersSummary expectedUserSummary;
    @Mock
    private Response<UsersSummary> usersSummaryResponse;
    @Mock
    private Call<UsersSummary> usersSummaryCall;
    @Mock
    private UserService service;
    @Mock
    private Retrofit retrofit;
    private UserRepository userRepository;

    @Before
    @SuppressWarnings(value = "Unchecked")
    public void setup() throws IOException {
        initMocks(this);
        expectedUserSummary = new UsersSummary();
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());
        expectedUserSummary.setData(expectedUsers);


        Mockito.when(usersSummaryResponse.body()).thenReturn(expectedUserSummary);
        Mockito.when(usersSummaryCall.execute()).thenReturn(usersSummaryResponse);
        Mockito.when(service.listUsers()).thenReturn(usersSummaryCall);
        Mockito.when(retrofit.create(UserService.class)).thenReturn(service);

        userRepository = new UserRepositoryDefault(retrofit);
    }

    @Test
    public void mustBeSuccessfulIfReturnAnUserList() throws ServiceNotAvailableException {
        UsersSummary actualUsersSummary = userRepository.list();

        Assert.assertEquals(expectedUserSummary.toString(), actualUsersSummary.toString());
    }
}
