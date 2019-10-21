import Control.ElementControl;
import Model.*;
import View.ProvisoryView;

public class Root {
    public Root(){
        ElementControl EC = ElementControl.getInstance();
        /** Création Indicateur **/
        Indicator argent_disponible = EC.createIndicator("argent disponible",0, new Argent(),true);
        Indicator nombre_professeur = EC.createIndicator("nombre de professeur",0, new NombreProfesseur(),false);
        Indicator nombre_eleve = EC.createIndicator("nombre d'élève",50000, new NombreProfesseur(),false);//50 000 étudiants pour commencer comme à strasbourg
        Indicator qualite_formation = EC.createIndicator("qualité de la formation",50, new NombreProfesseur(),false);
        Indicator revenue_inscription = EC.createIndicator("revenue des inscription",1, new Argent(),false);

        /** Création famille de levier **/
        LeverFamily Central = new LeverFamily("Central");
        LeverFamily Immobilier = new LeverFamily("Immobilier");
        LeverFamily Formation = new LeverFamily("Formation");
        LeverFamily Recherche = new LeverFamily("Recherche");

        /** Création Levier categorie Central **/
        Lever cPrimes = EC.createLever("Primes pour la central",0);
        Lever cComm = EC.createLever("Communication",0);
        Lever cAdmin = EC.createLever("Administration",0);
        Lever cEvenement = EC.createLever("Evenement",0);
        Lever cSubAssoc = EC.createLever("Subvention association",0);
        Central.addLever(cPrimes);
        Central.addLever(cComm);
        Central.addLever(cEvenement);
        Central.addLever(cAdmin);
        Central.addLever(cSubAssoc);

        /** Création Levier categorie Immobilier **/
        Lever iConstruction = EC.createLever("Construction",20000000);
        Lever iEntretien = EC.createLever("Entretien",60000000);
        Lever iRenovation = EC.createLever("Renovation",80000000);
        Immobilier.addLever(iConstruction);
        Immobilier.addLever(iEntretien);
        Immobilier.addLever(iRenovation);

        /** Création Levier categorie formation **/
        Lever fContractuel = EC.createLever("Contractuel", 20000000);
        Lever fTitulaire = EC.createLever("Titulaire", 18000000);
        Lever fDotRecur = EC.createLever("Dotation Recurente", 0);
        Lever fDotSpe = EC.createLever("Dotation Specifique", 0);
        Lever fPrimes = EC.createLever("Primes de formation", 10000);
        Lever fPartenariats = EC.createLever("Partenariat", 0);
        Lever fFraisInscri = EC.createLever("Frais d'inscription", 1800);
        Formation.addLever(fContractuel);
        Formation.addLever(fTitulaire);
        Formation.addLever(fDotRecur);
        Formation.addLever(fDotSpe);
        Formation.addLever(fPrimes);
        Formation.addLever(fPartenariats);
        Formation.addLever(fFraisInscri);

        /** Création Levier categorie recherche **/
        Lever rContractuel = EC.createLever("Contractuel", 20000000);
        Lever rTitulaire = EC.createLever("Titulaire", 18000000);
        Lever rDotRecur = EC.createLever("Dotation recurente", 0);
        Lever rDotSpe = EC.createLever("Dotation specifique", 0);
        Lever rPrimes = EC.createLever("Primes de recherche", 20000);
        Lever rValorisation = EC.createLever("Valorisation", 0);
        Recherche.addLever(rContractuel);
        Recherche.addLever(rTitulaire);
        Recherche.addLever(rDotRecur);
        Recherche.addLever(rDotSpe);
        Recherche.addLever(rPrimes);
        Recherche.addLever(rValorisation);

        Lever subEtat = EC.createLever("subvention de l'état",283000000);

        /** Ajout des facteurs de l'argent_disponible **/
        /** Manque frais d'inscription +revenue valorisation **/
        argent_disponible.addFacteur(fContractuel,"-");//20M soit 40M par an
        argent_disponible.addFacteur(fTitulaire,"-");//18M soit 36M par an
        argent_disponible.addFacteur(fPrimes,"-2");//10 000

        argent_disponible.addFacteur(rContractuel,"-");//20M soit 40M par an
        argent_disponible.addFacteur(rTitulaire,"-");//20M soit 40M par an
        argent_disponible.addFacteur(rPrimes,"-2");//20 000

        argent_disponible.addFacteur(iConstruction,"-");//20M
        argent_disponible.addFacteur(iEntretien,"-");//60M
        argent_disponible.addFacteur(iRenovation,"-");//80M

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

        /** Calcul valeur indicateur**/
        revenue_inscription.initValue();
        argent_disponible.initValue();
        nombre_professeur.initValue();

        Semestre.getInstance().ClockForvard();
        System.out.println("argent disponible : " + argent_disponible.getValue());

        /** View **/
        ProvisoryView view = new ProvisoryView();
    }
    public static void main(String[] args){
        new Root();
    }
}
