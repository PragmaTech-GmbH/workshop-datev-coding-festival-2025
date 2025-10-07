package pragmatech.digital.workshops.lab4.experiment.slow;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Artificially slow test class to demonstrate parallel test execution.
 */
class ThirdSlowTest {

  private static final Logger LOG = LoggerFactory.getLogger(ThirdSlowTest.class);

  @Test
  void slowDown() throws Exception {
    LOG.info("Starting ThirdSlowTest.slowDown");
    Thread.sleep(5_000);
  }

  @Test
  void expensiveOperation() throws Exception {
    LOG.info("Starting ThirdSlowTest.expensiveOperation");
    Thread.sleep(5_000);
  }
}
