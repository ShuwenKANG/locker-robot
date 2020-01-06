import Exceptions.InvalidTicketException;
import Exceptions.NoEmptyBoxException;
import java.util.HashMap;
import java.util.Map;


public class Locker {

  private int capacity = 24;
  private Map<Integer, Boolean> boxUsageStatusMap;

  public Locker() {
    boxUsageStatusMap = new HashMap<>();
    generateBoxUsageStatusMap(capacity);
  }

  public Locker( int capacity) {
    setCapacity(capacity);
    boxUsageStatusMap = new HashMap<>();
    generateBoxUsageStatusMap(capacity);
  }

  private void generateBoxUsageStatusMap(int capacity) {
    for (int boxId = 0; boxId < capacity; boxId++) {
      // initially not used
      boxUsageStatusMap.put(boxId, false);
    }
  }

  private void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public Ticket pressSave() throws NoEmptyBoxException {
    if(hasEmptyBox()) {
      return generateTicket();
    }
    throw new NoEmptyBoxException();
  }

  private boolean hasEmptyBox() {
    int boxId = 0;
    boolean hasEmpty = false;
    while(!hasEmpty && boxId<capacity) {
      hasEmpty = checkIsEmptyBox(boxId);
      boxId++;
    }
    return hasEmpty;
  }

  private boolean checkIsEmptyBox(int boxId) {
    return !boxUsageStatusMap.get(boxId);
  }

  private Ticket generateTicket() {
    Ticket ticket = new Ticket();
    int boxId = generateBoxId();
    ticket.setBoxId(boxId);
    blockBox(boxId);

    return ticket;
  }

  private void blockBox(int boxId) {
    boxUsageStatusMap.put(boxId, true);
  }

  private int generateBoxId() {
    int boxId = 0;
    while(boxId<capacity) {
      if(checkIsEmptyBox(boxId)){
        break;
      }
      boxId++;
    }
    return boxId;
  }

  public void pressGet(Ticket ticket) throws InvalidTicketException {
    int boxId = ticket.getBoxId();
    if(boxId>= capacity || !getBoxStatus(boxId)) {
      throw new InvalidTicketException();
    }
    releaseBox(boxId);
  }

  private void releaseBox(int boxId) {
    boxUsageStatusMap.put(boxId, false);
  }

  public boolean getBoxStatus(int boxId) {
    return boxUsageStatusMap.get(boxId);
  }
}
