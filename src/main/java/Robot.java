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
    return lockerList.get(0).pressSave();
  }
}
