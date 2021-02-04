package com.jump.dolphin;/*
 *      _   _   _       ___  ___   _____            ___       ___  ___   _____
 *     | | | | | |     /   |/   | |  _  \          /   |     /   |/   | /  ___/
 *     | | | | | |    / /|   /| | | |_| |         / /| |    / /|   /| | | |___
 *  _  | | | | | |   / / |__/ | | |  ___/        / / | |   / / |__/ | | \___  \
 * | |_| | | |_| |  / /       | | | |           / /  | |  / /       | |  ___| |
 * \_____/ \_____/ /_/        |_| |_|          /_/   |_| /_/        |_| /_____/
 *
 *
 * Jump com.jump.dolphin.Asset Management Solution Jump Informatique. Tous droits réservés.
 * Ce programme est protégé par la loi relative au droit d'auteur et par les conventions internationales.
 * Toute reproduction ou distribution partielle ou totale du logiciel, par quelque moyen que ce soit, est
 * strictement interdite. Toute personne ne respectant pas ces dispositions se rendra coupable du délit de
 * contrefaçon et sera passible des sanctions pénales prévues par la loi.
 * daté du 20/07/2017.
 */

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Paul PANGANIBAN <Paul.PANGANIBAN@jump-informatique.com>
 * Date: 20/07/2017
 */
public class Price {
    /**
     * Date des valeurs
     */
    public JumpValue date;
    /**
     * NAV
     */
    @SerializedName("nav")
    public JumpValue _nav;
    /**
     * Montant brut
     */
    @SerializedName("gross")
    public JumpValue _gross;
    /**
     * +/- value
     */
    @SerializedName("pl")
    public JumpValue _pl;
    /**
     * Valeur de clotûre
     */
    @SerializedName("close")
    public JumpValue _close;
    /**
     * Rendement
     */
    @SerializedName("return")
    public JumpValue _return;

    @Override
    public String toString() {
        return date._value + " : " + _close._value;
    }
}