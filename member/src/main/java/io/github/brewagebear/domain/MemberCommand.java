package io.github.brewagebear.domain;

public class MemberCommand {

    public record RegisterMemberRequest(
            String name,
            String email,
            String password
    ) {
        public Member toEntity() {
            return new Member(name, email, password);
        }
    }

    public record LoginMemberRequest(
            String email,
            String password
    ) {
    }
}
