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
 * daté du 17/07/2017.
 */

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Représente une ligne d'actifs de portefeuille
 *
 * User: Paul PANGANIBAN <Paul.PANGANIBAN@jump-informatique.com>
 * Date: 17/07/2017
 */
public final class DynAmountLineAssetModel {
  /**
   * Identifiant de l'actif.
   */
  @SerializedName("asset")
  public int _asset;
  /**
   * Quantité de l'actif _asset.
   */
  @SerializedName("quantity")
  public Double _quantity;

  public DynAmountLineAssetModel(final int parAsset,
                                 final Double parQuantity) {
    _asset = parAsset;
    _quantity = parQuantity;
  }

  public String toString(){
    return "(com.jump.dolphin.Asset : "+_asset+", "+_quantity+")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DynAmountLineAssetModel that = (DynAmountLineAssetModel) o;
    return _asset == that._asset &&
            Objects.equals(_quantity, that._quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_asset, _quantity);
  }
}

