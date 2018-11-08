package lgit;
import java.lang.*;
import java.util.logging.*;
import java.io.*;
public class Logit {
	static Logger logger;
	public static void main(String[] args) {
		Logger.getGlobal().info("Opening file "+"foobar.txt");
		
		// make a logger
		logger = Logger.getLogger("lgit.logit");
		logger.warning("foo");
		
		logger.setLevel(Level.FINER);
		// prevent redundant output
		logger.setUseParentHandlers(false);
		
		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.FINER);
		logger.addHandler(handler); // bind logger and handler
		// do something i.e. log something
		logger.finer("bar");
		
		// now try it with a file to catch the output
		try {
			// XML based logging
			String pattern = "%h/foo/bar%u-%g.xml";
			FileHandler fh = new FileHandler(pattern,0,3,false);
			
			// TEXT based logging note directory
			// C:\Users\jrm424\foo
			//String pattern = "%h/foo/bar%u-%g.log";
			//FileHandler fh = new FileHandler(pattern,10000,3,true);
			//fh.setFormatter(new SimpleFormatter());
			
			// bind file handler to logger
			logger.addHandler(fh);
			
			// prevent parent loggers from redundantly outputting
			logger.setUseParentHandlers(false);
			
			// call a method
			lg2file(logger,"doing something");
			
			// throw an exception
			//throw(new IOException("Boom"));
			
		} catch (IOException ioex) {
			lg2file(logger,ioex.toString());
			logger.log(Level.SEVERE,"Big trouble",ioex);
		}
		
	}
	public static void lg2file(Logger lgr, String msg) {
		lgr.entering("lgit.Logit","lg2file");
		System.out.println(msg);
		lgr.exiting("lgit.Logit", "lg2file");
	}
}

