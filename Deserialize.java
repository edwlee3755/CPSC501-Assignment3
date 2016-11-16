import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

public class Deserialize {
	private HashMap table;
	
	public Deserialize(){
		table = new HashMap();
	}

	public Object deserialize(Document document) throws Exception
	{	

		List<Element> objectList = document.getRootElement().getChildren();
		String id = objectList.get(0).getAttributeValue("id");
		
		Object objectInstance;
		for (Element objElement : objectList)
		{
			Class classObj = Class.forName(objElement.getAttributeValue("class"));
		
			if (!classObj.isArray())
			{
			
				Constructor constructor = classObj.getDeclaredConstructor(new Class[]{});
				constructor.setAccessible(true);
				objectInstance = constructor.newInstance();		
			}
			else
			{
				objectInstance = Array.newInstance(classObj.getComponentType(), Integer.parseInt(objElement.getAttributeValue("length")));
			}
			table.put(objElement.getAttributeValue("id"), objectInstance);
		}
		
		deserializeFields(objectList);
		
		return table.get(id);	
		
	}
	
	public void deserializeFields(List<Element> list) throws Exception
	{
		for (int i = 0; i < list.size(); i++)
		{
			Object instance = table.get(list.get(i).getAttributeValue("id"));
			List<Element> fieldElements = list.get(i).getChildren();
			
			if (!instance.getClass().isArray())
			{
				for (int j = 0; j < fieldElements.size(); j++)
				{
					String className = fieldElements.get(0).getAttributeValue("declaringclass");
					
					System.out.println(className);
					Class declaringClass = Class.forName(className);
					
					String fieldName = fieldElements.get(0).getAttributeValue("name");
					
					System.out.println(fieldName);
					
					Field field = declaringClass.getDeclaredField(fieldName);
					field.setAccessible(true);
					
	//				Element e = fieldElements.get(j).getChildren().get(0);
//					deserializeValue(field, instance, e);
					
					
					
				}
			}
			else
			{
				Class componentType = instance.getClass().getComponentType();
				for (int j = 0; j < list.size(); j++)
				{
					Array.set(instance,  j, checkType(fieldElements.get(j), componentType));
				}
			}
		}
	}

/*
	public void deserializeValue(Field field, Object instance, Element element)  throws Exception
	{
		System.out.println("DeserializeValue check");
		if (element.getName().equals("reference"))
		{
			field.set(instance, table.get(element.getText()));
		}
		else
		{
			Class fieldType = field.getType();
			field.set(instance, checkType(element, fieldType));
		}
		
	}
	*/
	public Object checkType(Element element, Class type)
	{
		if(type.equals(int.class))
		{
			return Integer.valueOf(element.getText());
		}
		else if(type.equals(char.class))
		{
			return new Character(element.getText().charAt(0));
		}
		else
		{
			return table.get(element.getText());
		}
		
	}

}
