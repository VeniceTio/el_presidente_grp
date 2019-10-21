import Control.ElementControl;
import Model.*;
import View.ProvisoryView;

public class Root {
    public Root(){
        ElementControl EC = new ElementControl();
        /** Création Indicateur **/
        Indicateur argent_disponible = EC.createIndicateur("argent disponible",0, new Argent(),true);
        Indicateur nombre_professeur = EC.createIndicateur("nombre de professeur",0, new NombreProfesseur(),false);
        Indicateur nombre_eleve = EC.createIndicateur("nombre d'élève",50000, new NombreProfesseur(),false);//50 000 étudiants pour commencer comme à strasbourg
        Indicateur qualite_formation = EC.createIndicateur("qualité de la formation",50, new NombreProfesseur(),false);
        Indicateur revenue_inscription = EC.createIndicateur("revenue des inscription",1, new Argent(),false);

        /** Création famille de levier **/
        FamilleLevier Central = new FamilleLevier("Central");
        FamilleLevier Immobilier = new FamilleLevier("Immobilier");
        FamilleLevier Formation = new FamilleLevier("Formation");
        FamilleLevier Recherche = new FamilleLevier("Recherche");

        /** Création Levier categorie Central **/
        Levier cPrimes = EC.createLevier("Primes",15000);
        Levier cComm = EC.createLevier("Communication",72000);
        Levier cAdmin = EC.createLevier("Administration",10000000);
        Levier cEvenement = EC.createLevier("Evenement",1000000);
        Levier cSubAssoc = EC.createLevier("Subvention association",1000000);
        Central.addLevier(cPrimes);
        Central.addLevier(cComm);
        Central.addLevier(cEvenement);
        Central.addLevier(cAdmin);
        Central.addLevier(cSubAssoc);

        /** Création Levier categorie Immobilier **/
        Levier iConstruction = EC.createLevier("Construction",20000000);
        Levier iEntretien = EC.createLevier("Entretien",60000000);
        Levier iRenovation = EC.createLevier("Renovation",80000000);
        Immobilier.addLevier(iConstruction);
        Immobilier.addLevier(iEntretien);
        Immobilier.addLevier(iRenovation);

        /** Création Levier categorie formation **/
        Levier fContractuel = EC.createLevier("Contractuel", 20000000);
        Levier fTitulaire = EC.createLevier("Titulaire", 18000000);
        Levier fDotRecur = EC.createLevier("Dotation Recurente", 180000);
        Levier fDotSpe = EC.createLevier("Dotation Specifique", 180000);
        Levier fPrimes = EC.createLevier("Primes", 10000);
        Levier fPartenariats = EC.createLevier("Partenariat", 200);
        Levier fFraisInscri = EC.createLevier("Frais d'inscription", 1800);
        Formation.addLevier(fContractuel);
        Formation.addLevier(fTitulaire);
        Formation.addLevier(fDotRecur);
        Formation.addLevier(fDotSpe);
        Formation.addLevier(fPrimes);
        Formation.addLevier(fPartenariats);
        Formation.addLevier(fFraisInscri);

        /** Création Levier categorie recherche **/
        Levier rContractuel = EC.createLevier("Contractuel", 20000000);
        Levier rTitulaire = EC.createLevier("Titulaire", 18000000);
        Levier rDotRecur = EC.createLevier("Dotation recurente", 180000);
        Levier rDotSpe = EC.createLevier("Dotation specifique", 18000);
        Levier rPrimes = EC.createLevier("Primes", 20000);
        Levier rValorisation = EC.createLevier("Valorisation", 180000);
        Recherche.addLevier(rContractuel);
        Recherche.addLevier(rTitulaire);
        Recherche.addLevier(rDotRecur);
        Recherche.addLevier(rDotSpe);
        Recherche.addLevier(rPrimes);
        Recherche.addLevier(rValorisation);

        Levier subEtat = EC.createLevier("subvention de l'état",283000000);

        /** Ajout des facteurs de l'argent_disponible **/
        /** Manque frais d'inscription +revenue valorisation **/
        argent_disponible.addFacteur(fContractuel,"-");//20M soit 40M par an
        argent_disponible.addFacteur(fTitulaire,"-");//18M soit 36M par an
        argent_disponible.addFacteur(fPrimes,"-2");//10 000

        argent_disponible.addFacteur(rContractuel,"-");//20M soit 40M par an
        argent_disponible.addFacteur(rTitulaire,"-");//20M soit 40M par an
        argent_disponible.addFacteur(rPrimes,"-2");//20 000

        argent_disponible.addFacteur(iConstruction,"-2");//20M
        argent_disponible.addFacteur(iEntretien,"-2");//60M
        argent_disponible.addFacteur(iRenovation,"-2");//80M

        //total perte pas année 40M+36M+10 000+40M+40M+20M+60M+80M = 316 030 000 €

        argent_disponible.addFacteur(subEtat,"+2");//283M
        argent_disponible.addFacteur(revenue_inscription,"+2");//283M

        /** Ajout des facteurs du nombre de professeur**/
        nombre_professeur.addFacteur(fContractuel,"28800");
        nombre_professeur.addFacteur(fTitulaire,"57600");

        nombre_professeur.addFacteur(rContractuel,"30800");
        nombre_professeur.addFacteur(rTitulaire,"60600");

        /** Ajout des facteurs du nombre de d'élève **/

        /** Ajout des facteurs du revenue inscription **/
        revenue_inscription.addFacteur(fFraisInscri,"*");
        revenue_inscription.addFacteur(nombre_eleve,"*");

        /** Ajout Famille Levier au ElementControl **/
        EC.addGroupe(Central);
        EC.addGroupe(Immobilier);
        EC.addGroupe(Formation);
        EC.addGroupe(Recherche);

        /** Ajout du listener argent_disponible **/
        fContractuel.addInfluencer(argent_disponible);
        fTitulaire.addInfluencer(argent_disponible);
        fPrimes.addInfluencer(argent_disponible);
        rContractuel.addInfluencer(argent_disponible);
        rTitulaire.addInfluencer(argent_disponible);
        rPrimes.addInfluencer(argent_disponible);
        iConstruction.addInfluencer(argent_disponible);
        iEntretien.addInfluencer(argent_disponible);
        iRenovation.addInfluencer(argent_disponible);

        /** Ajout du listener nombre professeur**/
        fContractuel.addInfluencer(nombre_professeur);
        fTitulaire.addInfluencer(nombre_professeur);
        rContractuel.addInfluencer(nombre_professeur);
        rTitulaire.addInfluencer(nombre_professeur);

        /** Ajout du listener revenue inscription**/
        fFraisInscri.addInfluencer(revenue_inscription);

        /** Calcul valeur indicateur**/
        revenue_inscription.initValue();
        argent_disponible.initValue();
        nombre_professeur.initValue();

        Semestre.getInstance().ClockForvard();
        System.out.println("argent disponible : " + argent_disponible.getValue());

        /** View **/
        ProvisoryView view = new ProvisoryView(EC);
    }
    public static void main(String[] args){
        new Root();
    }
}
