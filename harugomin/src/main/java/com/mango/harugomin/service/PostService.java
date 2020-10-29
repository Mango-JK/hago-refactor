package com.mango.harugomin.service;

import com.mango.harugomin.domain.entity.Post;
import com.mango.harugomin.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    /**
     * 1. User ID로 모든 게시글 검색
     */
    public Page<Post> findAllByUserId(Long userId, PageRequest pageRequest) {
        return postRepository.findAllByUserUserId(userId, pageRequest);
    }
}
