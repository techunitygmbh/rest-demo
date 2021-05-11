package de.techunity.itsc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.servlet.Listener;
import org.jboss.weld.environment.servlet.WeldServletLifecycle;

public class Itsc {

	public static void main(String[] args) {
		// Initialize CDI implementation Weld
		// Will discover all beans by annotation, due to an
		// empty beans.xml file in META-INF
		Weld weld = new Weld(); 
		WeldContainer container = weld.initialize();

		// Initialize Jetty web server and Servlet container
		final Server server = new Server(9000);
		final ServletContextHandler context = 
				new ServletContextHandler(
						ServletContextHandler.NO_SESSIONS);
		context.setContextPath("/");

		// Make out Servlet context in Jetty aware of Weld. 
		// CDI injections will now work in our Servlets. This 
		// is important, as our Servlet instances are created 
		// by Jetty as part of the Servlet lifecycle, and without 
		// this step they would be created as plain objects, 
		// without being wrapped in Proxy objects, which 
		// give us the CDI functionality
        context.addEventListener(Listener.using(weld));
        context.setAttribute(
        		WeldServletLifecycle.BEAN_MANAGER_ATTRIBUTE_NAME, 
        		container.getBeanManager());
        
		// Register and map the dispatcher servlet
		final ServletHolder servletHolder = new ServletHolder(
				ServletContainer.class);
		servletHolder.setInitOrder(1);
		servletHolder.setInitParameter(
				"jersey.config.server.provider.packages", 
				"de.techunity.itsc");
		
		context.addServlet(servletHolder, "/rest/*");

		server.setHandler(context);

		// Start the embedded Jetty web server and Servlet
		// container
		try {
			server.start();
		}
		catch (Exception e) {
			// ignore
		}

		// Our status endpoint is now available, also while we 
		// sleep for 1000 seconds.

		try {
			Thread.sleep(1000 * 1000); // 1000 seconds
		}
		catch (InterruptedException e) {
			// doesn't matter
		}
		
		// stop Jetty.
		try {
			server.stop();
		}
		catch (Exception e) {
			// ignore
		}
		server.destroy();
		
		// Our status endpoint is not available anymore.

		// Program terminates
	}
}