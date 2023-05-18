package mg.RESTAPI.service;

import mg.RESTAPI.dtos.LoginDto;
import mg.RESTAPI.dtos.RegisterDto;

public interface AuthService {

    String login(LoginDto logindto);
    String register(RegisterDto registerDto);

}
