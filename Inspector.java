import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Vector;

/*
 * Re-used code from Assignment 2
 * 
 */
public class Inspector {
	public void inspect(Object obj, boolean recursive) throws InstantiationException, IllegalAccessException
	{
		Vector<Field> toInspect = new Vector<Field>();
		
		Class classObject = obj.getClass();
		
		printDeclaringClass(classObject);
		printSuperClass( classObject);
		printInterfaces(classObject);
		printMethods(classObject);
		printConstructors(classObject);
		
		//Field class declares
		//current value of each field
		printFields(classObject, obj, recursive, toInspect);
		
		if(recursive)
			inspectFieldClasses( obj, classObject, toInspect, recursive);
		
		
		//inheritance traversal
		Class<?> superclass = obj.getClass().getSuperclass();
		if(superclass != null) {
			System.out.println("\n====================Inspecting Superclass====================");
			inspectSuper(obj, superclass, recursive);
		}
	}
	
	//like above except for super
	//made to avoid instantiation exception error
	public void inspectSuper(Object obj, Class classObject, boolean recursive)
	{
		Vector<Field> toInspect = new Vector<Field>();
				

		printDeclaringClass(classObject);
		printSuperClass( classObject);
		printInterfaces(classObject);
		printMethods(classObject);
		printConstructors(classObject);

		printFields(classObject, obj, recursive, toInspect);
		
		if(recursive)
			inspectFieldClasses( obj, classObject, toInspect, recursive);
		
		

		Class<?> superclass = classObject.getSuperclass();
		if(superclass != null) {
			System.out.println("\n====================Inspecting Superclass====================");
			inspectSuper(obj, superclass, recursive);
		}
	}
	public String printDeclaringClass(Class classObject)
	{
		String result = null;

		
		if(classObject.getDeclaringClass() != null)
    	{
			result = classObject.getDeclaringClass().getName();
			System.out.println("The declaring class is: " + result);
    	}
		
		else
		{
			result = classObject.getName();
			System.out.println("The declaring class is: " + result);
		}
		return result;
	}
	
	public String printSuperClass(Class classObject)
	{
		String result =null;
		Class superClass = classObject.getSuperclass();
		if(superClass !=null)
			result = superClass.getSimpleName();
		System.out.println("Superclass is: " + result + "\n");
		return result;
	}
	
	public String printInterfaces(Class classObject)
	{
		Class[] interfaces = classObject.getInterfaces();
		String result = null;
		System.out.println("====================Interfaces====================");
		if (interfaces.length > 0)
		{
			System.out.println("The interfaces used are: ");
			
			for(Class currentInterface : interfaces)
			{
				String thisInterface = currentInterface.getName();
				System.out.println(thisInterface);
				result += thisInterface;
			}
		}
		
		else
			System.out.println("No interfaces used");
		
		return result;
	}
	
	public String printMethods(Class classObject)

	
	{
		String result = null;
		
		Method[] methods = classObject.getDeclaredMethods();
		System.out.println("\n====================Methods====================");
		for(Method method: methods)
		{
			method.setAccessible(true);
			//print name of each method
			String methodName = method.getName();
			System.out.println(methodName);
			result+=methodName;
			
			//Get and print exception throws by methods
			Class[] exceptionList = method.getExceptionTypes();
			if(exceptionList.length > 0)
			{
				System.out.print("			Exception thrown by method: ");
				for(Class exception: exceptionList)
				{
					System.out.print(" " + exception.getName() + " ");
				}
			}
			
			else
				System.out.print("			No exceptions thrown");
			
			
			//get and print parameter types
			Class[] parameterList = method.getParameterTypes();
			
			if(parameterList.length > 0)
			{
				System.out.print("\n" + "			Parameter types: ");
				for (Class parameter: parameterList)
				{
					System.out.print(parameter.getName() + " ");
					
				}
			}
			
			else
				System.out.print("\n			No parameters used");
			
			
			System.out.println();
			
			//print return type of the methods
			System.out.println("			Return type: " + method.getReturnType());
			
			//Get and print access modifier of each method
			int modifiers = method.getModifiers();
			System.out.print("			Modifier: " + Modifier.toString(modifiers) + "\n");
		}
		
		return result;
	}

	public String printConstructors(Class classObject)
	{
		String result =null;
		
		Constructor[] constructors = classObject.getConstructors();
		System.out.println("\n====================Constructors====================");
		for(Constructor constructor: constructors)
		{
			//print name
			String constructorName = constructor.getName();
			System.out.println("Constructor name: " + constructorName);
			result+=constructorName;
			
			//get and print access modifer of each constructor
			int modifiers = constructor.getModifiers();
			System.out.println("Constructor modifier: " + Modifier.toString(modifiers));
			
			//Get and print parameter types
			Class[] parameterList = constructor.getParameterTypes();
			if(parameterList.length > 0)
			{
				System.out.println("Constructor parameter types: ");
				for (Class parameter: parameterList)
				{
					System.out.println(parameter.getName() + " ");
					
				}
			}
			
			else
				System.out.print("Constructors uses no parameters");
			
			
		
			System.out.println();
			
			
			System.out.println();
			System.out.println("====================");
				
		}
		
		return result;
	}

	public String printFields(Class classObject, Object obj, boolean recursive, Vector<Field> toInspect)
	{
		String result = null; 		
		
		Field[] fields = classObject.getDeclaredFields();
		System.out.println("====================Fields====================");
		if (fields.length > 0)
		{
			System.out.println("The fields used are: \n");
			
			for(Field field : fields)
			{
				//Object arr;
				field.setAccessible(true);
				String fieldName = field.getName();
				try {
					Object arr = field.get(obj);
					
					//if field is array
					if(field.getType().isArray() && arr != null){
						System.out.println("---Array---	");
						System.out.println(fieldName);
						System.out.println("Component Type: " + field.getType().getComponentType());
						System.out.println("Length: " + Array.getLength(arr));
					
						//print out content
						for(int index = 0; index<Array.getLength(arr); index++)
						{
							System.out.println(String.format("element[%s]: ", index) + Array.get(arr, index));
						}
						
						System.out.println();
					}
					
					//if field isn't array
					else
					{						
						boolean isPrimitive = field.getType().isPrimitive();
						
						field.setAccessible(true);
						int modifiers = field.getModifiers();
						System.out.print(Modifier.toString(modifiers) + " " + field.getType() + " " + fieldName + " ");
						
						//if it is a primitive, print the value
						if (isPrimitive)
						{
							System.out.println(field.get(obj) + "\n");
						}
						
						//else if it is not primitive and recursive is false, then print 
						else if (!isPrimitive && recursive == false)
						{
							System.out.println("Reference value is: " + field.getType().getSimpleName() + " "+ System.identityHashCode(field.get(obj)) + "\n");
						}
						
						else if (!isPrimitive && recursive == true)
						{
							toInspect.addElement( field );
						}
					}
					
					result+=fieldName;
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				
			}
		}
		
		else
			System.out.println("No fields used");
		
		return result;
	}
	
	private void inspectFieldClasses(Object obj, Class classObject,
		     Vector<Field> toInspect,boolean recursive)
	{
	
		if(toInspect.size() > 0 )
		   System.out.println("\n\n====================RECURSIVE Inspection====================\n");
		
		Enumeration<Field> e = toInspect.elements();
		while(e.hasMoreElements())
		{
			Field field = e.nextElement();
			System.out.println("\nInspecting Field: " + field.getName() + "\n");
			
			try
		    {
				inspect( field.get(obj) , recursive);
		    }
			catch(Exception exp) { exp.printStackTrace(); }
		}
		
	}
}
