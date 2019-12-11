package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Info {
    final String indicatorsFile = "src/utils/indicators.json";
    final String leversFile = "src/utils/levers.json";
    private JSONArray indicators;
    private JSONArray levers;

    /**
     * Méthode permettant de créer une instance de classe info
     */
    public Info() {
        try {
            String dir = new java.io.File( "." ).getCanonicalPath();
            String indicatorsContents = new String((Files.readAllBytes(Paths.get(dir + "\\utils\\indicators.json"))));
            String leversContents = new String((Files.readAllBytes(Paths.get(dir + "\\utils\\levers.json"))));

            JSONObject indicatorObj = new JSONObject(indicatorsContents);
            JSONObject leverObj = new JSONObject(leversContents);

            indicators = indicatorObj.getJSONArray("indicators");
            levers = leverObj.getJSONArray("levers");
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
    private int getIndex(String name, JSONArray array) {
        boolean res = false;
        int i = 0, index = 0;

        if(!(name.isEmpty() && array.isEmpty())) {
            while ((i < array.length()) && !res) {
                JSONObject oIndic = array.getJSONObject(i);
                if (oIndic.getString("name").equals(name)) {
                    index = i;
                    res = true;
                }
                else {
                    index = -1;
                }
                i++;
            }
        }
        return index;
    }

    /**
     * Méthode permettant de renvoyer l'index d'un indicateur
     * @param indicator l'indicateur recherché
     * @return l'index de l'indicateur recherché
     */
    public int getIndicatorIndex(String indicator) {
        return getIndex(indicator, indicators);
    }

    /**
     * Méthode permettant de renvoyer l'index d'un levier
     * @param lever le levier recherché
     * @return l'index du levier recherché
     */
    public int getLeverIndex(String lever) {
        return getIndex(lever, levers);
    }

    /**
     * Méthode permettant de renvoyer une chaîne qui précise si le levier
     * influe négativement, positivement ou les deux en même temps
     * @param i le type du levier
     * @return une chaîne contenant les informations à propos de ce levier
     */
    public String getLeverInfluence(int i) {
        String res = "";

        switch(i) {
            case -1:
                res = "(-)";
                break;
            case 0:
                res = "(-/+)";
                break;
            case 1:
                res = "(+)";
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * Méthode permettant de renvoyer une chaîne contenant les informations
     * à propos d'un indicateur précis
     * @param indicator l'indicateur dont on souhaite récuperer les informations
     * @return la chaîne contenant les informations
     */
    public String getIndicatorInfo(String indicator) {
        String indicInfo = "Indicateur non-répertorié";

        int indicatorIndex = getIndicatorIndex(indicator);

        if(indicatorIndex > 0) {
            JSONObject oIndic = indicators.getJSONObject(indicatorIndex);
            indicInfo = oIndic.getString("description");
        }

        return indicInfo;
    }

    /**
     * Méthode permettant de renvoyer une chaîne contenant les informations
     * du levier et la liste des indicateurs sur lequel in influe
     * @param lever le levier dont on souhaite récuperer les informations
     * @return la chaîne contenant les informations
     */
    public String getLeverInfo(String lever) {
        String leverInfo = "Levier non-répertorié";
        StringBuilder indicatorsList = new StringBuilder();

        int leverIndex = getLeverIndex(lever);

        if(leverIndex >= 0) {
            JSONObject oLevers = levers.getJSONObject(leverIndex);

            leverInfo = oLevers.getString("description");
            leverInfo = leverInfo + " qui influe sur";
            leverInfo = leverInfo + "\n";

            JSONArray leverIndicators = oLevers.getJSONArray("indicators");

            for(int i = 0; i < leverIndicators.length(); i++) {
                JSONObject objLevers = leverIndicators.getJSONObject(i);
                indicatorsList.append("• ");
                indicatorsList.append(objLevers.getString("name"));
                indicatorsList.append(" ");
                indicatorsList.append(getLeverInfluence(objLevers.getInt("type")));

                if(i != leverIndicators.length() - 1) {
                    indicatorsList.append("\n");
                }
            }

            leverInfo = leverInfo + indicatorsList;
        }

        return leverInfo;
    }

    public static void main(String[] args) {
        Info info = new Info();
        System.out.println(info.getIndicatorInfo("nombre d'étudiant"));
        System.out.println(info.getLeverInfo("iRenovation"));
    }
}

