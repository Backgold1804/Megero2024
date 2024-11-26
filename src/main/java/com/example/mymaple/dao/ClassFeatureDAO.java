package com.example.mymaple.dao;

import com.example.mymaple.vo.*;
import com.example.mymaple.vo.classfeature.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ClassFeatureDAO {

    @Autowired
    private SqlSession sqlSession;

    private final String NAMESPACE = "com.example.mymaple.repository.MymapleMapper.";

    public List<ClassFeatureVO> getAllClass() throws Exception {
        return sqlSession.selectList(NAMESPACE + "getAllClass");
    }

    public List<HashMap<String, String>> getAllKind() throws Exception {
        return sqlSession.selectList(NAMESPACE + "getAllKind");
    }

    public int updateApiKey(MemberVO member) throws Exception{
        return  sqlSession.update(NAMESPACE + "updateApiKey", member);
    }

    public int updateCharacterMain(CharacterVO character) throws Exception{
        sqlSession.update(NAMESPACE + "clearCharacterMain", character);
        return  sqlSession.update(NAMESPACE + "updateCharacterMain", character);
    }

    public int getClassUid(String classname) throws Exception{
        if(classname != null && !"".equals(classname))
            return sqlSession.selectOne(NAMESPACE + "getClassUid", classname);
        else
            return 0;
    }

    public CharacterVO getCharacter(CharacterVO character) throws Exception{
        return  (CharacterVO)sqlSession.selectOne(NAMESPACE + "getCharacter", character);
    }

    public CharacterVO getCharacterMain(int member_uid) throws Exception{
        return  (CharacterVO)sqlSession.selectOne(NAMESPACE + "getCharacterMain", member_uid);
    }

    public List<CharacterVO> getCharacterList(int member_uid) throws Exception{
        return  sqlSession.selectList(NAMESPACE + "getCharacterList", member_uid);
    }

    public int updateCharacter(CharacterVO character) throws Exception{
        return  sqlSession.update(NAMESPACE + "updateCharacter", character);
    }

    public int insertCharacter(CharacterVO character) throws Exception{
        return  sqlSession.insert(NAMESPACE + "insertCharacter", character);
    }

    public boolean isCharacterSetting(int member_uid)throws Exception{
        if(
                (int)sqlSession.selectOne(NAMESPACE + "isApiKey", member_uid) > 0
                && (int)sqlSession.selectOne(NAMESPACE + "isMainYN", member_uid) > 0
                )
            return true;
        else
            return false;
    }

    public String getMainOcid(int user_uid) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getMainOcid", user_uid);
    }

    public ClassFeatureDetail1VO getClassDetail1(int classUid) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getClassDetail1", classUid);
    }

    public List<LinkVO> getLinkList(int classUid, int groupUid, int kindUid) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("classUid", classUid);
        map.put("groupUid", groupUid);
        map.put("kindUid", kindUid);
        return sqlSession.selectList(NAMESPACE + "getLinkList", map);
    }

    public AbilityVO getAbility(int classUid) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getAbility", classUid);
    }

    public List<HyperPassiveVO> getHyperPassive(int classUid) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getHyperPassive", classUid);
    }

    public List<VSkillVO> getVSkillOO(HashMap classUid) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getVSkillOO", classUid);
    }

    public List<VSkillVO> getVSkill(int classUid) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getVSkill", classUid);
    }
}
