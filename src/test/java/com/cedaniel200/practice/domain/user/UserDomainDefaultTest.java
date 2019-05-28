package com.cedaniel200.practice.domain.user;

import com.cedaniel200.practice.exception.MalformedDataException;
import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.model.UsersSummary;
import com.cedaniel200.practice.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;

public class UserDomainDefaultTest {

    @Mock
    private UserRepository userRepository;
    private UserDomain userDomain;

    @Before
    public void setup(){
        initMocks(this);
        userDomain = new UserDomainDefault(userRepository);
    }

    @Test
    public void mustIsSuccessfulIfReturnUserList() throws ServiceNotAvailableException, MalformedDataException {
        UsersSummary userSummary = new UsersSummary();
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());
        userSummary.setData(expectedUsers);
        Mockito.when(userRepository.list()).thenReturn(userSummary);

        List<User> actualUsers = userDomain.list();

        Assert.assertEquals(2, actualUsers.size());
    }

    @Test(expected = MalformedDataException.class)
    public void mustIsSuccessfulIfThrowAnException() throws ServiceNotAvailableException, MalformedDataException {
        Mockito.when(userRepository.list()).thenReturn(null);

        userDomain.list();
    }

}
