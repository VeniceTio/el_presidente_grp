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
        Indicator satisfation_etudiante = EC.createIndicator("satisfaction étudiante",75,new pourcentSatisfaction(),true);
        Indicator satisfation_professeur = EC.createIndicator("satisfaction professeur",75,new pourcentSatisfaction(),true);
        Indicator charge_de_travail = EC.createIndicator("charge de travail",0,new rapport(),true);

        Indicator val_batiment = EC.createIndicator("valorisation batiment",672000000,new degradation(),true);
        Indicator val_bien = EC.createIndicator("valorisation bien",672000000,new neutre(),false);
        Indicator etat_batiment = EC.createIndicator("état des batiments",100,new neutre(),true);


        Indicator taux_réussite = EC.createIndicator("taux de réussite",0,new Taux(),true);
        Indicator reputation_formation = EC.createIndicator("réputation de formation",0,new Taux(),true);
        Indicator taux_insertion_pro = EC.createIndicator("taux d'insertion profesionnel",0,new Taux(),true);

        Indicator taux_recherche_applique = EC.createIndicator("recherche appliqué",0,new Taux(),true);
        Indicator taux_recherche_fondamental = EC.createIndicator("recherche fondamental",0,new Taux(),true);

        /**
        Indicator nombre_article_pub = EC.createIndicator("recherche appliqué",0,new nombre(),true);
        Indicator nombre_prix_nobel = EC.createIndicator("recherche fondamental",0,new nombre(),true);

        Indicator revenue_valorisation = EC.createIndicator("revenue de valorisation",0,new revenue(),true);
        **/

        /** Création famille de levier **/
        LeverFamily Central = new LeverFamily("Central");
        LeverFamily Immobilier = new LeverFamily("Immobilier");
        LeverFamily Formation = new LeverFamily("Formation");
        LeverFamily Recherche = new LeverFamily("Recherche");

        /** Création Levier categorie Central **/
        Lever cPrimes = EC.createLever("cPrimes pour la central",1000000);       //1M
        Lever cComm = EC.createLever("cCommunication",1000000);                  //1M
        Lever cAdmin = EC.createLever("cAdministration",20000000);                //20M
        Lever cEvenement = EC.createLever("cEvenement",1000000);                 //1M
        Lever cSubAssoc = EC.createLever("cSubvention association",1000000);     //1M             tot: 24M
        Central.addLever(cPrimes);
        Central.addLever(cComm);
        Central.addLever(cEvenement);
        Central.addLever(cAdmin);
        Central.addLever(cSubAssoc);

        /** Création Levier categorie Immobilier **/
        Lever iConstruction = EC.createLever("iConstruction",0);           //~
        Lever iEntretien = EC.createLever("iEntretien",100800000);            //100.8M ~14.9%
        Lever iRenovation = EC.createLever("iRenovation",0);               //~
        Immobilier.addLever(iConstruction);
        Immobilier.addLever(iEntretien);
        Immobilier.addLever(iRenovation);

        /** Création Levier categorie formation **/
        Lever fContractuel = EC.createLever("fContractuel", 20000000);     //20M
        Lever fTitulaire = EC.createLever("fTitulaire", 18000000);         //18M ================
        Lever fDotRecur = EC.createLever("fDotation Recurente", 1000000);        //1M
        Lever fDotSpe = EC.createLever("fDotation Specifique", 500000);         //0.5M
        Lever fPrimes = EC.createLever("fPrimes de formation", 10000);     //~
        Lever fPartenariats = EC.createLever("fPartenariat", 500000);           //0.5M
        Lever fFraisInscri = EC.createLever("fFrais d'inscription", 300);
        Formation.addLever(fContractuel);
        Formation.addLever(fTitulaire);
        Formation.addLever(fDotRecur);
        Formation.addLever(fDotSpe);
        Formation.addLever(fPrimes);
        Formation.addLever(fPartenariats);
        Formation.addLever(fFraisInscri);

        /** Création Levier categorie recherche **/
        Lever rContractuel = EC.createLever("rContractuel", 33000000);     //30M   --> 1222   -->tot:1898
        Lever rTitulaire = EC.createLever("rTitulaire", 23000000);         //23M   --> 676           tot : 94M
        Lever rDotRecur = EC.createLever("rDotation recurente", 20000000);        //20M
        Lever rDotSpe = EC.createLever("rDotation specifique", 20000000);         //20M
        Lever rPrimes = EC.createLever("rPrimes de recherche", 20000);     //~
        Lever rValorisation = EC.createLever("rValorisation", 0);          //~
        Recherche.addLever(rContractuel);
        Recherche.addLever(rTitulaire);
        Recherche.addLever(rDotRecur);
        Recherche.addLever(rDotSpe);
        Recherche.addLever(rPrimes);
        Recherche.addLever(rValorisation);

        Lever subEtat = EC.createLever("rsubvention de l'état",199000000);

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
        satisfation_etudiante.addFacteur(etat_batiment,"3");
        satisfation_etudiante.addFacteur(cSubAssoc,"r");
        satisfation_etudiante.addFacteur(qualite_formation,"3");

        /** Ajout des facteurs de la satisfaction professeur **/
        satisfation_professeur.addFacteur(etat_batiment,"3");
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
        taux_réussite.addFacteur(satisfation_professeur,"40/100");
        taux_réussite.addFacteur(fDotRecur,"40c2");
        taux_réussite.addFacteur(fDotSpe,"20c1");

        /** Ajout des facteurs de la réputation de formation **/
        reputation_formation.addFacteur(taux_réussite,"40/100");
        reputation_formation.addFacteur(cComm,"40c2");
        reputation_formation.addFacteur(fDotSpe,"20c1");

        /** Ajout des facteurs du taux d'insertion profesionnel **/
        taux_insertion_pro.addFacteur(reputation_formation,"30/100");
        taux_insertion_pro.addFacteur(cComm,"35c2");
        taux_insertion_pro.addFacteur(cEvenement,"35c3");

        /** Ajout des facteurs de la recherche fondamental **/
        taux_recherche_fondamental.addFacteur(satisfation_professeur,"30/100");
        taux_recherche_fondamental.addFacteur(etat_batiment,"25/100");//             <- à faire
        taux_recherche_fondamental.addFacteur(rDotRecur,"18c4");//             <- à faire
        taux_recherche_fondamental.addFacteur(rDotSpe,"27c4");//             <- à faire


        /** Ajout des facteurs de la recherche appliqué **///                      <- verifier les coefficients
        taux_recherche_applique.addFacteur(satisfation_professeur,"30/100");
        taux_recherche_applique.addFacteur(etat_batiment,"25/100");//             <- à faire
        taux_recherche_applique.addFacteur(rDotRecur,"30c4");//             <- à faire

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

        taux_recherche_applique.initValue();
        taux_recherche_fondamental.initValue();


        Semestre.getInstance().ClockForvard();
        System.out.println("argent disponible : " + argent_disponible.getValue());

        /** View **/
        ProvisoryView view = new ProvisoryView();
    }
    public static void main(String[] args){
        new Root();
    }
}
