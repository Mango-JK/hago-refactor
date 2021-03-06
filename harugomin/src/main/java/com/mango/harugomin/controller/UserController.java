package com.mango.harugomin.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.mango.harugomin.domain.entity.History;
import com.mango.harugomin.domain.entity.Post;
import com.mango.harugomin.domain.entity.User;
import com.mango.harugomin.dto.UserTokenResponseDto;
import com.mango.harugomin.dto.UserUpdateRequestDto;
import com.mango.harugomin.jwt.JwtService;
import com.mango.harugomin.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(origins = "*")
@Slf4j
@Api(tags = "1. User")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class UserController {

    private final KakaoAPIService kakaoAPIService;
    private final NaverAPIService naverAPIService;
    private final JwtService jwtService;
    private final S3Service s3Service;
    private final UserService userService;
    private final PostService postService;
    private final HistoryService historyService;

    @ApiOperation("카카오 로그인")
    @PostMapping("/users/login/kakao")
    @ResponseBody
    public String kakaoLogin(HttpServletRequest request) {
        String accessToken = request.getHeader("accessToken");
        JsonNode json = kakaoAPIService.getKaKaoUserInfo(accessToken);

        String result = null;
        JsonObject data = new JsonObject();
        try {
            result = kakaoAPIService.redirectToken(json); // 토큰 발행
        } catch (Exception e) {
            log.error(e + "");
            data.addProperty("status", String.valueOf(HttpStatus.BAD_REQUEST));
            return data.toString();
        }
        data.addProperty("jwt", result);
        data.addProperty("status", String.valueOf(HttpStatus.OK));
        return data.toString();
    }

    @ApiOperation("네이버 로그인")
    @PostMapping("/users/login/naver")
    public String naverLogin(HttpServletRequest request) {
        String accessToken = request.getHeader("accessToken");
        JsonNode json = naverAPIService.getNaverUserInfo(accessToken);

        String result = null;
        JsonObject data = new JsonObject();
        try {
            result = naverAPIService.redirectToken(json);
        } catch (Exception e) {
            data.addProperty("status", String.valueOf(HttpStatus.BAD_REQUEST));
            return data.toString();
        }
        data.addProperty("jwt", result);
        data.addProperty("status", String.valueOf(HttpStatus.OK));
        return data.toString();
    }

    /**
     * 애플로그인
     */


    @ApiOperation("토큰 검증")
    @PostMapping("/users/check")
    public ResponseEntity checkToken(HttpServletRequest request) {
        User user = null;
        if (jwtService.isUsable(request.getHeader("jwt"))) {
            Object obj = jwtService.get("user");
            user = userService.findById(Long.parseLong(obj.toString())).get();
        }

        UserTokenResponseDto result = new UserTokenResponseDto(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation("유저 프로필 사진 업데이트")
    @PutMapping(value = "/users/profileImage/{id}")
    public String updateUserProfile(@PathVariable(value = "id") Long userId, @RequestParam MultipartFile file) throws IOException {
        User user = userService.findById(userId).get();
        String imgPath = S3Service.CLOUD_FRONT_DOMAIN_NAME + s3Service.upload(user.getProfileImage(), file);
        user.updateUserImage(imgPath);
        userService.saveUser(user);
        JsonObject data = new JsonObject();
        data.addProperty("imgPath", imgPath);
        data.addProperty("status", String.valueOf(HttpStatus.OK));

        return data.toString();
    }

    @ApiOperation("유저 프로필 업데이트 [사진, 닉네임, 연령대, 해시태그]")
    @PutMapping(value = "/users")
    public ResponseEntity updateUserProfile(@RequestBody UserUpdateRequestDto requestDto) {
        userService.updateUser(requestDto);
        User user = userService.findById(requestDto.getUserId()).get();
        return new ResponseEntity(new UserTokenResponseDto(user), HttpStatus.OK);
    }

    @ApiOperation("유저 해시태그 업데이트")
    @PutMapping(value = "/users/hashtag/{id}")
    public ResponseEntity updateUserHashtag(@PathVariable(value = "id") Long userId, @RequestParam String[] hashtags) {

        User user = userService.updateUserHashtag(userId, hashtags);

        return new ResponseEntity(new UserTokenResponseDto(user), HttpStatus.OK);
    }

    @ApiOperation("유저 닉네임 중복검사")
    @GetMapping(value = "/users/check/{nickname}")
    public ResponseEntity<String> duplicationCheck(@PathVariable("nickname") String nickname) {
        JsonObject data = new JsonObject();
        data.addProperty("flag", userService.duplicationCheck(nickname));

        return new ResponseEntity<>(data.toString(), HttpStatus.OK);
    }

    @ApiOperation("현재 게시중인 고민글")
    @GetMapping(value = "/users/posts/{userId}")
    public ResponseEntity myCurrentPosting(@PathVariable("userId") Long userId, @RequestParam int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 15, Sort.by("createdDate").descending());
        Page<Post> result = null;

        try {
            result = postService.findAllByUserId(userId, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getContent(), HttpStatus.OK);
    }

    @ApiOperation("내 글 보관함")
    @GetMapping(value = "/users/history/{userId}")
    public ResponseEntity myHistoryPost(@PathVariable("userId") Long userId, @RequestParam int pageNum) throws Exception {
        PageRequest pageRequest = PageRequest.of(pageNum, 15, Sort.by("createdDate").descending());
        Page<History> result = null;
        try {
            result = historyService.myHistoryPost(userId, pageRequest);
        } catch (Exception e) {
            return new ResponseEntity(result.getContent(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.getContent(), HttpStatus.OK);
    }

//    @ApiOperation("유저 삭제")
//    @DeleteMapping(value = "/users/{userId}")
//    public ResponseEntity<Long> deleteUser(@PathVariable("userId") Long userId) {
//        try {
//            postService.foreignkeyOpen();
//            historyService.deleteUserHistories(userId);
//            commentService.deleteByUserId(userId);
//            likerService.deleteAllByUsers(userId);
//            userHashtagService.deleteAllByUsers(userId);
//            postService.deleteUserPosts(userId);
//            userService.deleteById(userId);
//            postService.foreignkeyClose();
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
