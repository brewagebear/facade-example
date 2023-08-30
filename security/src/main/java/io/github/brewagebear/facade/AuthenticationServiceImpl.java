package io.github.brewagebear.facade;

import io.github.brewagebear.domain.MemberCommand;
import io.github.brewagebear.domain.MemberInfo;
import io.github.brewagebear.domain.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MemberInfo.readOnly login(MemberCommand.LoginMemberRequest request) {
        validationPassword(request);
        return memberService.retrieveMemberInfo(request.email());
    }

    @Override
    public MemberInfo.readOnly register(MemberCommand.RegisterMemberRequest request) {
        return memberService.registerMember(encrypt(request));
    }

    private void validationPassword(MemberCommand.LoginMemberRequest request) {
        MemberInfo.secret secret = memberService.retrieveMemberSecretInfo(request.email(), request.password());

        if(!passwordEncoder.matches(request.password(), secret.password())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    private MemberCommand.RegisterMemberRequest encrypt(MemberCommand.RegisterMemberRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        return new MemberCommand.RegisterMemberRequest(request.name(), request.email(), encodedPassword);
    }
}
