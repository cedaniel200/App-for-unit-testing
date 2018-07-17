package com.cedaniel200.practice.console;

import com.cedaniel200.practice.domain.user.UserDomain;
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

    @ShellMethod(value = "List the users")
    public void list(){
        List<User> users = userDomain.list();
        for (User user : users){
            System.out.println(user.toString());
        }
    }
}
