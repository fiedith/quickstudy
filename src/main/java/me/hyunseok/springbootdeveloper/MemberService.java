package me.hyunseok.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public void test(){
        // create
        memberRepository.save(new Member(1L, "A"));

        // read one
        Optional<Member> member = memberRepository.findById(1L);

        // read all
        List<Member> allMembers = memberRepository.findAll();

        // delete
        memberRepository.deleteById(1L);
    }

}
