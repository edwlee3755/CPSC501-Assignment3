import java.util.Scanner;

public class ObjectCreator {
	
	public ObjectCreator() {
		boolean go = true;
		
//		while (go){

			Scanner in = new Scanner(System.in);
			int userInput = in.nextInt();
			

			
			//First Object: A simple object with only primitives for instance variables.
			if (userInput == 1)
			{
				System.out.println("Enter y to set variable values. Otherwise enter n");
				String toSetValue = in.nextLine();
				if (toSetValue.equals("y")){
					System.out.println("Enter value for integer variable");
					int i = in.nextInt();
					System.out.println("Enter value for char variable");
					char c = in.nextLine().charAt(0);
					
					ObjectPrimitiveVariables obj = new ObjectPrimitiveVariables(i, c);
				}
				//else default if no set
				else
				{
					ObjectPrimitiveVariables obj = new ObjectPrimitiveVariables();
				}
			}
			
			//Second Object: An object that contains references to other objects
			else if (userInput == 2)
			{
				System.out.println("2 Objects are referenced. Enter y to set variable values. Otherwise enter n");
				String toSetValue = in.nextLine();
				if (toSetValue.equals("y")){
					System.out.println("Begin entering values for first referenced object");
					
					System.out.println("(Object 1)Enter value for integer variable");
					int firstIntObj = in.nextInt();
					System.out.println("(Object 1)Enter value for char variable");
					char firstCharObj = in.nextLine().charAt(0);
					
					System.out.println("Begin entering values for second referenced object");
					
					System.out.println("(Object 2)Enter value for integer variable");
					int secondIntObj = in.nextInt();
					System.out.println("(Object 2)Enter value for char variable");
					char secondCharObj = in.nextLine().charAt(0);
					
					ObjectReferenceObject obj = new ObjectReferenceObject(firstIntObj, firstCharObj, secondIntObj, secondCharObj);
				}
				else
				{
					ObjectReferenceObject obj = new ObjectReferenceObject();
				}
			}
			
			//Third Object: An object that contains an array of primitives
			else if (userInput == 3)
			{
				ObjectPrimitiveArray obj = new ObjectPrimitiveArray();		//Create the object
				
				System.out.print("Object that contains an array of size 5 has been created");
				System.out.println("Enter y to set array values. Otherwise enter n");
				String toSetValue = in.nextLine();
				if (toSetValue.equals("y"))
				{
					//Loop to check if user wants to keep changing values in the array
					boolean changeValue = true;
					while(changeValue)
					{
						System.out.println("Enter the INDEX you wish to set");
						int index = in.nextInt();
						System.out.println("Enter the VALUE for the index you wish to set");
						int value = in.nextInt();
						
						obj.setIntArray(index, value);
						
						System.out.println("Enter y to set another value in the array. Otherwise enter n");
						String checkSetValue = in.nextLine();
						if (checkSetValue.equals("n"))
						{
							changeValue = false;
						}
							
					}
				}
			}
			
			//Fourth Object: An object that contains an array of object references
			else if (userInput == 4)
			{
				ObjectArrayObjectReferences obj4 = new ObjectArrayObjectReferences();
			}
			
			//Fifth Object: An object that uses an instance of one of Java's Collection classes to refer to several other objects
			else
			{
				ObjectJavaCollection obj = new ObjectJavaCollection();
				obj.javaCollectionObj.toArray();
			}
			//-----------Serialize------------------
			

			
			
			
			
			
			
			
			
			
			
			//----------------------------------------
			
			//Prompt to make another object
			System.out.println("Do you want to make another object? y//n");
			String nextIter = in.nextLine();
			if (nextIter.equals("n"))
			{
				go = false;
			}
		
			
	//	} while loop bracket
	}
}
