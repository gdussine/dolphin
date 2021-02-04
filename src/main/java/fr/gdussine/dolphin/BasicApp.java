package fr.gdussine.dolphin;

import com.jump.dolphin.*;
import eu.telecomnancy.dolphin.util.Context;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Programme naif de référence.
 *
 * User: Paul PANGANIBAN <Paul.PANGANIBAN@jump-informatique.com>
 * Date: 20/07/2017
 */
public class BasicApp {


    private static final int NB_LINES = 25;

    public static void main(String[] args) throws IOException, URISyntaxException {
        Context.get();
        RESTManager locRESTManager = new RESTManager();

        //Récupère la liste de ratio
        Map<Integer,Ratio> locRatiosMap = locRESTManager.getRatioMap();

        //On récupère la liste des actions
        List<Asset> locAllAssetList = locRESTManager.getAssetList(Context.get().PTF_DATE_START);
        System.out.println(locAllAssetList);
        List<Asset> locStocksList = locAllAssetList
                .stream()
                .filter(parAsset -> parAsset.assetType == EnumAssetType.STOCK)
                .filter(parAsset -> parAsset.priceValue != null)
                .collect(Collectors.toList());

        //On applique les ratios sur la liste
        List<Ratio> locRatiosToInvoke = new ArrayList<>();
        Ratio locSharpe = locRatiosMap.get(Context.get().API_SHARPE_ID);

        locRatiosToInvoke.add(locSharpe);

        List<Integer> locIdAssetList = locStocksList.stream()
                .map(parAsset -> Integer.parseInt(parAsset.id._value))
                .collect(Collectors.toList());

        Map<Integer, Map<Integer, JumpValue>> locRatioInvoked = locRESTManager.invokeRatios(
                locRatiosToInvoke,
                locIdAssetList,
                Context.get().PTF_DATE_START,
                Context.get().PTF_DATE_STOP);

        List<RatioResult> locRatioResultList = new ArrayList<>();

        locRatioInvoked.forEach((s, stringNumberMap) ->
                locRatioResultList.add(new RatioResult(s, stringNumberMap)));

        System.out.println("\nCréation du portefeuille naif");

        //Création d'un portefeuille en fonction des volatillités
        Portfolio locSimpleSolution = createSimplePortfolio(locRESTManager,
                locRatioResultList, Context.get().API_SHARPE_ID);


        locRESTManager.pushPortfolio(Context.get().PTF_ID, locSimpleSolution);
        System.out.println("Résultat final : ");
        System.out.println(locRESTManager.getPortfolio(Context.get().PTF_ID));

    }


    /**
     * Crée un portefeuille de NB_LINES constitué des actifs ayant les meilleurs
     * résultats pour le ratios parRatioToCompare
     * @param parRestManager REST Manager
     * @param parRatioResultList List des résulats de ratios
     * @param parRatioToCompare Le ratio sur lequel on se base
     */
    private static Portfolio createSimplePortfolio(
            RESTManager parRestManager,
            List<RatioResult> parRatioResultList,
            int parRatioToCompare) {
        DynAmountLineAssetModel locAssetLine;
        DynAmountLineContainerModel locContainerLine;
        List<DynAmountLineContainerModel> locListLines = new ArrayList<>(NB_LINES);
        Map<String, List<DynAmountLineContainerModel>> locCompo = new HashMap<>();
        AssetCurrency locPtfCurrency = new AssetCurrency();
        locPtfCurrency._code = "EUR";
        AssetCurrencyManager locCurrencyManager = new AssetCurrencyManager(Context.get().PTF_DATE_START, parRestManager);
        double locQuantity;
        /*Construction du portefeuille risqué naif*/
        int locI = 0;
        while (locListLines.size() < NB_LINES) {
            RatioResult ratioResult = parRatioResultList.get(locI);
            Integer assetID = ratioResult._assetID;
            Asset locAsset = parRestManager.getAsset(assetID, Context.get().PTF_DATE_START);
            Map<String, Double> changeRateMap = locCurrencyManager.changeRateMap;
            MonetaryNumber locCloseValue = locAsset.monetaryPrice;
            locQuantity = 1000 / (locCloseValue._amount * changeRateMap.get(locCloseValue._currency));
            ///if (locQuantity >= 0.5 && locQuantity <= 2) {
            locAssetLine = new DynAmountLineAssetModel(assetID, locQuantity);
            locContainerLine = new DynAmountLineContainerModel();
            locContainerLine._asset = locAssetLine;
            locListLines.add(locContainerLine);
            //}
            locI++;
        }

        locCompo.put(Context.get().PTF_DATE_START, locListLines);

        return new Portfolio(
                Context.get().PTF_NAME,
                locPtfCurrency,
                EnumDynAmountTypeWebModel.front,
                locCompo);
    }
}
