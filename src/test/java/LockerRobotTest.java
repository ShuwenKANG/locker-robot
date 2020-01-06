import Exceptions.InvalidTicketException;
import Exceptions.NoEmptyBoxException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class LockerRobotTest {

  @Test
  public void should_reten_ticket_when_press_save_button_given_empty_locker() {
    Locker locker = new Locker();

    Ticket ticket = locker.pressSave();

    assertNotNull(ticket);
  }

  @Test
  public void should_release_box_when_input_ticket_given_valid_ticket() throws InvalidTicketException {
    Locker locker = new Locker();
    Ticket ticket = locker.pressSave();

    locker.pressGet(ticket);

    assertFalse(locker.getBoxStatus(ticket.getBoxId()));
  }

  @Test(expected = InvalidTicketException.class)
  public void should_throw_exception_when_input_ticket_given_invalid_ticket() throws InvalidTicketException {
    Locker locker = new Locker();
    Ticket ticket = new Ticket();
    ticket.setBoxId(123);

    locker.pressGet(ticket);
  }

  @Test(expected = NoEmptyBoxException.class)
  public void should_throw_exception_when_press_save_button_given_full_locker() {
    Locker locker = new Locker(0);

    locker.pressSave();
  }
}
