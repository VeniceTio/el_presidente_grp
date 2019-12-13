package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Info {
    /**
     * Chemins d'accès aux fichiers JSON pour les leviers et indicateurs
     */
    final String indicatorsFile = "\\src\\utils\\indicators.json";
    final String leversFile = "\\src\\utils\\levers.json";

    /**
     * Tableaux JSON servant à stocker les indicateurs et leviers à partir des fichiers JSON
     */
    private JSONArray indicators;
    private JSONArray levers;

    /**
     * Constructeur de la classe Info
     */
    public Info() {
        try {
            String dir = new java.io.File( "." ).getCanonicalPath();
            String indicatorsContents = new String((Files.readAllBytes(Paths.get(dir + indicatorsFile))));
            String leversContents = new String((Files.readAllBytes(Paths.get(dir + leversFile))));
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
     * Méhode renvoyant l'index d'un indicateur ou d'un levier
     * en spécifiant son nom et le tableau dans lequel il est
     * @param name Le nom de l'indicateur ou du levier recherché
     * @param array Le tableau dans lequel l'indicateur ou le levier est répertorié
     * @return L'index de l'indicateur ou du levier qu'on recherche
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
     * Méthode renvoyant l'index d'un indicateur
     * @param indicator L'indicateur recherché
     * @return L'index de l'indicateur recherché
     */
    public int getIndicatorIndex(String indicator) {
        return getIndex(indicator, indicators);
    }

    /**
     * Méthode renvoyant l'index d'un levier
     * @param lever Le levier recherché
     * @return L'index du levier recherché
     */
    public int getLeverIndex(String lever) {
        return getIndex(lever, levers);
    }

    /**
     * Méthode renvoyant une chaîne qui précise si le levier
     * influe négativement, positivement ou les deux en même temps
     * @param i Le type du levier
     * @return Une chaîne contenant les informations à propos de ce levier
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
     * @param indicator L'indicateur dont on souhaite récuperer les informations
     * @return La chaîne contenant les informations
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
     * du levier et la liste des indicateurs sur lequel il influe
     * @param lever Le levier dont on souhaite récuperer les informations
     * @return La chaîne contenant les informations
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

            int arraySize = leverIndicators.length();
            if(arraySize == 0) {
                indicatorsList.append("• Aucun");
            }

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
}

