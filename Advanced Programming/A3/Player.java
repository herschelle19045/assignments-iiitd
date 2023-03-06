package Assignment3;

import java.util.HashMap;

public abstract class Player {
    private final int id;
    private double health;
    private boolean potentialMafia;

    public Player(int id, int hp) {
        this.id = id;
        health = hp;
        potentialMafia = true;
    }

    public void takeDamage(double dmg) {
        if(dmg > health) health = 0;
        else health -= dmg;
    }

    public void heal() {
        health += 500;
    }

    public boolean isDead() {
        return health == 0;
    }

    public int getId() {
        return id;
    }

    public double getHealth() {
        return health;
    }

    public boolean isPotentialMafia() {
        return potentialMafia;
    }

    public void setPotentialMafia(boolean potentialMafia) {
        this.potentialMafia = potentialMafia;
    }

    public abstract Player vote(HashMap<Integer, Player> players, int n);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public String toString() {
        return "Player" + id;
    }
}

