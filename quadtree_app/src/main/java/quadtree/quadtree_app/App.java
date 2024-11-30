package quadtree.quadtree_app;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Hello world!
 */
class App{
	public static void main(String[] args) {
		//System.out.println("Hello World!");
		Quadtree root = new Quadtree();
		
		if(args.length == 0) {
			System.out.println("No arguments");
			return;
		}
		ArrayList<String> commands = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(new File(args[0]));
			// implement reading input
			System.out.println(args[0]);
			
			while(sc.hasNextLine()) {
				commands.add(sc.nextLine());
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String command;
		for(int i = 0 ; i<commands.size(); i++) {
			command = commands.get(i);
			
			String[] splitcmd = command.split(" ");
			if( splitcmd[0].equals("dump;")) {
				root.dump();
			}
			else if(splitcmd[0].equals("insert")){
				double x, y, length, width;
				x = Double.valueOf(splitcmd[1]);
				y = Double.valueOf(splitcmd[2]);
				
				length = Double.valueOf(splitcmd[3]);
				width = Double.valueOf(splitcmd[4].substring(0, splitcmd[4].length()-1));
				rectangle rect = new rectangle(x,y,length, width);
				boolean res = root.insert(rect);
				if(!res) {
					return;
				}
			}
			
			else if(splitcmd[0].equals("delete")){
				double x = Double.valueOf(splitcmd[1]);
				double y = Double.valueOf(splitcmd[2].substring(0, splitcmd[2].length()-1) );
				
				boolean res = root.delete(x, y);
				if(!res) {
					return;
				}
			}
			
			else if(splitcmd[0].equals("update")){
				double x, y, length, width;
				
				x = Double.valueOf(splitcmd[1]);
				y = Double.valueOf(splitcmd[2]);
				
				length = Double.valueOf(splitcmd[3]);
				width = Double.valueOf(splitcmd[4].substring(0, splitcmd[4].length()-1));
				
				rectangle rect = new rectangle(x, y, length, width);
				boolean res = root.update(rect);
				if(!res) {
					return;
				}
			}
			
			
			else if(splitcmd[0].equals("find")){
				double x, y;
				
				x = Double.valueOf(splitcmd[1]);
				y = Double.valueOf(splitcmd[2].substring(0, splitcmd[2].length()-1));
				
				rectangle res = root.find(x, y);
				if(res == null) {
					return;
				}
				System.out.println(res.toString());
			}
			
			
			else {
				System.out.println("Can't read the program input");
				return;
			}
		}
	}
}
