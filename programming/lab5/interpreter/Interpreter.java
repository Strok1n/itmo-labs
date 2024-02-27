package interpreter;


import java.util.*;
import commands.*;
import java.io.*;
import base.*;

//import static java.lang.System.*;



public class Interpreter
{
	private Map<String, Command> availableCommands;
	private Scanner consoleScanner;
	
	private Map<LabWork, String> baseMap;
	private Collection baseCollection;
	
	private java.time.ZonedDateTime collectionInitializationDate;
	
	private InputStream inputStream;
	private PrintStream outputStream;





	public Interpreter() throws Exception
	{
		
		this.inputStream = System.in;
		this.outputStream = System.out;
		this.consoleScanner = new Scanner(System.in);
		
		this.availableCommands = new HashMap<String, Command>();
		
		
		this.baseMap = new TreeMap<LabWork, String>();

		
		this.baseCollection = new Collection();
		this.baseCollection.setBaseCollection( new HashSet<LabWork>() );
		this.baseCollection.setCollectionInitializationDate(java.time.ZonedDateTime.now());


		
		Help help = new Help();
		Info info = new Info();
		Show show = new Show();
		Insert insert = new Insert();
		Update update = new Update();
		RemoveById removeById = new RemoveById();
		Clear clear = new Clear();
		Save save = new Save();
		ExecuteScript executeScript = new ExecuteScript();
		Exit exit = new Exit();
		RemoveLower removeLower = new RemoveLower();
		ReplaceIfLower replaceIfLower = new ReplaceIfLower();
		RemoveLowerKey removeLowerKey = new RemoveLowerKey();
		AverageOfMinimalPoint averageOfMinimalPoint = new AverageOfMinimalPoint();
		PrintUniqueDiscipline printUniqueDiscipline = new PrintUniqueDiscipline();
		PrintFieldDescendingDifficulty printFieldDescendingDifficulty = new PrintFieldDescendingDifficulty();
		
		this.availableCommands.put("help", help);
		this.availableCommands.put("info", info);
		this.availableCommands.put("show", show);
		this.availableCommands.put("insert", insert);
		this.availableCommands.put("add", insert);
		this.availableCommands.put("update", update);
		this.availableCommands.put("remove_by_id", removeById);
		this.availableCommands.put("clear", clear);
		this.availableCommands.put("save", save);
		this.availableCommands.put("execute_script", executeScript);
		this.availableCommands.put("exit", exit);
		this.availableCommands.put("remove_lower", removeLower);
		this.availableCommands.put("replace_if_lower", removeLowerKey);
		this.availableCommands.put("remove_lower_key", averageOfMinimalPoint);
		this.availableCommands.put("average_of_minimal_point", averageOfMinimalPoint);
		this.availableCommands.put("print_unique_discipline", printUniqueDiscipline);
		this.availableCommands.put("print_field_descending_difficulty ", printFieldDescendingDifficulty);
		
		help.setAvailableCommands( Arrays.copyOf( 
		this.availableCommands.values().toArray(), 
		this.availableCommands.size(), Command[].class ) );
		
	}
	
	public Command putCommand(String commandName, Command command)
	{
		return this.availableCommands.put(commandName, command);
	}
	

	
	public void handleInput()
	{
		boolean exit = false;
		while (!exit)
		{
			this.outputStream.println("Enter the command:");
			String inputString = this.consoleScanner.nextLine();
			String[] tokens = inputString.split(" ");
			
			String commandName = tokens[0];
			System.out.println("#$##");
			System.out.println(inputString);
			System.out.println( Arrays.toString (tokens));
			System.out.println( Arrays.toString (Arrays.copyOfRange(tokens, 1, tokens.length)));
			System.out.println("#$##");
			if (this.availableCommands.keySet().contains(commandName))
			{
				
				this.availableCommands.get(commandName).setBaseMap(baseMap);
				this.availableCommands.get(commandName).setBaseCollection(this.baseCollection);
				
				if (tokens.length > 1)
					this.availableCommands.get(commandName).setArguments(
					Arrays.copyOfRange(tokens, 1, tokens.length));
				

				this.availableCommands.get(commandName)
				.setIOStreams(this.consoleScanner, this.outputStream);
				

				this.availableCommands.get(commandName).execute();
				
				//if(commandName == "insert" && this.baseMap.size() == 0)
				//	this.collectionInitializationDate = java.time.ZonedDateTime.now();
				
			}
			else
			{
				this.outputStream.println("Unknown command: " + commandName);
			}
			
		}

	}
}