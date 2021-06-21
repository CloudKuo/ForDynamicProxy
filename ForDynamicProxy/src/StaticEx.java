import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
//Static Proxy
interface ProxyInterface {
	public void SayHello(String name);
}

class BusinessLogic implements ProxyInterface{
	public void SayHello(String name) {
		System.out.println("Hey My name is :"+ name);
	}
	public void Test() {
		System.out.println("test");
	}
}

class ForLogProxy implements ProxyInterface{
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private ProxyInterface helloObject;
//	to connect two method 
	public ForLogProxy(ProxyInterface helloObject) {
		// TODO Auto-generated constructor stub
		this.helloObject = helloObject;
	}
	
	@Override
	public void SayHello(String name) {
		// TODO Auto-generated method stub
		logger.log(Level.INFO,"Method Start...");
		helloObject.SayHello(name);
//		執行商業邏輯
//		helloObject.test(); 這個是不行的 因為interface型別，裡面是沒有這個method的

		logger.log(Level.INFO,"Method END...");

	}
	
}

class BusinessTwo implements ProxyInterface{
	int a = 50;
	int b = 100;

	@Override
	public void SayHello(String name) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		System.out.println(a+b);
	}
	
}
public class StaticEx {
	public static void main(String[] args) {
		ProxyInterface proxy = new ForLogProxy(new BusinessLogic());
		proxy.SayHello("CLoud");
		new ForLogProxy(new BusinessTwo()).SayHello("Alice");
		
	}
}
