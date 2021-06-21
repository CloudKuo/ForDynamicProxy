import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx {
	public static void main(String[] args) {
//		intermediateEx();
//		TerminateEx();
//		deeper();
//		collectDeeper();
//		parallel();
//		parallelTime();
		paraTest();
	}
	
	static void parallelTime() {
		long start;
		long end;
		start = System.currentTimeMillis();
		IntStream.range(0, 100).forEach((s)-> System.out.print(s+" "));
		end = System.currentTimeMillis();
		System.out.println("\nNormal Stream :" + (end-start));
		
		start = System.currentTimeMillis();
		IntStream.range(0, 100).parallel().forEach((s)-> System.out.print(s+" "));
		end = System.currentTimeMillis();
		System.out.println("\nParallel Stream :" + (end-start));
		
		IntStream.range(0, 10).forEach((s) -> {
			System.out.println("Thread " + Thread.currentThread().getName() + " : " + s);
		});
		IntStream.range(0, 10).parallel().forEach((s) -> {
			System.out.println("Parallel Thread " + Thread.currentThread().getName() + " : " + s);
		});
		
		
	}
	
	static void intermediateEx() {
		List<Integer> number = Arrays.asList(2,3,4,5);
		List<Integer> square = number.stream().map(s -> s*s).collect(Collectors.toList());
		System.out.println("Map:");
		square.forEach(s -> System.out.print(s+" "));
		
		System.out.println("\nfilter:");
		List names = Arrays.asList("Reflection","Collection","Stream");
		List result = (ArrayList) names.stream().filter(s->((String) s).startsWith("S")).collect(Collectors.toList());
		result.forEach(System.out::print);
		
		System.out.println("\nsorted:");
		List result2 = (ArrayList)names.stream().sorted().collect(Collectors.toList());
		result2.forEach(s -> System.out.print(s+" "));
	}
	
	static void TerminateEx() {
		List<Integer> number = Arrays.asList(2,3,4,5,3);
		Set square =  number.stream()
				.map(s-> (s*s))
				.collect(Collectors.toSet());
		System.out.println("collect:");
		square.forEach(s -> System.out.print(s+" "));
		
		System.out.println("\nforEach:");
		number.stream().map(x->x*x).forEach(y->System.out.print(y));
		
		System.out.println("\nreduce:");
		int even = number.stream().filter(x->x%2==0).reduce(0,(subTotal,i)-> subTotal+i);
//		int even2 = number.stream().filter(x->x%2==0).reduce(0,Integer::sum);
//		System.out.println(Stream.of(2,3,4,5,3).filter(x->x%2==0).reduce(0, Integer::sum));
//		可以用method reference寫法 但是44行of裡面的參數型態不同
//		reduce第一個參數是 the identity value for the accumulating function 這裡就是初始的值 需要去做累加  第二個參數是an associative, non-interfering, stateless function for combining two values
		System.out.println(even);
//		System.out.println(even2);
		
	}
	static void collectDeeper() {
		List<Integer> count = Arrays.asList(200,34,45,62,10);
		List<Integer> c = count.stream().filter((s)->s<50).collect(()-> new ArrayList<Integer>(),//supplier
				(a,p)->a.add(p), // accumulator
				(a1,a2)->a1.addAll(a2));//combiner
		System.out.println(c);
		List<Integer> d = count.stream().filter((s)->s<50).collect(ArrayList::new,
				ArrayList::add,
				ArrayList::addAll);
//		same with c
		System.out.println(d);

	}
	
	static void deeper() {
		List<Integer> age = Arrays.asList(20,35,48,55,30);
		int sum = age.stream().mapToInt(num -> (num)).sum();
//		mapToInt(ToIntFunction mapper)返回一個IntStream 跟primitive int不同
		System.out.println("Sum: "+sum);
		
		Double average = (Double)age.stream().mapToInt(num -> (num)).average().getAsDouble();
//		因為兩邊的型別不同 所以需要用getAsDouble()做轉型的回傳
		System.out.println("Average: "+average);
		
		int max = (int)age.stream().mapToInt(num -> (num)).max().getAsInt();
		System.out.println("Max: "+max);
		
		IntStream.range(0, 20).forEach(System.out::print);
		IntSummaryStatistics summary = IntStream.of(1,2,3,4,5,6,8,9,5).summaryStatistics();
		System.out.println("\n"+summary);
	}
	static void parallel() {
		List<Integer> age = Arrays.asList(20,35,48,55,30,45,98);
		List<String> ss = Arrays.asList("Cloud","Leonard","Lebron","Ant "," Booker ");
		Stream<String> s2 = Stream.of("Cloud","Leonard","Lebron","Ant "," Booker ");
		s2.parallel();
		Stream<String> s3 = Stream.of("Cloud","Leonard","Lebron","Ant "," Booker ");
		System.out.println("s2:"+s2.isParallel());
		System.out.println("s3:"+s3.isParallel());
		List<Integer> nopal = age.stream().collect(Collectors.toList());
		List<Integer> pal = age.parallelStream().collect(Collectors.toList());
		List<Integer> i = age.stream().map(num -> (num+2)).collect(Collectors.toList());
		System.out.println(i);

		Map<Integer, List<String>> m = ss.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
		s2.collect(Collectors.groupingByConcurrent(String::length));
		System.out.println("s2:"+s2.isParallel());	
		System.out.println("No ordered foreach: ");
		age.parallelStream().forEach(s -> System.out.print(s+" "));
		
		System.out.println("\nOrdered foreach: ");
		age.parallelStream().forEachOrdered(s -> System.out.print(s+" "));
	}
	public static int sum(List<Integer> intList) {
        if (intList == null || intList.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (int n : intList) {
            sum += n;
        }
        return sum;
    }
	static void paraTest() {
		List<Integer> age = Arrays.asList(20,35,48,55,30,45,98);
		System.out.println("groupingByConcurrent charact: "+Collectors.groupingByConcurrent(String::length).characteristics());
		System.out.println("groupingBy charact: "+Collectors.groupingBy(String::length).characteristics());
		System.out.println("toList charact: "+Collectors.toList().characteristics());
		System.out.println("toSet charact: "+Collectors.toSet().characteristics());
		int a = 10;
		int sum = age.stream().map(num -> (num+a)).mapToInt(num -> (num)).sum();
		System.out.println(sum);
		Integer pal2 = age.parallelStream().map(num -> (num+a)).reduce(0,(b,bb)->b = b+bb);
		System.out.println(pal2);
		List<Integer> sumList = age.parallelStream().map(num -> num = num+a).collect(Collectors.toList());
		System.out.println(sum(sumList));
//		-----------------------------------------------------------------------
		List<String> items = Arrays.asList("a", "b", "c");
		items.parallelStream().forEach(n -> n= n+"fff");
		System.out.println("case1 " +items);
//		forEach will just execute the method with each element. It does not replace the element in the list. In fact no operation of the stream API will replace an element in the list.
		items = items.parallelStream().map(e -> {
			e = e + "fff";
			return e;
		}).collect(Collectors.toList());
		System.out.println("case2 " + items);
		
		System.out.println(age.parallelStream().reduce(1,(num,mu) -> num=num*mu));
		System.out.println(age.parallelStream().reduce(1,(num,mu) -> {
			num = num*mu;
			return num;
		}));
		int b= 5;
		System.out.println(sumList);
		age.parallelStream().map(num -> (num+a)).forEach((s) -> {
			System.out.println("Parallel Thread " + Thread.currentThread().getName() + " : " + s);});
		
	}
	
}
