package com.example.mymaple.vo.classfeature;

import lombok.Getter;

@Getter
public class LinkVO {
    int uid;
    int parentUid;
    String code;
    String title;
    int displayOrderBoss;
    int displayOrderHunt;

    public LinkVO(int uid, int parentUid, String code, String title, int displayOrderBoss, int displayOrderHunt) {
        this.uid = uid;
        this.parentUid = parentUid;
        this.code = code;
        this.title = title;
        this.displayOrderBoss = displayOrderBoss;
        this.displayOrderHunt = displayOrderHunt;
    }
}
