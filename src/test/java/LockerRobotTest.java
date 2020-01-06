import Exceptions.InvalidTicketException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LockerRobotTest {

  @Test
  public void should_reten_ticket_when_press_save_button_given_empty_locker() {
    Locker locker = new Locker();
    Ticket ticket = locker.pressSave();

    assertNotNull(ticket);
  }

  @Test
  public void should_release_box_when_input_ticket_given_valid_ticket() {
    Locker locker = new Locker();
    Ticket ticket = locker.pressSave();

    locker.pressGet(ticket);

    assertTrue(locker.getBoxStatus(ticket.getBoxId()));
  }

  @Test(expected = InvalidTicketException.class)
  public void should_throw_exception_when_input_ticket_given_invalid_ticket() {
    Locker locker = new Locker();
    Ticket ticket = new Ticket();
    ticket.setBoxId(123);

    locker.pressGet(ticket);
  }
}
