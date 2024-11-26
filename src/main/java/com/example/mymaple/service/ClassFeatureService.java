package com.example.mymaple.service;

import com.example.mymaple.dao.ClassFeatureDAO;
import com.example.mymaple.dto.SessionUser;
import com.example.mymaple.util.NexonAPI;
import com.example.mymaple.vo.*;
import com.example.mymaple.vo.classfeature.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassFeatureService {

    @Autowired
    private ClassFeatureDAO dao;

    public CharacterVO getName(SessionUser user) throws Exception {
        if(user == null)
            return null;
        else if(!isCharacterSetting(user.getUid())) {
            CharacterVO character = new CharacterVO(user.getName());
            return character;
        }else {
            return getCharacterMain(user.getUid());
        }
    }

    public List<ClassFeatureVO> getAllClass() throws Exception {
        return dao.getAllClass();
    }

    public List<HashMap<String, String>> getAllKind() throws Exception {
        return dao.getAllKind();
    }

    public int updateApiKey(long uid, String apikey) throws Exception{
        MemberVO member = new MemberVO(uid,apikey);

        return dao.updateApiKey(member);
    }

    public int updateCharacterMain(long member_uid, int character_uid) throws Exception{
        CharacterVO character = new CharacterVO(character_uid, member_uid);

        return dao.updateCharacterMain(character);
    }
    public void updateCharacter(String api_key, long uid, List<Map<String,String>> list) throws Exception{
        for(int i = 0;i<list.size();i++){
            Map<String, String> map = list.get(i);
            String character_class = map.get("character_class").toString();
            int level = Integer.parseInt(map.get("character_level"));
            //200래벨미만은 등록하면 안된다.
            System.out.println(level + " : " + character_class);
            if(level >= 200) {
                Map<String, String> basic = NexonAPI.character_basic(api_key,map.get("ocid").toString());
                String img_path = "";
                if(basic != null && basic.get("character_image") != null)
                    img_path = basic.get("character_image").toString();
                String world_name = "";
                if(basic != null && basic.get("world_name") != null)
                    world_name = basic.get("world_name").toString();
                int class_uid = 0;

                try{
                    class_uid = getClassUid(character_class);
                }catch(Exception r){

                }
                if(class_uid > 0) {
                    CharacterVO character = new CharacterVO(
                            0
                            , uid
                            , class_uid
                            , map.get("character_name").toString()
                            , Integer.parseInt(map.get("character_level"))
                            , 0
                            , 0
                            , map.get("ocid").toString()
                            , img_path
                            , world_name
                    );
                    CharacterVO result = getCharacter(character);
                    if (result == null) {
                        insertCharacter(character);
                    } else {
                        updateCharacter(character);
                    }
                }
            }
        }
    }

    public CharacterVO getCharacter(CharacterVO character) throws Exception{
        return (CharacterVO)dao.getCharacter(character);
    }

    public CharacterVO getCharacterMain(int member_uid) throws Exception{
        return (CharacterVO)dao.getCharacterMain(member_uid);
    }

    public List<CharacterVO> getCharacterList(int member_uid) throws Exception{
        return (List<CharacterVO>) dao.getCharacterList(member_uid);
    }

    public int updateCharacter(CharacterVO character) throws Exception{
        return dao.updateCharacter(character);
    }

    public int insertCharacter(CharacterVO character) throws Exception{
        return dao.insertCharacter(character);
    }

    public int getClassUid(String classname) throws Exception{
        return dao.getClassUid(classname);
    }

    public boolean isCharacterSetting(int member_uid) throws Exception{
        return dao.isCharacterSetting(member_uid);
    }

    public String getMainOcid(SessionUser user) throws Exception {
        int userUid = user.getUid();
        return dao.getMainOcid(userUid);
    }

    public ClassFeatureDetail1VO getClassDetail1(int classUid) throws Exception {
        return dao.getClassDetail1(classUid);
    }

    public List<LinkVO> getLinkList(int classUid, int groupUid, int kindUid) throws Exception {
        return dao.getLinkList(classUid, groupUid, kindUid);
    }

    public AbilityVO getAbility(int classUid) throws Exception {
        return dao.getAbility(classUid);
    }

    public List<HyperPassiveVO> getHyperPassive(int classUid) throws Exception {
        return dao.getHyperPassive(classUid);
    }

    public List<VSkillVO> getVSkillOO(HashMap classUid) throws Exception {
        return dao.getVSkillOO(classUid);
    }

    public List<VSkillVO> getVSkill(int classUid) throws Exception {
        return dao.getVSkill(classUid);
    }
}
