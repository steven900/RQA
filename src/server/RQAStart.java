package server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class RQAStart {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		WebAppContext context = new WebAppContext();
		context.setContextPath("");
		String url = "./webRoot";
		context.setDescriptor(url+"/WEB-INF/web.xml");
		context.setResourceBase(url);
		server.setHandler(context);
		server.start();
		server.join();
	}
	
	
}
