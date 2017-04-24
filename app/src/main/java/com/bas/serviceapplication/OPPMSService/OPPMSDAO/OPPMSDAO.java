package com.bas.serviceapplication.OPPMSService.OPPMSDAO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by BassAye on 17/3/2560.
 */

public class OPPMSDAO {
    @SerializedName("details")
    public Details details;

    @SerializedName("graphData")
    public ArrayList<GraphData> graphData;



}
