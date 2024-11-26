package com.example.mymaple.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CharacterVO {
    int uid ;
    long member_uid;
    int class_uid;
    String nickname;
    int level;
    int main_yn;
    int use_yn;
    String character_key;
    String img_path;
    String world_name;
    String classname;
    String world_img;
    int daily_count;
    int weekly_count;
    int daily_clear_count;
    int weekly_clear_count;

    public CharacterVO(int uid, long member_uid, int class_uid, String nickname, int level, int main_yn, int use_yn, String character_key, String img_path, String world_name){
        this.uid = uid;
        this.member_uid = member_uid;
        this.class_uid = class_uid;
        this.nickname = nickname;
        this.level = level;
        this.main_yn = main_yn;
        this.use_yn = use_yn;
        this.character_key = character_key;
        this.img_path = img_path;
        this.world_name = world_name;
    }

    public CharacterVO(int uid, long member_uid, int class_uid, String nickname, int level, int main_yn, int use_yn, String character_key, String img_path, String world_name, int daily_count, int weekly_count, int daily_clear_count, int weekly_clear_count) {
        this.uid = uid;
        this.member_uid = member_uid;
        this.class_uid = class_uid;
        this.nickname = nickname;
        this.level = level;
        this.main_yn = main_yn;
        this.use_yn = use_yn;
        this.character_key = character_key;
        this.img_path = img_path;
        this.world_name = world_name;
        this.daily_count = daily_count;
        this.weekly_count = weekly_count;
        this.daily_clear_count = daily_clear_count;
        this.weekly_clear_count = weekly_clear_count;
    }

    public CharacterVO(int uid, long member_uid){
        this.uid = uid;
        this.member_uid = member_uid;
    }

    public CharacterVO(String nickname){
        this.nickname = nickname;
    }
}
