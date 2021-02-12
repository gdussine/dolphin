package fr.gdussine.dolphin.core;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import fr.gdussine.dolphin.api.JumpService;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SynergieSaver {

    private final Map<Integer, Double> mapRatio;
    private Map<Integer, Synergie> synergies;
    private Type targetType;
    private File memoryFile;

    public SynergieSaver(){
        targetType = new TypeToken<Map<Integer, Synergie>>() {}.getType();
        memoryFile = new File("train/memoire2.txt");
        mapRatio = JumpService.getInstance().getAllSharpValue();
    }

    public void read() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(memoryFile);
        synergies = gson.fromJson(reader, targetType);
        if(synergies == null)
            synergies = new HashMap<>();
        reader.close();
    }

    public void calcul() throws IOException {
        JumpService api = JumpService.getInstance();
        List<Integer> allIds = api.getAssetsId();
        List<Integer> sortedIds = allIds.stream().sorted((o1,o2)->Double.compare(mapRatio.get(o2), mapRatio.get(o1))).collect(Collectors.toList()).subList(0,100);
        System.out.println(sortedIds);
        for(int id : sortedIds){
            //System.out.println("Calcul de l'id "+id);
            read();
            Synergie synergie = new Synergie(id, mapRatio);
            synergie.loadMap(sortedIds, synergies);
            synergies.put(id, synergie);
            write();
            //System.out.println("Fin du calcul de l'id "+id);
        }
    }

    public void write() throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(memoryFile);
        writer.write(gson.toJson(synergies));
        writer.close();
    }

    public Map<Integer, Synergie> getSynergies() {
        return synergies;
    }
}
