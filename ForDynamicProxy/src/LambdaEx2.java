import static java.lang.System.out;
class Hello1{
	Runnable r1 = new Runnable() {
		
		@Override
		public void run() {
			out.println(this);
			
		}
//		@Override
//		public String toString() {
//			return "YO";
//		}
	};
	Runnable r2 = new Runnable() {

		@Override
		public void run() {
			out.println(toString());

		}
	};
	
	public String toString() {
		return "Hello everyone";
	}
}
class Hello2{
	Runnable r1 = ()-> out.println(this);
//	�bprintln�̭����ѼƬOobject�|��toString() �h����L�X���ʧ@
	Runnable r2 = ()-> out.println(toString());
	
	public String toString() {
		return "Hello everyone";
	}
	public void yo() {
		System.out.println("dad");
	}
	String[] names = {"Cloud","Leonard","Lebron","Ant "," Booker "};
//	7�H�e�n�[final
	Runnable run = ()->{
		for(String name: names) {
			System.out.println(name);
		}
	};

}


public class LambdaEx2 {
	public static void main(String[] args) {
		new Hello1().r1.run();
		new Hello1().r2.run();
		
		new Hello2().r1.run();
		new Hello2().r2.run();
		new Hello2().run.run();;

		
	}
}
