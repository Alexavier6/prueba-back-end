package desaweb.tareaback.controllers;

import desaweb.tareaback.services.TokenService;
import desaweb.tareaback.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<String> getToken(){
        return new WrapperResponse(true,"success",
                tokenService.createToken()).createResponse(HttpStatus.OK);
    }


}
