package com.example.mymaple.vo.classfeature;

import lombok.Getter;

@Getter
public class ClassFeatureVO {
    private final int uid;
    private final String name;
    private final int groupUid;
    private final String groupName;
    private final int kindUid;
    private final String kindName;
    private final String classIcon;

    public ClassFeatureVO(int uid, String name, int groupUid, String groupName,int kindUid, String kindName,String classIcon) {
        this.uid = uid;
        this.name = name;
        this.groupUid = groupUid;
        this.groupName = groupName;
        this.kindUid = kindUid;
        this.kindName = kindName;
        this.classIcon = classIcon;
    }
}
