package com.cedaniel200.practice.domain.user;

import com.cedaniel200.practice.exception.MalformedDataException;
import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import com.cedaniel200.practice.repository.user.UserRepository;

import java.util.List;

public class UserDomainDefault implements UserDomain {

    private UserRepository repository;

    public UserDomainDefault(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> list() throws ServiceNotAvailableException, MalformedDataException {
        List<User> users = repository.list();
        validateUsers(users);
        return users;
    }

    private void validateUsers(List<User> users) throws MalformedDataException {
        if(users == null){
            throw new MalformedDataException("la lista de usuarios tiene datos malformados");
        }
    }

    @Override
    public User findById(int id) throws ServiceNotAvailableException, MalformedDataException {
        validateId(id);
        User user = repository.findById(id);
        validateUser(user);
        return user;
    }

    private void validateId(int id) throws MalformedDataException {
        if(id < 0){
            throw new MalformedDataException("El parametro esta malformado: el id no puede ser menor que cero");
        } else if(id == 0){
            throw new MalformedDataException("El parametro esta malformado: el id debe ser mayor que cero");
        }else if(id > 10){
            throw new MalformedDataException("El parametro esta malformado: no hay un id mayor que diez");
        }
    }

    private void validateUser(User user) throws MalformedDataException {
        if(user == null){
            throw new MalformedDataException("el usuario tiene datos malformados");
        }
    }
}
