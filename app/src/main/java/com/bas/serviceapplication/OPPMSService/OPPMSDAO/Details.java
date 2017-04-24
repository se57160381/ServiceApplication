package com.bas.serviceapplication.OPPMSService.OPPMSDAO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by BassAye on 17/3/2560.
 */

public class Details {

    @SerializedName("programName")
    public String programName;

    @SerializedName("projectName")
    public String projectName;

    @SerializedName("dateStart")
    public String dateStart;

    @SerializedName("pspType")
    public String pspType;

    @SerializedName("creator")
    public String creator;

    @SerializedName("success")
    public boolean success;

    @SerializedName("languages")
    public ArrayList<String> languages;
}
