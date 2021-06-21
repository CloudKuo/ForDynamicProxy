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
//		of ���౵��null
		Optional<Object> testOf = Optional.of(test2);
		System.out.println(testOf.get());
		
//	------------------------------------------------------	
		Optional<String> option = getName("Wii");
		String initialName = "default";
//		��l�� �p�G�䤣��N�L�o��
		if(option.isPresent()) {
			initialName = option.get();
		}
//		�i�Q�� isPresent�P�_optional�O���O���� ���M����get�|��empty��exception  java.util.NoSuchElementException:
		System.out.println(initialName);
		Optional<String> option2 = getName("Wii");
		System.out.println(option2.orElse("default"));
		Optional<String> option3 = getName("Wiz");
		System.out.println(option3.orElse("default"));
//		��²�䪺�g�k ��W���@��
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
//		�T���B��l�p�G�e���O�諸 �᭱�N�^��?�᭱�� ���M�N�_���᭱��
		return Optional.ofNullable(names.get(name));
//		same with line 44
	}
	static void nameOrElseGet() {
		String name = null;
		Optional<String> opt = Optional.ofNullable(name);
		System.out.println(opt.orElseGet(()-> "It is Null!"));
//		if empty �|���᭱���Ʊ�

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
