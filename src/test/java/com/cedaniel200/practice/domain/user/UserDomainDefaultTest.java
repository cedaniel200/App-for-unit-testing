package com.cedaniel200.practice.domain.user;

import com.cedaniel200.practice.exception.MalformedDataException;
import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

public class UserDomainDefaultTest {

    @Mock
    private UserRepository userRepository;
    private UserDomain userDomain;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        userDomain = new UserDomainDefault(userRepository);
    }

    @Test
    void mustBeSuccessfulIfReturnUserList() throws ServiceNotAvailableException, MalformedDataException {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());
        Mockito.when(userRepository.list()).thenReturn(expectedUsers);

        List<User> actualUsers = userDomain.list();

        Mockito.verify(userRepository).list();
        assertEquals(2, actualUsers.size());
    }

    @Test
    void mustBeSuccessfulIfListMethodThrowAnMalformedDataException() throws ServiceNotAvailableException {
        Mockito.when(userRepository.list()).thenReturn(null);
        assertThrows(MalformedDataException.class, () -> userDomain.list());
    }

    @Test
    void mustBeSuccessfulIfListMethodThrowAnServiceNotAvailableException() throws ServiceNotAvailableException {
        Mockito.when(userRepository.list()).thenThrow(new ServiceNotAvailableException("", null));
        assertThrows(ServiceNotAvailableException.class, () -> userDomain.list());
    }

    @Test
    void mustBeSuccessfulIfFindByIdMethodReturnAnUser() throws MalformedDataException, ServiceNotAvailableException {
        int id = 1;
        User expectedUser = new User();
        expectedUser.setId(id);
        Mockito.when(userRepository.findById(anyInt())).thenReturn(expectedUser);

        User actualUser = userDomain.findById(id);

        assertEquals(id, actualUser.getId());
    }

    @Test
    void mustBeSuccessfulIfFindByIdMethodThrowAnServiceNotAvailableException() throws ServiceNotAvailableException {
        Mockito.when(userRepository.findById(anyInt())).thenThrow(new ServiceNotAvailableException("", null));
        assertThrows(ServiceNotAvailableException.class, () -> userDomain.findById(1));
    }

    @Test
    void mustBeSuccessfulIfFindByIdMethodThrowAnMalformedDataExceptionWhenUserIsNull() throws ServiceNotAvailableException {
        Mockito.when(userRepository.findById(anyInt())).thenReturn(null);
        MalformedDataException exception = assertThrows(MalformedDataException.class, () -> userDomain.findById(1));
        assertEquals("the user has malformed data", exception.getMessage());
    }

    @Test
    void mustBeSuccessfulIfFindByIdMethodThrowAnMalformedDataExceptionWhenIdIsNegative() {
        MalformedDataException exception = assertThrows(MalformedDataException.class, () -> userDomain.findById(-1));
        assertEquals("The parameter is malformed: The id can't be less than zero", exception.getMessage());
    }

    @Test
    void mustBeSuccessfulIfFindByIdMethodThrowAnMalformedDataExceptionWhenIdIsZero() {
        MalformedDataException exception = assertThrows(MalformedDataException.class, () -> userDomain.findById(0));
        assertEquals("The parameter is malformed: The id should be greater than zero", exception.getMessage());
    }

    @Test
    void mustBeSuccessfulIfFindByIdMethodThrowAnMalformedDataExceptionWhenIdGreaterThanTen() {
        MalformedDataException exception = assertThrows(MalformedDataException.class, () -> userDomain.findById(11));
        assertEquals("The parameter is malformed: There is no id greater than ten", exception.getMessage());
    }

}
