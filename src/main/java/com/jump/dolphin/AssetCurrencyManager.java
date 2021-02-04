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
 * daté du 21/07/2017.
 */

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe permettant de gérer les conversion de devise.
 * User: Paul PANGANIBAN <Paul.PANGANIBAN@jump-informatique.com>
 * Date: 21/07/2017
 */
public final class AssetCurrencyManager {
  public static String PTF_CURRENCY = "EUR";
  public static List<String> CURR_LIST = Arrays.asList(
          "EUR",
          "GBp",
          "JPY",
          "NOK",
          "SEK",
          "USD"
  );

  public Map<String, Double> changeRateMap = new HashMap<>();

  public AssetCurrencyManager(@Nonnull String parDate,
                              @Nonnull RESTManager parRESTManager) {

   for (String locCurrency : CURR_LIST) {
     changeRateMap.put(locCurrency, parRESTManager.getChangeRate(parDate, locCurrency, PTF_CURRENCY));
   }
   changeRateMap.put(PTF_CURRENCY, 1.0);
  }
}
