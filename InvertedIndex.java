import java.io.*;
import java.util.*;
public class InvertedIndex{
	TreeMap<String, TreeMap<String , SortedSet<Integer>>> index = new TreeMap<String, TreeMap<String , SortedSet<Integer>>>();
	
	public InvertedIndex(TreeMap<String, TreeMap<String, SortedSet<Integer>>> index){
		this.index = index;
	}
	
	public void JSON(String path){
		BufferedWriter bw = null;
		try{
			File file = new File( path );
			if(!file.exists()) {
				System.out.println("creating file");
				file.createNewFile();
			}
			
			bw = new BufferedWriter(new FileWriter( file));
			System.out.println(file.toString());
			bw.write("{\n");
			Set<String> i = index.keySet();
			Iterator<String> it = i.iterator();
			
			
			while(it.hasNext()) {
				String current = it.next();
				bw.write("\t" + '"' + current + '"' + ": {\n" );
				Set<String> fileName = index.get(current).keySet();
				Iterator<String> fileIt = fileName.iterator();
				while(fileIt.hasNext()) {
					String currentFileName = fileIt.next();
					String OSFormat = currentFileName.replaceAll("\\\\", "/");
					bw.write("\t\t" + '"' + OSFormat + '"' + ": [\n");
					Iterator<Integer> indexPosition = index.get(current).get(currentFileName).iterator();
					while(indexPosition.hasNext()) {
						int currentPos = indexPosition.next();
						if(indexPosition.hasNext()) {
							bw.write("\t\t\t" +currentPos + ",\n");
						}
						else {
							bw.write("\t\t\t" + currentPos + "\n");
						}
						
					}
					if(fileIt.hasNext()) {
						bw.write("\t\t],\n");
					}
					else {
						bw.write("\t\t]\n");
					}
				}
				if(it.hasNext()) {
					bw.write("\t},\n");
				}
				else {
					bw.write("\t}\n");
				}
			}
			bw.write("}");
			
		}catch (IOException e){
			System.out.println(e.getMessage());
		}finally{
			try{
				if(bw != null)
					bw.close();
			}catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	
}