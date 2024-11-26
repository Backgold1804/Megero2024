package com.example.mymaple.util;

import java.util.*;


import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NexonAPI {
    private static final String CHARACTER_LIST = "https://open.api.nexon.com/maplestory/v1/character/list";
    private static final String CHARACTER_BASIC = "https://open.api.nexon.com/maplestory/v1/character/basic";
    private static final String UNION_RAIDER = "https://open.api.nexon.com/maplestory/v1/user/union-raider";
    private static final String UNION_ARTIFACT = "https://open.api.nexon.com/maplestory/v1/user/union-artifact";

    public static String connection_nexon(String url, String api_key) throws  Exception{
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url); //GET 메소드 URL 생성
        getRequest.addHeader("x-nxopen-api-key", api_key); //KEY 입력
        getRequest.addHeader("accept","application/json");
        CloseableHttpResponse response = client.execute(getRequest);

        //Response 출력
        String body = "";

        if (response.getStatusLine().getStatusCode() == 200) {
            ResponseHandler<String> handler = new BasicResponseHandler();
            body = handler.handleResponse(response);
            System.out.println(body);
        } else {
            System.out.println("response is error : " + response.getStatusLine().getStatusCode());
        }
        return body;
    }


    public static String getString(Object obj){
        if(obj == null)
            return "";
        else
            return obj.toString();
    }
    //캐릭터 상세
    public static Map<String,String> character_basic(String api_key, String ocid){

        // 인증키 (개인이 받아와야함)
        String key = "";

        // 파싱한 데이터를 저장할 변수
        String result = "";
        String body = "";
        try {

            body = connection_nexon(CHARACTER_BASIC+"?ocid="+ocid,api_key);
            System.out.println("CHARACTER_BASIC : " + body);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(body);
            Map<String, String> map = new HashMap<String,String>();
            map.put("date", NexonAPI.getString(jsonObject.get("date")));
            map.put("character_name", NexonAPI.getString(jsonObject.get("character_name")));
            map.put("world_name", NexonAPI.getString(jsonObject.get("world_name")));
            map.put("character_gender", NexonAPI.getString(jsonObject.get("character_gender")));
            map.put("character_class", NexonAPI.getString(jsonObject.get("character_class")));
            map.put("character_class_level", NexonAPI.getString(jsonObject.get("character_class_level")));
            map.put("character_image", NexonAPI.getString(jsonObject.get("character_image")));


            return map;

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //캐릭터리스트
    public static List<Map<String,String>> character_list(String api_key){

        // 인증키 (개인이 받아와야함)
        String key = "";

        // 파싱한 데이터를 저장할 변수
        String result = "";
        String body = "";
        try {

            body = connection_nexon(CHARACTER_LIST,api_key);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(body);
            JSONArray json = (JSONArray) jsonObject.get("account_list");
            JSONObject account_list = (JSONObject) json.get(0);
            JSONArray character_list = (JSONArray) account_list.get("character_list");

            ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
            for(int i = 0; i < character_list.size(); i++) {
                JSONObject character = (JSONObject)character_list.get(i);
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("ocid", NexonAPI.getString(character.get("ocid")));
                map.put("character_name",NexonAPI.getString(character.get("character_name")));
                map.put("world_name",NexonAPI.getString(character.get("world_name")));
                map.put("character_class",NexonAPI.getString(character.get("character_class")));
                map.put("character_level",NexonAPI.getString(character.get("character_level")));
                list.add(map);
            }

            return list;

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 크리티컬 확률
    public static HashMap<String,Object> critical_rate_map(String api_key, String ocid){

        // 인증키 (개인이 받아와야함)
        String key = "";

        // 파싱한 데이터를 저장할 변수
        String result = "";
        String body = "";
        try {
            body =connection_nexon(UNION_ARTIFACT+"?ocid="+ocid, api_key);

            int critical_rate = 0;
            HashMap<String, Object> map = new HashMap<String, Object>();
            List<Integer> list = new ArrayList<Integer>();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
            JSONArray artifact_effect = (JSONArray)jsonObject.get("union_artifact_effect");
            for (int i = 0; i < artifact_effect.size(); i++) {
                JSONObject tempObject = (JSONObject) artifact_effect.get(i);

                if (NexonAPI.getString(tempObject.get("name")).contains("크리티컬 확률")) {
                    long t = (long) tempObject.get("level");
                    critical_rate += Long.valueOf(t).intValue() * 2;
                    break;
                }
            }

            body = connection_nexon(UNION_RAIDER+"?ocid="+ocid, api_key);

            jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(body);
            int use_no = Long.valueOf((long) jsonObject.get("use_preset_no")).intValue();
            map.put("use_no", use_no);

            System.out.println("use_no: " + map.keySet() + "no. " + map.get("use_no"));

            Iterator keys = jsonObject.keySet().iterator();
            String objKey = "";

            int preset_no = 0;

            for (int i = 0; i < jsonObject.size(); i++) {
                objKey = NexonAPI.getString(keys.next());

                if (objKey.contains("union_raider_preset")) {
                    int raiderRate = 0;
                    preset_no++;

                    JSONObject preset = (JSONObject) jsonObject.get(objKey);

                    JSONArray raiderArray = (JSONArray) preset.get("union_raider_stat");
                    for (int j = 0; j < raiderArray.size(); j++) {
                        String tmpStat = NexonAPI.getString(raiderArray.get(j));
                        if (tmpStat.contains("크리티컬 확률")) {
                            int temp = Integer.parseInt(tmpStat.substring(8, tmpStat.indexOf("%")));
                            raiderRate += temp;
                        }
                    }

                    JSONArray occupiedArray = (JSONArray) preset.get("union_occupied_stat");
                    for (int j = 0; j < occupiedArray.size(); j++) {
                        String tmpStat = NexonAPI.getString(occupiedArray.get(j));
                        if (tmpStat.contains("크리티컬 확률")) {
                            int temp = Integer.parseInt(tmpStat.substring(8, tmpStat.indexOf("%")));
                            raiderRate += temp;
                        }
                    }

                    raiderRate += critical_rate;

                    System.out.println("preset_no: " + preset_no + ", raiderRate: " + raiderRate);
                    list.add(raiderRate);
//                    String preset_num = new String();
//                    switch (preset_no) {
//                        case 1:
//                            preset_num = "preset_one";
//                            break;
//                        case 2:
//                            preset_num = "preset_two";
//                            break;
//                        case 3:
//                            preset_num = "preset_three";
//                            break;
//                        case 4:
//                            preset_num = "preset_four";
//                            break;
//                        case 5:
//                            preset_num = "preset_five";
//                            break;
//                        default:
//                            preset_num = "";
//                            break;
//                    }
//                    map.put(preset_num, raiderRate);
                }
            }
            map.put("list",list);
            return map;

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
