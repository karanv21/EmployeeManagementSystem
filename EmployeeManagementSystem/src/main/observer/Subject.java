package observer;

import model.Location;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<LocationObserver> observers = new ArrayList<>();

    //Modifies: observers
    //Effects: adds locatinoObserver to observers
    public void addObserver(LocationObserver locationObserver) {
        if (!observers.contains(locationObserver)) {
            observers.add(locationObserver);
        }
    }

    //Effects: calls update on all observers which simply displays items
    public void notifyObserver(Location location) {
        for (LocationObserver observer : observers) {
            observer.update(location);
        }
    }

}
