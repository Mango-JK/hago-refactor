package com.mango.harugomin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final HashtagService hashtagService;
    private final UserHashtagService userHashtagService;
}
