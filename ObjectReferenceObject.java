//An object that contains references to other objects

public class ObjectReferenceObject {
	
	public ObjectPrimitiveVariables o1;
	public ObjectPrimitiveVariables o2;

	public ObjectReferenceObject()
	{
		o1 = new ObjectPrimitiveVariables();
		o2 = new ObjectPrimitiveVariables();
	}
	
	public ObjectReferenceObject(int userInt1, char userChar1, int userInt2, char userChar2)
	{
		o1 = new ObjectPrimitiveVariables(userInt1, userChar1);
		o2 = new ObjectPrimitiveVariables(userInt2, userChar2);
	}
	
}
