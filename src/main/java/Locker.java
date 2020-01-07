import Exceptions.InvalidTicketException;
import Exceptions.NoEmptyBoxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;


public class Locker {

  private int capacity = 24;
  private Map<Integer, Boolean> boxUsageStatusMap;
  private Stack<Integer> emptyBoxStack;
  private UUID lockerId;
  private Set<UUID> ticketIdSet;

  public Locker() {
    lockerId = UUID.randomUUID();
    boxUsageStatusMap = new HashMap<>();
    emptyBoxStack = new Stack<>();
    initLocker(capacity);
    ticketIdSet = new HashSet<>();
  }

  public Locker( int capacity) {
    lockerId = UUID.randomUUID();
    setCapacity(capacity);
    boxUsageStatusMap = new HashMap<>();
    emptyBoxStack = new Stack<>();
    initLocker(capacity);
    ticketIdSet = new HashSet<>();
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
    if(containsEmptyBox()) {
      return generateTicket();
    }
    throw new NoEmptyBoxException();
  }

  private boolean containsEmptyBox() {

    return !emptyBoxStack.empty();
  }

  private boolean checkIsEmptyBox(int boxId) {
    return !boxUsageStatusMap.get(boxId);
  }

  private Ticket generateTicket() {
    Ticket ticket = new Ticket();
    int boxId = popEmptyBoxId();
    ticket.setBoxId(boxId);
    ticket.setLockerId(lockerId);
    blockBox(boxId);

    ticketIdSet.add(ticket.getTicketId());

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
    if(!isValidTicketId(ticket.getTicketId())) {
      throw new InvalidTicketException();
    }

    if(!isValidLockerId(ticket.getLockerId())) {
      throw new InvalidTicketException();
    }

    if(!isValidBoxId(ticket.getBoxId())) {
      throw new InvalidTicketException();
    }

    releaseBox(ticket.getBoxId());
    ticketIdSet.remove(ticket.getTicketId());
  }

  private boolean isValidTicketId(UUID ticketId) {
    return ticketIdSet.contains(ticketId);
  }

  private boolean isValidBoxId(int boxId) {
    return boxId<capacity && !checkIsEmptyBox(boxId);
  }

  private boolean isValidLockerId(UUID lockerId) {
    return lockerId == this.lockerId;
  }

  private void releaseBox(int boxId) {
    boxUsageStatusMap.put(boxId, false);
    emptyBoxStack.push(boxId);
  }

  public boolean getBoxStatus(int boxId) {
    return boxUsageStatusMap.get(boxId);
  }

  public UUID getLockerId() {
    return lockerId;
  }
}
