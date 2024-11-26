package com.example.mymaple.controller;


import com.example.mymaple.dto.SessionUser;
import com.example.mymaple.service.ClassFeatureService;
import com.example.mymaple.util.NexonAPI;
import com.example.mymaple.vo.classfeature.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ClassFeatureController {

    @Autowired
    private ClassFeatureService classFeatureService;

    private final HttpSession httpSession;


    @GetMapping(value = {"/", "/class-feature"})
    public String classFeature(Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        List<ClassFeatureVO> list = classFeatureService.getAllClass();
        List<HashMap<String, String>> kindList= classFeatureService.getAllKind();

        model.addAttribute("list", list);
        model.addAttribute("kindList", kindList);

        if (user != null) {
            model.addAttribute("userName", classFeatureService.getName(user));

            //캐릭터셋팅이 되었는지 확인한다
            if(!classFeatureService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
        }

        return "ClassFeature";
    }

    @GetMapping(value = {"/api-key"})
    public String apiKey(Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", classFeatureService.getName(user));
            model.addAttribute("apikey", user.getApikey());
            model.addAttribute("list", classFeatureService.getCharacterList(user.getUid()));

            //캐릭터셋팅이 되었는지 확인한다
            if(classFeatureService.isCharacterSetting(user.getUid()))
                return "redirect:/";
        }
        return "InputAPIKey";
    }

    @GetMapping("/save-api-key")
    public String saveApiKey(@RequestParam("apikey") String apikey, Model model) throws Exception {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", classFeatureService.getName(user));
        }
        if(apikey == null || "".equals(apikey)){

        }else{
            user.setApikey(apikey);
            httpSession.setAttribute("user", user);

            classFeatureService.updateApiKey(user.getId(), apikey);

            if (user != null) {
                model.addAttribute("apikey", user.getApikey());
                if(user.getApikey() != null && !"".equals(user.getApikey())){
                    List list = NexonAPI.character_list(user.getApikey());
                    try {
                        classFeatureService.updateCharacter(user.getApikey(), user.getId(), list);
                    } catch (Exception e){

                    }
                }
            }
        }
        return "InputAPIKey";
    }

    @GetMapping("/update_main")
    public String updateCharacterMain(@RequestParam("character_uid") int character_uid, Model model) throws Exception {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", classFeatureService.getName(user));

            classFeatureService.updateCharacterMain(user.getUid(),character_uid);
        }


        return "InputAPIKey";
    }

    @GetMapping("/class-feature/detail")
    public String classFeatureDetail(Model model, HttpServletRequest request, @RequestParam("classUid") Integer classUid) throws Exception {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", classFeatureService.getName(user));

            if (!classFeatureService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";

            String ocid = classFeatureService.getMainOcid(user);
            HashMap<String, Object> unionMap = NexonAPI.critical_rate_map(user.getApikey(),ocid);

            model.addAttribute("ocid", ocid);
            model.addAttribute("use_no",unionMap.get("use_no"));
            model.addAttribute("union", unionMap.get("list"));

            ClassFeatureDetail1VO vo = classFeatureService.getClassDetail1(classUid);
            int kind = 0;

            HashMap<String, Integer> totalCRMap = new HashMap<>();
            ArrayList<Integer> list = (ArrayList<Integer>)unionMap.get("list");
            for (int key : list) {
                if (vo.getSharpYN() == 0) {
                    totalCRMap.put(key+"", key + 10 + vo.getCriticalRate());
                } else {
                    totalCRMap.put(key+"", key + 20 + vo.getCriticalRate());
                }
            }

            model.addAttribute("totalCR", totalCRMap);

            System.out.println("uid :" + vo.getUid());
            System.out.println("linkCode :" + vo.getLinkCode());

            List<LinkVO> linkList = classFeatureService.getLinkList(classUid, vo.getGroupUid(), vo.getKindUid());

            ArrayList<LinkVO> bossD = new ArrayList<>();
            ArrayList<LinkVO> bossR = new ArrayList<>();
            ArrayList<LinkVO> bossL = new ArrayList<>();
            ArrayList<LinkVO> huntD = new ArrayList<>();
            ArrayList<LinkVO> huntR = new ArrayList<>();
            ArrayList<LinkVO> huntE = new ArrayList<>();

            for (LinkVO link : linkList) {
                switch (link.getParentUid()) {
                    case 6:
                        bossD.add(link);
                        huntD.add(link);
                        break;
                    case 7:
                        bossD.add(link);
                        break;
                    case 8:
                        huntD.add(link);
                        break;
                    case 9:
                        bossR.add(link);
                        break;
                    case 10:
                        bossR.add(link);
                        huntR.add(link);
                        break;
                    case 11:
                        bossL.add(link);
                        break;
                    case 12:
                        huntE.add(link);
                        break;
                    case 13:
                        bossR.add(link);
                        huntD.add(link);
                        break;
                    case 14:
                        if (vo.getUid() == 88 && link.getUid() == 404) {
                            bossD.add(link);
                            huntD.add(link);
                        } else if (vo.getUid() != 88 && link.getUid() == 395) {
                            bossD.add(link);
                            huntD.add(link);
                        }
                        break;
                    default:
                        break;
                }
            }

            bossD.sort(Comparator.comparing(LinkVO::getDisplayOrderBoss));
            bossR.sort(Comparator.comparing(LinkVO::getDisplayOrderBoss));
            bossL.sort(Comparator.comparing(LinkVO::getDisplayOrderBoss));
            huntD.sort(Comparator.comparing(LinkVO::getDisplayOrderHunt));
            huntR.sort(Comparator.comparing(LinkVO::getDisplayOrderHunt));
            huntE.sort(Comparator.comparing(LinkVO::getDisplayOrderHunt));

            while (bossD.size() > 12) {
                bossD.removeLast();
            }

            while (huntD.size() > 12) {
                huntD.removeLast();
            }

            HashMap<String, ArrayList> bossMap = new HashMap<>();
            bossMap.put("bossD", bossD);
            bossMap.put("bossR", bossR);
            bossMap.put("bossL", bossL);

            HashMap<String, ArrayList> huntMap = new HashMap<>();
            huntMap.put("huntD", huntD);
            huntMap.put("huntR", huntR);
            huntMap.put("huntE", huntE);

            HashMap<String, HashMap> linkMap = new HashMap<>();
            linkMap.put("boss", bossMap);
            linkMap.put("hunt", huntMap);

            ArrayList<HyperPassiveVO> hyperList = (ArrayList<HyperPassiveVO>) classFeatureService.getHyperPassive(classUid);
            ArrayList<HyperPassiveVO> orderHPList = new ArrayList<>();
            ArrayList<HyperPassiveVO> otherHPList = new ArrayList<>();

            for (HyperPassiveVO hp : hyperList) {
                if (hp.getDisplayOrder() == 0) {
                    otherHPList.add(hp);
                } else {
                    orderHPList.add(hp);
                }
            }

            orderHPList.sort(Comparator.comparing(HyperPassiveVO::getDisplayOrder));

            HashMap<String, ArrayList> hyperPassiveMap = new HashMap<>();
            hyperPassiveMap.put("order", orderHPList);
            hyperPassiveMap.put("other", otherHPList);

            HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
            paramMap.put("classUid",classUid);

            paramMap.put("parentUid",4);
            paramMap.put("union",0);
            ArrayList<VSkillVO> BSkillListBoss = (ArrayList<VSkillVO>) classFeatureService.getVSkillOO(paramMap);

            paramMap.put("parentUid",3);
            paramMap.put("union",0);
            ArrayList<VSkillVO> CSkillListBoss = (ArrayList<VSkillVO>) classFeatureService.getVSkillOO(paramMap);

            paramMap.put("parentUid",1);
            paramMap.put("union",0);
            ArrayList<VSkillVO> SSkillListBoss = (ArrayList<VSkillVO>) classFeatureService.getVSkillOO(paramMap);


            paramMap.put("parentUid",4);
            paramMap.put("union",1);
            ArrayList<VSkillVO> BSkillListUnion = (ArrayList<VSkillVO>) classFeatureService.getVSkillOO(paramMap);

            paramMap.put("parentUid",3);
            paramMap.put("union",1);
            ArrayList<VSkillVO> CSkillListUnion = (ArrayList<VSkillVO>) classFeatureService.getVSkillOO(paramMap);

            paramMap.put("parentUid",1);
            paramMap.put("union",1);
            ArrayList<VSkillVO> SSkillListUnion = (ArrayList<VSkillVO>) classFeatureService.getVSkillOO(paramMap);

            HashMap<String, ArrayList> vSkillMap = new HashMap<>();
            vSkillMap.put("sharedBoss", SSkillListBoss);
            vSkillMap.put("classBoss", CSkillListBoss);
            vSkillMap.put("boostBoss", BSkillListBoss);
            vSkillMap.put("sharedUnion", SSkillListUnion);
            vSkillMap.put("classUnion", CSkillListUnion);
            vSkillMap.put("boostUnion", BSkillListUnion);

            model.addAttribute("detail", vo);
            model.addAttribute("linkMap", linkMap);
            model.addAttribute("ability", classFeatureService.getAbility(classUid));
            model.addAttribute("hyperPassiveMap", hyperPassiveMap);
            model.addAttribute("vSkillMap", vSkillMap);

            //캐릭터셋팅이 되었는지 확인한다
            if(!classFeatureService.isCharacterSetting(user.getUid()))
                return "redirect:/api-key";
        }

        return "ClassFeatureDetail";
    }
}

