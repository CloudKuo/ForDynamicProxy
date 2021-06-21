import java.util.List;
import java.util.function.Consumer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
interface ProxyInterfaceFor {
	void basicForeach1(List<Integer> l);
	void basicForeach2(List<Integer> l);
	void basicForeach3(List<Integer> l);
	void basicForeach4(List<Integer> l);

}
class TimeProxy implements InvocationHandler{
	long start;
	long end;
	private Object realObject;
	Object result = null;
	
	public TimeProxy(Object realObject) {
		this.realObject = realObject;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		start = System.currentTimeMillis();
		result = method.invoke(realObject, args);
		end = System.currentTimeMillis(); 
		System.out.println("\nThis time is "+(end-start));
		return result;
	}
	public Object bind(Object delegate) {
		return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass().getInterfaces(), this);
	}
}

public class ForeachEx implements ProxyInterfaceFor{
	public static void main(String[] args) {
		List<Integer> l = Arrays.asList(1,2,3,4,5,6,8,8,9,7,5,6,5);
		ProxyInterfaceFor f = new ForeachEx();
		TimeProxy t2 = new TimeProxy(f);
		ProxyInterfaceFor p = (ProxyInterfaceFor)t2.bind(f);
		
		p.basicForeach1(l);
		p.basicForeach2(l);
		p.basicForeach3(l);
		p.basicForeach4(l);

	}
	public void basicForeach1(List<Integer> l) {
		
		
		System.out.println("Use for-each loop: ");
		for(int each:l) {
			System.out.print(each+" ");
		}
//		use for-each loop
	}
	@Override
	public void basicForeach2(List<Integer> l) {
		// TODO Auto-generated method stub
		System.out.println("\nUse foreach(): ");
		l.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				// TODO Auto-generated method stub
				System.out.print(t+" ");
			}
		});
		
//		use foreach()
	}
	@Override
	public void basicForeach3(List<Integer> l) {
		// TODO Auto-generated method stub
		System.out.println("\nUse foreach()and lambda: ");
		l.forEach((s)->System.out.print(s+" "));
//		Use foreach()and lambda:
	}
	@Override
	public void basicForeach4(List<Integer> l) {
		// TODO Auto-generated method stub
		System.out.println("\nUse foreach() and lambda and Method Reference: ");
		l.forEach(System.out::print);
//		Use foreach() and lambda and Method Reference 
	}
}
