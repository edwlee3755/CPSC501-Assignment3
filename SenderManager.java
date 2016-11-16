import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class SenderManager {
	
	public static void main(String[] args) 
	{
		Document document = null;

			System.out.println("Sender mode");
			printObjectCreationMenu();

			Scanner in = new Scanner(System.in);
			int userInput = in.nextInt();
			
			
			//==============================BEGIN OBJECT CREATION=============================================
			
			Object obj = null;	// create an object we can use to pass in
			//First Object: A simple object with only primitives for instance variables.
			if (userInput == 1)
			{
				System.out.println("Enter y to set variable values. Otherwise enter n");
				Scanner in2 = new Scanner(System.in);
				Scanner in3 = new Scanner(System.in);
				String toSetValue = in2.nextLine();
				if (toSetValue.equals("y")){
					System.out.println("Enter value for integer variable");
					int i = in2.nextInt();
					System.out.println("Enter value for char variable");
					String stringToConvert = in3.nextLine();
					char c = stringToConvert.charAt(0);
					
					ObjectPrimitiveVariables obj1 = new ObjectPrimitiveVariables(i, c);
					obj = obj1;
				}
				//else default if no set
				else
				{
					ObjectPrimitiveVariables obj1 = new ObjectPrimitiveVariables();
					obj = obj1;
				}
			}
			
			//Second Object: An object that contains references to other objects
			else if (userInput == 2)
			{
				System.out.println("2 Objects are referenced. Enter y to set variable values. Otherwise enter n");
				Scanner in2 = new Scanner(System.in);
				Scanner in3 = new Scanner(System.in);
				Scanner in4 = new Scanner(System.in);
				String toSetValue = in2.nextLine();
				if (toSetValue.equals("y")){
					System.out.println("Begin entering values for first referenced object");
					
					System.out.println("(Object 1)Enter value for integer variable");
					int firstIntObj = in2.nextInt();
					System.out.println("(Object 1)Enter value for char variable");
					char firstCharObj = in3.nextLine().charAt(0);
					
					System.out.println("Begin entering values for second referenced object");
					
					System.out.println("(Object 2)Enter value for integer variable");
					int secondIntObj = in3.nextInt();
					System.out.println("(Object 2)Enter value for char variable");
					String stringToConvert = in4.nextLine();
					char secondCharObj = stringToConvert.charAt(0);
					
					ObjectReferenceObject obj2 = new ObjectReferenceObject(firstIntObj, firstCharObj, secondIntObj, secondCharObj);
					obj = obj2;
				}
				else
				{
					ObjectReferenceObject obj2 = new ObjectReferenceObject();
					obj = obj2;
				}
			}
			
			//Third Object: An object that contains an array of primitives
			else if (userInput == 3)
			{
				ObjectPrimitiveArray obj3 = new ObjectPrimitiveArray();		//Create the object
				
				Scanner in2 = new Scanner(System.in);
				Scanner in3 = new Scanner(System.in);
				Scanner in4 = new Scanner(System.in);
				Scanner in5 = new Scanner(System.in);
				System.out.println("Object that contains an array of size 5 has been created");
				System.out.println("Enter y to set array values. Otherwise enter n");
				String toSetValue = in2.nextLine();
				if (toSetValue.equals("y"))
				{
					//Loop to check if user wants to keep changing values in the array
					boolean changeValue = true;
					while(changeValue)
					{
						System.out.println("Enter the INDEX you wish to set");
						int index = in3.nextInt();
						System.out.println("Enter the VALUE for the index you wish to set");
						int value = in4.nextInt();
						
						obj3.setIntArray(index, value);
						
						System.out.println("Enter y to set another value in the array. Otherwise enter n");
						String checkSetValue = in5.nextLine();
						if (checkSetValue.equals("n"))
						{
							changeValue = false;
						}
							
					}
				}
				obj = obj3;
			}
			
			//Fourth Object: An object that contains an array of object references
			else if (userInput == 4)
			{
				ObjectArrayObjectReferences obj4 = new ObjectArrayObjectReferences();
				obj = obj4;
			}
			
			//Fifth Object: An object that uses an instance of one of Java's Collection classes to refer to several other objects
			else
			{
				ObjectJavaCollection obj5 = new ObjectJavaCollection();
				obj5.javaCollectionObj.toArray();
				obj = obj5;
			}
			//========================= END OF OBJECT CREATION =======================================================================	
			
			System.out.println("Object has been created. Beginning Serialization");
			
			//-----------Serialize------------------

			String fileToSend = "toSend.xml";
			Serializer startSerialize = new Serializer();
			try 
			{
				document = startSerialize.serialize(obj);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			XMLOutputter xmlOutput = new XMLOutputter();
			
			xmlOutput.setFormat(Format.getPrettyFormat());
			try 
			{
				xmlOutput.output(document, new FileWriter(fileToSend));
			} 
			catch (IOException e) {

				e.printStackTrace();
			}
			
			//send file
			Sender send = new Sender();
		
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
