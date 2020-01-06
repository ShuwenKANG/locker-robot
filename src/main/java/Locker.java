import Exceptions.InvalidTicketException;
import Exceptions.NoEmptyBoxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Locker {

  private int capacity = 24;
  private Map<Integer, Boolean> boxUsageStatusMap;
  private UUID lockerId;

  public Locker() {
    lockerId = UUID.randomUUID();
    boxUsageStatusMap = new HashMap<>();
    generateBoxUsageStatusMap(capacity);
  }

  public Locker( int capacity) {
    lockerId = UUID.randomUUID();
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
    ticket.setLockerId(lockerId);
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
    if(!isValidLockerId(ticket.getLockerId())) {
      throw new InvalidTicketException();
    }

    if(!isValidBoxId(ticket.getBoxId())) {
      throw new InvalidTicketException();
    }

    releaseBox(ticket.getBoxId());
  }

  private boolean isValidBoxId(int boxId) {
    return boxId<capacity && !checkIsEmptyBox(boxId);
  }

  private boolean isValidLockerId(UUID lockerId) {
    return lockerId == this.lockerId;
  }

  private void releaseBox(int boxId) {
    boxUsageStatusMap.put(boxId, false);
  }

  public boolean getBoxStatus(int boxId) {
    return boxUsageStatusMap.get(boxId);
  }
}
