package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save ( @RequestBody User user ) {

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @GetMapping
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public  User findById( @PathVariable Integer id ){
        return userRepository
                .findById(id)
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado") );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        userRepository
                .findById(id)
                .map( user -> {
                    userRepository.delete(user);
                    return Void.TYPE;
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado") );
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody User updateUser ){
        userRepository
                .findById(id)
                .map( user -> {
                    user.setNome(updateUser.getNome());
                    user.setAniversario(updateUser.getAniversario());
                    user.setFoto(updateUser.getFoto());
                    return userRepository.save(user);
                })
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

}
