package Assignment3;

import java.util.HashMap;

public class Game {
    private final HashMap<Integer, Player> players;
    private int mafiasAlive;
    private int detectivesAlive;
    private int healersAlive;
    private boolean userAlive;

    public Game(HashMap<Integer, Player> players) {
        this.players = players;
        mafiasAlive = Mafia.getMafiasInGame();
        detectivesAlive = Detective.getDetectivesInGame();
        healersAlive = Healer.getHealersInGame();
        userAlive = true;
    }

    public void startGame(int choice, int userID, int n) {
        int i=1;    // Round number
        while (true) {

            System.out.println("\nRound " + i++ + ":\n");

            mafiasAlive = Mafia.getMafiasInGame();
            detectivesAlive = Detective.getDetectivesInGame();
            healersAlive = Healer.getHealersInGame();
            userAlive = checkUserAlive(userID);

            displayAlivePlayers(userID);

            if (mafiasAlive == 0) {
                System.out.println("Mafias have lost");
                Mafia.disposeMafias();
                Detective.disposeDetectives();
                Healer.disposeHealerList();

                break;
            }

            else if (2 * mafiasAlive >= players.size()) {
                System.out.println("Mafias have won");
                Mafia.disposeMafias();
                Detective.disposeDetectives();
                Healer.disposeHealerList();

                break;
            }

            else {
                Player target;
                Player testByDetectives;
                Player playerToHeal;

                if(choice == 1) {
                    target = playAsMafia(n);
                    testByDetectives = simulateDetectives(n);
                    playerToHeal = simulateHealers(n);
                }

                else if(choice == 2) {
                    target = simulateMafia(n);
                    testByDetectives = playAsDetective(n);
                    playerToHeal = simulateHealers(n);
                }

                else if(choice == 3) {
                    target = simulateMafia(n);
                    testByDetectives = simulateDetectives(n);
                    playerToHeal = playAsHealer(n, userID);
                }

                else {
                    target = simulateMafia(n);
                    testByDetectives = simulateDetectives(n);
                    playerToHeal = simulateHealers(n);
                }

                endOfActions(userID, n, target, testByDetectives, playerToHeal);
            }
        }
    }

    private void endOfActions(int userID, int n, Player target, Player testByDetectives, Player playerToHeal) {
        System.out.println("--End of actions--");

        if (target != playerToHeal && target.getHealth() == 0) {        //If not saved by healer and health = 0 -> die
            removeSpecialPlayer(target);
            players.remove(target.getId());
            System.out.println(target.toString() + " has died.");
        }

        else {
            System.out.println("No one died.");
        }

        if (testByDetectives == null) {
            Player playerToVoteOut = vote(userID, n);
            removeSpecialPlayer(playerToVoteOut);
            players.remove(playerToVoteOut.getId());
            System.out.println(playerToVoteOut.toString() + " has been voted out");
        }

        else {
            System.out.println(testByDetectives.toString() + " was detected by detectives to be Mafia, and was voted out");
            removeSpecialPlayer(testByDetectives);
            players.remove(testByDetectives.getId());
        }
    }

    private Player takeUserInput(int n) {
        Player player;
        int target = Main.scanner.nextInt();

        player = players.get(target);
        if (player == null) {
            if (target < 1 || target > n)
                System.out.print("Choose a valid player: ");
            else
                System.out.print("Player" + target + " is already dead choose again: ");
            return null;
        }
        return player;
    }

    // User section
    private Player playAsMafia(int n) {
        Player playerToKill;

        if(userAlive) {
            System.out.print("Choose a target: ");

            while (true) {
                playerToKill = takeUserInput(n);
                if (playerToKill == null) continue;

                if (playerToKill instanceof Mafia) {
                    System.out.print("You cannot choose a mafia to kill, choose again: ");
                } else break;
            }
        }

        else {
            playerToKill = Mafia.kill(players, n);
            System.out.println("Mafias have chosen their target");
        }

        double targetHP = playerToKill.getHealth();
        double totalMafiasHP = Mafia.getTotalMafiasHP();

        if(totalMafiasHP < targetHP) {
            targetHP = totalMafiasHP;
        }

        playerToKill.takeDamage(totalMafiasHP);
        Mafia.takeCollectiveDamage(targetHP);

        return playerToKill;
    }

