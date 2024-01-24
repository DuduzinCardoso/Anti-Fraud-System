package antifraud.controller;

import antifraud.domain.UserEntity;
import antifraud.dto.UserDto;
import antifraud.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @PostMapping("/user")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserEntity userEntity) {
        return ResponseEntity.status(201).body(userEntityService.registerUser(userEntity));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> showAllUsers() {
        return ResponseEntity.status(200).body(userEntityService.showAllUsers());
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String username) {
        return ResponseEntity.ok(userEntityService.deleteUser(username));
    }
}
