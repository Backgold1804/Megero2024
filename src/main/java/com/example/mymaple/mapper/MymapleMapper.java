package com.example.mymaple.mapper;

import com.example.mymaple.vo.*;
import com.example.mymaple.vo.classfeature.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.HashMap;
import java.util.List;

@MapperScan
public interface MymapleMapper {
    List<ClassFeatureVO> getAllClass();
    List<HashMap<String, String>> getAllKind();
    int updateApiKey(MemberVO member);
    int updateCharacterMain(CharacterVO character);
    int getClassUid(String classname);
    CharacterVO getCharacter(CharacterVO character);
    CharacterVO getCharacterMain(int member_uid);
    List<CharacterVO> getCharacterList(int member_uid);
    int updateCharacter(CharacterVO character);
    int insertCharacter(CharacterVO character);
    boolean isCharacterSetting(int member_uid);
    String getMainOcid(int user_uid);
    ClassFeatureDetail1VO getClassDetail1(int classUid);
    List<LinkVO> getLinkList(int classUid, int groupUid, int kindUid);
    AbilityVO getAbility(int classUid);
    List<HyperPassiveVO> getHyperPassive(int classUid);
}
