package com.example.mymaple.vo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class MemberVO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @Column
    private String picture;

    @Column
    private String access_token;

    @Column
    private String api_key;

    @Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
    @NotNull
    private Role role;

    @Builder
    public MemberVO(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    @Builder
    public MemberVO(long user_id, String api_key){
        this.id = user_id;
        this.api_key = api_key;
    }

    public MemberVO update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }



    public String getRoleKey() {
        return this.role.getKey();
    }
}
