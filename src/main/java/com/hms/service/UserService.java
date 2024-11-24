package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.LogInDto;
import com.hms.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public UserService(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    //Verify the username and password
    public String verifyLogin(LogInDto dto){
        //fetch the data from db
        Optional<AppUser> optionalAppUser = appUserRepository.findByUserName(dto.getUserName());//to search the data we used appUserRepo
        if(optionalAppUser.isPresent()){
            AppUser appUser = optionalAppUser.get();
             if(BCrypt.checkpw(dto.getPassword(), appUser.getPassword())){
                 //generate token
                 String token = jwtService.generateToken(appUser.getUserName());
                 return token;
             }
        }else {
            return null;
        }
        return null;
    }
}