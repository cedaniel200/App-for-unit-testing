package com.cedaniel200.practice.console;

import com.cedaniel200.practice.domain.user.UserDomain;
import com.cedaniel200.practice.exception.MalformedDataException;
import com.cedaniel200.practice.exception.ServiceNotAvailableException;
import com.cedaniel200.practice.model.User;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class UserCommands {

    private UserDomain userDomain;

    public UserCommands(UserDomain userDomain) {
        this.userDomain = userDomain;
    }

    @ShellMethod(value = "Listar los usuarios")
    public String list(){
        try {
            return tryList();
        } catch (Exception e) {
            return "intenta de nuevo. Error : " + e.getMessage();
        }
    }

    private String tryList() throws ServiceNotAvailableException, MalformedDataException {
        StringBuilder listOfUsers = new StringBuilder();
        List<User> users = userDomain.list();
        for (User user : users){
            listOfUsers.append(user.toString())
                    .append("\n");
        }
        return listOfUsers.toString();
    }

    @ShellMethod(value = "Buscar usuario por id")
    public String findById(int id){
        try {
            return tryFindById(id);
        } catch (Exception e) {
            return "intenta de nuevo. Error : " + e.getMessage();
        }
    }

    private String tryFindById(int id) throws MalformedDataException, ServiceNotAvailableException {
        return userDomain.findById(id).toString();
    }
}
