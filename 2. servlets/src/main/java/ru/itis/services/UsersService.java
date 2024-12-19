package ru.itis.services;

import ru.itis.dto.AjaxForm;
import ru.itis.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersService {
    List<User> getAll() throws SQLException;
    void addUser(AjaxForm form) throws SQLException;
}
