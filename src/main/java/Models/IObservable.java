package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public interface IObservable {
    //List<Enemy> enemies = new ArrayList<>();

    public void addObserver(IObserver obs);

    public void removeObserver(IObserver obs);

    public void notifyObservers();

}
