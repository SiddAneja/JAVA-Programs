import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class that stores the testcases for the data structure implementation for p1 CS400
 * 
 * @author Siddharth
 *
 * @param <T> - Generic data type of data structure
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  /**
   * Stores the data structure instance once created
   */
  private T dataStructureInstance;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }


  /**
   * Test 0 - Checks to see if a new data structure has size 0
   */
  @Test
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }

  /**
   * Test 1 - Checks if after inserting one value the size is 1
   */
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("test01_1", "test01_1");
    if (dataStructureInstance.size() != 1) {
      fail("data structure should have size = 1, but size=" + dataStructureInstance.size());
    }
  }

  /**
   * Test 2 - Checks to see if after inserting one and then removing it, the size is 0
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_0() {
    dataStructureInstance.insert("test02-1", "test02_1");
    dataStructureInstance.remove("test02-1");
    if (dataStructureInstance.size() != 0) {
      fail("data structure should have size 0 after remove(), but size="
          + dataStructureInstance.size());
    }
  }

  /**
   * Test 3 - Checks to see if adding the same key again throws a Runtime Exception
   */
  @Test
  void test03_duplicate_exception_is_thrown() {
    try {
      dataStructureInstance.insert("test03_1", "test03_1");
      dataStructureInstance.insert("test03_2", "test03_2");
      dataStructureInstance.insert("test03_3", "test03_3");
      dataStructureInstance.insert("test03_2", "test03_2");
      fail("data structure should throw RuntimeException when a duplicate key is entered");
    } catch (RuntimeException e) {
      // Excepted
    }
  }

  /**
   * Test 4 - Checks to see if remove returns false if the key is not present in the data structure
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    dataStructureInstance.insert("test04_1", "test04_1");
    dataStructureInstance.insert("test04_2", "test04_2");
    dataStructureInstance.insert("test04_3", "test04_3");
    if (dataStructureInstance.remove("test04_4")) { // because this should return false
      fail("data structure should return false value when a key that does not exist is removed");
    }
  }

  /**
   * Test 5 - Checks to see if contains returns false when a key is added and then removed.
   */
  @Test
  void test05_contains_returns_false_when_key_inserted_then_removed() {
    dataStructureInstance.insert("test05", "test05");
    dataStructureInstance.remove("test05");
    if (dataStructureInstance.contains("test05") == true) {
      fail("data structure should return false when the key is removed and contains() is called");
    }
  }

  /**
   * Test 6 - Checks to see if get returns the correct value for a key
   */
  @Test
  void test06_get_returns_correct_value() {
    dataStructureInstance.insert("test06_1", "test06_1");
    dataStructureInstance.insert("test06_2", "test06_2");
    if (!(dataStructureInstance.get("test06_1").equals("test06_1"))) {
      fail("data stucture should return value associated with the key");
    }
  }

  /**
   * Test 7 - Checks to see if get returns an error when the key is null
   */
  @Test
  void test07_get_returns_error_when_key_null() {
    try {
      dataStructureInstance.insert("test07", "test07");
      dataStructureInstance.get(null);
      fail("data structure should throw IllegalArguementException when key null");
    } catch (IllegalArgumentException e) {
      // Expected
    }
  }

  /**
   * Test 8 - Checks to see if when multiple entries and removals are made, the size and contains
   * method return the excepted values.
   */
  @Test
  void test08_check_size_and_contains_for_key_that_exists() {
    dataStructureInstance.insert("test08_1", "test08_1");
    dataStructureInstance.insert("test08_2", "test08_2");
    dataStructureInstance.insert("test08_3", "test08_3");
    dataStructureInstance.remove("test08_2");
    if (dataStructureInstance.size() != 2 && !dataStructureInstance.contains("test08_3")) {
      fail("data structure should have size 2 and contain the key");
    }
  }

  /**
   * Test 9 - Checks if a key can be re-added once it has been removed
   */
  @Test
  void test09_key_can_be_readded_after_removal() {
    try {
      dataStructureInstance.insert("test09_1", "test09_1");
      dataStructureInstance.insert("test09_2", "test09_2");
      dataStructureInstance.remove("test09_1");
      dataStructureInstance.insert("test09_1", "test09_1");
    } catch (RuntimeException e) {
      fail("data structure should allow a key to be re-added");
    }
  }

  /**
   * Test 10 - Checks if 500 values can be added and deleted.
   */
  @Test
  void test10_adding_and_removing_500_key_value_pairs() {
    int i;
    for (i = 0; i < 500; i++) {
      dataStructureInstance.insert("" + i, null);
    }
    for (i = 0; i < 500; i++) {
      dataStructureInstance.remove("" + i);
    }
    if (dataStructureInstance.size() != 0) {
      fail(
          "data structure should be able to add and remove 500 values and have size 0, but size is "
              + dataStructureInstance.size());
    }
  }

}
