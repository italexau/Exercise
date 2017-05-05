// Class describing structure of Position: x,y,Direction(Facing)

public class Position
{
	int positionX;
	int positionY;
	String face;

	public Position (int positionX, int positionY, String face)
	{
		this.positionX = positionX;
		this.positionY = positionY;
		this.face = face;
	}

	public void setPositionX(int positionX)
	{
		this.positionX = positionX;
	}

	public int getPositionX()
	{
		return positionX;
	}

	public void setPositionY(int positionY)
	{
		this.positionY = positionY;
	}

	public int getPositionY()
	{
		return positionY;
	}

	public void setFace(String face)
	{
		this.face = face;
	}

	public String getFace()
	{
		return face;
	}

	@Override
	public String toString()
	{
		return positionX + "," + positionY + "," + face;
	}
}