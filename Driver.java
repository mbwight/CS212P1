import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Driver{
	
	
	public static void main(String[] args) throws Exception{
		TreeMap<String, TreeMap<String , SortedSet<Integer>>> index = new TreeMap<String, TreeMap<String , SortedSet<Integer>>>();
		String indexPath;
		try{
			
			CLParser clparser = new CLParser(args);
			
			DirectoryParser stream = new DirectoryParser(clparser.getPath());
			indexPath = clparser.getIndex().toString();
			stream.parser(clparser.getPath());
			HTMLCleaner cleaner = new HTMLCleaner();
			for(File file: stream.files){
				FileParser fileParser = new FileParser(file);
				String output = fileParser.parse(file);
				output = cleaner.stripHTML(output);
				System.out.println(output.toString());
				String[] words = output.split(" ");
				for(int i = 0; i < words.length; i++){
					if(!words[i].replace("\\s+", "").equals("")){
						String word = words[i];
						String fileName = file.toString();
						if(index.get(word) == null){
							index.put(word, new TreeMap<String, SortedSet<Integer>>());
						}
						if(index.get(word).containsKey(file.toString()) == false){
							index.get(word).put(fileName, new TreeSet<>());
						}
						index.get(word).get(fileName).add(i+1); 
					}
				}
			}
			
//			for(String word: index.keySet()){
//				System.out.print(word + " ") ;
//				for(String file: index.get(word).keySet()){
//					System.out.println(file + " " +index.get(word).get(file).toString());
//				}
//			}
			
			InvertedIndex JSON = new InvertedIndex(index);
			File test = Paths.get(indexPath).toFile();
			System.out.println(indexPath + test.exists() );
			if(clparser.getIndex().toString().equals(null)) {
				System.out.println("No index");
			}
			JSON.JSON(clparser.getIndex().toString());
		}catch (NullPointerException e){
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}