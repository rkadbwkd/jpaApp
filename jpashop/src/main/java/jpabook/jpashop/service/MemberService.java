package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // readOnly true = 조회시 성능
//@AllArgsConstructor
@RequiredArgsConstructor // final이 있는 필드만 가지고 생성자를 만들어줌
public class MemberService {    // 등록(join시는 따로 Annotation

    // MemberRepositorry(이전에 생성한거 주입)
    //@Autowired
    // private MemberRepository memberRepository;
    private final MemberRepository memberRepository;
    //생성자 Injection 사용
    // 생략가능(생성자가 1개있는경우 자동 주입) @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional // readOnly=false
    public Long join(Member member){
        //중복회원 검증
        validateDuplicateMember(member);
        //저장
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByNaame(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }


    }
    //회원 전체 조회

    public List<Member> findMembers(){
        return memberRepository.findAll();

    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
