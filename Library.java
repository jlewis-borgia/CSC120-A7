import java.util.Hashtable;
import java.util.Enumeration;

/**
 * subclass that creates libraries in which books can be added, removed, checked out, and collections printed out
 */
public class Library extends Building{

  private Hashtable<String, Boolean> collection;
  String key_avail;
  private boolean hasElevator;

    public Library(String name, String address, int nFloors, boolean hasElevator) {
      super(name, address, nFloors);
      this.collection = new Hashtable<String, Boolean>();
      this.hasElevator = hasElevator;
    }

    /**
     * adds a book title to a library as long as the book isn't already in the library
     * @param title
     */
    public void addTitle(String title) {
      if (this.collection.containsKey(title)) {
        throw new RuntimeException(title + " already exists in this library.");
      }
      this.collection.put(title, true);
      System.out.println(title + " has just been added to " + this.name + " Library.");
    }

    /**
     * removes a title from a library after first checking that the title is in the library
     * @param title
     * @return title of book that was removed from the library
     */
    public String removeTitle(String title) {
      if(this.collection.containsKey(title)) {
        this.collection.remove(title);
        System.out.println(title + " has just been removed from " + this.name + " Library.");
        return title;
      } else {
        throw new RuntimeException(title + " is not in this library.");
      }
    }

    /**
     * original method that checks out a title from a library after checking that it is not currently checked out
     * @param title
     */
    public void checkOut(String title) {
      if(this.collection.containsKey(title)) {
        this.collection.replace(title, true, false);
        System.out.println(title + " has just been checked out of the " + this.name + " Library.");
      } else {
        throw new RuntimeException(title + " is not in this library");
      }
    }

    /**
     * overloaded method that checks out two title from a library after checking that they are not currently checked out
     * @param title
     */
    public void checkOut(String title, String second_title) {
      if(this.collection.containsKey(title)) {
        this.collection.replace(title, true, false);
        System.out.println(title + " has just been checked out of the " + this.name + " Library.");
      } else {
        throw new RuntimeException(title + " is not in this library");
      }
      if(this.collection.containsKey(second_title)) {
        this.collection.replace(second_title, true, false);
        System.out.println(second_title + " has just been checked out of the " + this.name + " Library.");
      } else {
        throw new RuntimeException(second_title + " is not in this library");
      }
    }

    /**
     * original method that returns a book to a library after checking that it was checked out
     * @param title
     * @param second_title
     */
    public void returnBook(String title) {
      if (this.collection.containsKey(title)) {
        this.collection.replace(title, false, true);
        System.out.println(title + " has just been returned to " + this.name + " Library." );
      } else {
        throw new RuntimeException(title + " is not in this library");
      }
    }

    /**
     * overloaded method that returns two books to a library after checking that they are already checked out
     * @param title
     * @param second_title
     */
    public void returnBook(String title, String second_title) {
      if (this.collection.containsKey(title)) {
        this.collection.replace(title, false, true);
        System.out.println(title + " has just been returned to " + this.name + " Library." );
      } else {
        throw new RuntimeException(title + " is not in this library");
      }
      if (this.collection.containsKey(second_title)) {
        this.collection.replace(second_title, false, true);
        System.out.println(second_title + " has just been returned to " + this.name + " Library." );
      } else {
        throw new RuntimeException(second_title + " is not in this library");
      }
    }

    /**
     * Checks whether or not a collection contains a title
     * @param title
     * @return true/false whether or not a title is in the collection of a library
     */
    public boolean containsTitle(String title) {
      if (this.collection.containsKey(title)) {
        System.out.println(title + " is in the library's collection.");
      } else {
        System.out.println(title + " is not in the library's collection.");
      }
      return this.collection.containsKey(title);
    }

    /**
     * checks whether or not a title is available to be checked out in a collection of a library
     * @param title
     * @return true if the title in the collection is available and false if it's either not available or not in the collection
     */
    public boolean isAvailable(String title) {
      if (collection.get(title) == null) {
        System.out.println(title + " is not in this library.");
        return false;
      } else {
        if (collection.get(title) == false) {
          System.out.println(title + " is not available.");
          return collection.get(title);
        } else {
          System.out.println(title + " is available.");
          return collection.get(title);
        }
      }
    }

    /**
     * prints out the collection of a library including whether or not each book is available in a user friendly way
     */
    public void printCollection() {
      System.out.println(this.name + " Library Collection:");
      Enumeration<String> Enum = collection.keys();
      while (Enum.hasMoreElements()) {
        String key = Enum.nextElement();
        if (collection.get(key) == true) {
          key_avail = "yes";
        }
        if (collection.get(key) == false) {
          key_avail = "no";
        }
        System.out.println("Title : " + key + "\t Available : " + key_avail);
      }
    }

    /**
     * prints out a description of a library including its name, address, number of floors, and number of books in its collection
     */
    public String toString() {
      String description2 = super.toString();
      description2 += " There are currently " + this.collection.size() + " books in this library's collection.";
      return description2;
    }

    /**
    * method to print out for user what available methods there are for the Library class
    */
    public void showOptions(){
      System.out.println("Available options at " + this.name + ":\n + enter() \n + exit() \n + goUp() \n + goDown()\n + goToFloor()\n + printCollection()\n + isAvailable()\n + containsTitle()\n + returnBook()\n + checkOut()\n + removeTitle()\n + addTitle()\n");    
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
     * main method that builds libraries calls other methods from the class
     * @param args
     */
    public static void main(String[] args) {
      Library neilson = new Library("Neilson", "7 Neilson Drive", 4, true);
      neilson.addTitle("The Lorax by Dr. Seuss");
      neilson.addTitle("War and Peace by Leo Tolstoy");
      neilson.addTitle("Julius Caesar by William Shakespeare");
      System.out.println(neilson);
      neilson.removeTitle("The Lorax by Dr. Seuss");
      neilson.checkOut("War and Peace by Leo Tolstoy");
      neilson.checkOut("Julius Caesar by William Shakespeare");
      neilson.returnBook("War and Peace by Leo Tolstoy");
      neilson.isAvailable("Julius Caesar by William Shakespeare");
      neilson.containsTitle("War and Peace by Leo Tolstoy");
      neilson.printCollection();
      neilson.enter();
      neilson.goToFloor(3);
    }
}
