import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

@FunctionalInterface
interface F <P,R>{
	default String a() {
		return "Wow";
	}
	R run(P p1,P p2);

}

interface D{
	public void apply(String s);
}

interface Multi{
	public void apply(String s);
	public void run();
}

class StringOrder{
	public static int bylength(String n1,String n2) {
		return n1.length()-n2.length();
	}
	
	public static int byAlphabet(String n1,String n2) {
		return n1.compareTo(n2);
	}
	public static int byAlphabetIgnore(String n1,String n2) {
//		忽略大小寫
		return n1.compareToIgnoreCase(n2);
	}
}
public class LambdaEx1 {
	public static void main(String[] args) {
		String[] names = {"Cloud","Leonard","Lebron","Ant "," Booker "};
		System.out.println("Original array: "+Arrays.toString(names));
		Comparator<String> byLength = (String name1, String name2)-> name1.length()-name2.length();
		Arrays.sort(names,byLength);
		System.out.println("Sort by Length"+Arrays.toString(names));
//		很多敘述句的寫法
		Comparator<String> byAlphbetAndTrim = (String name1, String name2) -> {
			String n1 = name1.trim();
			String n2 = name2.trim();

			return n1.compareTo(n2);
		};
		Arrays.sort(names,byAlphbetAndTrim);
		System.out.println("Sort by Alphbet"+Arrays.toString(names));
//		Arrays.sort(names,Comparator.naturalOrder());
//		System.out.println(Arrays.toString(names));  
//		Comparator裡面有提供同樣意思的函數
		
		
		D dun = (s) -> System.out.println(s);
		dun.apply("123");
		F<String,Integer> fun = (a,b) -> a.length()+b.length();
		System.out.println(fun.run("123", "6556"));
		System.out.println("Sort by Length"+Arrays.toString(names));
//		Multi m = mm-> System.out.println(mm);
//		Interface Must functional
		
		
		Arrays.sort(names,(n1,n2)->StringOrder.byAlphabetIgnore(n1, n2));
//		直接使用在已經定義的函數裡
		
//		Arrays.sort(names,(name1,name2)->name1.length()-name2.length());
//		直接使用在sort裡面
//		 Instantiate Functional Interfaces With Lambda Expressions
		F fooByIC = new F() {
			@Override
			public Object run(Object p1, Object p2) {
				System.out.println("wow");
				return p2;
			}
		    
		};
		fooByIC.run("123", "456");
		
	}
}
