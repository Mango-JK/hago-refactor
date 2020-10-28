package com.mango.harugomin.controller;

import com.mango.harugomin.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Slf4j
@Api(tags = "1. User")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class UserController {

    private final UserService userService;
}
