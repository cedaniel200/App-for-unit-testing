package com.cedaniel200.practice.repository.user;

import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.model.UsersSummary;
import com.cedaniel200.practice.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserRepositoryDefaultTest {

    private UsersSummary expectedUserSummary;
    private User expectedUser;
    private HashMap<String, User> hashMap;
    @Mock
    private Response<HashMap<String, User>> userResponse;
    @Mock
    private Call<HashMap<String, User>> userCall;
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

        expectedUser = new User();
        expectedUser.setId(1);
        hashMap = new HashMap<>();
        hashMap.put("data", expectedUser);

        expectedUserSummary = new UsersSummary();
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());
        expectedUserSummary.setData(expectedUsers);


        Mockito.when(userResponse.body()).thenReturn(hashMap);
        Mockito.when(usersSummaryResponse.body()).thenReturn(expectedUserSummary);
        Mockito.when(userCall.execute()).thenReturn(userResponse);
        Mockito.when(usersSummaryCall.execute()).thenReturn(usersSummaryResponse);
        Mockito.when(service.listUsers()).thenReturn(usersSummaryCall);
        Mockito.when(service.findById(anyInt())).thenReturn(userCall);
        Mockito.when(retrofit.create(UserService.class)).thenReturn(service);

        userRepository = new UserRepositoryDefault(retrofit);
    }

    @Test
    public void mustBeSuccessfulIfReturnAnUserList() throws ServiceNotAvailableException {
        UsersSummary actualUsersSummary = userRepository.list();

        Assert.assertEquals(expectedUserSummary.toString(), actualUsersSummary.toString());
    }

    @Test(expected = ServiceNotAvailableException.class)
    public void mustBeSuccessfulIfListMethodThrowAReturnAnServiceNotAvailableException() throws IOException {
        Mockito.when(usersSummaryCall.execute()).thenThrow(new IOException());

        userRepository.list();
    }

    @Test
    public void mustBeSuccessfulIfReturnAnUser() throws ServiceNotAvailableException {
        User actualUser = userRepository.findById(1);

        Assert.assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test(expected = ServiceNotAvailableException.class)
    public void mustBeSuccessfulIfFindByIdMethodThrowAReturnAnServiceNotAvailableException() throws IOException {
        Mockito.when(userCall.execute()).thenThrow(new IOException());

        userRepository.findById(1);
    }
}
