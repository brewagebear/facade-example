package io.github.brewagebear.domain;

public interface MemberService {
    MemberInfo.readOnly registerMember(MemberCommand.RegisterMemberRequest request);
    MemberInfo.readOnly retrieveMemberInfo(String memberId);
    MemberInfo.secret retrieveMemberSecretInfo(String email, String password);
}
