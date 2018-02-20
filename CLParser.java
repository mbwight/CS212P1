import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CLParser{
	protected Path path, index; 
	
	
	public CLParser(String[] args) throws Exception{
		if(args.length != 0){
			parse(args);
		}
		else{
			throw new NullPointerException("error");
		}
		
	}
	
	public void parse(String[] args) throws Exception{
		if(args.length == 0){
			System.out.println("No arguments given");
			
		}
		
		if(!hasPathFlag(args)) {
			System.out.println("No path flag");
			throw new Exception();
		}
		
		for(int i = 0; i < args.length; i++){
			if(this.isPathFlag(args[i])){
				if(i+1 < args.length && !isIndexFlag(args[i+1])){
					this.setPath(args[i+1]);
				}
				if(i+1 < args.length && isIndexFlag(args[i+1])){
					throw new Exception("No path provided");
				}
			}
			
			if(this.isIndexFlag(args[i])){
				if(i+1 < args.length){
					this.setIndex(args[i+1]);
				}
				else {
					this.setIndex("index.json");
				}
			}
		}
	}
	
	public boolean isPathFlag(String arg){
		if(arg.contains("-path")){
			return true;
		}
		return false;
	}
	
	public boolean hasPathFlag(String[] arg) {
		for(int i = 0; i < arg.length; i++) {
			if(isPathFlag(arg[i])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isIndexFlag(String arg){
		if(arg.contains("-index")){
			return true;
		}
		return false;
	}
	
	public boolean hasIndexFlag(String[] arg) {
		for(int i = 0; i < arg.length; i++) {
			if(isIndexFlag(arg[i])) {
				return true;
			}
		}
		return false;
	}
	
	public void setPath(String arg){
		this.path = Paths.get(arg);
	}
	
	public Path getPath(){
		return path;
	}
	
	public void setIndex(String arg){
		this.index = Paths.get(arg);
	}
	
	public Path getIndex(){
		return index;
	}
	
	public String toString(){
		return this.path.toString() + " " + this.index.toString();
	}
}