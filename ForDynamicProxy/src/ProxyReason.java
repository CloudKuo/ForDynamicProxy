import java.util.logging.*;
public class ProxyReason {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public void ForLog(String name) {
//		method �}�l�ɯd�Ulog
		logger.log(Level.INFO,"Method Start...");
		System.out.println("Hello everyone"+name);
//		method �����e�d�Ulog
		logger.log(Level.INFO,"Method END...");

	}
}
