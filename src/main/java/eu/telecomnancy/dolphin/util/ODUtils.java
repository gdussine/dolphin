package eu.telecomnancy.dolphin.util;

import com.jump.dolphin.*;
import eu.telecomnancy.dolphin.model.ODPortfolio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ODUtils {


    public static int randint(int size, int start){
        return (int)randouble(size, start);
    }

    public static double randouble(double size, double start){
        return Math.random()*size +start;
    }

    public static List<Integer> copy(List<Integer> src){
        List<Integer> copy = new ArrayList<>();
        for(Integer integer : src){
            copy.add(integer);
        }
        return copy;

    }

    public static ODPortfolio fromPortfolio(Portfolio portfolio){
        ODPortfolio result = new ODPortfolio();
        for(DynAmountLineContainerModel line : portfolio._values.get(Context.get().PTF_DATE_START)){
            result.put(line._asset._asset, line._asset._quantity);
        }
        return result;
    }

    public static Portfolio toPorftolio(ODPortfolio portfolio){
        Map<String, List<DynAmountLineContainerModel>> map = new HashMap<String, List<DynAmountLineContainerModel>>();
        List<DynAmountLineContainerModel> list = new ArrayList<>();
        for( Map.Entry<Integer, Double> entry: portfolio.getMap().entrySet()){
            list.add(DynAmountLineContainerModel.of(new DynAmountLineAssetModel(entry.getKey(), entry.getValue())));
        }
        AssetCurrency currency = new AssetCurrency();
        currency._code="EUR";
        map.put(Context.get().PTF_DATE_START, list);
        return new Portfolio(Context.get().PTF_NAME, currency, EnumDynAmountTypeWebModel.front, map);
    }

    public static int[] distribPresqueAlea(int length){
        int[] res = new int[length];
        int i = 0;
        while( i != length/2){
            int rand = ODUtils.randint(length,0);
            if(res[rand] != 1){
                res[rand] = 1;
                i++;
            }
        }
        return res;
    }



}
