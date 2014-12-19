import java.util.ArrayList;

/**
 * Created by jkunimune15 on 11/26/14.
 */
public class Team {
  public int number;
  
  public int matchCount = 0;
  
  public ArrayList<Integer> autoScore = new ArrayList<Integer>();
  public ArrayList<Integer> teleScore = new ArrayList<Integer>();
  public ArrayList<Integer> penalty = new ArrayList<Integer>();
  public ArrayList<Team> ally = new ArrayList<Team>();
  public ArrayList<String> comments = new ArrayList<String>();
  
  public double elevatorRank = 0;
  public double intakeRank = 0;
  public double driveRank = 0;
  public double autoRank = 0;
  
  public int highestGoal = 0;
  public boolean cascade = false;
  public boolean tubeManeuver = false;
  public boolean rampStart = false;
  public boolean parkStart = false;
  
  
  
  public Team() {}
  
  
  public Team(int newNum) {
    number = newNum;
  }
  
  
  
  public void tallyMatch(Team allianceMember, boolean startPos, int auto, int tele, int pen) {
    if (startPos)
      rampStart = true;
    else
      parkStart = true;
    
    ally.add(allianceMember);
    autoScore.add(auto);
    teleScore.add(tele);
    penalty.add(pen);
    matchCount ++;
  }
  
  
  public void rank(int auto, int elev, int intake, int drive) {
    if (matchCount <= 0) {
      autoRank = (double)auto/10;
      elevatorRank = (double)elev/10;
      intakeRank = (double)intake/10;
      driveRank = (double)drive/10;
    }
    else {
      autoRank = (autoRank + (double)auto/10) / 2;
      elevatorRank = (elevatorRank + (double)elev/10) / 2;
      intakeRank = (intakeRank + (double)intake/10) / 2;
      driveRank = (driveRank + (double)drive/10) / 2;
    }
  }
  
  
  public void comment(String newComment) {
    comments.add(newComment);
  }
  
  
  public void newHighestGoal(int newHighGoal) {
    if (newHighGoal > highestGoal)
      highestGoal = newHighGoal;
  }
  
  
  public void cascade() {
    cascade = true;
  }
  
  
  public void tubeManeuver() {
    tubeManeuver = true;
  }
  
  
  public int avgScore() {
    if (matchCount <= 0)
      return 0;
    
    int sum = 0;
    
    for (int i = 0; i < matchCount; i ++) {
      sum += autoScore.get(i) + teleScore.get(i) - penalty.get(i);
    }
    
    return sum/matchCount;
  }
  
  
  public int weightedAvgScore() {
    if (matchCount <= 0)
      return 0;
    
    int sum = 0;
    
    for (int i = 0; i < matchCount; i ++) {
      sum += autoScore.get(i) + teleScore.get(i) - penalty.get(i) - ally.get(i).avgScore()/2;
    }
    
    return sum/matchCount;
  }
  
  
  public int totScore(int match) {
    if (matchCount <= match)
      return 0;
    
    return autoScore.get(match) + teleScore.get(match) - penalty.get(match);
  }
  
  
  public int weightedTotScore(int match) {
    if (matchCount <= match)
      return 0;
    
    int sum = 0;
    
    return autoScore.get(match) + teleScore.get(match) - penalty.get(match) - ally.get(match).avgScore()/2;
  }
  
  
  private int or(boolean a, boolean b) {
    if (a || b)
      return 1;
    else
      return 0;
  }
  
  
  private int opposingStarts(boolean ramp1, boolean ramp2, boolean zone1, boolean zone2) {
    if ((ramp1 && zone2) || (ramp2 && zone1))
      return 2;
    else
      return 0;
  }
  
  
  private int getBestGoal(int highest1, int highest2) {
    if (highest1 > highest2)  return highest1;
    else                      return highest2;
  }
  
  
  private int bestScore() {
    int best = 0;
    for (int i = 0; i < matchCount; i ++) {
      if (totScore(i) > best)
        best = totScore(i);
      if (weightedTotScore(i) > best)
        best = weightedTotScore(i);
    }
    return best;
  }
  
  
  public int compatibleWith(Team them) {
    return (int)(weightedAvgScore() *
                 (them.autoRank + this.autoRank + or(them.cascade, this.cascade) +
                  opposingStarts(them.rampStart,this.rampStart,them.parkStart,this.parkStart) + 1) *
                 (them.driveRank + this.driveRank + or(them.tubeManeuver, this.tubeManeuver) + 1) *
                 (them.intakeRank*them.elevatorRank + this.intakeRank*this.elevatorRank + getBestGoal(them.highestGoal, this.highestGoal) + 1));
  }
  
  
  public String report() {
    String report = "";
    
    if (cascade)       report += "Releases cascade.  ";
    if (tubeManeuver)  report += "Maneuvers tubes.  ";
    switch (highestGoal) {
      case 1:
        report += "Scores in low goal.  ";
        break;
      case 2:
        report += "Scores in medium goal.  ";
        break;
      case 3:
        report += "Scores in high goal.  ";
        break;
      case 4:
        report += "Scores in center goal during endgame.  ";
    }
    report += "\nAutonomous   ";
    for (int i = 0; i < autoRank; i ++)
      report += "-";
    report += "\nDrive Train  ";
    for (int i = 0; i < driveRank; i ++)
      report += "-";
    report += "\nIntake       ";
    for (int i = 0; i < intakeRank; i ++)
      report += "-";
    report += "\nElevator     ";
    for (int i = 0; i < elevatorRank; i ++)
      report += "-";
    report += "\n";
    
    report += "Scores and estimated weighted scores:\n";
    for (int i = bestScore()/25*25; i >=0; i -= 25) {
      for (int j = 0; j < matchCount; j ++) {
        if (totScore(j) >= i)
          report += "|";
        else
          report += " ";
        if (weightedTotScore(j) >= i)
          report += "|";
        else
          report += " ";
        report += " ";
      }
      report += "\n";
    }
    
    for (String c: comments)
      if (c.length() > 0)
        report += c + "\n";
    
    return report;
  }
}
