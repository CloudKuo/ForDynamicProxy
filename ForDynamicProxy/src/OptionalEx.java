import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalEx {
	public static void main(String[] args) {
//		System.out.println(getName("Jordan"));
//		System.out.println(getName("Wiz"));
		Object test1 = null;
		Object test2 = 23;
//		Optional<Object> testNull = Optional.of(test1);
//		System.out.println(testNull.get());
//		of 不能接受null
		Optional<Object> testOf = Optional.of(test2);
		System.out.println(testOf.get());
		
//	------------------------------------------------------	
		Optional<String> option = getName("Wii");
		String initialName = "default";
//		初始值 如果找不到就印這個
		if(option.isPresent()) {
			initialName = option.get();
		}
//		可利用 isPresent判斷optional是不是有值 不然直接get會有empty的exception  java.util.NoSuchElementException:
		System.out.println(initialName);
		Optional<String> option2 = getName("Wii");
		System.out.println(option2.orElse("default"));
		Optional<String> option3 = getName("Wiz");
		System.out.println(option3.orElse("default"));
//		更簡潔的寫法 跟上面一樣
//		--------------------------------------------------
		nameOrElseGet();
		nameOrElseThrow();
		

	}
	
	static Optional<String> getName(String name){
		Map<String, String> names = new HashMap<>();
		names.put("Wiz", "Weed");
		names.put("Kenndrick", "Kdot");
		names.put("JCole", "Baby Daddy");
		String pick = names.get(name);
//		return (pick ==null) ? Optional.empty(): Optional.of(pick);
//		三元運算子如果前面是對的 後面就回傳?後面的 不然就冒號後面的
		return Optional.ofNullable(names.get(name));
//		same with line 44
	}
	static void nameOrElseGet() {
		String name = null;
		Optional<String> opt = Optional.ofNullable(name);
		System.out.println(opt.orElseGet(()-> "It is Null!"));
//		if empty 會做後面的事情

	}
	static void nameOrElseThrow() {
		String name = null;
		Optional<String> opt = Optional.ofNullable(name);
		try {
			System.out.println(opt.orElseThrow(()-> new Exception("What is it null!?")));

		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
