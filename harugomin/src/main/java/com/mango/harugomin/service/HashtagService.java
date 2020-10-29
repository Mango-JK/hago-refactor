package com.mango.harugomin.service;

import com.mango.harugomin.domain.entity.Hashtag;
import com.mango.harugomin.domain.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    /**
     * 1. 태그 추가
     */
    public Hashtag saveHashtag(Hashtag hashtag) {
        return hashtagRepository.save(hashtag);
    }

    /**
     * 2. TagName으로 태그 찾기
     */
    public Hashtag findByName(String tagName) {
        return hashtagRepository.findByTagName(tagName).get();
    }

    /**
     * 3. Tag CountUp
     */
    public void countUp(Long tagId) {

    }



}
