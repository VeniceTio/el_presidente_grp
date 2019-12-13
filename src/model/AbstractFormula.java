package model;

public interface AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator L'indicateur sur lequel agit le levier
     * @param lever Le levier qui agit sur l'indicateur
     */
    public void updateByOneLever(Indicator indicator, Lever lever);

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator L'indicateur sur lequel le levier agit
     */
    public void updateByLevers(Indicator indicator);
}
