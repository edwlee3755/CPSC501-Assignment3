import org.jdom2.Document;

public class Manager {

	public static void main(String[] args) {

		Document document = null;
		if (args[0].equals("sender"))
		{
			System.out.println("Sender mode");
			printObjectCreationMenu();
			
			
			
			
			String fileToSend = "toSend.xml";
			Serializer startSerialize = new Serializer();
			document = startserialize.serialize(obj);
		}
		
		else if (args[1].equals("receiver"))
		{
			
		}
	}
	
	private static void printObjectCreationMenu()
	{
		System.out.println("Enter any of the following to create specified object");
		System.out.println("(1) A simple object with only primitives for instance variables.");
		System.out.println("(2) An object that contains references to other objects");
		System.out.println("(3) An object that contains an array of primitives");
		System.out.println("(4) An object that contains an array of object references");
		System.out.println("(5) An object that uses an instance of one of Java's Collection classes to refer to several other objects");
	}

}
