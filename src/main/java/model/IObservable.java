package model;

public interface IObservable {
    //List<Enemy> enemies = new ArrayList<>();

    public void addObserver(IObserver obs);

    public void removeObserver(IObserver obs);

    public void notifyObservers();

}
