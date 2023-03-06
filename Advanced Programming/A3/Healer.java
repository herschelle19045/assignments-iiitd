package Assignment3;

import java.util.Collection;
import java.util.HashMap;

public class Healer extends Player {
    // Static members
    private static HashMap<Integer, Healer> healers = new HashMap<>();

    public static void disposeHealerList() {
        healers = new HashMap<>();
    }

    public static Player heal(HashMap<Integer,Player> players, int n) {
        int i; Player p;
        do {
            i = Main.generate(n)+1;
            p = players.get(i);
        } while (p == null || (p instanceof Healer));       // Should not choose self or healer
        return p;
    }

    public static void removeHealer(Player p) {
        int keyToRemove = p.getId();
        healers.entrySet().removeIf( entry -> ( keyToRemove == entry.getKey() ) );
    }

    public static int getHealersInGame() {
        return healers.size();
    }

    public static Collection<Healer> getHealers() {
        return healers.values();
    }
    // End of static section

    public Healer(int id) {
        super(id, 800);
        healers.put(this.getId(), this);
    }

    public Player choose(HashMap<Integer,Player> players, int n) {
        int i; Player p;
        do {
            i = Main.generate(n)+1;
            p = players.get(i);
        } while (p == null || equals(p));       // Should not choose self
        return p;
    }

    @Override
    public Player vote(HashMap<Integer, Player> players, int n) {
        return choose(players, n);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && getClass() == obj.getClass()) {
            Healer h = (Healer) obj;
            return getId() == h.getId();
        }
        return false;
    }

}
