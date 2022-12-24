package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // Junit 실행시 Spring이랑 같이 실행
@SpringBootTest //Springboot를 띄운 상태로 실행
@Transactional //
public class MemberServiceTest {

    //@Autowired Member
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em; // 33 Line Rollback 주석 처리후 Query 복기위함

    @Test
    //@Rollback(false) // insert query안나ㅏ감(Tracntional 있을경우)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        em.flush();
        //then
        Assert.assertEquals(member,memberRepository.findOne(saveId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{

        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when

        memberService.join(member1);

        // Annotation으로 대체
        try{
            memberService.join(member2);
        } catch (IllegalStateException e){
            return;
        }

        //memberService.join(member2);



        //then
        //Assert.fail("예외가 바라생해야한다.");

    }
}