package com.example.mymaple.vo.classfeature;

import lombok.Getter;

@Getter
public class ClassFeatureDetail1VO {
    int uid;
    int groupUid;
    int kindUid;
    String groupName;
    String kindName;
    String className;
    int sharpYN;
    int criticalRate;
    String unionBonus;
    int resetCRD;
    int buffDuration;
    String linkCode;
    String linkName;

    public ClassFeatureDetail1VO(int uid
                                , int groupUid
                                , int kindUid
                                , String groupName
                                , String kindName
                                , String className
                                , int sharpYN
                                , int criticalRate
                                , String unionBonus
                                , int resetCRD
                                , int buffDuration
                                , String linkCode
                                , String linkName) {
        this.uid = uid;
        this.groupUid = groupUid;
        this.kindUid = kindUid;
        this.groupName = groupName;
        this.kindName = kindName;
        this.className = className;
        this.sharpYN = sharpYN;
        this.criticalRate = criticalRate;
        this.unionBonus = unionBonus;
        this.resetCRD = resetCRD;
        this.buffDuration = buffDuration;
        this.linkCode = linkCode;
        this.linkName = linkName;
    }
}
