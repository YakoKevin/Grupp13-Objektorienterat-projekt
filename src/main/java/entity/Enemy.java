package entity;

import utilz.Coordinate;

public abstract class Enemy extends Entity implements Hostile {
    private Friendly friendly;
    private Coordinate friendlyLastSeenCoordinate;
    //private EnemyStates enemyState = IDLE;

    public Enemy(Coordinate startPosition, int width, int height) { //TODO: den som vet: gör bättre variabelnamn!... Vad är int v?
        super(startPosition, width, height);
    }

    //TODO: lägg här allt som ska hända när logiken i fienden ska göras. Ska EnemyBrain hantera detta tro?
    public void tick() {
        //TODO: lägg till att gå,
        //TODO: lägg till att slå
        //TODO: lägg till att ...?
        //TODO: ELLER anropa EnemyBrain.tick() kankse, om vi fixar den klassen dvs.?
    }

    public void addFriendly(Friendly friendly) {
        this.friendly = friendly;
    }

    public class EnemyBrain {
        public void think() {

        }
    }
}
