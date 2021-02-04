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
 * daté du 21/09/2017.
 */

import com.jump.dolphin.JumpValue;
import com.jump.dolphin.NumberValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Paul PANGANIBAN <p.panganiban at jum-informatique.com>
 *         Date: 21/09/2017
 */
public class RatioResult {
  public Integer _assetID;
  public Map<Integer, NumberValue> _ratioValue;

  public RatioResult(Integer parAssetID, Map<Integer, JumpValue> parMapRatio) {
    _assetID = parAssetID;
    _ratioValue = new HashMap<>();
    parMapRatio.forEach((s, number)-> _ratioValue.put(s, (NumberValue) (number)));
  }

  public String toString(){
    final StringBuffer sb = new StringBuffer(_assetID + " : [");
    _ratioValue.forEach((k,v)->sb.append(k+":"+v+" "));
    return sb.toString()+"]";
  }
}

