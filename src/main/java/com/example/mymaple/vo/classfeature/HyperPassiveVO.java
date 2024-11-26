package com.example.mymaple.vo.classfeature;

import lombok.Getter;

@Getter
public class HyperPassiveVO {
    int uid;
    String title;
    String code;
    int displayOrder;

    public HyperPassiveVO(int uid, String title, String code, int displayOrder) {
        this.uid = uid;
        this.title = title;
        this.code = code;
        this.displayOrder = displayOrder;
    }
}
