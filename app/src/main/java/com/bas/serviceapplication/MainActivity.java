package com.bas.serviceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bas.serviceapplication.OPPMSService.OPPMSDAO.OPPMSDAO;
import com.bas.serviceapplication.OPPMSService.OPPMSDAO.SendQuick;
import com.bas.serviceapplication.OPPMSService.OPPMSService;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private  OPPMSService service;
    private PieChart mypiechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //เชื่อม mypiechat
        mypiechart = (PieChart) findViewById(R.id.mypiechart);

        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://10.16.68.147/ppms/")
                                .addConverterFactory(GsonConverterFactory.create())   //ไว้เอาข้อมูลไปใส่เพื่อเข้าถึงข้อมูล
                                .build();

        service = retrofit.create(OPPMSService.class);

        //เรียกใช้ service
        service.getOPPMSData().enqueue(new Callback<OPPMSDAO>() {
            @Override
            public void onResponse(Call<OPPMSDAO> call, Response<OPPMSDAO> response) {
                Log.d("RESPONSE","SUCCESS");
                if (response.isSuccessful()) {
                    //Log.d("RESPONSE", response.body().details.creator);  ลบLog อันเก่าออกไป แล้วเริ่มเขียนใหม่ด้านล่าง
                    ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
                    //ทำกราฟ จากฐานข้อมูล
                    for(int i=0; i<response.body().graphData.size();i++)
                    {
                        entries.add(new PieEntry(
                                response.body().graphData.get(i).second,
                                response.body().graphData.get(i).phaseName)
                        );


                        PieDataSet pieDataSet = new PieDataSet(entries,"อธิบายกราฟ ##");
                        //ทำสี
                        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        PieData pieData = new PieData(pieDataSet); //เอา pieDataSet จากข้างบนมาใส่
                        mypiechart.setData(pieData);
                    }
                }else {
                    Log.d("RESPONSE","service error");
                }
            }

            @Override
            public void onFailure(Call<OPPMSDAO> call, Throwable t) {
                Log.d("RESPONSE",t.getMessage());
            }
        });

//        service.sendData("57160381","Perawat").enqueue(new Callback<SendQuick>() {
//            @Override
//            public void onResponse(Call<SendQuick> call, Response<SendQuick> response) {
//                Log.d("RESPONSE",response.body().result);
//            }
//
//            @Override
//            public void onFailure(Call<SendQuick> call, Throwable t) {
//
//            }
//        });
    }
}
