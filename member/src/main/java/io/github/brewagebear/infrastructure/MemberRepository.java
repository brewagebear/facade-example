package io.github.brewagebear.infrastructure;

import io.github.brewagebear.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByEmail(String email);
}
