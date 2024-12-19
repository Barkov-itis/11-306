package ru.itis.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignUpForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.sql.SQLException;

public class SignUpServiceImpl implements SignUpService{

    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public void signUp(SignUpForm form) throws SQLException {
        User user =  User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .login(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .age(form.getAge())
                .build();

        usersRepository.save(user);

        // функция проверки пароля
        // Boolean pass = passwordEncoder.matches(rawPass, encodePass);
    }
}
