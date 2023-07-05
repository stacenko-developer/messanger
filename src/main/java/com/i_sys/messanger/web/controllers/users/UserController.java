package com.i_sys.messanger.web.controllers.users;

import com.i_sys.messanger.core.domains.messages.services.IMessageService;
import com.i_sys.messanger.core.domains.users.services.IUserService;
import com.i_sys.messanger.data.messages.Message;
import com.i_sys.messanger.data.users.User;
import com.i_sys.messanger.web.controllers.messages.MessageController;
import com.i_sys.messanger.web.controllers.messages.dto.MessageDtoGet;
import com.i_sys.messanger.web.controllers.messages.dto.MessageDtoPostOrPut;
import com.i_sys.messanger.web.controllers.users.dto.UserDtoGet;
import com.i_sys.messanger.web.controllers.users.dto.UserDtoPostOrPut;
import com.i_sys.messanger.web.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class.getName());

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public List<UserDtoGet> getAllUsers() throws Exception {
        log.info("Call method of UserController: getAllUsers()");
        List<UserDtoGet> result = new ArrayList<>();

        for (User user : userService.getAllUsers()) {
            result.add(new UserDtoGet(user));
        }

        log.info("Method of UserController: getAllUsers(): HttpStatus 200");
        return result;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping({"id"})
    public UserDtoGet getUserById(@RequestParam UUID id) throws Exception {
        log.info("Call method of UserController: getUserById(" + id + ")");

        if (id == null) {
            throw new ValidationException("Id is null!");
        }

        UserDtoGet result = new UserDtoGet(userService.getUserById(id));
        log.info("Method of UserController: getUserById(" + id + ") HttpStatus 200");
        return result;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public User createUser(@RequestBody UserDtoPostOrPut user) throws Exception {
        log.info("Call method of UserController: createUser(" + user + ")");

        if (user == null) {
            throw new ValidationException("User is null!");
        }

        User result = userService.createUser(user);
        log.info("Call method of UserController: createUser(" + user + ") HttpStatus 200");

        return result;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping({"id"})
    public User updateUser(@RequestParam UUID id, @RequestBody UserDtoPostOrPut user) throws Exception {
        log.info("Call method of UserController: updateUser(" + id + "," + user + ")");

        if (id == null) {
            throw new ValidationException("Id is null!");
        }

        if (user == null) {
            throw new ValidationException("User is null!");
        }

        User result = userService.updateUser(id, user);
        log.info("Call method of UserController: updateUser(" + user + ") HttpStatus 200");

        return result;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping({"id"})
    public void deleteUser(@RequestParam UUID id) throws Exception {
        log.info("Call method of UserController: deleteUser(" + id + ")");

        if (id == null) {
            throw new ValidationException("Id is null!");
        }

        userService.deleteUser(id);
        log.info("Method of UserController: deleteUser(" + id + ") HttpStatus 200");
    }
}
