import java.io.*;
import java.nio.file.Files;

public class FileParser{
	private File file;
	public FileParser(File file){
		this.file = file;
		
	}
	
	public String parse(File file) throws Exception{
		try{
			String stringFile = new String(Files.readAllBytes(file.toPath()), "UTF-8");
			return stringFile;
		}catch(IOException e){
			System.out.println("Input for file is wrong or missing");
		}
		return null;
	}
	
	public String toString(){
		try {
			String file = parse(this.file);
			return file.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
}