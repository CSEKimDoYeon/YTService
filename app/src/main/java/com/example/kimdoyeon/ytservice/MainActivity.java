package com.example.kimdoyeon.ytservice;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.kimdoyeon.ytservice.DataObjects.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Video> myDataset;

    //ListView lv;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;

    ArrayList<Video> video_Arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //lv = findViewById(R.id.lv_data);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);

    }


    public void parseYTVideoData(String keyword){
        myDataset = new ArrayList<Video>();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String result = "";
        List<String> list = new ArrayList<String>();

        video_Arr = new ArrayList<Video>(); // 음악 데이터 객체들을 저장할 배열.

        try {
            URL url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&q="+keyword+
                        "&maxResults=10&key=AIzaSyACiPdTjfzQiOPSK84TSo7pTzyzp8YDdQw");
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            while (true) {
                String data = reader.readLine();
                if (data == null) break;
                result += data+"\n";
            }
            Log.e("MainActivity", result);
            JSONObject obj = new JSONObject(result);
            JSONArray arr = (JSONArray) obj.get("items");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject item = (JSONObject) arr.get(i);
                JSONObject snippet = (JSONObject) item.get("snippet");
                String title = (String) snippet.get("title");
                String description = (String) snippet.get("description");


                JSONObject thumbnails = (JSONObject) snippet.get("thumbnails");
                JSONObject def = (JSONObject) thumbnails.get("high");
                String thumbnail_Url = (String) def.get("url");

                JSONObject id = (JSONObject) item.get("id");
                String object_Id = "";
                int is_Playlist = 0;
                if ((id.get("kind")).equals("youtube#video")){
                    object_Id = (String) id.get("videoId");
                    is_Playlist = 0;
                }
                else if ((id.get("kind")).equals("youtube#playlist")){
                    object_Id = (String) id.get("playlistId");
                    is_Playlist = 1;
                }

                Log.e("Data >>", "\n " + title + " \n " + description + " \n " + thumbnail_Url + " \n "
                        + object_Id + " \n ");

                list.add(title); // 화면에 뿌려주기 위해 타이틀들만 따로 리스트에 추가
                
                Video v = new Video(title, description, object_Id, thumbnail_Url, is_Playlist ); // 비디오 객체 생성
                video_Arr.add(v); // 비디오 배열에 비디오 객체를 저장한다.

                myDataset.add(v);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, list) ;

        // listview 생성 및 adapter 지정.
        //final ListView listview = (ListView) findViewById(R.id.lv_data) ;
        //listview.setAdapter(adapter) ;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1 :
                parseYTVideoData("기쁠 때 듣는 음악");
                break ;
            case R.id.btn_2 :
                parseYTVideoData("즐거울 때 듣는 음악");
                break ;
            case R.id.btn_3 :
                parseYTVideoData("슬플 때 듣는 음악");
                break ;
            case R.id.btn_4 :
                parseYTVideoData("우울할 때 듣는 음악");
                break ;
            case R.id.btn_5 :
                parseYTVideoData("화날 때 듣는 음악");
                break ;
        }
    }

}