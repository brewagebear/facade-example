package io.github.brewagebear.domain;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberReader memberReader;
    private final MemberStore memberStore;

    public MemberServiceImpl(MemberReader memberReader, MemberStore memberStore) {
        this.memberReader = memberReader;
        this.memberStore = memberStore;
    }

    @Override
    public MemberInfo.readOnly registerMember(MemberCommand.RegisterMemberRequest request) {
        Member member = request.toEntity();
        return memberStore.store(member);
    }

    @Override
    public MemberInfo.readOnly retrieveMemberInfo(String email) {
        return memberReader.getMember(email);
    }

    @Override
    public MemberInfo.secret retrieveMemberSecretInfo(String email, String password) {
        return memberReader.gertMemberSecret(email);
    }
}
