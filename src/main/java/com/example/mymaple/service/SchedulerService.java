package com.example.mymaple.service;

import com.example.mymaple.dao.SchedulerDAO;
import com.example.mymaple.dto.SessionUser;
import com.example.mymaple.vo.CharacterVO;
import com.example.mymaple.vo.scheduler.CharScheduleVO;
import com.example.mymaple.vo.scheduler.SchSummaryVO;
import com.example.mymaple.vo.scheduler.ScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SchedulerService {

    @Autowired
    private SchedulerDAO dao;

    public CharacterVO getName(SessionUser user) throws Exception {
        if (user == null) {
            return null;
        } else if (!isCharacterSetting(user.getUid())) {
            CharacterVO character = new CharacterVO(user.getName());
            return character;
        } else {
            return getCharacterMain(user.getUid());
        }
    }

    public CharacterVO getCharacter(CharacterVO character) throws Exception {
        return (CharacterVO)dao.getCharacter(character);
    }

    public CharacterVO getCharacterMain(int member_uid) throws Exception {
        return (CharacterVO)dao.getCharacterMain(member_uid);
    }

    public List<CharacterVO> getCharacterList(int member_uid) throws Exception {
        return (List<CharacterVO>) dao.getCharacterList(member_uid);
    }

    public boolean isCharacterSetting(int member_uid) throws Exception {
        return dao.isCharacterSetting(member_uid);
    }

    public int save_daily(int uid, int is_clear) throws Exception {
        return dao.save_daily(uid,is_clear);
    }

    public int allsave_daily(int uid, int is_clear) throws Exception {
        return dao.allsave_daily(uid,is_clear);
    }

    public int save_weekly(int uid, int is_clear) throws Exception {
        return dao.save_weekly(uid,is_clear);
    }

    public int allsave_weekly(int uid, int is_clear) throws Exception {
        return dao.allsave_weekly(uid,is_clear);
    }

    public List<CharacterVO> getSchCharacterList(int member_uid) throws Exception {
        return dao.getSchCharacterList(member_uid);
    }

    public CharacterVO getCharacterInfo(int character_uid) throws Exception {
        return dao.getCharacterInfo(character_uid);
    }

    public List<CharScheduleVO> getDailyList(int character_uid) throws Exception {
        return dao.getDailyList(character_uid);
    }

    public List<CharScheduleVO> getWeeklyList(int character_uid) throws Exception {
        return dao.getWeeklyList(character_uid);
    }

    public List<ScheduleVO> getAllDailyList() throws Exception {
        return dao.getAllDailyList();
    }

    public List<ScheduleVO> getAllWeeklyList(int uid) throws Exception {
        return dao.getAllWeeklyList(uid);
    }

    public SchSummaryVO getSchSummary(int uid) throws Exception {
        return dao.getSchSummary(uid);
    }
    public int insertTodo(int character_uid,int ttodo_uid,String diff_level)throws Exception {
        return dao.insertTodo(character_uid,ttodo_uid,diff_level);
    }

    public int deleteTodo(int character_uid,int ttodo_uid) throws Exception {
        return dao.deleteTodo(character_uid,ttodo_uid);
    }

    public int insertAllTodo(int character_uid,int level)throws Exception {
        return dao.insertAllTodo(character_uid,level);
    }

    public int insertMtodo(int character_uid,int ttodo_uid, String diff_level,int set_headcount)throws Exception {
        if(dao.countTodo(character_uid,ttodo_uid) > 0)
            return dao.updateMtodo(character_uid,ttodo_uid,diff_level,set_headcount);
        else
            return dao.insertMtodo(character_uid,ttodo_uid,diff_level,set_headcount);
    }

    public int updateCharactorYN(int uid)throws Exception {
        return dao.updateCharactorYN(uid);
    }

}
