import controller.ElementControl;
import model.*;
import view.GameView;
import view.IndicatorText;
import view.LeverController;
import view.ProvisoryView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Root {
    public Root(){
        ElementControl EC = ElementControl.getInstance();
        /** Création Indicateur **/
        Indicator subEtat = EC.createIndicator("subvention de l'état",185000000,new aleaSub(),true,IndicatorType.NUMBER);
        Indicator argent_disponible = EC.createIndicator("argent disponible",0, new Argent(),true, IndicatorType.NUMBER);
        Indicator nombre_professeur = EC.createIndicator("nombre de professeur",0, new NombreProfesseur(),false, IndicatorType.NUMBER);
        Indicator nombre_eleve = EC.createIndicator("nombre d'étudiant",50000, new NombreEleve(),true, IndicatorType.NUMBER);//50 000 étudiants pour commencer comme à strasbourg
        Indicator revenue_inscription = EC.createIndicator("revenue des inscription",1, new Argent(),true, IndicatorType.NUMBER);

        Indicator qualite_formation = EC.createIndicator("qualité de la formation",75, new QualiteFormation(),true, IndicatorType.PERCENTAGE);
        Indicator satisfation_etudiante = EC.createIndicator("satisfaction étudiante",75,new PourcentSatisfaction(),true, IndicatorType.PERCENTAGE);
        Indicator satisfation_professeur = EC.createIndicator("satisfaction professeur",75,new PourcentSatisfaction(),true, IndicatorType.PERCENTAGE);
        Indicator charge_de_travail = EC.createIndicator("charge de travail",0,new Rapport(),true, IndicatorType.PERCENTAGE);

        Indicator val_batiment = EC.createIndicator("valorisation batiment",672000000,new Degradation(),true, IndicatorType.NUMBER);
        Indicator val_bien = EC.createIndicator("valorisation bien",672000000,new Neutre(),false, IndicatorType.NUMBER);
        Indicator etat_batiment = EC.createIndicator("état des batiments",100,new Neutre(),true, IndicatorType.NUMBER);


        Indicator taux_réussite = EC.createIndicator("taux de réussite",0,new Taux(),true, IndicatorType.PERCENTAGE);
        Indicator reputation_formation = EC.createIndicator("réputation de formation",0,new Taux(),true, IndicatorType.PERCENTAGE);
        Indicator taux_insertion_pro = EC.createIndicator("taux d'insertion profesionnel",0,new Taux(),true, IndicatorType.PERCENTAGE);

        Indicator taux_recherche_appliquee = EC.createIndicator("recherche appliquée",0,new Taux(),true, IndicatorType.PERCENTAGE);
        Indicator taux_recherche_fondamentale = EC.createIndicator("recherche fondamentale",0,new Taux(),true, IndicatorType.PERCENTAGE);


        Indicator nombre_article_pub = EC.createIndicator("nombre d'article publié",0,new Nombre(),true, IndicatorType.NUMBER);
        Indicator nombre_prix_nobel = EC.createIndicator("nombre de prix nobel",0,new Nombre(),true, IndicatorType.NUMBER);
        Indicator reputation_recherche = EC.createIndicator("réputation de la recherche",0,new RepRec(),true, IndicatorType.PERCENTAGE);
/**
        Indicator revenue_valorisation = EC.createIndicator("revenue de valorisation",0,new revenue(),true);
        **/

        /** Création famille de levier **/
        LeverFamily Central = new LeverFamily("Central");
        LeverFamily Immobilier = new LeverFamily("Immobilier");
        LeverFamily Formation = new LeverFamily("Formation");
        LeverFamily Recherche = new LeverFamily("Recherche");

        /** Création Levier categorie Central **/
        Lever cPrimes = EC.createLever("cPrimes centrales",1000000,100000);       //1M
        Lever cComm = EC.createLever("cCommunication",1000000,100000);                  //1M
        Lever cAdmin = EC.createLever("cAdministration",20000000,500000);                //20M
        Lever cEvenement = EC.createLever("cEvenement",1000000,50000);                 //1M
        Lever cSubAssoc = EC.createLever("cSubv. assoc.",1000000,75000);     //1M             tot: 24M
        Central.addLever(cPrimes);
        Central.addLever(cComm);
        Central.addLever(cEvenement);
        Central.addLever(cAdmin);
        Central.addLever(cSubAssoc);

        /** Création Levier categorie Immobilier **/
        Lever iConstruction = EC.createLever("iConstruction",0,100000);           //~
        Lever iEntretien = EC.createLever("iEntretien",100800000,1000000);            //100.8M ~14.9%
        Lever iRenovation = EC.createLever("iRenovation",0,100000);               //~
        Immobilier.addLever(iConstruction);
        Immobilier.addLever(iEntretien);
        Immobilier.addLever(iRenovation);

        /** Création Levier categorie formation **/
        Lever fContractuel = EC.createLever("fContractuel", 20000000,200000);     //20M
        Lever fTitulaire = EC.createLever("fTitulaire", 18000000,200000);         //18M ================
        Lever fDotRecur = EC.createLever("fDotation Recurrente", 1000000,100000);        //1M
        Lever fDotSpe = EC.createLever("fDotation Specifique", 500000,50000);         //0.5M
        Lever fPrimes = EC.createLever("fPrimes de formation", 10000,1000);     //~
        Lever fPartenariats = EC.createLever("fPartenariat", 500000,50000);           //0.5M
        Lever fFraisInscri = EC.createLever("fFrais d'inscription", 300,200);
        Formation.addLever(fContractuel);
        Formation.addLever(fTitulaire);
        Formation.addLever(fDotRecur);
        Formation.addLever(fDotSpe);
        Formation.addLever(fPrimes);
        Formation.addLever(fPartenariats);
        Formation.addLever(fFraisInscri);

        /** Création Levier categorie recherche **/
        Lever rContractuel = EC.createLever("rContractuel", 33000000,500000);     //30M   --> 1222   -->tot:1898
        Lever rTitulaire = EC.createLever("rTitulaire", 23000000,500000);         //23M   --> 676           tot : 94M
        Lever rDotRecur = EC.createLever("rDotation recurrente", 20000000,500000);        //20M
        Lever rDotSpe = EC.createLever("rDotation specifique", 20000000,500000);         //20M
        Lever rPrimes = EC.createLever("rPrimes de recherche", 20000,1000);     //~
        Lever rValorisation = EC.createLever("rValorisation", 0,1000);          //~
        Recherche.addLever(rContractuel);
        Recherche.addLever(rTitulaire);
        Recherche.addLever(rDotRecur);
        Recherche.addLever(rDotSpe);
        Recherche.addLever(rPrimes);
        Recherche.addLever(rValorisation);



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
        nombre_professeur.addFacteur(fContractuel,"23000");
        nombre_professeur.addFacteur(fTitulaire,"26000");

        nombre_professeur.addFacteur(rContractuel,"27800");
        nombre_professeur.addFacteur(rTitulaire,"34600");

        /** Ajout des facteurs du nombre de d'élève **/

        /** Ajout des facteurs du revenue inscription **/
        revenue_inscription.addFacteur(fFraisInscri,"*");
        revenue_inscription.addFacteur(nombre_eleve,"*");

        /** Ajout des facteurs de la satisfaction des étudiants **/
        satisfation_etudiante.addFacteur(etat_batiment,"c33");
        satisfation_etudiante.addFacteur(cSubAssoc,"r");
        satisfation_etudiante.addFacteur(qualite_formation,"c33");

        /** Ajout des facteurs de la satisfaction professeur **/
        satisfation_professeur.addFacteur(etat_batiment,"c35");
        satisfation_professeur.addFacteur(charge_de_travail,"25");

        /** Ajout des facteurs de la charge de travail **/
        charge_de_travail.addFacteur(nombre_eleve,"sur");
        charge_de_travail.addFacteur(nombre_professeur,"/");

        /** Ajout des facteurs de la qualité de formation **/

        /** Ajout des facteurs de la valorisation des batiments **/
        val_batiment.addFacteur(iConstruction,"+");
        val_batiment.addFacteur(iRenovation,"+");
        val_batiment.addFacteur(iEntretien,"/");

        /** Ajout des facteurs du taux de réussite **/
        taux_réussite.addFacteur(satisfation_professeur,"30/100");
        taux_réussite.addFacteur(fDotRecur,"20c2");
        taux_réussite.addFacteur(fDotSpe,"20c1");
        taux_réussite.addFacteur(qualite_formation,"30c6");

        /** Ajout des facteurs de la réputation de formation **/
        reputation_formation.addFacteur(taux_réussite,"40/100");
        reputation_formation.addFacteur(cComm,"40c2");
        reputation_formation.addFacteur(fDotSpe,"20c1");

        /** Ajout des facteurs du taux d'insertion profesionnel **/
        taux_insertion_pro.addFacteur(reputation_formation,"30/100");
        taux_insertion_pro.addFacteur(cComm,"35c2");
        taux_insertion_pro.addFacteur(cEvenement,"35c3");

        /** Ajout des facteurs de la recherche fondamental **/
        taux_recherche_fondamentale.addFacteur(satisfation_professeur,"30/100");
        taux_recherche_fondamentale.addFacteur(etat_batiment,"25/100");//             <- à faire
        taux_recherche_fondamentale.addFacteur(rDotRecur,"18c4");//             <- à faire
        taux_recherche_fondamentale.addFacteur(rDotSpe,"27c4");//             <- à faire


        /** Ajout des facteurs de la recherche appliqué **///                      <- verifier les coefficients
        taux_recherche_appliquee.addFacteur(satisfation_professeur,"30/100");
        taux_recherche_appliquee.addFacteur(etat_batiment,"25/100");//             <- à faire
        taux_recherche_appliquee.addFacteur(rDotRecur,"30c4");//             <- à faire

        /** Ajout des facteurs du nombre d'article publié **/
        nombre_article_pub.addFacteur(taux_recherche_appliquee,"cI106");
        nombre_article_pub.addFacteur(taux_recherche_fondamentale,"cI104");

        /** Ajout des facteurs du nombre de prix nobel **/
        nombre_prix_nobel.addFacteur(taux_recherche_fondamentale,"cI21");

        /** Ajout des facteurs de la réputation de recherche **/
        reputation_recherche.addFacteur(cComm,"com");
        reputation_recherche.addFacteur(nombre_article_pub,"val1");
        reputation_recherche.addFacteur(nombre_prix_nobel,"val2");

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


        /** Calcul valeur indicateur**/
        revenue_inscription.initValue();
        argent_disponible.initValue();
        nombre_professeur.initValue();
        qualite_formation.initValue();
        charge_de_travail.initValue();
        satisfation_etudiante.initValue();
        satisfation_professeur.initValue();

        taux_réussite.initValue();
        reputation_formation.initValue();
        taux_insertion_pro.initValue();

        taux_recherche_appliquee.initValue();
        taux_recherche_fondamentale.initValue();

        nombre_article_pub.initValue();
        nombre_prix_nobel.initValue();


        Semestre.getInstance().ClockForvard();
        System.out.println("argent disponible : " + argent_disponible.getValue());
    }

    public static void main(String[] args) {
        new Root();
        GameView.initialize();
    }
}
