import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;


interface ForEx{
	String sp();
}
class VaryLambda{
	void print() {
		Comparator<String> byLength = (String name1, String name2)-> name1.length()-name2.length();
//Lambda expression in right side, target type in left side
		ForEx s = ()->"Justin";//不接受參數 傳回字串
		Sayable s2 =()->System.out.println("hi"); //不接受參數，沒有傳回值

		
	}
}

//實作自己的say方法 如果有default就不用實作，但是也可以覆寫
class WinnieThePooh implements Sayable{
	private int weight=100; 
	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("my Weight is "+weight);
		
	}
	
}
interface Sayable{  
    void say();
    default void pooh() {
    	System.out.println("AAAAAAA");
    }
}  
class MethodReference {  
	public MethodReference() {
		// TODO Auto-generated constructor stub
        System.out.print("123");  
	}
    public static void saySomething(){  
        System.out.println("Hello, this is static method.");  
    }  
    void plusWithInitial() {
    	System.out.println(1+20);
    }
    
    void plusWithPara(int a, int b) {
    	System.out.println("\n"+a+b);
    }
}
interface Messageable{  
    Message getMessage(String msg);  
}  
class Message{  
    Message(String msg){  
        System.out.print(msg);  
    }  
}  
public class LambdaEx3 {
	public static void main(String[] args) {
		String[] rappers = {"Dababy","Joey Badass","Eminem","Gai"};
		Arrays.sort(rappers,StringOrder::byAlphabet);
//		System.out.println(Arrays.toString(rappers));
		Arrays.sort(rappers,String::compareTo);
//		same with line 6
		List<String> names = Arrays.asList(rappers);
//		new HashSet<String>(names).forEach(System.out::println);
//		new ArrayList<String>(names).forEach(System.out::println);
		String s = "12";
//		Supplier::get
		Supplier<Integer> ss = new String("213")::length;
		System.out.println(ss.get());
//		equals to print method
		print(s::length);
		
		
		Sayable sayable = MethodReference::saySomething;  
        // Calling interface method  reference static method
        sayable.say();  
		Sayable p = new MethodReference()::plusWithInitial;  
		p.say();
//		reference non-static method need to create a instance

//		Sayable p2 = MethodReference::new; 
//		p2.say(); 
//		reference constructor 不一定要像下面這種做法 普通的constructor也可以
		
		
		Messageable hello = Message::new;  
        hello.getMessage("Hello\n");  
//        reference constructor and initial parameter
        
		List<MethodReference> a = new ArrayList<MethodReference>();
		a.add(new MethodReference());
		a.forEach((pp) -> pp.plusWithPara(32, 12));
//		method reference can't pass a parameter can use lambda

	}
	public static void print(Supplier<Integer> supplier)
	   {
	      System.out.println(supplier.get());
	   }
}
