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
// TODO: 07-3. SWAGGER에서 사용되는 controller의 설정을 확인하세요.
@Tag(name = "AuthRestController", description = "회원 관리를 위한 기능 제공")
public class AuthRestController implements RestControllerHelper {
    private final MemberService mService;
    private final AddressService aService;

    // @GetMapping("/member-detail")
    // private String memberDetail(@RequestParam String email, Model model) {
    // try {
    // Member member = mService.selectDetail(email);
    // model.addAttribute("member", member);
    // } catch (DataAccessException e) {
    // e.printStackTrace();
    // model.addAttribute("alertMsg", e.getMessage());
    // }
    // return "member/member-detail";
    // }

    // TODO: 03-1. memberDetail에 대한 rest api를 만들어보자.
    //  email에 대한 사용자가 있으면 사용자 정보를 보내고 없으면 Map.of("result", member)로 결과를 반환한다.
    //  사용자가 없으면 Map.of("result", email)와 HttpStatus.NO_CONTENT 상태를 반환한다.

    // @GetMapping("/member-list")
    // private String memberList(@ModelAttribute SearchCondition condition, Model
    // model) {
    // try {
    // Page<Member> page = mService.search(condition);
    // model.addAttribute("page", page);
    // } catch (DataAccessException e) {
    // e.printStackTrace();
    // model.addAttribute("alertMsg", e.getMessage());
    // }
    // return "member/member-list";
    // }


    // TODO: 07-4. 메서드에 대한 swagger 설정을 확인하세요.
    @Operation(summary = "회원 목록 조회", description = "모든 회원의 정보를 반환한다.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공"),
            @ApiResponse(responseCode = "500", description = "회원 목록 조회 실패"), })
    // TODO: 04-1. 모든 회원 정보를 검색해서 반환하는 기능을 REST로 작성해보자.
    @GetMapping
    private ResponseEntity<?> memberList(@ModelAttribute SearchCondition condition) {
        try {
            Page<Member> page = mService.search(condition);
            return handleSuccess(Map.of("result", page));
        } catch (DataAccessException e) {
            return handleFail(e);
        }
    }

    // @PostMapping("/member-modify")
    // private String memberModify(@ModelAttribute Member member, HttpSession
    // session, RedirectAttributes redirs) {
    //
    // try {
    // mService.update(member);
    // if (((Member)
    // session.getAttribute("loginUser")).getEmail().equals(member.getEmail())) {
    // session.setAttribute("loginUser", member);
    // }
    // return "redirect:/auth/member-detail?email=" + member.getEmail();
    // } catch (DataAccessException e) {
    // e.printStackTrace();
    // redirs.addFlashAttribute("alertMsg", e.getMessage());
    // return "redirect:/auth/member-detail?email=" + member.getEmail();
    // }
    // }

    // TODO: 05-1. 회원 수정을 처리하는 REST API를 작성하세요.

    // @PostMapping("/member-delete")
    // private String memberDelete(@RequestParam int mno, @RequestParam String
    // email, HttpSession session,
    // RedirectAttributes redirs) {
    // try {
    // mService.delete(mno);
    // if (session.getAttribute("loginUser") instanceof Member m && m.getMno() ==
    // mno) {
    // session.invalidate();
    // return "redirect:/";
    // } else {
    // session.setAttribute("alertMsg", "삭제되었습니다.");
    // return "redirect:/auth/member-list?currentPage=1";
    // }
    // } catch (DataAccessException e) {
    // e.printStackTrace();
    // redirs.addFlashAttribute("alertMsg", e.getMessage());
    // return "redirect:/auth/member-detail?email=" + email;
    // }
    // }

    // TODO: 06-1. 회원 삭제에 대한 REST API를 구성하세요.

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
