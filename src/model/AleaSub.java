package model;

public class AleaSub implements AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator L'indicateur sur lequel agit le levier
     * @param lever Le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {}

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator L'indicateur sur lequel le levier agit
     */
    @Override
    public void updateByLevers(Indicator indicator) {
        long Val = 174000000;
        /*long oldValue = indicator.getValue();
        long nbStudent = ElementControl.getInstance().getElement("nombre d'étudiants").getValue();
        long maxVal = (long)(-0.01*nbStudent)+4500;
        long minVal = (long)(-0.01*nbStudent)+3800;
        if (nbStudent >200000){
            maxVal = 2500;
            minVal = 1800;
        }
        maxVal *= nbStudent;
        minVal *= nbStudent;


        Random rand = new Random();
        Val = minVal + rand.nextInt((int) (maxVal - minVal));
        Val = (oldValue+4*Val)/5;*/
        indicator.setValue(Val);
    }
}
