package hello;

import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class Receiver {
	 private static final Logger LOGGER = LogManager.getLogger();

	    private CountDownLatch latch;

	    @Autowired
	    public Receiver(CountDownLatch latch) {
	        this.latch = latch;
	    }

	    public void receiveMessage(String message) {
	        LOGGER.info("Received <" + message + ">");
	        latch.countDown();
	    }
}
