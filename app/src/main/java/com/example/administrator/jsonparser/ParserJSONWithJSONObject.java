package com.example.administrator.jsonparser;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChuPeng on 2016/10/10.
 */
public class ParserJSONWithJSONObject
{

    public static List<Goods> getJsonContentForJSONObject(String json)
    {
        List<Goods> goodsList = new ArrayList<Goods>();
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            String rt = jsonObject.getString("rt");
            JSONObject jsonObjectRt = new JSONObject(rt);
            String tasks = jsonObjectRt.getString("tasks");
            JSONObject jsonObjectTasks = new JSONObject(tasks);
            String task = jsonObjectTasks.getString("task");
            JSONArray jsonArray = new JSONArray(task);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                Goods goods = new Goods();
                JSONObject jsonObjectTask = jsonArray.getJSONObject(i);
                goods.setName(jsonObjectTask.getString("name"));
                goods.setRefercode(jsonObjectTask.getString("refercode"));
                goods.setStatus(jsonObjectTask.getString("status"));
                goodsList.add(goods);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return goodsList;
    }
}
