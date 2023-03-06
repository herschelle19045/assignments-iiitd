package Assignment3;

import java.util.Collection;
import java.util.HashMap;

public class Detective extends Player {
    // Static attributes and methods
    private static HashMap<Integer, Detective> detectives = new HashMap<>();

    public static void disposeDetectives() {
        detectives = new HashMap<>();
    }

    public static Player test(HashMap<Integer, Player> players, int n) {
        int i; Player p;
        do {
            i = Main.generate(n)+1;
            p = players.get(i);
        } while ( p == null || ( p instanceof Detective ) /*|| !p.isPotentialMafia()*/ );// isPotentialMafia -> Intelligence on previous testing
        return p;
    }

    public static void removeDetective(Player p) {
        int keyToRemove = p.getId();
        detectives.entrySet().removeIf( entry -> ( keyToRemove == entry.getKey() ) );
    }

    public static int getDetectivesInGame() {
        return detectives.size();
    }

    public static Collection<Detective> getDetectives() {
        return detectives.values();
    }
    // End of static section

    public Detective(int id) {
        super(id, 800);
        detectives.put(this.getId(), this);
    }

    @Override
    public Player vote(HashMap<Integer, Player> players, int n) {
        return test(players, n);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && getClass() == obj.getClass()) {
            Detective d = (Detective) obj;
            return getId() == d.getId();
        }
        return false;
    }

}
