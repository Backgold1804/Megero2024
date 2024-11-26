package com.example.mymaple.controller;

import com.example.mymaple.dto.SessionUser;
import com.example.mymaple.service.ClassFeatureService;
import com.example.mymaple.service.SchedulerService;
import com.example.mymaple.util.NexonAPI;
import com.example.mymaple.vo.scheduler.CharScheduleVO;
import com.example.mymaple.vo.scheduler.ScheduleVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private ClassFeatureService classFeatureService;

    private final HttpSession httpSession;

    @GetMapping(value = "/scheduler")
    public String scheduler(Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";

            model.addAttribute("charList", schedulerService.getSchCharacterList(user.getUid()));
            model.addAttribute("summary", schedulerService.getSchSummary(user.getUid()));
        }

        return "Scheduler";
    }

    @GetMapping(value = "/dailyCheck")
    public String dailyCheck(@RequestParam("uid") int uid, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("uid", uid);

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";

            model.addAttribute("char", schedulerService.getCharacterInfo(uid));
            model.addAttribute("dailyList", schedulerService.getDailyList(uid));
            model.addAttribute("charList", schedulerService.getSchCharacterList(user.getUid()));
        }

        return "DailyCheck";
    }

    @GetMapping(value = "/weeklyCheck")
    public String weeklyCheck(@RequestParam("uid") int uid, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("uid", uid);

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";

            model.addAttribute("char", schedulerService.getCharacterInfo(uid));
            model.addAttribute("weeklyList", schedulerService.getWeeklyList(uid));
            model.addAttribute("charList", schedulerService.getSchCharacterList(user.getUid()));
        }

        return "WeeklyCheck";
    }

    @GetMapping(value = "/dailySetting")
    public String dailySetting(@RequestParam("uid") int uid, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("uid", uid);

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";

            ArrayList<CharScheduleVO> charSchList = (ArrayList<CharScheduleVO>) schedulerService.getDailyList(uid);
            ArrayList<Integer> uidList = new ArrayList<Integer>();

            for (CharScheduleVO vo : charSchList) {
                uidList.add(vo.getTtodo_uid());
            }

            model.addAttribute("char", schedulerService.getCharacterInfo(uid));
            model.addAttribute("uidList", uidList);
            model.addAttribute("allDailyList", schedulerService.getAllDailyList());
            model.addAttribute("charList", schedulerService.getSchCharacterList(user.getUid()));
        }

        return "DailySetting";
    }

    @GetMapping(value = "/weeklySetting")
    public String weeklySetting(@RequestParam("uid") int uid, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("uid", uid);

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";

            model.addAttribute("char", schedulerService.getCharacterInfo(uid));
            model.addAttribute("weeklyList", schedulerService.getWeeklyList(uid));
            model.addAttribute("allWeeklyList", schedulerService.getAllWeeklyList(uid));
            model.addAttribute("charList", schedulerService.getSchCharacterList(user.getUid()));
        }

        return "WeeklySetting";
    }


    @GetMapping(value = "/save_daily")
    @ResponseBody
    public String save_daily(@RequestParam("uid") int uid,@RequestParam("is_clear") int is_clear, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("uid", uid);

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
            schedulerService.save_daily(uid,is_clear);
        }

        return "ok";
    }

    @GetMapping(value = "/allsave_daily")
    @ResponseBody
    public String allsave_daily(@RequestParam("uid") int uid,@RequestParam("is_clear") int is_clear, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("uid", uid);

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
            schedulerService.allsave_daily(uid,is_clear);
        }

        return "ok";
    }

    @GetMapping(value = "/save_weekly")
    @ResponseBody
    public String save_weekly(@RequestParam("uid") int uid,@RequestParam("is_clear") int is_clear, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("uid", uid);

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
            schedulerService.save_weekly(uid,is_clear);
        }

        return "ok";
    }

    @GetMapping(value = "/allsave_weekly")
    @ResponseBody
    public String allsave_weekly(@RequestParam("uid") int uid,@RequestParam("is_clear") int is_clear, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("uid", uid);

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
            schedulerService.allsave_weekly(uid, is_clear);
        }

        return "ok";
    }

    @GetMapping(value = "/save_daily_setting")
    @ResponseBody
    public String save_daily_setting(@RequestParam("character_uid") int character_uid,@RequestParam("ttodo_uid") int ttodo_uid,@RequestParam("diff_level") String diff_level, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
            schedulerService.insertTodo(character_uid,ttodo_uid,diff_level);
        }

        return "ok";
    }

    @GetMapping(value = "/delete_daily_setting")
    @ResponseBody
    public String delete_daily_setting(@RequestParam("character_uid") int character_uid,@RequestParam("ttodo_uid") int ttodo_uid, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
            schedulerService.deleteTodo(character_uid,ttodo_uid);
        }

        return "ok";
    }


    @GetMapping(value = "/save_daily_all")
    @ResponseBody
    public String save_daily_all(@RequestParam("character_uid") int character_uid,@RequestParam("level") int level, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
            schedulerService.insertAllTodo(character_uid,level);
        }

        return "ok";
    }

    @GetMapping(value = "/insert_m_todo")
    @ResponseBody
    public String insert_m_todo(@RequestParam("character_uid") int character_uid
            ,@RequestParam("ttodo_uid") int ttodo_uid
            ,@RequestParam("diff_level") String diff_level
            ,@RequestParam("set_headcount") int set_headcount
            , Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            if (!schedulerService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
            schedulerService.insertMtodo(character_uid,ttodo_uid,diff_level,set_headcount);
        }

        return "ok";
    }

    @GetMapping("/my_char_list")
    public String my_char_list(@RequestParam("uid") int uid, Model model) throws Exception {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            model.addAttribute("list", classFeatureService.getCharacterList(uid));
        }

        return "SchedulerSetting";
    }

    @GetMapping("/update_charactor_yn")
    @ResponseBody
    public String update_charactor_yn(@RequestParam("uid") int uid, Model model) throws Exception {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", schedulerService.getName(user));

            schedulerService.updateCharactorYN(uid);
        }

        return "ok";
    }


}
