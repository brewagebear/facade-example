package io.github.brewagebear.domain;

public interface MemberReader {
    MemberInfo.secret gertMemberSecret(String email);
    MemberInfo.readOnly getMember(String email);
}
