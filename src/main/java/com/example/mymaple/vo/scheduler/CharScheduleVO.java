package com.example.mymaple.vo.scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CharScheduleVO {
    int uid;
    int character_uid;
    int ttodo_uid;
    int clear_people;
    String diff_level;
    int set_headcount;
    String title;
    String code;

    public CharScheduleVO(int uid, int character_uid, int ttodo_uid, int clear_people, int set_headcount, String diff_level, String title, String code) {
        this.uid = uid;
        this.character_uid = character_uid;
        this.ttodo_uid = ttodo_uid;
        this.clear_people = clear_people;
        this.set_headcount = set_headcount;
        this.diff_level = diff_level;
        this.title = title;
        this.code = code;
    }
}
