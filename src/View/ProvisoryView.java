package View;

import Control.ElementControl;
import Model.FamilleLevier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;

public class ProvisoryView extends PresidentView {

    private ElementControl _EC;
    private JTabbedPane onglet;

    public ProvisoryView(ElementControl EC) {
        super();
        this._EC = EC;

        this.setLocationRelativeTo(null);

        //Création de plusieurs Panneau
        Collection<GroupeLevier> tPan = new ArrayList<GroupeLevier>();
        for(FamilleLevier GL : EC.getGroupes()){
            tPan.add(new GroupeLevier(GL,_EC));
        }


        //Création de notre conteneur d'onglets
        onglet = new JTabbedPane();
        int i = 0;
        for(GroupeLevier pan : tPan){
            //Méthode d'ajout d'onglet
            onglet.add(pan.getName(), pan);
            //Vous pouvez aussi utiliser la méthode addTab
            //onglet.addTab("Onglet n° "+(++i), pan);

        }
        //On passe ensuite les onglets au content pane
        this.getContentPane().add(onglet);

        IndicPanelDyn panelNbProf = new IndicPanelDyn("nombre de professeur",EC);
        IndicPanelDyn panelArgent = new IndicPanelDyn("argent disponible",EC);
        JButton ButtonClock = new JButton( new AbstractAction("Next Semestre") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EC.ClockForvard();
            }
        });
        JPanel indicateurPan = new JPanel();
        indicateurPan.setLayout(new FlowLayout());
        indicateurPan.add(panelNbProf);
        indicateurPan.add(panelArgent);
        indicateurPan.add(ButtonClock);
        indicateurPan.setVisible(true);
        this.getContentPane().add(indicateurPan, BorderLayout.SOUTH);

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