    private Player playAsDetective(int n) {
        Player playerToTest;

        if(userAlive) {
            System.out.print("Choose a player to test: ");

            while (true) {
                playerToTest = takeUserInput(n);
                if (playerToTest == null) continue;

                if(playerToTest instanceof Detective) {
                    System.out.print("You cannot choose a detective to test, choose again: ");
                }
                else break;
            }
        }

        else {
            playerToTest = Detective.test(players, n);
            System.out.println("Detectives have chosen their target");
        }

        if(playerToTest instanceof Mafia) {
            return playerToTest;
        }

        else {
            System.out.println(playerToTest.toString() + " is not mafia");
            playerToTest.setPotentialMafia(false);
        }
        return null;
    }

    private Player playAsHealer(int n, int userID) {
        Player playerToHeal = null;

        if(userAlive) {
            System.out.print("Choose a player to heal: ");

            while (true) {
                playerToHeal = takeUserInput(n);
                if (playerToHeal == null) continue;

                if(playerToHeal.equals(players.get(userID))) {
                    System.out.print("You cannot heal yourself, choose again: ");
                }
                else break;
            }
        }
        else {
            if(healersAlive > 0) {
                System.out.println("Healers have chosen someone to heal");
                playerToHeal = Healer.heal(players, n);
                playerToHeal.heal();
            }
        }
        return playerToHeal;
    }
    // End of user section

    // Computer simulations
    private Player simulateMafia(int n) {
        System.out.println("Mafias have chosen their target");

        Player target = Mafia.kill(players, n);
        double targetHP = target.getHealth();
        double totalMafiasHP = Mafia.getTotalMafiasHP();

        target.takeDamage(totalMafiasHP);
        Mafia.takeCollectiveDamage(targetHP);
        return target;
    }

    private Player simulateDetectives(int n) {
        System.out.println("Detectives have chosen a player to test");
        Player playerToTest = Detective.test(players, n);

        if(playerToTest instanceof Mafia) {
            return playerToTest;
        }

        else {
            playerToTest.setPotentialMafia(false);
        }
        return null;
    }

    private Player simulateHealers(int n) {
        System.out.println("Healers have chosen someone to heal");
        Player player = null;

        if(healersAlive > 0) {
            player = Healer.heal(players, n);
            player.heal();
        }
        return player;
    }
    // End of simulations

    private Player vote(int userID, int n) {
        HashMap<Player, Integer> voteList = new HashMap<>();

        Player playerToKick = null;
        boolean flag = true;

        // If player alive take vote
        Player chosenByUser = null;
        if(isUserAlive(userID)) {
            System.out.print("Select a person to vote out: ");

            while (true) {
                int c = Main.scanner.nextInt();
                chosenByUser = players.get(c);

                if (chosenByUser == null) {
                    if(c < 1 || c > n) {
                        System.out.print("Choose a valid player: ");
                    }
                    else {
                        System.out.print("Player" + c + " is already dead choose another player: ");
                    }
                } else break;
            }
        }

        while (flag) {
            for (Player p : players.values()) {
                voteList.put(p, 0);
            }

            for (Player p : players.values()) {
                Player chosen;

                if (p.getId() == userID) {
                    chosen = chosenByUser;
                }
                else {
                    chosen = p.vote(players, n);
                }
                int votes = voteList.get(chosen);
                voteList.replace(chosen, votes + 1);
            }

            playerToKick = players.values().toArray(new Player[0])[0];

            for (Player p : voteList.keySet()) {
                if (voteList.get(p) > voteList.get(playerToKick)) {
                    playerToKick = p;
                }
            }

            flag = checkTie(playerToKick, voteList);
        }
        return playerToKick;
    }

    private boolean checkTie(Player playerToKick, HashMap<Player, Integer> voteList) {
        for(Player p : voteList.keySet())
            if (!p.equals(playerToKick))
                if (voteList.get(p).equals(voteList.get(playerToKick)))
                    return true;
        return false;
    }

    private boolean checkUserAlive(int userID) {
        for(Player p : players.values()) {
            if(p.getId() == userID)
                return true;
        }
        return false;
    }

    private void displayAlivePlayers(int userID) {
        System.out.print(players.size() + " players are alive: [ ");
        for(Player p : players.values()) {
            System.out.print(p.toString() + (p.equals(players.get(userID)) ? "(User) " : " "));
        }
        System.out.println("]");
    }

    private void removeSpecialPlayer(Player player) {
        if(player instanceof Detective) {
            Detective.removeDetective(player);
        }
        else if(player instanceof Healer) {
            Healer.removeHealer(player);
        }
        else if(player instanceof Mafia) {
            Mafia.removeMafia(player);
        }
    }

    private boolean isUserAlive(int userID) {
        for(Player p : players.values()) {
            if(p.getId() == userID) {
                return true;
            }
        }
        return false;
    }
}
