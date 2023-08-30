package io.github.brewagebear.domain;

public interface MemberStore {
    MemberInfo.readOnly store(Member member);
}
