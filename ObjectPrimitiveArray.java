//An object that contains an array of primitives

public class ObjectPrimitiveArray {
	
	private int[] intArray = new int[5];
	
	public ObjectPrimitiveArray(){
		intArray[0]=0;
		intArray[1] = 1;
		intArray[2] = 2;
		intArray[3] = 3;
		intArray[4] = 4;
	}

	public int[] getIntArray() {
		return intArray;
	}


	public void setIntArray(int index, int value) {
		intArray[index] = value;
	}
	
	
}
