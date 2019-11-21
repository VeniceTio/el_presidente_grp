package View;

import Control.ElementControl;
import Model.LeverFamily;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class ProvisoryView extends PresidentView {

    private ElementControl _EC;
    private JPanel onglet;

    public ProvisoryView() {
        super();
        this._EC = ElementControl.getInstance();

        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,2,10,10));

        JPanel levers = new JPanel();
        JPanel infos = new JPanel();


        levers.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.RED));

        //Création de plusieurs Panneau
        Collection<GroupeLevier> tPan = new ArrayList<GroupeLevier>();
        for(LeverFamily GL : _EC.getFamilyLevers()){
            tPan.add(new GroupeLevier(GL,_EC));
        }


        //Création de notre conteneur d'onglets
        onglet = new JPanel();
        onglet.setLayout(new BoxLayout(onglet, BoxLayout.Y_AXIS));
        onglet.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        int i = 0;
        for(GroupeLevier pan : tPan){
            //Méthode d'ajout d'onglet
            onglet.add(pan.getName(), pan);
            //Vous pouvez aussi utiliser la méthode addTab
            //onglet.addTab("Onglet n° "+(++i), pan);

        }
        //On passe ensuite les onglets au content pane
        levers.add(onglet);
        this.getContentPane().add(levers);

        //ajout partie droite
        JPanel RightPan = new JPanel();
        RightPan.setLayout(new GridLayout(2,1,10,10));
        this.getContentPane().add(RightPan);

        IndicPanelDyn panelNbProf = new IndicPanelDyn("nombre de professeur");
        IndicPanelDyn panelNbEtudiant = new IndicPanelDyn("nombre d'étudiant");
        IndicPanelDyn panelSatisfactionEtudiant = new IndicPanelDyn("satisfaction étudiante");
        IndicPanelDyn panelQualiteFormation = new IndicPanelDyn("qualité de la formation");
        IndicPanelDyn panelSatisfactionProf = new IndicPanelDyn("satisfaction professeur");
        IndicPanelDyn panelArgent = new IndicPanelDyn("argent disponible");

        IndicPanelDyn panelValBien = new IndicPanelDyn("valorisation bien");
        IndicPanelDyn panelValImmob = new IndicPanelDyn("valorisation batiment");
        IndicPanelDyn panelEtatBat = new IndicPanelDyn("état des batiments");
        IndicPanelDyn panelChargeTravail = new IndicPanelDyn("charge de travail");

        IndicPanelDyn panelTauxReussite = new IndicPanelDyn("taux de réussite");
        IndicPanelDyn panelReputation = new IndicPanelDyn("réputation de formation");
        IndicPanelDyn panelTauxInsertion = new IndicPanelDyn("taux d'insertion profesionnel");

        IndicPanelDyn panelTauxRechercheAppliquee = new IndicPanelDyn("recherche appliquée");
        IndicPanelDyn panelTauxRechercheFondamentale = new IndicPanelDyn("recherche fondamentale");

        IndicPanelDyn panelArticlePublie = new IndicPanelDyn("nombre d'article publié");
        IndicPanelDyn panelPrixNobel = new IndicPanelDyn("nombre de prix nobel");

        JPanel indicateurPan = new JPanel();
        indicateurPan.setLayout(new BoxLayout(indicateurPan, BoxLayout.Y_AXIS));

        indicateurPan.add(panelNbProf);
        indicateurPan.add(panelNbEtudiant);
        indicateurPan.add(panelSatisfactionEtudiant);
        indicateurPan.add(panelSatisfactionProf);
        indicateurPan.add(panelChargeTravail);
        indicateurPan.add(panelQualiteFormation);
        indicateurPan.add(panelEtatBat);
        indicateurPan.add(panelValBien);
        indicateurPan.add(panelValImmob);

        indicateurPan.add(panelTauxReussite);
        indicateurPan.add(panelReputation);
        indicateurPan.add(panelTauxInsertion);

        indicateurPan.add(panelTauxRechercheAppliquee);
        indicateurPan.add(panelTauxRechercheFondamentale);

        indicateurPan.add(panelArticlePublie);
        indicateurPan.add(panelPrixNobel);

        indicateurPan.add(panelArgent);

        indicateurPan.add(new SemestreView());
        RightPan.add(indicateurPan);
        indicateurPan.setVisible(true);


        this.setVisible(true);
        /**
        JPanel groupeLevier = (JPanel) this.getContentPane();
        groupeLevier.setLayout(new GridLayout(10,1,10,10));

        IndicPanelDyn panelTitre = new IndicPanelDyn("argent_disponible",EC);
        groupeLevier.add(panelTitre);
        JPanel pan = new LevierPanel("fContractuel",EC);
        //pan.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan);

        JPanel pan1 = new LevierPanel("fTitulaire",EC);
        //pan1.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan1);

        JPanel pan2 = new LevierPanel("fPrimes",EC);
        //pan2.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan2);

        JPanel pan3 = new LevierPanel("rContractuel",EC);
        //pan3.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan3);

        JPanel pan4 = new LevierPanel("rTitulaire",EC);
        //pan4.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan4);

        JPanel pan5 = new LevierPanel("rPrimes",EC);
        //pan4.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan5);

        this.setVisible(true);**/
    }
}
