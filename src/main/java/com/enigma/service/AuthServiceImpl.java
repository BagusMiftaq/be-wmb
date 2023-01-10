package com.enigma.service;

import com.enigma.exception.EntityExistException;
import com.enigma.exception.NotFoundException;
import com.enigma.exception.UnauthorizedException;
import com.enigma.model.Auth;
import com.enigma.repository.AuthRepository;
import com.enigma.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    AuthRepository authRepository;

    @Autowired
    JwtUtils jwtUtils;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public String login(Auth login) {
        try {
            Optional<Auth> auth = authRepository.findById(login.getEmail());
            if (auth.isEmpty()) throw new NotFoundException();
            if (!auth.get().getEmail().equals(login.getEmail())) {
                throw new UnauthorizedException("User not Found");
            } else if (!auth.get().getPassword().equals(login.getPassword())) {
                throw new UnauthorizedException("Password not mathced");
            }

            String token = jwtUtils.generateToken(login.getEmail());
            return token;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }


}
