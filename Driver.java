import java.io.File;
import java.util.HashSet;
import java.util.TreeMap;

public class Driver{
	
	
	public static void main(String[] args) throws Exception{
		TreeMap<String, TreeMap<String , HashSet<Integer>>> index = new TreeMap<String, TreeMap<String , HashSet<Integer>>>();
		try{
			CLParser clparser = new CLParser(args);
			DirectoryParser stream = new DirectoryParser(clparser.getPath());
			stream.parser(clparser.getPath());
			HTMLCleaner cleaner = new HTMLCleaner();
			for(File file: stream.files){
				//System.out.println(file.toString());
				FileParser fileParser = new FileParser(file);
				String output = fileParser.parse(file);
				output = cleaner.stripHTML(output);
				String[] words = output.split(" ");
				for(int i = 0; i < words.length; i++){
					if(!words[i].replace("\\s+", "").equals("")){
						String word = words[i];
						String fileName = file.toString();
						if(index.get(word) == null){
							index.put(word, new TreeMap<String, HashSet<Integer>>());
						}
						if(index.get(word).containsKey(file.toString()) == false){
							index.get(word).put(fileName, new HashSet<>());
						}
						index.get(word).get(fileName).add(i+1);
					}
				}
			}
			
			for(String word: index.keySet()){
				System.out.print(word + " ") ;
				for(String file: index.get(word).keySet()){
					System.out.println(file + " " +index.get(word).get(file).toString());
				}
			}
			
			InvertedIndex JSON = new InvertedIndex(index);
			System.out.println(clparser.getIndex().toString());
			JSON.JSON(clparser.getIndex().toString());
		}catch (NullPointerException e){
			System.out.println("Invalid arguments given. Please enter arguments in this format: -path path -index index");
		}
		
		
	}
}