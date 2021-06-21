import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

interface ProxyInterface2 {
	public void SayHello(String name);
	public void Test();
}

class BusinessLogic3 implements ProxyInterface2{
	public void SayHello(String name) {
		System.out.println("Hey My name is :"+ name);
	}
	public void Test() {
		System.out.println("test");
	}
}
class LogHandler implements InvocationHandler{
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private Object realObject;
	
	public LogHandler(Object realObject) {
		this.realObject = realObject;
	}
	public Object bind(Object delegate) {
		return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		try {
			logger.log(Level.INFO,"Method Start..."+method);
			result = method.invoke(realObject, args);
			logger.log(Level.INFO,"Method End..."+ method);

		}catch (Exception e) {
			// TODO: handle exception
			logger.log(Level.INFO,e.toString());

		}
		return result;
	}
	
}
public class DynamicEx {
	public static void main(String[] args) {
		ProxyInterface2 b = new BusinessLogic3();
//		LogHandler lh = new LogHandler(b);
		LogHandler l2 = new LogHandler(b);
		ProxyInterface2 p = (ProxyInterface2)l2.bind(b);
//		bind �O��²�ƤU��newProxyInstance�o�Ӱʧ@ �ѼƤ]�i�H����new ����  �U���O�t�@�Ӱ��k ��������O����֤F��
		p.SayHello("Hello");
		p.Test();
		
//		InvocationHandler myHandler = (proxy, method, ar) -> method.invoke(b,ar);
//		ProxyInterface2 pr = (ProxyInterface2)Proxy.newProxyInstance(BusinessLogic3.class.getClassLoader(), new Class[] { ProxyInterface2.class }, myHandler);
//		pr.SayHello("123");
//		Lambda expression
		
//		ProxyInterface2 proxy = (ProxyInterface2) Proxy.newProxyInstance(b.getClass().getClassLoader(), b.getClass().getInterfaces(), new LogHandler(b));
//		proxy.SayHello("Why");
	}
}
