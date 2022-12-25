package jpabook.jpashop.api;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//@Controller @RequestBody
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    //entity 직접 잔환 X
    @GetMapping("/api/v1/members")
    public List<Member> memberV1(){
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m-> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{ private int count;
       private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
        //private String

    }


    @PostMapping("/api/v1/members") //@RequestBody == Json으로 온 내용을 Member로 바꿔줌
                                    //API 개발시 파라미터로 Entity를 받지 말것. V2에서 해결
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);

    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest {
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

            // 수정시 변경감지 사용
            memberService.update(id,request.getName());
            //command와 query 분리
        Member findMember = memberService.findOne(id);;
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());

    }

    @Data

    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor // DTO에는 롬복난사
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

}
