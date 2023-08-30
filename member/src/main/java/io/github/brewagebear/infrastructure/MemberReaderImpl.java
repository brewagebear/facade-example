package io.github.brewagebear.infrastructure;

import io.github.brewagebear.domain.Member;
import io.github.brewagebear.domain.MemberInfo;
import io.github.brewagebear.domain.MemberReader;
import org.springframework.stereotype.Component;

@Component
public class MemberReaderImpl implements MemberReader {
    private final MemberRepository memberRepository;

    public MemberReaderImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberInfo.secret gertMemberSecret(String email) {
        Member member = findMember(email);
        return new MemberInfo.secret(member.getPassword());
    }

    @Override
    public MemberInfo.readOnly getMember(String email) {
        Member member = findMember(email);
        return new MemberInfo.readOnly(member.getEmail(), member.getName());
    }

    private Member findMember(String email) {
        return memberRepository.findMemberByEmail(email)
                .orElseThrow(RuntimeException::new);
    }
}
