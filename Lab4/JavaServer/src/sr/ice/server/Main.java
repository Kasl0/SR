package sr.ice.server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class Main {
	private static final String ADAPTER_NAME = "Adapter1";

	public static void main(String[] args) {
		int status = 0;
		Communicator communicator = null;

		try {
			communicator = Util.initialize(args);

			ObjectAdapter adapter = communicator.createObjectAdapter(ADAPTER_NAME);

			System.out.println("Initializing defined devices...");
			Configurator.initializeDevices(adapter);

			adapter.activate();

			System.out.println("Entering event processing loop...");

			communicator.waitForShutdown();

		} catch (Exception e) {
			e.printStackTrace(System.err);
			status = 1;
		}
		if (communicator != null) {
			try {
				communicator.destroy();
			} catch (Exception e) {
				e.printStackTrace(System.err);
				status = 1;
			}
		}
		System.exit(status);
	}
}