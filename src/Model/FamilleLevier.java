package Model;

import java.util.ArrayList;
import java.util.Collection;

public class FamilleLevier {
    private String _name;
    private Collection<Levier> _contenu = new ArrayList<Levier>();

    public FamilleLevier(String name){_name = name;}

    public void addLevier(Levier levier){
        _contenu.add(levier);
    }
    public Collection<Levier> getContenus(){
        return _contenu;
    }
    public String getName(){
        return _name;
    }
}
