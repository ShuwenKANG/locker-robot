import Exceptions.InvalidTicketException;
import java.util.HashMap;
import java.util.Map;

public class Locker {

  private int CAPACITY = 24;
  private Map<Integer, Boolean> boxStatusMap;

  public Locker() {
    boxStatusMap = new HashMap<>();
    for(int boxId=0; boxId<CAPACITY; boxId++) {
      boxStatusMap.put(boxId, false);
    }
  }

  public Locker( int capacity) {
    boxStatusMap = new HashMap<>();
    for(int boxId=0; boxId<capacity; boxId++) {
      boxStatusMap.put(boxId, false);
    }
  }

  public Ticket pressSave() {
    return generateTicket();
  }

  private Ticket generateTicket() {
    Ticket ticket = new Ticket();
    int boxId = generateBoxId();
    ticket.setBoxId(boxId);
    blockBox(boxId);

    return ticket;
  }

  private void blockBox(int boxId) {
    boxStatusMap.put(boxId, true);
  }

  private int generateBoxId() {
    return 0;
  }

  public void pressGet(Ticket ticket) throws InvalidTicketException {
    int boxId = ticket.getBoxId();
    if(boxId>=CAPACITY || !getBoxStatus(boxId)) {
      throw new InvalidTicketException();
    }
    releaseBox(boxId);
  }

  private void releaseBox(int boxId) {
    boxStatusMap.put(boxId, false);
  }

  public boolean getBoxStatus(int boxId) {
    return boxStatusMap.get(boxId);
  }
}
