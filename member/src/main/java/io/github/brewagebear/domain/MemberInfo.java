package io.github.brewagebear.domain;

public class MemberInfo {
    public record readOnly(String email, String name) {}
    public record secret(String password) {}
}
