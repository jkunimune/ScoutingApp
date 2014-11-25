import java.util.Scanner;
import java.util.*;


public class ScoutingApp {
  public static Scanner scan = new Scanner(System.in);
  public static ArrayList<Team> lineup = new ArrayList<Team>(); // list of all FTC teams
  
  
  public static void main(String[] args) {
    boolean nextMatch = true; // tells program when to exit while loop
    do {
      System.out.println("What teams are on the red alliance?");
      Team red1 = getTeam(scan.nextInt()); // asks for each team on the red alliance and exchanges team number for team
      Team red2 = getTeam(scan.nextInt());
      
      System.out.println("And what teams are on the blue alliance?");
      Team blu1 = getTeam(scan.nextInt()); // same for blue alliance
      Team blu2 = getTeam(scan.nextInt());
      
      System.out.println("Is team "+red1.number+" starting on the ramp or parking zone (R or P)?"); // Asks for information on teams
      boolean r1Start = scan.nextLine().equalsIgnoreCase("R");
      scan.nextLine(); // fixes the annoying thing where nextLine() skips ahead
      System.out.println("Is team "+red2.number+" starting on the ramp or parking zone (R or P)?");
      boolean r2Start = scan.nextLine().equalsIgnoreCase("R");
      System.out.println("Is team "+blu1.number+" starting on the ramp or parking zone (R or P)?");
      boolean b1Start = scan.nextLine().equalsIgnoreCase("R");
      System.out.println("Is team "+blu2.number+" starting on the ramp or parking zone (R or P)?");
      boolean b2Start = scan.nextLine().equalsIgnoreCase("R");
      System.out.println("Please enter the final score in the form Red Autonomous, Red Teleop, Red Penalty, Blue Autonomous, Blue Teleop, Blue Penalty.");
      int rAuto = scan.nextInt();
      int rTele = scan.nextInt();
      int rPen = scan.nextInt();
      int bAuto = scan.nextInt();
      int bTele = scan.nextInt();
      int bPen = scan.nextInt();
      red1.tallyMatch(r1Start, rAuto, rTele, -bPen); // updates lineup based on scores
      red2.tallyMatch(r2Start, rAuto, rTele, -bPen);
      blu1.tallyMatch(b1Start, bAuto, bTele, -rPen);
      blu2.tallyMatch(b2Start, bAuto, bTele, -rPen);
      
      System.out.println("Very well.\nIs there another match?"); // asks if it should continue
      nextMatch = scan.nextLine().equalsIgnoreCase("yes");
      scan.nextLine(); // fixes annoying nextLine() skipping thing again
    } while (nextMatch);
    
    System.out.println("They all seem pretty great. You should use eeny-meeny-miney-mo.");
  }
  
  
  public static Team getTeam(int teamNumber) { // returns the team with the given number.  if team is not in lineup, adds it
    for (Team t: lineup)
      if (t.number == teamNumber)
        return t;
    
    lineup.add(new Team(teamNumber));
    return lineup.get(lineup.size()-1);
  }
}