//An object that uses an instance of one of Java's Collection classes to refer to several other objects

import java.util.ArrayList;
import java.util.Collection;

public class ObjectJavaCollection{
	public Collection<ObjectPrimitiveVariables> javaCollectionObj;
	
	public ObjectJavaCollection(){
		javaCollectionObj = new ArrayList<ObjectPrimitiveVariables>();
		
		for (int i = 0; i < 3; i++)
		{
			javaCollectionObj.add(new ObjectPrimitiveVariables());
		}
				
	}
}
