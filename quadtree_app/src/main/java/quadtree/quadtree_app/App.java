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
		System.out.println(commands.toString());
		
		String command;
		for(int i = 0 ; i<commands.size(); i++) {
			command = commands.get(i);
			
			String[] splitcmd = command.split(" ");
			System.out.println(Arrays.toString(splitcmd));
			// TODO: Implement each of these
			if( splitcmd[0].equals("dump;")) {
				System.out.println("DUMP");
				root.dump();
			}
			else if(splitcmd[0].equals("insert")){
				System.out.println("insert");
				double x, y, length, width;
				x = Double.valueOf(splitcmd[1]);
				y = Double.valueOf(splitcmd[2]);
				
				length = Double.valueOf(splitcmd[3]);
				width = Double.valueOf(splitcmd[4].substring(0, splitcmd[4].length()-1));
				rectangle rect = new rectangle(x,y,length, width);
				root.insert(rect);
			}
			else if(splitcmd[0].equals("delete")){
				System.out.println("delete");
				double x = Double.valueOf(splitcmd[1]);
				double y = Double.valueOf(splitcmd[2]);
				root.delete(x, y);
			}
			else if(splitcmd[0].equals("update")){
				System.out.println("update");
				root.update(null);
			}
			else if(splitcmd[0].equals("find")){
				System.out.println("find");
				root.find(i, i);
			}
			else {
				System.out.println("Can't read the program input");
			}
		}
	}
}
