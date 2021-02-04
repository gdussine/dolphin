package com.jump.dolphin;

import com.google.gson.annotations.SerializedName;

public class ChangeRate {
    /**
     * Date des valeurs
     */
    public JumpValue date;
    /**
     * NAV
     */
    @SerializedName("src_curr")
    public JumpValue _src;
    /**
     * Montant brut
     */
    @SerializedName("tgt_curr")
    public JumpValue _tgt;
    /**
     * +/- value
     */
    @SerializedName("rate")
    public JumpValue _rate;
}
