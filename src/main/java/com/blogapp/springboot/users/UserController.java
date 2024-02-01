package com.blogapp.springboot.users;

import com.blogapp.springboot.security.JWTService;
import com.blogapp.springboot.users.dto.CreateUserRequestDTO;
import com.blogapp.springboot.users.dto.LoginUserRequestDTO;
import com.blogapp.springboot.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JWTService jwtService;
    private final ModelMapper modelMapper;


    public UserController(UserService userService, JWTService jwtService, ModelMapper modelMapper) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }
    @PostMapping()
    public ResponseEntity<UserResponseDTO> signup(@RequestBody CreateUserRequestDTO request){
        UserEntity user = userService.createUser(request);
        URI savedUserURI = URI.create("/users/"+user.getId());
        var userResponse = modelMapper.map(user,UserResponseDTO.class);
        userResponse.setToken(jwtService.createJWT(user.getId()));
        return ResponseEntity.created(savedUserURI).body(userResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginUserRequestDTO request) {
        UserEntity user = userService.loginuser(request.getUsername(), request.getPassword());
        var userResponse = modelMapper.map(user, UserResponseDTO.class);
        userResponse.setToken(jwtService.createJWT(user.getId()));
        return ResponseEntity.ok(userResponse);
    }

    @ExceptionHandler({
            UserService.UserNotFoundException.class,
            UserService.InvalidCredentialsException.class
    })
    ResponseEntity<ErrorResponse> handleUserExceptions(Exception ex) {
        String message;
        HttpStatus status;

        if (ex instanceof UserService.UserNotFoundException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UserService.InvalidCredentialsException) {
            message = ex.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        } else {
            message = "Something went wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse response = ErrorResponse.builder(ex,status,message)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
