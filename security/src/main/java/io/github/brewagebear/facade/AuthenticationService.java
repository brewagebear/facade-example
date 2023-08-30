package io.github.brewagebear.facade;

import io.github.brewagebear.domain.MemberCommand;
import io.github.brewagebear.domain.MemberInfo;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    MemberInfo.readOnly login(MemberCommand.LoginMemberRequest request);
    MemberInfo.readOnly register(MemberCommand.RegisterMemberRequest request);
}
