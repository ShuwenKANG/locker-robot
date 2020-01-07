import Exceptions.InvalidTicketException;
import Exceptions.NoEmptyBoxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Robot {

  private List<Locker> lockerList;
  private Map<UUID, Locker> lockerIdMap;

  public Robot() {
    lockerList = new ArrayList<>();
    lockerIdMap = new HashMap<>();
  }

  public void assignLocker(Locker locker) {
    lockerList.add(locker);
    lockerIdMap.put(locker.getLockerId(), locker);
  }

  public Ticket savePackage() throws NoEmptyBoxException {
    for( Locker locker: lockerList) {
      try {
        return locker.pressSave();
      } catch (NoEmptyBoxException ignored) {
      }
    }
    throw new NoEmptyBoxException();
  }

  public void getPackage(Ticket ticket) throws InvalidTicketException {
    lockerIdMap.get(ticket.getLockerId()).pressGet(ticket);
  }
}
