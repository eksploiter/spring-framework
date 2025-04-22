package com.ssafy.live.restcontroller;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO: 02-1. MemberController의 내용을 RestController를 구성해보자.
//  대표 경로는 /api/v1/members으로 처리한다. REST API는 세션 관리를 하지 않는다.
//  checkEmail은 detail을 조회하는 코드로 대체한다.
 public class MemberRestController { }

// END
