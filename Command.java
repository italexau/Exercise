// Class describing structure of Command: Command(Order),Position

public class Command
{
	String order;
	Position position;

	public Command (String order, Position position)
	{
		this.order = order;
		this.position = position;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public String getOrder()
	{
		return order;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public Position getPosition()
	{
		return position;
	}

	@Override
	public String toString()
	{
		return order + "," + position.toString();
	}
}