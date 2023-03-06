package Assignment3;

import java.util.HashMap;

public class Commoner extends Player {

    public Commoner(int id) {
        super(id, 1000);
    }

    @Override
    public Player vote(HashMap<Integer, Player> players, int n) {
        int i; Player p;
        do {
            i = Main.generate(n)+1;
            p = players.get(i);
        } while (p == null || equals(p));       // Should not choose self
        return p;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj != null && getClass() == obj.getClass() ) {
            Commoner c = (Commoner) obj;
            return getId() == c.getId();
        }
        return false;
    }
}

