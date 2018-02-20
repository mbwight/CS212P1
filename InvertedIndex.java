import java.io.*;
import java.util.*;
public class InvertedIndex{
	TreeMap<String, TreeMap<String , HashSet<Integer>>> index = new TreeMap<String, TreeMap<String , HashSet<Integer>>>();
	
	public InvertedIndex(TreeMap<String, TreeMap<String , HashSet<Integer>>> index){
		this.index = index;
	}
	
	public void JSON(String path){
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter( path + ".txt"));
			bw.write("{");
			System.out.println("REE");
			
		}catch (IOException e){
			System.out.println(e.getMessage());
		}finally{
			try{
				if(bw != null)
					bw.flush();
			}catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	
}