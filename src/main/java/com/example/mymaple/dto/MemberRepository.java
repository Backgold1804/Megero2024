package com.example.mymaple.dto;

import com.example.mymaple.vo.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberVO, Long> {
    Optional<MemberVO> findByEmail(String email); // 중복 가입 확인
}
