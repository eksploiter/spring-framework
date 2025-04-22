package com.ssafy.live.restcontroller;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.live.model.dto.Address;
import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;
import com.ssafy.live.model.service.AddressService;
import com.ssafy.live.model.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@Tag(name = "AuthRestController", description = "회원 관리를 위한 기능 제공")
public class AuthRestController implements RestControllerHelper {
    private final MemberService mService;
    private final AddressService aService;

    @GetMapping("/{email}")
    @Operation(summary = "회원 상세 조회", description = "주소를 포함한 회원의 상세 정보를 반환한다.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공"),
            @ApiResponse(responseCode = "204", description = "조회는 성공했으나 회원 정보가 없음"),
            @ApiResponse(responseCode = "500", description = "회원 정보 조회 실패"), })
    private ResponseEntity<?> memberDetail(@PathVariable String email) {
        try {
            Member member = mService.selectDetail(email);
            if (member == null) {
                return handleSuccess(Map.of("result", email), HttpStatus.NO_CONTENT);
            } else {
                return handleSuccess(Map.of("result", member));
            }

        } catch (DataAccessException e) {
            return handleFail(e);
        }
    }

    @GetMapping
    @Operation(summary = "회원 목록 조회", description = "모든 회원의 정보를 반환한다.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공"),
            @ApiResponse(responseCode = "500", description = "회원 목록 조회 실패"), })
    private ResponseEntity<?> memberList(@ModelAttribute SearchCondition condition, Model model) {
        try {
            Page<Member> page = mService.search(condition);
            return handleSuccess(Map.of("result", page));
        } catch (DataAccessException e) {
            return handleFail(e);
        }
    }

    @PutMapping
    private ResponseEntity<?> memberModify(@RequestBody Member member) {
        try {
            mService.update(member);
            return handleSuccess(Map.of("result", member));
        } catch (DataAccessException e) {
            return handleFail(e);
        }
    }

    @DeleteMapping("/{mno}")
    private ResponseEntity<?> memberDelete(@PathVariable Integer mno) {
        try {
            mService.delete(mno);
            return handleSuccess(Map.of("result", mno));
        } catch (DataAccessException e) {
            return handleFail(e);
        }
    }

    @PostMapping("/{email}/addresses")
    @Operation(summary = "회원의 주소 추가", description = "회원의 주소 정보를 추가하고 전체 주소 목록을 반환한다.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "주소 추가 성공"),
            @ApiResponse(responseCode = "500", description = "주소 추가 실패"), })
    private ResponseEntity<?> addressInsert(@RequestBody Address addr, @PathVariable String email) {
        try {
            aService.registAddress(addr);
            Member member = mService.selectDetail(email);
            return handleSuccess(Map.of("result", member.getAddresses()));
        } catch (DataAccessException e) {
            return handleFail(e);
        }
    }

    @DeleteMapping("/{email}/addresses/{ano}")
    @Operation(summary = "회원의 주소 삭제", description = "회원의 주소 정보를 삭제하고 전체 주소 목록을 반환한다.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "주소 삭제 성공"),
            @ApiResponse(responseCode = "500", description = "주소 삭제 실패"), })
    private ResponseEntity<?> addressDelete(@PathVariable int ano, @PathVariable String email) {
        try {
            aService.deleteAddress(ano);
            Member member = mService.selectDetail(email);
            return handleSuccess(Map.of("result", member.getAddresses()));
        } catch (DataAccessException e) {
            return handleFail(e);
        }
    }

    @Operation(summary = "회원의 profile 변경", description = "회원의 profile을 수정하고 base64 인코딩 정보로 반환한다.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "회원 profile 수정 성공"),
            @ApiResponse(responseCode = "500", description = "회원 profile 수정 실패"), })
    @PatchMapping("/{email}/profile")
    private ResponseEntity<?> profileAjax(@RequestParam MultipartFile file, @PathVariable String email) {
        try {
            mService.updateProfile(email, file.getBytes());
            return handleSuccess(Map.of("img", Base64.getEncoder().encodeToString(file.getBytes())));
        } catch (DataAccessException | IOException e) {
            return handleFail(e);
        }
    }
}
