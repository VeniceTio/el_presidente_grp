import Control.ElementControl;
import Model.Indicateur;
import Model.Levier;
import View.PresidentView;
import View.ProvisoryView;

public class Root {
    public Root(){
        ElementControl EC = new ElementControl();
        /** Création Indicateur **/
        Indicateur argent_disponible = EC.createIndicateur("argent_disponible",0);

        /** Création Levier categorie formation **/
        Levier fContractuel = EC.createLevier("fContractuel", 20000000);
        Levier fTitulaire = EC.createLevier("fTitulaire", 18000000);
        Levier fPrimes = EC.createLevier("fPrimes", 20000000);

        /** Création Levier categorie recherche **/
        Levier rContractuel = EC.createLevier("rContractuel", 20000000);
        Levier rTitulaire = EC.createLevier("rTitulaire", 18000000);
        Levier rPrimes = EC.createLevier("rPrimes", 20000000);

        Levier subEtat = EC.createLevier("subEtat",283000000);

        /** Ajout des facteurs de l'argent_disponible **/
        argent_disponible.addFacteur(fContractuel,"-");
        argent_disponible.addFacteur(fTitulaire,"-");
        argent_disponible.addFacteur(fPrimes,"-");

        argent_disponible.addFacteur(rContractuel,"-");
        argent_disponible.addFacteur(rTitulaire,"-");
        argent_disponible.addFacteur(rPrimes,"-");

        argent_disponible.addFacteur(subEtat,"+");

        /** Ajout des listener **/


        /** View **/
        ProvisoryView view = new ProvisoryView(EC);
    }
    public void main(String[] args){
        new Root();
    }
}
