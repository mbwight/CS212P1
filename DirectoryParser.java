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
		if(file.listFiles() == null){
			return null;
		}
		
		for(File f: file.listFiles()){
			if(f.isDirectory()){
				parser(f.toPath());
			}
			else{
				if(f.toString().toLowerCase().endsWith(".htm") || f.toString().toLowerCase().endsWith(".html")){
					
					files.add(f);
					//System.out.println(f);
				}
			}
		}
		return null;
	}
}