package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.payload.LogInDto;
import com.hms.payload.TokenDto;
import com.hms.repository.AppUserRepository;
import com.hms.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {
   private AppUserRepository appUserRepo ;
   private AppUserService appUserService;


    public AppUserController(AppUserRepository appUserRepo, AppUserService appUserService) {
        this.appUserRepo = appUserRepo;
        this.appUserService = appUserService;
    }

    @PostMapping("/signupuser")
    public ResponseEntity<?> createUser(@RequestBody AppUser appUser){

        Optional<AppUser> optUserName = appUserRepo.findByUserName(appUser.getUserName());
        if (optUserName.isPresent()){
            return new ResponseEntity<>("This username is already in use", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> optEmail = appUserRepo.findByEmail(appUser.getEmail());
        if(optEmail.isPresent()){
            return new ResponseEntity<>("This email is already is in use",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String encryptedPassword = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(4));
        appUser.setPassword(encryptedPassword);
        appUser.setRole("ROLE_USER");
        AppUser saved = appUserRepo.save(appUser);

        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    @GetMapping("/mess")
    public String getMessege(){
        return "hello";
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LogInDto dto){
        String token = appUserService.verifyLogin(dto);

        if(token!=null){

            TokenDto tokenDto = new TokenDto();

            tokenDto.setToken(token);
            tokenDto.setType("JWT");

            //return new ResponseEntity<>("User Logged in and the token is : "+tokenDto, HttpStatus.OK);
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid Username/password", HttpStatus.FORBIDDEN);
        }
    }

    //signup for property owner
    @PostMapping("/signupowner")
    public ResponseEntity<?> createOwner(@RequestBody AppUser appUser){

        Optional<AppUser> optUserName = appUserRepo.findByUserName(appUser.getUserName());
        if (optUserName.isPresent()){
            return new ResponseEntity<>("This username is already in use", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> optEmail = appUserRepo.findByEmail(appUser.getEmail());
        if(optEmail.isPresent()){
            return new ResponseEntity<>("This email is already is in use",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String encryptedPassword = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(4));
        appUser.setPassword(encryptedPassword);
        appUser.setRole("ROLE_OWNER");
        AppUser saved = appUserRepo.save(appUser);

        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }
}
