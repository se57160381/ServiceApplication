package com.bas.serviceapplication.OPPMSService;

import com.bas.serviceapplication.OPPMSService.OPPMSDAO.OPPMSDAO;
import com.bas.serviceapplication.OPPMSService.OPPMSDAO.SendQuick;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by BassAye on 17/3/2560.
 */

public interface OPPMSService {

    @POST("index.php/OPPMS/service_android/graph_cycle")
    Call<OPPMSDAO> getOPPMSData();

    @FormUrlEncoded
    @POST("index.php/OPPMS/service_android/send_quick")
    Call<SendQuick> sendData(@Field("id") String id,@Field("name") String name);
}
