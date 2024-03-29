import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class UseFridge {
	public static void main(String[] args) throws FileNotFoundException {

		Fridge fridge = new Fridge();
		Scanner console = new Scanner(System.in);
		
		// List all items in the fridge (or say the fridge is empty)
		System.out.println("Items in your fridge:");
		System.out.println(fridge.review());
		System.out.println();
		
		boolean usingFridge = true;
		while (usingFridge) {
			System.out.println("Would you like to add (to), remove (from), review your fridge, find (items), or quit?");
			String userRequest = console.nextLine();

			// Add an item to the fridge
			if (userRequest.contains("add")) {
				System.out.println("Enter the name of the food to add:");
				String food = console.nextLine();
				System.out.println("Enter the quantity:");
				int foodQuantity = console.nextInt();
				// Remove an excess line in the console
				console.nextLine();
				
				// If the food is not already in the fridge, ask for the category
				String category = "none";
				if(fridge.itemSearch(food).contains("no")) {
					System.out.println("Enter a category you want this food to be in (e.g. meat), or type \"none\":");
					category = console.nextLine();
				}
				
				fridge.addItem(food, foodQuantity, category);
				
			// Remove an item from the fridge
			} else if (userRequest.contains("remove")) {
				System.out.println("Enter the name of the food to remove:");
				String foodToRemove = console.nextLine();
				System.out.println("How many of this food do you want to remove?");
				int quantityRemoved = console.nextInt();
				console.nextLine();
 
				fridge.removeItem(foodToRemove, quantityRemoved);
				
			// Review the items in the fridge
			} else if (userRequest.contains("review")) {
				System.out.println(fridge.review());
				
			// Find an items from a name or a category
			} else if (userRequest.contains("find")) {
				System.out.println("Do you want to search for a category or the name of a food?");
				String request = console.nextLine();
				// If the user requests to search for items in a category
				if(request.contains("category")) {
					System.out.println("Enter the name of the category you want to search for:");
					String category = console.nextLine();
					System.out.println(fridge.categorySearchToString(category));
				// If the user requests to search for items by name
				} else if(request.contains("name") || request.contains("food")) {
					System.out.println("Enter the name of the food you want to find in your fridge:");
					String foodToFind = console.nextLine();
					System.out.println(fridge.itemSearch(foodToFind));
				// If the command is unrecognizable, let the user know
				} else {
					System.out.println("Unable to understand that command. Please read the questions carefully.");
				}
				
			// Quit the program and write to the Fridge.txt file
			} else if (userRequest.equals("quit")) {
				usingFridge = false;
				fridge.updateFile();
			// If the command is unrecognizable, let the user know
			} else {
				System.out.println("Unable to understand that command. Please read the questions carefully.");
			}
		}
	}
}
