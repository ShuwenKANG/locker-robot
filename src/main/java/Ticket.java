import java.util.UUID;

public class Ticket {

  private int boxId;
  private UUID lockerId;
  private UUID ticketId;

  public Ticket() {
    ticketId = UUID.randomUUID();
  }

  public int getBoxId() {
    return boxId;
  }

  public void setBoxId(int id) {
    boxId = id;
  }

  public UUID getLockerId() {
    return lockerId;
  }

  public void setLockerId(UUID lockerId) {
    this.lockerId = lockerId;
  }

  public UUID getTicketId() {
    return ticketId;
  }
}
