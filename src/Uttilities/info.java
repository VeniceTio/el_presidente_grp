package Uttilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class info {
    String file = "C:/Users/Elève/Documents/dutINFO/S3/UE33/T3/elpresidente/src/Uttilities/info.json";
    private JSONArray indicators;
    private JSONArray levers;

    /**
     * Méthode permettant de créer une instance de classe info
     */
    public info() {
        try {
            String contents = new String((Files.readAllBytes(Paths.get(file))));
            JSONObject obj = new JSONObject(contents);
            indicators = obj.getJSONArray("indicators");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méhode permettant de renvoyer l'index d'un indicateur ou d'un levier
     * en spécifiant son nom et le tableau dans lequel il est
     * @param name représente le nom de l'indicateur ou du levier recherché
     * @param array le tableau dans lequel l'indicateur ou le levier est répertorié
     * @return l'index de l'indicateur ou du levier qu'on recherche
     */
    private Integer getIndex(String name, JSONArray array) {
        boolean res = false;
        int i = 0, index = -1;

        while ((i < array.length()) && !res) {
            JSONObject oIndic = array.getJSONObject(i);
            if (oIndic.getString("name").equals(name)) {
                index = i;
                res = true;
            }
            i++;
        }

        return index;
    }

    /**
     * Méthode permettant de renvoyer l'index d'un indicateur
     * @param indicator l'indicateur recherché
     * @return l'index de l'indicateur recherché
     */
    public Integer getIndicatorIndex(String indicator) {
        if(getIndex(indicator, indicators) == -1) {
            throw new IndexOutOfBoundsException();
        }
        return getIndex(indicator, indicators);
    }

    /**
     * Méthode permettant de renvoyer l'index d'un levier
     * @param lever le levier recherché
     * @return l'index du levier recherché
     */
    public Integer getLeverIndex(String lever) {
        if(getIndex(lever, levers) == -1) {
            throw new IndexOutOfBoundsException();
        }
        return getIndex(lever, levers);
    }

    /**
     * Méthode permettant de renvoyer une chaîne qui précise si le levier
     * influe négativement, positivement ou les deux en même temps
     * @param i le type du levier
     * @return un chaîne contenant les informations à propos de ce levier
     */
    public String getLeverInfluence(int i) {
        String res = "";

        if(i == -1)
            res = "Ce levier agira de manière négative, c'est-à-dire qu'il fera baisser la valeur de l'indicateur sur lequel il agit";
        else if(i == 0)
            res = "Ce levier agit de manière négative et positive, c'est-à-dire qu'il peut faire baisser ou augmenter l'indicateur qu'il influence";
        else if(i == 1)
            res = "Ce levier agira de manière positive, c'est-à-dire qu'il fera augmenter la valeur de l'indicateur sur lequel il agit";

        return res;
    }

    /**
     * Méthode permettant de renvoyer une chaîne contenant les informations
     * à propos d'un indicateur précis
     * @param indicator l'indicateur dont on souhaite récuperer les informations
     * @return la chaîne contenant les informations
     */
    public String getIndicatorInfo(String indicator) {
        String indicInfo;

        JSONObject oIndic = indicators.getJSONObject(getIndicatorIndex(indicator));
        indicInfo = oIndic.getString("description");

        return indicInfo;
    }

    /**
     * Méthode permettant de renvoyer une chaîne contenant les informations
     * à propos d'un levier précis
     * @param indicator l'indicateur sur lequel influe le levier
     * @param lever le levier dont on souhaite récuperer les informations
     * @return la chaîne contenant les informations
     */
    public String getLeverInfo(String indicator, String lever) {
        StringBuilder leverInfo = new StringBuilder();
        int indicatorIndex = this.getIndicatorIndex(indicator);

        JSONObject oIndic = indicators.getJSONObject(indicatorIndex);
        levers = oIndic.getJSONArray("levers");

        int leverIndex = getLeverIndex(lever);
        JSONObject oLevers = levers.getJSONObject(leverIndex);

        leverInfo.append(oLevers.getString("description")).append("\n");
        leverInfo.append(getLeverInfluence(oLevers.getInt("type")));

        return leverInfo.toString();
    }

    public static void main(String[] args) {
        info info = new info();
        System.out.println(info.getIndicatorInfo("nombre d'étudiant"));
        System.out.println(info.getLeverInfo("charge de travail", "nombre de professeur"));
    }
}

