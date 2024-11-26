package com.example.mymaple.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassFeatureDTO {
    private int uid;
    private String name;
    private int groupUid;
    private String groupName;
    private int kindUid;
    private String kindName;
    private String classIcon;
}
