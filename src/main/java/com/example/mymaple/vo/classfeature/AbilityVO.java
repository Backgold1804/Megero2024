package com.example.mymaple.vo.classfeature;

import lombok.Getter;

@Getter
public class AbilityVO {
    String greenAbility1;
    String greenAbility2;
    String yellowAbility1;
    String yellowAbility2;
    String yellowAbility3;
    String yellowAbility4;

    public AbilityVO(String greenAbility1, String greenAbility2, String yellowAbility1, String yellowAbility2, String yellowAbility3, String yellowAbility4) {
        this.greenAbility1 = greenAbility1;
        this.greenAbility2 = greenAbility2;
        this.yellowAbility1 = yellowAbility1;
        this.yellowAbility2 = yellowAbility2;
        this.yellowAbility3 = yellowAbility3;
        this.yellowAbility4 = yellowAbility4;
    }
}
