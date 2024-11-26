package com.example.mymaple.vo.scheduler;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleVO {
    int uid;
    String code;
    String title;
    int easy;
    int normal;
    int hard;
    int chaos;
    int extreme;
    int display_order;
    int set_headcount;
    String diff_level;

    public ScheduleVO(int uid, String code, String title, int display_order) {
        this.uid = uid;
        this.code = code;
        this.title = title;
        this.display_order = display_order;
    }

    public ScheduleVO(int uid, String code, String title, int easy, int normal, int hard, int chaos, int extreme, int display_order) {
        this.uid = uid;
        this.code = code;
        this.title = title;
        this.easy = easy;
        this.normal = normal;
        this.hard = hard;
        this.chaos = chaos;
        this.extreme = extreme;
        this.display_order = display_order;
    }
}
