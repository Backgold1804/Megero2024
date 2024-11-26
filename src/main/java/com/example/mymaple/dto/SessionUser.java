package com.example.mymaple.dto;

import com.example.mymaple.vo.MemberVO;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { // 직렬화 기능을 가진 세션 DTO

    // 인증된 사용자 정보만 필요 => name, email, picture 필드만 선언
    //private long user_id;
    private long id;
    private String name;
    private String email;
    private String picture;
    private String apikey;

    public SessionUser(MemberVO user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.apikey = user.getApi_key();
    }
    public int getUid(){
        String temp = id+"";
        return Integer.parseInt(temp);
    }
    public void setApikey(String apikey){
        this.apikey = apikey;
    }
}