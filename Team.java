public class Team {
  private boolean team = false;
  private int number = 0;
  
  private int matchCount = 0;
  
  private int autoScore = 0;
  private int teleScore = 0;
  private int endScore = 0;
  
  private int elevatorRank = 0;
  private int intakeRank = 0;
  private int autoRank = 0;
  
  private int highestGoal = 0;
  private boolean centerGoal = false;
  private boolean cascade = false;
  private boolean tubeManeuver = false;
  private boolean rampStart = false;
  private boolean parkStart = false;
  
  
  
  public void tallyMatch(boolean startPos, int auto, int tele, int end) {
    if (startPos)
      rampStart = true;
    else
      parkStart = true;
    
    autoScore += auto;
    teleScore += tele;
    endScore += end;
    matchCount ++;
  }
  
  
  public void lowGoal() {
    if (highestGoal < 1)
      highestGoal = 1;
  }
  
  
  public void midGoal() {
    if (highestGoal < 2)
      highestGoal = 2;
  }
  
  
  public void highGoal() {
    highestGoal = 3;
  }
  
  
  public void centerGoal() {
    centerGoal = true;
  }
  
  
  public void cascade() {
    cascade = true;
  }
  
  
  public void tubeManeuver() {
    tubeManeuver = true;
  }
}