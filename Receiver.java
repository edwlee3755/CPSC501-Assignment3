import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Receiver {
	
	public static void main(String[] args) throws JDOMException, IOException
	{
		try{
			int portNumber = 6666;
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket socket = serverSocket.accept();
			
			//receiving xml file
			String outputFile = ".\\outfile.xml";
			File myFile = new File(outputFile);
			FileOutputStream fos = new FileOutputStream(myFile);
			BufferedOutputStream out = new BufferedOutputStream(fos);
			byte[] buffer = new byte[1024];
			int count;
			InputStream in = socket.getInputStream();
			while ((count = in.read(buffer)) > 0){
				byte revisedBuffer[] = new String(buffer).replaceAll("\0", "").getBytes();
				fos.write(revisedBuffer);
			}
			


			serverSocket.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build(".\\outfile.xml");
			Deserialize deserializer = new Deserialize();
			Object obj = deserializer.deserialize(document);
			Inspector inspect = new Inspector();
			inspect.inspect(obj, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		
		
		
		
		
		
		
		
	}

}
