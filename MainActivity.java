package proveb.gk.com.foresightsqlite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import proveb.gk.com.foresightsqlite.Adapter.CustomAdapter;
import proveb.gk.com.foresightsqlite.model.Jsonmodel;

public class MainActivity extends Activity {
    private TextView degree, specialty;
    private ListView list;
    private ArrayList<Jsonmodel> jsonmodelArrayList;
    Jsonmodel jsonmodel;
    CustomAdapter customAdapter;
    public DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        degree = (TextView) findViewById(R.id.tv_degree);
        specialty = (TextView) findViewById(R.id.tv_specialty);
        list = (ListView) findViewById(R.id.lv_list);
        jsonmodelArrayList = new ArrayList<>();
        dbHelper = new DBHelper(MainActivity.this);
//        getDetails();
        getList();

    }
    private void getDetails() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final String url = "http://www.elitedoctorsonline.com/api_mob/browser/search/search.aspx?cou_id=211&lang=en";
        asyncHttpClient.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                Log.e("url", "success" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("doctor_data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        if (dbHelper.insertdoctordetails(String.valueOf(i + 1), object.getString("degree"), object.getString("specialty"))) {
                            Log.e("db", "stored" + i);
                        } else {
                            Log.e("db", "not stored" + i);
                        }
//                        Log.e("object", String.valueOf(object));
//                        jsonmodel=new Jsonmodel();
//                        jsonmodel.setDegree(object.getString("degree"));
//                        Log.e("degree=", String.valueOf(degree));
//                        jsonmodel.setSpecialty(object.getString("specialty"));
//                        Log.e("specialty=", String.valueOf(specialty));
//                        jsonmodelArrayList.add(jsonmodel);
                    }
//                    customAdapter =new CustomAdapter(MainActivity.this,jsonmodelArrayList);
//                    list.setAdapter(customAdapter);

                    getList();
                } catch (Exception e) {
                    Log.e("Exception=", e.toString());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void getList() {
        jsonmodelArrayList = dbHelper.getalldoctor();
        Log.e("size---",String.valueOf(jsonmodelArrayList.size()));
        if (jsonmodelArrayList.size() == 0) {
            getDetails();
        } else {

            customAdapter = new CustomAdapter(MainActivity.this, jsonmodelArrayList);
            list.setAdapter(customAdapter);
        }
    }

}
