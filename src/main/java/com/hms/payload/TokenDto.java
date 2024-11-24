package com.hms.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {

    private String token;
    private String type;

   /* @Override
    public String toString() {
        return "TokenDto{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                '}';
    }*/
}
