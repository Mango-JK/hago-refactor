package com.mango.harugomin.service;

import com.mango.harugomin.domain.entity.Hashtag;
import com.mango.harugomin.domain.entity.User;
import com.mango.harugomin.domain.entity.UserHashtag;
import com.mango.harugomin.domain.repository.UserRepository;
import com.mango.harugomin.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final HashtagService hashtagService;
    private final UserHashtagService userHashtagService;
    private final UserRepository userRepository;

    /**
     * 1. 유저 저장
     */
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 2. 유저 검색
     */
    @Transactional(readOnly = true)
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * 3. 닉네임 중복 검사
     */
    public boolean duplicationCheck(String nickname) {
        if(userRepository.countByNickname(nickname) > 0)
            return false;
        else
            return true;
    }

    /**
     * 4. 유저 삭제
     */
    @Transactional
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * 5. 해시태그 업데이트
     */
    @Transactional
    public User updateUserHashtag(Long userId, String[] hashtags) {
        User user = findById(userId).get();
        userHashtagService.deleteByUser(userId);
        user.initHashtag();

        for (String tagName : hashtags) {
            Hashtag hashtag = hashtagService.findByName(tagName);
            UserHashtag newUserHashtag = new UserHashtag(user, hashtag);
            hashtagService.countUp(hashtag.getTagId());

            userHashtagService.save(newUserHashtag);
            user.userAddTag(newUserHashtag);
        }
        userRepository.save(user);
        return user;
    }

    /**
     * 6. 유저 수정
     */
    @Transactional
    public void updateUser(UserUpdateRequestDto requestDto) {
        User user = findById(requestDto.getUserId()).get();
        user.updateUserProfile(requestDto);

        user = updateUserHashtag(requestDto.getUserId(), requestDto.getUserHashtags());
        userRepository.save(user);
    }

}
