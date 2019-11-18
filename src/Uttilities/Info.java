package Uttilities;

import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Info {
    /** Attributes **/
    String file = "C:/Users/Elève/Documents/dutINFO/S3/UE33/T3/elpresidente/src/Uttilities/info.json";
    private JSONArray indicators;
    private JSONArray levers;

    /** Constructor **/
    public Info() {
        try {
            String contents = new String((Files.readAllBytes(Paths.get(file))));
            JSONObject obj = new JSONObject(contents);
            indicators = obj.getJSONArray("indicators");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /** Methods */
    public String getIndicatorInfo(String indicator) {
        String indicInfo = "";
        boolean res = true;
        int i = 0;
        while((i < indicators.length()) && res) {
            JSONObject oIndic = indicators.getJSONObject(i);
            if(oIndic.getString("name").equals(indicator)) {
                indicInfo = oIndic.getString("description");
                res = false;
            }
            i++;
        }
        return indicInfo;
    }

    public String getLeverInfo(String indicator, String lever) {
        StringBuilder leverInfo = new StringBuilder();
        boolean resIndic = true;
        boolean resLever = true;
        int i = 0;
        int y = 0;
        while((i < indicators.length()) && resIndic) {
            JSONObject oIndic = indicators.getJSONObject(i);
            if(oIndic.getString("name").equals(indicator)) {
                levers = oIndic.getJSONArray("levers");
                while(y < levers.length() && resLever) {
                    JSONObject oLevers = levers.getJSONObject(y);
                    if(oLevers.getString("name").equals(lever)) {
                        leverInfo.append(oLevers.getString("description")).append("\n");
                        resIndic = false;
                        resLever = false;
                        if(oLevers.getInt("type") == -1) {
                            leverInfo.append("Ce levier agira de manière négative, c'est-à-dire qu'il fera baisser la valeur de l'indicateur sur lequel il agit");
                        }
                        else if(oLevers.getInt("type") == 0) {
                            leverInfo.append("Ce levier agit de manière négative et positive, c'est-à-dire qu'il peut faire baisser ou augmenter l'indicateur qu'il influence");
                        }
                        else if(oLevers.getInt("type") == 1) {
                            leverInfo.append("Ce levier agira de manière positive, c'est-à-dire qu'il fera augmenter la valeur de l'indicateur sur lequel il agit");
                        }
                    }
                    y++;
                }
            }
            i++;
        }
        return leverInfo.toString();
    }

    public static void main(String[] args) {
        Info info = new Info();
        System.out.println(info.getIndicatorInfo("nombre d'étudiant"));
        System.out.println(info.getLeverInfo("satisfaction étudiante", "cSubvention association"));
    }
}

