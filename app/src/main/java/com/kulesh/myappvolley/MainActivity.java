package com.kulesh.myappvolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void hiVolley(View view) {
        String namii=((EditText) findViewById(R.id.etxt)).getText().toString();
        String  url = String.format("http://ipaddresshere/volley/vuser.php?naga="+namii);
        TextView tx=findViewById(R.id.txt);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    int count=0;
                    while (count<response.length()){
                        try {
                            JSONObject jsonObject=response.getJSONObject(count);
                            tx.setText(jsonObject.getString("name")+" "+jsonObject.getString("email")+
                                    " "+jsonObject.getString("mobile")+" "+jsonObject.getString("password"));
                            count++;


                        }catch (Exception e){

                        }
                    }

                },
                error -> Log.i("rrrrr",error.getMessage())
        );MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    public void hi(View view) {
        String namii=((EditText) findViewById(R.id.etxt)).getText().toString();
        String  url = String.format("http://ipaddresshere/volley/wuser.php");

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView tx=findViewById(R.id.txt);

                tx.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("naga",namii);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void postVolley(View view) {
        String jam=((EditText)findViewById(R.id.etxt2)).getText().toString();
        String url=String.format("http://ipaddresshere/volley/wuser.php");
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 TextView tv=findViewById(R.id.txt2);
                 tv.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String>params=new HashMap<>();
                params.put("naga",jam);
                return  params;
            }
        };RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
