package Assignment3;

import java.util.*;

public class Main {

    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    private static HashMap<Integer, Player> players, playersCopy;
    private static Map<Integer, Mafia> mafias;
    private static Map<Integer, Detective> detectives;
    private static Map<Integer, Healer> healers;

    public static void main(String[] args) {

        while (true) {
            players = new HashMap<>(); playersCopy = new HashMap<>();
            mafias = new TreeMap<>(); detectives = new TreeMap<>(); healers = new TreeMap<>();

            System.out.println("Welcome to Mafia");
            System.out.print("Enter number of players: ");
            int n = scanner.nextInt();
            while (n < 6) {
                System.out.print("Enter number greater than 5: ");
                n = scanner.nextInt();
            }

            displayMenu();

            int choice = scanner.nextInt();
            if(choice == 5) choice = generate(4)+1;

            int userID = generateUserID(n, choice);
            Game g = new Game(players);
            g.startGame(choice, userID, n);

            displayRoles(userID);

            System.out.println("Play Again?");
            System.out.println("    1) Yes");
            System.out.println("    2) No");

            int c = scanner.nextInt();
            if(c == 2) break;
        }
    }

    public static int generate(int range) {
        return random.nextInt(range);
    }

    private static void displayMenu() {
        System.out.println("Choose a Character");
        System.out.println("    1) Mafia");
        System.out.println("    2) Detective");
        System.out.println("    3) Healer");
        System.out.println("    4) Commoner");
        System.out.println("    5) Assign Randomly");
    }

    private static int generateUserID(int n, int choice) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) list.add(i);
        Collections.shuffle(list);

        int maxMafias = n / 5;
        int maxDetectives = n / 5;
        int maxHealers = Math.max(1, n / 10);
        int maxCommoners = n - maxMafias - maxDetectives - maxHealers;

        // For user
        if (choice == 1) maxMafias--;
        else if (choice == 2) maxDetectives--;
        else if (choice == 3) maxHealers--;
        else maxCommoners--;

        // Generate id and add players
        for (int i = 0; i < maxMafias; i++) {
            int id = list.remove(0);
            Mafia m = new Mafia(id);
            players.put(id, m);
        }
        for (int i = 0; i < maxDetectives; i++) {
            int id = list.remove(0);
            Detective d = new Detective(id);
            players.put(id, d);
        }
        for (int i = 0; i < maxHealers; i++) {
            int id = list.remove(0);
            Healer h = new Healer(id);
            players.put(id, h);
        }
        for (int i = 0; i < maxCommoners; i++) {
            int id = list.remove(0);
            Commoner c = new Commoner(id);
            players.put(id, c);
        }

        // Generate user id
        int userID = list.remove(0);
        System.out.println("You are Player" + userID);

        // Display teammates
        if (choice == 1) {
            System.out.print("You are Mafia. Other Mafias are: [ ");
            for (Player p : players.values()) {
                if (p instanceof Mafia) {
                    System.out.print(p.toString() + " ");
                }
            }
            System.out.println("]");
            Mafia m = new Mafia(userID);
            players.put(userID, m);
        }
        else if (choice == 2) {
            System.out.print("You are Detective. Other Detectives are: [ ");
            for (Player p : players.values()) {
                if (p instanceof Detective) {
                    System.out.print(p.toString() + " ");
                }
            }
            System.out.println("]");
            Detective d = new Detective(userID);
            players.put(userID, d);
        }
        else if (choice == 3) {
            System.out.print("You are Healer. Other Healers are: [ ");
            for (Player p : players.values()) {
                if (p instanceof Healer) {
                    System.out.print(p.toString() + " ");
                }
            }
            System.out.println("]");
            Healer h = new Healer(userID);
            players.put(userID, h);
        }
        else {
            System.out.println("You are Commoner");
            Commoner c = new Commoner(userID);
            players.put(userID, c);
        }

        // Used for storing permanently, used in displayRoles function below
        playersCopy.putAll(players);

        for(Mafia m : Mafia.getMafias()) {
            mafias.put(m.getId(), m);
        }

        for(Detective d : Detective.getDetectives()) {
            detectives.put(d.getId(), d);
        }

        for(Healer h : Healer.getHealers()) {
            healers.put(h.getId(), h);
        }

        return userID;
    }

    // At the end
    private static void displayRoles(int userID) {
        for(Mafia m : mafias.values()) {
            System.out.print(m.toString() + (m.equals(playersCopy.get(userID)) ? "(User) " : " "));
        }
        System.out.println("were Mafias");

        for(Detective d : detectives.values()) {
            System.out.print(d.toString() + (d.equals(playersCopy.get(userID)) ? "(User) " : " "));
        }
        System.out.println("were Detectives");

        for(Healer h : healers.values()) {
            System.out.print(h.toString() + (h.equals(playersCopy.get(userID)) ? "(User) " : " "));
        }
        System.out.println("were healers");

        for(Player p : playersCopy.values()) {
            if(p instanceof Commoner) {
                System.out.print(p.toString() + (p.equals(playersCopy.get(userID)) ? "(User) " : " "));
            }
        }
        System.out.println("were Commoners");
    }
}
