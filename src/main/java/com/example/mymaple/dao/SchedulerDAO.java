package com.example.mymaple.dao;

import com.example.mymaple.vo.CharacterVO;
import com.example.mymaple.vo.classfeature.LinkVO;
import com.example.mymaple.vo.scheduler.CharScheduleVO;
import com.example.mymaple.vo.scheduler.SchSummaryVO;
import com.example.mymaple.vo.scheduler.ScheduleVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class SchedulerDAO {

    @Autowired
    private SqlSession sqlSession;

    private final String NAMESPACE = "com.example.mymaple.repository.SchedulerMapper.";

    public CharacterVO getCharacter(CharacterVO character) throws Exception {
        return (CharacterVO) sqlSession.selectOne(NAMESPACE + "getCharacter", character);
    }

    public CharacterVO getCharacterMain(int member_uid) throws Exception {
        return (CharacterVO) sqlSession.selectOne(NAMESPACE + "getCharacterMain", member_uid);
    }

    public List<CharacterVO> getCharacterList(int member_uid) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getCharacterList", member_uid);
    }

    public boolean isCharacterSetting(int member_uid) throws Exception {
        if (
                (int) sqlSession.selectOne(NAMESPACE + "isApiKey", member_uid) > 0
                        && (int) sqlSession.selectOne(NAMESPACE + "isMainYN", member_uid) > 0
        )
            return true;
        else
            return false;
    }

    public List<CharacterVO> getSchCharacterList(int member_uid) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getSchCharacterList", member_uid);
    }

    public CharacterVO getCharacterInfo(int character_uid) {
        return sqlSession.selectOne(NAMESPACE + "getCharacterInfo", character_uid);
    }

    public List<CharScheduleVO> getDailyList(int character_uid) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getDailyList", character_uid);
    }

    public List<CharScheduleVO> getWeeklyList(int character_uid) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getWeeklyList", character_uid);
    }

    public List<ScheduleVO> getAllDailyList() throws Exception {
        return sqlSession.selectList(NAMESPACE + "getAllDailyList");
    }

    public List<ScheduleVO> getAllWeeklyList(int uid) throws Exception {
        return sqlSession.selectList(NAMESPACE + "getAllWeeklyList",uid);
    }

    public int save_daily(int uid, int is_clear) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("uid", uid);
        if (is_clear == 1)
            map.put("is_clear", 1);
        else
            map.put("is_clear", -1);
        return sqlSession.update(NAMESPACE + "save_daily", map);
    }

    public int allsave_daily(int uid, int is_clear) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("uid", uid);
        if (is_clear == 1)
            map.put("is_clear", 1);
        else
            map.put("is_clear", 0);
        return sqlSession.update(NAMESPACE + "allsave_daily", map);
    }

    public int save_weekly(int uid, int is_clear) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("uid", uid);
        if (is_clear == 1)
            map.put("is_clear", 1);
        else
            map.put("is_clear", -1);
        return sqlSession.update(NAMESPACE + "save_weekly", map);
    }

    public int allsave_weekly(int uid, int is_clear) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("uid", uid);
        if (is_clear == 1)
            map.put("is_clear", 1);
        else
            map.put("is_clear", 0);
        return sqlSession.update(NAMESPACE + "allsave_weekly", map);
    }

    public SchSummaryVO getSchSummary(int uid) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "getSchSummary", uid);
    }

    public int insertTodo(int character_uid, int ttodo_uid, String diff_level) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("character_uid", character_uid);
        map.put("ttodo_uid", ttodo_uid);
        if (diff_level == null)
            diff_level = "";
        map.put("diff_level", diff_level);
        return sqlSession.insert(NAMESPACE + "insertTodo", map);
    }

    public int deleteTodo(int character_uid, int ttodo_uid) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("character_uid", character_uid);
        map.put("ttodo_uid", ttodo_uid);
        return sqlSession.delete(NAMESPACE + "deleteTodo", map);
    }


    public int insertAllTodo(int character_uid, int level) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("character_uid", character_uid);
        map.put("level", level);
        return sqlSession.insert(NAMESPACE + "insertAllTodo", map);
    }


    public int countTodo(int character_uid,int ttodo_uid) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("character_uid", character_uid);
        map.put("ttodo_uid", ttodo_uid);
        return (int)sqlSession.selectOne(NAMESPACE + "countTodo",map);
    }

    public int updateMtodo(int character_uid,int ttodo_uid, String diff_level,int set_headcount) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("character_uid", character_uid);
        map.put("ttodo_uid", ttodo_uid);
        map.put("diff_level", diff_level);
        map.put("set_headcount", set_headcount);
        return sqlSession.update(NAMESPACE + "updateMtodo",map);
    }

    public int insertMtodo(int character_uid,int ttodo_uid, String diff_level,int set_headcount) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("character_uid", character_uid);
        map.put("ttodo_uid", ttodo_uid);
        map.put("diff_level", diff_level);
        map.put("set_headcount", set_headcount);
        return sqlSession.insert(NAMESPACE + "insertMtodo",map);
    }

    public int updateCharactorYN(int uid) throws Exception {
        return sqlSession.update(NAMESPACE + "updateCharactorYN",uid);
    }

}