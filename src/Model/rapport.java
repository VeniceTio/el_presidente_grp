package Model;

import java.util.Map;

public class rapport implements AbstractFormula {
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
        updateByLevers(indicator);
    }

    @Override
    public void updateByLevers(Indicator indicator) {
        indicator.setValue(0);
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            if(mapEntry.getValue()=="sur"){
                indicator.setValue(indicator.getValue()+((Indicator)mapEntry.getKey()).getValue());
            }
            else if (mapEntry.getValue()=="/"){
                indicator.setValue(indicator.getValue()/((Indicator)mapEntry.getKey()).getValue());
            }
        }
    }
}
