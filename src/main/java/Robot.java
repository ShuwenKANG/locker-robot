import Exceptions.InvalidTicketException;
import Exceptions.NoEmptyBoxException;
import java.util.ArrayList;
import java.util.List;

public class Robot {

  private List<Locker> lockerList;

  public Robot() {
    lockerList = new ArrayList<>();
  }

  public void assignLocker(Locker locker) {
    lockerList.add(locker);
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
    lockerList.get(0).pressGet(ticket);
  }
}
