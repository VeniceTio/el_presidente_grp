package Model;

public class Semestre implements Time{
    private int _nbrSemestre = 0;
    private static Semestre Instance = null;
    private Semestre(){}
    public static Semestre getInstance(){
        if(Instance == null){
            Instance = new Semestre();
        }
        return Instance;
    }
    public int getSemestre(){return _nbrSemestre;}
    @Override
    public void ClockForvard() {
    _nbrSemestre++;
    }
}
