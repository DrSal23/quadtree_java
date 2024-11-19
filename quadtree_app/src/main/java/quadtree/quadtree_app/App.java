package quadtree.quadtree_app;


import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Hello world!
 */
class App{
	public static void main(String[] args) {
		//System.out.println("Hello World!");
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
	}
}
