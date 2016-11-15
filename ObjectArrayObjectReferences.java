//An object that contains an array of object references

public class ObjectArrayObjectReferences {
	
	ObjectPrimitiveVariables referencedObject1 = new ObjectPrimitiveVariables();
	ObjectPrimitiveVariables referencedObject2 = new ObjectPrimitiveVariables();

	Object[] objArray;
	
	public ObjectArrayObjectReferences()
	{
		objArray = new Object[2];
		objArray[0] = referencedObject1;
		objArray[1] = referencedObject2;

	}
}
