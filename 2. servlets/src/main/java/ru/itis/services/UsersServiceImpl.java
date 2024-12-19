package ru.itis.services;

import ru.itis.dto.AjaxForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.sql.SQLException;
import java.util.List;

public class UsersServiceImpl implements UsersService{

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public List<User> getAll() throws SQLException {
        return usersRepository.findAll();
    }

    @Override
    public void addUser(AjaxForm form) throws SQLException {
        User user =  User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .login(form.getEmail())
                .age(22)
                .password("qewrtyuiop")
                .build();
        usersRepository.save(user);
    }
}
