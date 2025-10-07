package pragmatech.digital.workshops.lab4.experiment.slow;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Artificially slow test class to demonstrate parallel test execution.
 */
class SlowTest {

  private static final Logger LOG = LoggerFactory.getLogger(SlowTest.class);

  @Test
  void slowDown() throws Exception {
    LOG.info("Starting SlowTest.slowDown");
    Thread.sleep(5_000);
  }

  @Test
  void expensiveOperation() throws Exception {
    LOG.info("Starting SlowTest.expensiveOperation");
    Thread.sleep(5_000);
  }
}
