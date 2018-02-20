import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DirectoryParser{
	private Path path;
	protected ArrayList<File> files = new ArrayList<>();
	
	public DirectoryParser(Path path){
		this.path = path;
	}
	
	public File parser(Path path){
		File file = new File(path.normalize().toString());
		if(file.toString().toLowerCase().endsWith(".htm") || file.toString().toLowerCase().endsWith(".html")){
			files.add(file);
		}
		
		
		System.out.println(file.toString() + " REEEEEEEEEEEEE");
		if(file.isDirectory()) {
			for(File f: file.listFiles()){
				if(f.isDirectory()){
					parser(f.toPath());
				}
				else{
					if(f.toString().toLowerCase().endsWith(".htm") || f.toString().toLowerCase().endsWith(".html")){
					
						files.add(f);
					}
				}
			}
		}
		return null;
	}
}