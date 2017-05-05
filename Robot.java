import java.util.*;

public class Robot
{
	private final int BOARD_SIZE_X = 5;
	private final int BOARD_SIZE_Y = 5;
	private final String RULES1 = "\nThe following commands are allowed: PLACE X,Y,Direction(NORTH, SOUTH, WEST, EAST), MOVE, RIGHT, LEFT, REPORT. ";
	private final String RULES2 = "PLACE should be the first command. Enter EXIT to stop game.";

	private Position currentPosition = new Position (-1, -1,"");
	private Command currentCommand = new Command("",null);
	private boolean setExit = false;

    // method used to process user's input and make sure the command is entered in correct format and with all required parameters
    public void receiveCommand()
    {
		Scanner inputString = new Scanner (System.in);
		String command = "";
		boolean correctCommand = false;

		System.out.print ("\nPlease enter next command for Robot: \n");
		command = inputString.nextLine();

		while (!correctCommand) // flag to show the command is correct
		{
			switch (command.toLowerCase()) // possible commands
			{
				case "exit":
							setExit = true;
							correctCommand = true;
							break;
				case "move":
							currentCommand.setOrder("MOVE");
							correctCommand = true;
							break;
				case "left":
							currentCommand.setOrder("LEFT");
							correctCommand = true;
							break;
				case "right":
							currentCommand.setOrder("RIGHT");
							correctCommand = true;
							break;
				case "report":
							currentCommand.setOrder("REPORT");
							correctCommand = true;
							break;
				default:
							if (command.startsWith("place ",0))
							{
								// breaking position into parts X,Y,Facing
								try
								{
									String position = command.substring(6, command.length());
									StringTokenizer tokens = new StringTokenizer(position,",");
									String stringX = "";
									String stringY = "";
									String face = "";
									int numberX = -1;
									int numberY = -1;
									while(tokens.hasMoreTokens())
		        					{
										stringX = tokens.nextToken();
										stringY = tokens.nextToken();
										face = tokens.nextToken();
									}
									try
									{
										numberX = Integer.parseInt (stringX);
										numberY = Integer.parseInt (stringY);
										if (numberX<0 || numberX>BOARD_SIZE_X-1 || numberY<0 || numberY>BOARD_SIZE_Y) // if the new position is out of the border
										{
											throw new IllegalArgumentException();
										}
										else
										{
											Position facing;
											switch (face.toLowerCase()) // getting facing
											{
												case "north":
															currentCommand.setOrder("PLACE");
															facing = new Position (numberX, numberY, "NORTH");
															currentCommand.setPosition(facing);
															correctCommand = true;
															break;
												case "south":
															currentCommand.setOrder("PLACE");
															facing = new Position (numberX, numberY, "SOUTH");
															currentCommand.setPosition(facing);
															correctCommand = true;
															break;
												case "west":
															currentCommand.setOrder("PLACE");
															facing = new Position (numberX, numberY, "WEST");
															currentCommand.setPosition(facing);
															correctCommand = true;
															break;
												case "east":
															currentCommand.setOrder("PLACE");
															facing = new Position (numberX, numberY, "EAST");
															currentCommand.setPosition(facing);
															correctCommand = true;
															break;
												default:
															System.out.print (RULES1);
															System.out.print (RULES2);
															System.out.print ("\nIncorrect input, please try again\n");
															command = inputString.nextLine();
															break;
											}
										}
									}
									catch (NumberFormatException e)
									{
										System.out.print ("\nCoordinates should be numeric, please try again\n");
										command = inputString.nextLine();
									}
									catch (IllegalArgumentException e)
									{
										System.out.print ("\nNumbers can be 0 to " + (BOARD_SIZE_X-1) + " for X and 0 to " + (BOARD_SIZE_Y-1)  + " for Y only, please try again\n");
										command = inputString.nextLine();
									}
								}
								catch (NoSuchElementException e)
								{
									System.out.print ("\nCoordinates should be entered in the following format X,Y,Facing, please try again\n");
									command = inputString.nextLine();
								}
							}
							else
							{
								System.out.print (RULES1);
								System.out.print (RULES2);
								System.out.print ("\nIncorrect input, please try again\n");
								command = inputString.nextLine();
							}
							break;
			}
		}
	}

	// method used to proceed the entered command
	public void executeCommand()
	{
		boolean placeFirst = false;
		String commandOrder = "";
		Position commandPosition;

		receiveCommand();
		while (!setExit) // until the user enters Exit
		{
			commandOrder = currentCommand.getOrder();
			if (commandOrder.equals("PLACE")) // checking if the robot was placed on the board
			{
				placeFirst = true;
			}
			if (placeFirst)
			{
				String direction = currentPosition.getFace();
				switch (commandOrder)
				{
					case "PLACE":
								  commandPosition = currentCommand.getPosition();
								  currentPosition.setPositionX(commandPosition.getPositionX());
								  currentPosition.setPositionY(commandPosition.getPositionY());
								  currentPosition.setFace(commandPosition.getFace());
								  break;
					case "MOVE":
								  int x = currentPosition.getPositionX();
								  int y = currentPosition.getPositionY();
								  switch (direction) // moving according to direction
								  {
									  case "NORTH":
									  			   y++;
									  			   if (y>4)
									  			   {
													   System.out.print ("\nThis movement is not allowed and will be ignored");
												   }
												   else
												   {
													   currentPosition.setPositionY(y);
												   }
									  			   break;
									  case "SOUTH":
									  			   y--;
									  			   if (y<0)
									  			   {
													   System.out.print ("\nThis movement is not allowed and will be ignored");
												   }
												   else
												   {
													   currentPosition.setPositionY(y);
												   }
									  			   break;
									  case "EAST":
									  			   x++;
									  			   if (x>4)
									  			   {
													   System.out.print ("\nThis movement is not allowed and will be ignored");
												   }
												   else
												   {
													   currentPosition.setPositionX(x);
												   }
									  			   break;
									  case "WEST":
									  			   x--;
									  			   if (x<0)
									  			   {
													   System.out.print ("\nThis movement is not allowed and will be ignored");
												   }
												   else
												   {
													   currentPosition.setPositionX(x);
												   }
									  			   break;
								  }
								  break;
					case "LEFT":
								  switch (direction)
								  {
									  case "NORTH":
									  			   currentPosition.setFace("WEST");
									  			   break;
									  case "WEST":
									  			   currentPosition.setFace("SOUTH");
									  			   break;
									  case "SOUTH":
									  			   currentPosition.setFace("EAST");
									  			   break;
									  case "EAST":
									  			   currentPosition.setFace("NORTH");
									  			   break;
								  }
								  break;
					case "RIGHT":
								  switch (direction)
								  {
									  case "NORTH":
								  				   currentPosition.setFace("EAST");
								  				   break;
								  	  case "EAST":
								  				   currentPosition.setFace("SOUTH");
								  				   break;
								  	  case "SOUTH":
								  				   currentPosition.setFace("WEST");
								  				   break;
								  	  case "WEST":
								  				   currentPosition.setFace("NORTH");
								  				   break;
								  }
								  break;
					case "REPORT":
								  System.out.print ("\nOutput: " + currentPosition.toString() + "\n");
								  break;
				}
				receiveCommand();
			}
			else
			{
				System.out.print ("\nPLACE should be the first command. The current input will be ignored");
				receiveCommand();
			}
		}
	}

    public static void main(String[] args)
    {
        Robot myRobot = new Robot();

		System.out.print (myRobot.RULES1);
		System.out.print (myRobot.RULES2);
		myRobot.executeCommand();

    }
}
