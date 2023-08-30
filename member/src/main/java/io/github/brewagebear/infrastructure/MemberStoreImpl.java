package io.github.brewagebear.infrastructure;

import io.github.brewagebear.domain.Member;
import io.github.brewagebear.domain.MemberInfo;
import io.github.brewagebear.domain.MemberStore;
import org.springframework.stereotype.Component;

@Component
public class MemberStoreImpl implements MemberStore {
    private final MemberRepository memberRepository;

    public MemberStoreImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberInfo.readOnly store(Member member) {
        Member saved = memberRepository.save(member);

        return new MemberInfo.readOnly(saved.getName(), saved.getEmail());
    }
}
