import java.util.logging.*;
public class ProxyReason {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public void ForLog(String name) {
//		method 開始時留下log
		logger.log(Level.INFO,"Method Start...");
		System.out.println("Hello everyone"+name);
//		method 結束前留下log
		logger.log(Level.INFO,"Method END...");

	}
}
