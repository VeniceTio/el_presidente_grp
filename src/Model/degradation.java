package Model;

import Control.ElementControl;

public class degradation implements AbstractFormula {
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {

    }

    @Override
    public void updateByLevers(Indicator indicator) {
        long val = ElementControl.getInstance().getElement("valorisation batiment").getValue();
        long ent = ElementControl.getInstance().getElement("iEntretien").getValue();
        long ajout = 0;
        long ajoutR = 0;
        float pourcent = (float)(ent)/(float)(val);
        if (pourcent<0.1){
            indicator.setValue((long)(indicator.getValue()-(-5000000*pourcent+1000000)));
        }
        else if (pourcent<0.2){
            indicator.setValue((long)(indicator.getValue()-(-4000000*pourcent+900000)));
        }
        else if (pourcent<0.3){
            indicator.setValue((long)(indicator.getValue()-(-800000*pourcent+250000)));
        }
        else {
            indicator.setValue(indicator.getValue()-10000);
        }
        if (ElementControl.getInstance().getElement("iConstruction").getValue()>0){
            ajout = ElementControl.getInstance().getElement("iConstruction").getValue();
        }
        if (ElementControl.getInstance().getElement("iRenovation").getValue()>0){
            ajoutR += ElementControl.getInstance().getElement("iRenovation").getValue();
        }
        indicator.setValue(indicator.getValue()+ajout+ajoutR);
        ElementControl.getInstance().getElement("valorisation bien").setValue(ElementControl.getInstance().getElement("valorisation bien").getValue()+ajout);
        if(indicator.getValue()>ElementControl.getInstance().getElement("valorisation bien").getValue()){
            ElementControl.getInstance().getElement("valorisation bien").setValue(indicator.getValue());
        }


        System.out.println("\n######################################################");
        System.out.println("valeur    : " + val);
        System.out.println("entretien : " + ent);
        System.out.println("pourcent  : " + pourcent);
        System.out.println("ajout     : " + ajout);
        System.out.println("######################################################");
    }
}
