package com.cedaniel200.practice.domain.user;

import com.cedaniel200.practice.exception.MalformedDataException;
import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.model.UsersSummary;
import com.cedaniel200.practice.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDomainDefaultTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

        Mockito.verify(userRepository).list();
        Assert.assertEquals(2, actualUsers.size());
    }

    // Segunda forma de validar si se lanza una excepción
    @Test(expected = MalformedDataException.class)
    public void mustIsSuccessfulIfListMethodThrowAnMalformedDataException() throws ServiceNotAvailableException, MalformedDataException {
        Mockito.when(userRepository.list()).thenReturn(null);

        userDomain.list();
    }

    // Segunda forma de validar si se lanza una excepción
    @Test(expected = ServiceNotAvailableException.class)
    public void mustIsSuccessfulIfListMethodThrowAnServiceNotAvailableException() throws ServiceNotAvailableException, MalformedDataException {
        Mockito.when(userRepository.list()).thenThrow(new ServiceNotAvailableException("", null));

        userDomain.list();
    }

    @Test
    public void mustIsSuccessfulIfFindByIdMethodReturnAnUser() throws MalformedDataException, ServiceNotAvailableException {
        int id = 1;
        User expectedUser = new User();
        expectedUser.setId(id);
        Mockito.when(userRepository.findById(anyInt())).thenReturn(expectedUser);

        User actualUser = userDomain.findById(id);

        Assert.assertEquals(id, actualUser.getId());
    }

    @Test(expected = ServiceNotAvailableException.class)
    public void mustIsSuccessfulIfFindByIdMethodThrowAnServiceNotAvailableException() throws ServiceNotAvailableException, MalformedDataException {
        Mockito.when(userRepository.findById(anyInt())).thenThrow(new ServiceNotAvailableException("", null));

        userDomain.findById(1);
    }

    // Tercera forma para validar si se lanza una excepción
    @Test
    public void mustIsSuccessfulIfFindByIdMethodThrowAnMalformedDataExceptionWhenUserIsNull() throws ServiceNotAvailableException, MalformedDataException {
        Mockito.when(userRepository.findById(anyInt())).thenReturn(null);
        expectedException.expect(MalformedDataException.class);
        expectedException.expectMessage("the user has malformed data");

        userDomain.findById(1);
    }

    @Test
    public void mustIsSuccessfulIfFindByIdMethodThrowAnMalformedDataExceptionWhenIdIsNegative() throws MalformedDataException, ServiceNotAvailableException {
        expectedException.expect(MalformedDataException.class);
        expectedException.expectMessage("The parameter is malformed: The id can´t be less than zero");

        userDomain.findById(-1);
    }

    @Test
    public void mustIsSuccessfulIfFindByIdMethodThrowAnMalformedDataExceptionWhenIdIsZero() throws MalformedDataException, ServiceNotAvailableException {
        expectedException.expect(MalformedDataException.class);
        expectedException.expectMessage("The parameter is malformed: The id should greater than zero");

        userDomain.findById(0);
    }

    @Test
    public void mustIsSuccessfulIfFindByIdMethodThrowAnMalformedDataExceptionWhenIdGreaterThanTwelve() throws MalformedDataException, ServiceNotAvailableException {
        expectedException.expect(MalformedDataException.class);
        expectedException.expectMessage("The parameter is malformed: There is no id greater than twelve");

        userDomain.findById(13);
    }
}
