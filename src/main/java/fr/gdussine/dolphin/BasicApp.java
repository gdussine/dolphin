package fr.gdussine.dolphin;

import com.google.gson.Gson;
import com.jump.dolphin.*;
import eu.telecomnancy.dolphin.util.Context;
import fr.gdussine.dolphin.api.JumpService;
import fr.gdussine.dolphin.core.PortfolioBuilder;
import fr.gdussine.dolphin.core.PortfolioSelector;
import fr.gdussine.dolphin.core.Synergie;
import fr.gdussine.dolphin.core.SynergieSaver;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

    public static void main(String[] args){
        Context.get();
        JumpService api = JumpService.getInstance();
        RESTManager locRESTManager = new RESTManager();
        List<Ratio> locRatiosToInvoke = List.of(api.getSharp());
        List<Asset> assets = api.getAssets();
        List<Integer> locIdAssetList = api.getAssetsId();

        SynergieSaver saver = new SynergieSaver();
        try {
            saver.calcul();
        } catch (IOException e) {

        }
    }
}
