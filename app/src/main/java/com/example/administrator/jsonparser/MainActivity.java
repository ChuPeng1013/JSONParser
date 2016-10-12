package com.example.administrator.jsonparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private List<Goods> goodsList;
    private Button JSONObjectParser;
    private Button GSONParser;
    private Button Clean;
    private String result;
    private ListView listview;
    private ListViewAdapter adapter;
    private Gson gson;
    private String json = "{" +
            "rt:{" +
            "tid:20140318155513001," +
            "warehouseid:47a7377d4a9e42e3a665af0894946e21," +
            "rc:0000," +
            "rm:成功," +
            "tasks:{" +
            "task:[" +
            "{" +
            "name:102," +
            "refercode:150303101458414," +
            "status:3" +
            "}," +
            "{" +
            "name:119," +
            "refercode:20150303-6," +
            "status:3" +
            "}," +
            "{" +
            "name:202," +
            "refercode:150307125515568," +
            "status:3" +
            "}," +
            "{" +
            "name:107," +
            "refercode:150303140159283," +
            "status:3" +
            "}" +
            "]" +
            "}" +
            "}" +
            "} ";
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JSONObjectParser = (Button) findViewById(R.id.JSONObjectParser);
        GSONParser = (Button) findViewById(R.id.GSONParser);
        Clean = (Button) findViewById(R.id.Clean);
        listview = (ListView) findViewById(R.id.listviewContent);
        JSONObjectParser.setOnClickListener(this);
        GSONParser.setOnClickListener(this);
        Clean.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.JSONObjectParser:
                goodsList = ParserJSONWithJSONObject.getJsonContentForJSONObject(json);
                adapter = new ListViewAdapter(MainActivity.this, goodsList);
                listview.setAdapter(adapter);
                break;
            case R.id.GSONParser:
                try
                {
                    JSONObject jsonObject = new JSONObject(json);
                    String rt = jsonObject.getString("rt");
                    JSONObject jsonObjectRt = new JSONObject(rt);
                    String tasks = jsonObjectRt.getString("tasks");
                    JSONObject jsonObjectTasks = new JSONObject(tasks);
                    String task = jsonObjectTasks.getString("task");
                    gson = new Gson();
                    goodsList = gson.fromJson(task, new TypeToken<List<Goods>>(){}.getType());
                    adapter = new ListViewAdapter(MainActivity.this, goodsList);
                    listview.setAdapter(adapter);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.Clean:
                listview.setAdapter(null);
                break;
        }
        if(v.getId() == R.id.JSONObjectParser)
        {
            result = "JSONObject解析数据成功！";
        }
        else if(v.getId() == R.id.GSONParser)
        {
            result = "GSON解析数据成功！";
        }
        else if(v.getId() == R.id.Clean)
        {
            result = "数据清空成功！";
        }
        //listview加载完成后触发此方法
        listview.addOnLayoutChangeListener(new View.OnLayoutChangeListener()
        {
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
            {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
