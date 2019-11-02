import Control.ElementControl;
import Model.*;
import View.ProvisoryView;

public class Root {
    public Root(){
        ElementControl EC = ElementControl.getInstance();
        /** Création Indicateur **/
        Indicator argent_disponible = EC.createIndicator("argent disponible",0, new Argent(),true);
        Indicator nombre_professeur = EC.createIndicator("nombre de professeur",0, new NombreProfesseur(),false);
        Indicator nombre_eleve = EC.createIndicator("nombre d'étudiant",50000, new NombreEleve(),false);//50 000 étudiants pour commencer comme à strasbourg
        Indicator revenue_inscription = EC.createIndicator("revenue des inscription",1, new Argent(),false);

        Indicator qualite_formation = EC.createIndicator("qualité de la formation",75, new qualiteFormation(),true);
        Indicator satisfation_etudiante = EC.createIndicator("satisfaction étudiante",75,new pourcentSatisfaction(),false);
        Indicator satisfation_professeur = EC.createIndicator("satisfaction professeur",75,new pourcentSatisfaction(),false);

        /** Création famille de levier **/
        LeverFamily Central = new LeverFamily("Central");
        LeverFamily Immobilier = new LeverFamily("Immobilier");
        LeverFamily Formation = new LeverFamily("Formation");
        LeverFamily Recherche = new LeverFamily("Recherche");

        /** Création Levier categorie Central **/
        Lever cPrimes = EC.createLever("cPrimes pour la central",0);
        Lever cComm = EC.createLever("cCommunication",0);
        Lever cAdmin = EC.createLever("cAdministration",0);
        Lever cEvenement = EC.createLever("cEvenement",0);
        Lever cSubAssoc = EC.createLever("cSubvention association",0);
        Central.addLever(cPrimes);
        Central.addLever(cComm);
        Central.addLever(cEvenement);
        Central.addLever(cAdmin);
        Central.addLever(cSubAssoc);

        /** Création Levier categorie Immobilier **/
        Lever iConstruction = EC.createLever("iConstruction",0);
        Lever iEntretien = EC.createLever("iEntretien",60000000);
        Lever iRenovation = EC.createLever("iRenovation",0);
        Immobilier.addLever(iConstruction);
        Immobilier.addLever(iEntretien);
        Immobilier.addLever(iRenovation);

        /** Création Levier categorie formation **/
        Lever fContractuel = EC.createLever("fContractuel", 20000000);
        Lever fTitulaire = EC.createLever("fTitulaire", 18000000);
        Lever fDotRecur = EC.createLever("fDotation Recurente", 0);
        Lever fDotSpe = EC.createLever("fDotation Specifique", 0);
        Lever fPrimes = EC.createLever("fPrimes de formation", 10000);
        Lever fPartenariats = EC.createLever("fPartenariat", 0);
        Lever fFraisInscri = EC.createLever("fFrais d'inscription", 1800);
        Formation.addLever(fContractuel);
        Formation.addLever(fTitulaire);
        Formation.addLever(fDotRecur);
        Formation.addLever(fDotSpe);
        Formation.addLever(fPrimes);
        Formation.addLever(fPartenariats);
        Formation.addLever(fFraisInscri);

        /** Création Levier categorie recherche **/
        Lever rContractuel = EC.createLever("rContractuel", 20000000);
        Lever rTitulaire = EC.createLever("rTitulaire", 18000000);
        Lever rDotRecur = EC.createLever("rDotation recurente", 0);
        Lever rDotSpe = EC.createLever("rDotation specifique", 0);
        Lever rPrimes = EC.createLever("rPrimes de recherche", 20000);
        Lever rValorisation = EC.createLever("rValorisation", 0);
        Recherche.addLever(rContractuel);
        Recherche.addLever(rTitulaire);
        Recherche.addLever(rDotRecur);
        Recherche.addLever(rDotSpe);
        Recherche.addLever(rPrimes);
        Recherche.addLever(rValorisation);

        Lever subEtat = EC.createLever("rsubvention de l'état",120000000);

        /** Ajout des facteurs de l'argent_disponible **/
        /** Manque frais d'inscription +revenue valorisation **/
        argent_disponible.addFacteur(fContractuel,"-");//20M soit 40M par an
        argent_disponible.addFacteur(fTitulaire,"-");//18M soit 36M par an
        argent_disponible.addFacteur(fPrimes,"-");//10 000

        argent_disponible.addFacteur(rContractuel,"-");//20M soit 40M par an
        argent_disponible.addFacteur(rTitulaire,"-");//20M soit 40M par an
        argent_disponible.addFacteur(rPrimes,"-");//20 000

        argent_disponible.addFacteur(iConstruction,"-");//20M
        argent_disponible.addFacteur(iEntretien,"-");//60M
        argent_disponible.addFacteur(iRenovation,"-");//80M

        //total perte pas année 40M+36M+10 000+40M+40M+20M+60M+80M = 316 030 000 €

        argent_disponible.addFacteur(subEtat,"+");//150M
        argent_disponible.addFacteur(revenue_inscription,"+");//283M

        /** Ajout des facteurs du nombre de professeur**/
        nombre_professeur.addFacteur(fContractuel,"28800");
        nombre_professeur.addFacteur(fTitulaire,"57600");

        nombre_professeur.addFacteur(rContractuel,"30800");
        nombre_professeur.addFacteur(rTitulaire,"60600");

        /** Ajout des facteurs du nombre de d'élève **/

        /** Ajout des facteurs du revenue inscription **/
        revenue_inscription.addFacteur(fFraisInscri,"*");
        revenue_inscription.addFacteur(nombre_eleve,"*");

        /** Ajout des facteurs de la satisfaction des étudiants **/
        satisfation_etudiante.addFacteur(iConstruction,"s");//bonus Spontané
        satisfation_etudiante.addFacteur(iEntretien,"r");//regulier
        satisfation_etudiante.addFacteur(iRenovation,"s");
        satisfation_etudiante.addFacteur(cSubAssoc,"r");
        satisfation_etudiante.addFacteur(qualite_formation,"3");

        /** Ajout des facteurs de la qualité de formation **/


        /** Ajout Famille Levier au ElementControl **/
        EC.addFamilyLever(Central);
        EC.addFamilyLever(Immobilier);
        EC.addFamilyLever(Formation);
        EC.addFamilyLever(Recherche);

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

        /** Ajout du listener satisfaction étudiante**/
        iConstruction.addInfluencer(satisfation_etudiante);
        iEntretien.addInfluencer(satisfation_etudiante);
        iRenovation.addInfluencer(satisfation_etudiante);
        cSubAssoc.addInfluencer(satisfation_etudiante);

        /** Ajout du listener qualite formation**/


        /** Calcul valeur indicateur**/
        revenue_inscription.initValue();
        argent_disponible.initValue();
        nombre_professeur.initValue();
        satisfation_etudiante.initValue();
        qualite_formation.initValue();

        Semestre.getInstance().ClockForvard();
        System.out.println("argent disponible : " + argent_disponible.getValue());

        /** View **/
        ProvisoryView view = new ProvisoryView();
    }
    public static void main(String[] args){
        new Root();
    }
}
