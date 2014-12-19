import java.util.Scanner;
import java.util.*;


public class FTCScoutingApp {
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
      
      scan.nextLine();
      System.out.println("\nIs team "+red1.number+" starting on the ramp or parking zone?"); // Asks for information on teams
      boolean r1Start = scan.nextLine().substring(0,1).equalsIgnoreCase("R");
      System.out.println("Is team "+red2.number+" starting on the ramp or parking zone?");
      boolean r2Start = scan.nextLine().substring(0,1).equalsIgnoreCase("R");
      System.out.println("Is team "+blu1.number+" starting on the ramp or parking zone?");
      boolean b1Start = scan.nextLine().substring(0,1).equalsIgnoreCase("R");
      System.out.println("Is team "+blu2.number+" starting on the ramp or parking zone?");
      boolean b2Start = scan.nextLine().substring(0,1).equalsIgnoreCase("R");
      
      for (int i = 0; i < 4; i ++) {
        Team current;
        switch (i) {
          case 0:   current = red1;
            break;
          case 1:   current = red2;
            break;
          case 2:   current = blu1;
            break;
          default:  current = blu2;
        }
        System.out.println("\nPlease enter the following information about team "+current.number+".\nCascade?");
        if (scan.nextLine().equalsIgnoreCase("yes"))
          current.cascade();
        System.out.println("Maneuvering tubes?");
        if (scan.nextLine().equalsIgnoreCase("yes"))
          current.tubeManeuver();
        System.out.println("Highest goal scored in during teleop? (none = 0, low = 1, center = 4)");
        current.newHighestGoal(scan.nextInt());
        System.out.println("Rank the autonomous from 0 (non-functional) to 10 (amazing)");
        int newAutoRank = scan.nextInt();
        System.out.println("Rank the drive train from 0 to 10");
        int newDriveRank = scan.nextInt();
        System.out.println("Rank the intake from 0 to 10");
        int newIntRank = scan.nextInt();
        System.out.println("Rank the elevator from 0 to 10");
        int newElevRank = scan.nextInt();
        System.out.println("Any comments?");
        String rbtComments = scan.nextLine();
        rbtComments = scan.nextLine();
        
        current.rank(newAutoRank, newElevRank, newIntRank, newDriveRank);
        current.comment(rbtComments);
      }
      
      System.out.println("\nPlease enter the final score in the form Red Autonomous, Red Teleop, Red Penalty, Blue Autonomous, Blue Teleop, Blue Penalty.");
      int rAuto = scan.nextInt();
      int rTele = scan.nextInt();
      int rPen = scan.nextInt();
      int bAuto = scan.nextInt();
      int bTele = scan.nextInt();
      int bPen = scan.nextInt();
      red1.tallyMatch(red2, r1Start, rAuto, rTele, bPen); // updates lineup based on scores
      red2.tallyMatch(red1, r2Start, rAuto, rTele, bPen);
      blu1.tallyMatch(blu2, b1Start, bAuto, bTele, rPen);
      blu2.tallyMatch(blu1, b2Start, bAuto, bTele, rPen);
      
      System.out.println("Very well.\n\nIs there another match?"); // asks if it should continue
      nextMatch = scan.nextLine().equalsIgnoreCase("yes");
      nextMatch = scan.nextLine().equalsIgnoreCase("yes"); // do it twice to fix dumb skipping thing
    } while (nextMatch);
    
    System.out.println("\nHere is my ranking for the teams you input.\n");
    
    int best = 0;
    int worst = 0;
    for (int i = 0; i < lineup.size(); i ++) { // go through the lineup to find the best team
      if (lineup.get(i).compatibleWith(getTeam(7550)) > lineup.get(best).compatibleWith(getTeam(7550)))
        best = i;
    }
    for (int i = 0; i < lineup.size(); i ++) { // go through the lineup to find the worst team
      if (lineup.get(i).compatibleWith(getTeam(7550)) < lineup.get(worst).compatibleWith(getTeam(7550)))
        worst = i;
    }
    
    for (int i = lineup.get(best).compatibleWith(getTeam(7550)); i >= lineup.get(worst).compatibleWith(getTeam(7550)); i --) { // for every possible rank
      for (Team t: lineup) { // for each team
        if (t.compatibleWith(getTeam(7550)) == i) // if the team has that rank
          System.out.println("Team " + t.number + " - " + t.compatibleWith(getTeam(7550)) + "\n" + t.report() + "\n"); // display it
      }
    }
  }
  
  
  public static Team getTeam(int teamNumber) { // returns the team with the given number.  if team is not in lineup, adds it
    for (Team t: lineup)
      if (t.number == teamNumber)
        return t;
    
    lineup.add(new Team(teamNumber));
    return lineup.get(lineup.size()-1);
  }
}