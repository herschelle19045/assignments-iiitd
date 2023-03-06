package Assignment3;

import java.util.Collection;
import java.util.HashMap;

public class Mafia extends Player {
    // Static members
    private static HashMap<Integer, Mafia> mafias = new HashMap<>();

    public static void disposeMafias() {
        mafias = new HashMap<>();
    }

    public static Player kill(HashMap<Integer, Player> players, int n) {
        int i; Player p;
        do {
            i = Main.generate(n)+1;
            p = players.get(i);
        } while ( p == null || (p instanceof Mafia) );
        return p;
    }

    public static void removeMafia(Player p) {
        int keyToRemove = p.getId();
        mafias.entrySet().removeIf( entry -> ( keyToRemove == entry.getKey() ) );
    }

    public static double getTotalMafiasHP() {
        double hp=0;
        for(Mafia m : mafias.values()) {
            hp += m.getHealth();
        }
        return hp;
    }

    public static void takeCollectiveDamage(double dmg) {
        while (dmg > 0.000000000000001) {       // not 0 due to fraction result handling
            int alive = mafiasAlive();
            if(alive == 0) return;

            double dmgPerMafia = dmg/alive;
            for(Mafia m : mafias.values()) {
                if (!m.isDead()) {
                    double mHealth = m.getHealth();
                    if (mHealth < dmgPerMafia) {
                        m.takeDamage(mHealth);
                        dmg -= mHealth;
                    } else {
                        m.takeDamage(dmgPerMafia);
                        dmg -= dmgPerMafia;
                    }
                }
            }
        }
    }

    public static int mafiasAlive() {       // Mafias having hp > 0
        int count=0;
        for(Mafia m : mafias.values()) {
            if(m.getHealth() > 0) count++;
        }
        return count;
    }

    public static int getMafiasInGame() {       // Mafias not eliminated
        return mafias.size();
    }

    public static Collection<Mafia> getMafias() {
        return mafias.values();
    }
    // End of static section

    public Mafia(int id) {
        super(id, 2500);
        mafias.put(this.getId(), this);
    }

    @Override
    public Player vote(HashMap<Integer, Player> players, int n) {
        return kill(players, n);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && getClass() == obj.getClass()) {
            Mafia m = (Mafia) obj;
            return getId() == m.getId();
        }
        return false;
    }

}
