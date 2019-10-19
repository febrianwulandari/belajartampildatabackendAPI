package com.iky.myapplicationapidb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rv_pegawai ;
    private PegawaiAdapter adapter;
    private ArrayList<Pegawai> pegawais;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_pegawai = findViewById(R.id.rv_pegawai);
        adapter =  new PegawaiAdapter(this);
        gson = new Gson();
        ambilData();
        LinearLayoutManager lm = new LinearLayoutManager(this);
        DividerItemDecoration divider = new DividerItemDecoration(this, lm.getOrientation());
        rv_pegawai.setLayoutManager(lm);
        rv_pegawai.setAdapter(adapter);
        rv_pegawai.addItemDecoration(divider);
    }

    private void ambilData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.15.2/demoapi/tampilsemuapgw.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PegawaiResult result = gson.fromJson(response,PegawaiResult.class);
                        pegawais = result.getResult();
                        adapter.setPegawais(pegawais);
                    }
                },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){

                        }
                    }
                );
        queue.add(stringRequest);
                }
                public void aksiTambah(View view){
                    RequestQueue queue = Volley.newRequestQueue(this);
                    String url = "http://192.168.15.2/demoapi/tambahpgw.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                 Log.d("responapp", "Hasil" + response);
                                }
                            },
                            new Response.ErrorListener(){
                                @Override
                                public void onErrorResponse(VolleyError error){

                                }
                            }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String > parameter = new HashMap<>();
                            parameter.put("name","siva");
                            parameter.put("position","Programmer");
                            parameter.put("salary","5000");
                            return parameter;
                        }
                    };
                    queue.add(stringRequest);
                }
                }

