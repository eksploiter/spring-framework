package com.ssafy.live.ai.tools;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.dto.Page;
import com.ssafy.live.model.dto.SearchCondition;
import com.ssafy.live.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberTools {
    private final MemberService mService;

    // TODO: 08-4. Member관리를 위한 Tool을 설정해보자.
    //  email을 사용하는 사용자의 정보를 반환한다.
    //  Member 정보를 저장한다.
    //  전체 사용자의 목록을 반환한다.
    //  검색 조건인 SearchCondition에 해당하는 데이터를 반환한다.1페이지 당 5개의 정보를 출력한다.
    //  주어진 mno에 해당하는 Member를 삭제한다.
    //  전달받은 Member 정보로 회원의 정보를 업데이트 한다.
    //  email에 해당하는 사용자의 profile 정보를 업데이트 한다.

    // END
}
