package io.github.brewagebear.controller;


import io.github.brewagebear.domain.MemberCommand;
import io.github.brewagebear.domain.MemberInfo;
import io.github.brewagebear.facade.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberInfo.readOnly> signup(@RequestBody MemberCommand.RegisterMemberRequest signupRequest) {
        return ResponseEntity.ok()
                .body(authenticationService.register(signupRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<MemberInfo.readOnly> login(@RequestBody MemberCommand.LoginMemberRequest loginRequest) {
        return ResponseEntity.ok()
                .body(authenticationService.login(loginRequest));
    }
}
