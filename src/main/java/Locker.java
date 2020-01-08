import Exceptions.InvalidTicketException;
import Exceptions.NoEmptyBoxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;


public class Locker {

  private int capacity = 24;
  private Map<Integer, Boolean> boxUsageStatusMap;
  private Stack<Integer> emptyBoxStack;
  private UUID lockerId;
  private Map<UUID, Integer> ticketBoxMap;

  public Locker() {
    lockerId = UUID.randomUUID();
    boxUsageStatusMap = new HashMap<>();
    emptyBoxStack = new Stack<>();
    initLocker(capacity);
    ticketBoxMap = new HashMap<>();
  }

  public Locker( int capacity) {
    lockerId = UUID.randomUUID();
    setCapacity(capacity);
    boxUsageStatusMap = new HashMap<>();
    emptyBoxStack = new Stack<>();
    initLocker(capacity);
    ticketBoxMap = new HashMap<>();
  }

  private void initLocker(int capacity) {
    for (int boxId = 0; boxId < capacity; boxId++) {
      // initially not used
      boxUsageStatusMap.put(boxId, false);
      emptyBoxStack.push(boxId);
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

    return !emptyBoxStack.empty();
  }

  private Ticket generateTicket() {
    Ticket ticket = new Ticket();
    int boxId = popEmptyBoxId();
    ticket.setBoxId(boxId);
    ticket.setLockerId(lockerId);
    blockBox(boxId);

    ticketBoxMap.put(ticket.getTicketId(), boxId);

    return ticket;
  }

  private int popEmptyBoxId() {
    int boxId = emptyBoxStack.pop();
    boxUsageStatusMap.put(boxId, true);
    return boxId;
  }

  private void blockBox(int boxId) {
    boxUsageStatusMap.put(boxId, true);
  }

  public void pressGet(Ticket ticket) throws InvalidTicketException {

    if(!isValidTicket(ticket)) {
      throw new InvalidTicketException();
    }

    releaseBox(ticket);
    expireTicket(ticket);
  }

  private void expireTicket(Ticket ticket) {
    ticketBoxMap.remove(ticket.getTicketId());
  }

  private boolean isValidTicket(Ticket ticket) {
    return ticketBoxMap.containsKey(ticket.getTicketId()) &&
        ticketBoxMap.get(ticket.getTicketId()).equals(ticket.getBoxId());
  }

  private void releaseBox(Ticket ticket) {
    boxUsageStatusMap.put(ticket.getBoxId(), false);
    emptyBoxStack.push(ticket.getBoxId());
  }

  public boolean getBoxStatus(int boxId) {
    return boxUsageStatusMap.get(boxId);
  }

  public UUID getLockerId() {
    return lockerId;
  }
}
