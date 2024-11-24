package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.LogInDto;
import com.hms.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public AppUserService(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    public String verifyLogin(LogInDto dto) {
        Optional<AppUser> optUser = appUserRepository.findByUserName(dto.getUserName());

        if(optUser.isPresent()){
            AppUser appUser = optUser.get();

            if(BCrypt.checkpw(dto.getPassword(),appUser.getPassword())){
             //generate token
                String token = jwtService.generateToken(appUser.getUserName());
                return token;
            } else {
                // Password mismatch
                return  null;
                //throw new RuntimeException("Invalid password");
            }
        }else {
            return null;
        }
    }
}
