public class Test {
  public static void main(String args[]) {
    Team us = new Team(7550);
    Team them = new Team(5159);
    System.out.println(them.compatibleWith(us));
  }
}