import org.junit.*;
import static org.junit.Assert.*;

public class ContactTest {
  // @After
  // public void tearDown() {
  //   Category.clear();
  //   Task.clear();
// }

  @Test
  public void contact_instantiatesCorrectly_true() {
    Contact testContact = new Contact("John Doe");
    assertEquals(true, testContact instanceof Contact);
  }

  @Test
  public void getName_contactInstantiatesWithName_JohnDoe() {
    Contact testContact = new Contact("John Doe");
    assertEquals("John Doe", testContact.getName());
  }
}
