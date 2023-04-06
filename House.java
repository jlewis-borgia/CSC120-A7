/* This is a stub for the House class */
import java.util.ArrayList; 

/**
 * subclass that creates house-type buildings where people are allowed to move in and out of
 */
public class House extends Building{

  private ArrayList<String> residents;
  private boolean hasDiningRoom;
  private boolean hasElevator;
  
  
  public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
    super(name, address, nFloors);
    this.residents = new ArrayList<String>();
    this.hasDiningRoom = hasDiningRoom;
    this.hasElevator = hasElevator;
  }
/**
 * Accessor for hasDiningRoom
 * @return whether or not the house has a dining room (true/false)
 */
  public boolean hasDiningRoom() {
    return this.hasDiningRoom;
  }

  /**
 * Accessor for hasElevator
 * @return whether or not the house has an elevator (true/false)
 */
public boolean hasElevator() {
  return this.hasElevator;
}


  /**
   * Accessor for nResidents
   * @return number of residents in house
   */
  public int nResidents() {
    return this.residents.size();
  }

  /**
   * original method for moving in someone into a given house after checking they don't already live there
   * @param name
   */
  public void moveIn(String name) {
    // check if this.residents contains name
    // if so: throw exception
    // append name to this.residents
    if (this.residents.contains(name)) {
      throw new RuntimeException(name + " is already a resident of this house.");
    }
    // if we're good to go, add to roster
    this.residents.add(name);
    System.out.println(name + " has just moved into " + this.name + ". Go say hello!");
  }

  /**
   * overloaded method for moving in two people into a given house after checking that either don't already live there
   * @param name
   * @param second_name
   */
  public void moveIn(String name, String second_name) {
    // check if this.residents contains name and second_name
    // if so: throw exception
    // append name to this.residents
    if (this.residents.contains(name)) {
      throw new RuntimeException(name + " is already a resident of this house.");
    }
    if (this.residents.contains(second_name)) {
      throw new RuntimeException(second_name + " is already a resident of this house.");
    }
    // if we're good to go, add to roster
    this.residents.add(name);
    this.residents.add(second_name);
    System.out.println(name + " and " + second_name + " have just moved into " + this.name + ". Go say hello!");
  }

  /**
   * method for moving out someone from a given house after checking they are currently living there
   * @param name
   * @return name of a person who has moved out of the house
   */
  public String moveOut(String name) {
    //check if this.residents contains name
    // if not throw exception
    // if so: remove name from this.residents
    if (this.residents.contains(name)) {
      this.residents.remove(name);
      System.out.println(name + " has just moved out of " + this.name + ".");
      return name;
    } else {
      throw new RuntimeException(name + " is not a resident of this house.");
    }
  }

  /**
   * Original method that checks list of residents of a house to see if a person lives there
   * @param person
   * @return true if a person is a resident of a given house
   */
  public boolean isResident(String person) {
    if (this.residents.contains(person)) {
      System.out.println(person + " is a resident of this house.");
    } else {
      System.out.println(person + " is not a resident of this house.");
    }
    return this.residents.contains(person);
  }

   /**
   * Overloaded method that checks list of residents of a house to see if two different people both live there
   * @param person
   * @param second_person
   * @return true if both people are residents of a given house, false if either are not a resident
   */
  public boolean isResident(String person, String second_person) {
    if (this.residents.contains(person)) {
      System.out.println(person + " is a resident of this house.");
      if (this.residents.contains(second_person)) {
        System.out.println(second_person + " is a resident of this house.");
        return true;
      } else {
        System.out.println(second_person + " is not a resident of this house.");
        return false;
      }
    } else {
      System.out.println(person + " is not a resident of this house.");
      return false;
    }
  }

  /**
   * prints out a description of the house including name, address, number of floors, how many people live there, and if the house hs a dining room
   */
  public String toString() {
    String description = super.toString();
    description += " There are currently " + this.residents.size() + " people living in this house.";
    description += " This house ";
    if (this.hasDiningRoom) {
      description += "has";
    } else {
      description += " does not have";
    }
    description += " an active dining room.";
    return description;
  }

  /**
   * method to print out for user what available methods there are for the House class
   */
  public void showOptions(){
      System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goDown()\n + goToFloor(n)\n + isResident()\n + moveOut()\n + moveIn()\n");    
  }
  
  /**
   * Moves someone from one floor to the other after they have entered the building. Can only move between non-adjacent floors if the building has an elevator.
   */
  public void goToFloor(int floorNum) {
    if (this.activeFloor == -1) {
        throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
    }
    if (floorNum < 1 || floorNum > this.nFloors) {
        throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
    }
    if (this.hasElevator == false) {
      if ( Math.abs(this.activeFloor - floorNum) > 1 ) {
        throw new RuntimeException(this.name + " does not have an elevator so you can not move between nonadjacent floors in one action.");
      } else {
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
      }
    } else {
      System.out.println("You are now on floor #" + floorNum + " of " + this.name);
    }
    this.activeFloor = floorNum;
  }
  
  /**
   * main method that forms houses and calls other methods in class
   * @param args
   */
  public static void main(String[] args) {
    House morrow = new House("Morrow", "The Quad", 4, false, false);
    House king = new House("King", "The Quad", 3, true, true);
    morrow.moveIn("Jordan");
    morrow.moveIn("Julia");
    System.out.println(morrow);
    morrow.moveOut("Jordan");
    System.out.println(morrow);
    System.out.println(king);
    morrow.isResident("Jordan");
    morrow.isResident("Julia");
    morrow.showOptions();
    morrow.isResident("Jordan", "Abby");
    morrow.moveIn("Jason", "Abby");
  }

}