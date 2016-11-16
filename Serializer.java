import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.IdentityHashMap;

import org.jdom2.Document;
import org.jdom2.Element;




public class Serializer {

	public IdentityHashMap table;
	public Document document;
	
	public Serializer()
	{
		table = new IdentityHashMap();
		document = new Document(new Element("serialized"));
		
	}
	public org.jdom2.Document serialize(Object obj) throws Exception
	{
		serializeObject(obj);
		return document;
	}
	
	public void serializeObject(Object obj) throws Exception
	{
		Class classObj = obj.getClass();	
		
		String id = Integer.toString(obj.hashCode());		//id of object
		table.put(obj, id);		
		
		//nesting object within root element
		Element objectElement = new Element("object");
		objectElement.setAttribute("class", classObj.getName());
		objectElement.setAttribute("id", id);
		
		if (classObj.isArray())
		{
			int length = Array.getLength(obj); //get the length to pass in
			objectElement.setAttribute("length", Integer.toString(length));
			
			//serialize array
			//using recursion to serialize each element of array if it isn't primitive (object ref)
			if (!obj.getClass().getComponentType().isPrimitive())
			{
				for (int i = 0; i < length; i++)
				{
					Element referenceElement = new Element("reference");
					Object objectRefValue = Array.get(obj, i);
					serializeObject(objectRefValue);
					referenceElement.addContent((String) table.get(objectRefValue));
					objectElement.addContent(referenceElement);
				}
			}
		
			else
			{
				for (int i = 0; i < length; i++)
				{
					Element objectValue = new Element("value");
					Object objectRefValue = Array.get(obj, i);
					objectValue.addContent((String) objectRefValue);
					objectElement.addContent(objectValue);
				}
			}
		}
		
		document.getRootElement().addContent(objectElement);
		
		//serialize fields
		//use reflection to iterate through fields
		Field[] fields = obj.getClass().getDeclaredFields();
		if (fields.length > 0)
		{
			for (int index = 0; index < fields.length; index++)
			{
				//add fields 
				fields[index].setAccessible(true);
				Element fieldElement = new Element("field");
				fieldElement.setAttribute("name", fields[index].getName());
				fieldElement.setAttribute("declaringclass", fields[index].getDeclaringClass().getName());
				objectElement.addContent(fieldElement);
				
				//get the types of each field and serialize accordingly
				//if the field is a primitive type
				if (fields[index].getType().isPrimitive())
				{
					Element fieldValueElement = new Element("value");
					fieldValueElement.addContent((String) fields[index].get(obj));
					objectElement.addContent(fieldValueElement);
					
				}
/*				
				//if field is an array type
				else if (fields[index].getType().isArray())
				{
					Element referenceValueElement = new Element("reference");
					Object objectRefValue = fields[index].get(obj);
					serialize(objectRefValue);
					referenceValueElement.addContent((String) table.get(objectRefValue));
					objectElement.addContent(referenceValueElement);
				}
	*/			
				//else if field references object / is array, we recurse and serialize on object
				else
				{
					Element referenceValueElement = new Element("reference");
					Object objectRefValue = fields[index].get(obj);
					serializeObject(objectRefValue);
					referenceValueElement.addContent((String) table.get(objectRefValue));
					objectElement.addContent(referenceValueElement);

				}
				
			}
		}
	}
}
