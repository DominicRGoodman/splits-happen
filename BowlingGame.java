/**
 * 
 * @author Dominic Goodman
 * 
 *         Creates a game of bowling then allows for calculation of the total
 *         score
 * 
 */
public class BowlingGame
{
	private String[] neededThrowsPerFrame = new String[10];
	private String[] frames = new String[10];

	/**
	 * 
	 * @param input
	 *            - string of throws in a game of bowling
	 * 
	 *            Using an input it will split the string to allow for each
	 *            frame to have its needed info for scoring
	 * 
	 */
	public BowlingGame(String input)
	{
		String[] frameHits = input.split("");
		int currentHit = 1;
		for (int i = 0; i < 10; i++)
		{
			int neededHits = 2;
			int hitsInFrame = 2;
			neededThrowsPerFrame[i] = "";
			for (int j = 0; j < neededHits; j++)
			{
				if (frameHits[currentHit + j].equalsIgnoreCase("x"))
				{
					switch (neededHits)
					{
					case 2:
					case 4:
						neededHits = 3;
						break;
					case 3:
						// only unique case with strikes is the ninth and tenth
						// frame that dont use up to 4 throws
						if (i < 8)
							neededHits = 4;
						break;
					}

					hitsInFrame = 1;
				}
				neededThrowsPerFrame[i] += frameHits[currentHit + j];
			}
			if (neededThrowsPerFrame[i].length() == 2 && neededThrowsPerFrame[i].endsWith("/"))
			{
				neededThrowsPerFrame[i] += frameHits[currentHit + 2];
			}
			currentHit += hitsInFrame;
		}

		for (int i = 0; i < 10; i++)
		{
			if (i == 9 && neededThrowsPerFrame[i].length() == 3)
			{
				frames[i] = neededThrowsPerFrame[i].substring(0, 3);
			} else if ((neededThrowsPerFrame[i].charAt(0) == 'x' || neededThrowsPerFrame[i].charAt(0) == 'X'))
			{
				frames[i] = neededThrowsPerFrame[i].charAt(0) + "";
			} else
				frames[i] = neededThrowsPerFrame[i].substring(0, 2);
		}
	}

	/**
	 * 
	 * @return - the total points scored within the game
	 * 
	 *         Calculates the points by a frame to frame basis and adds to the
	 *         total as it goes
	 */
	public int getScore()
	{
		int total = 0;
		for (String nextFrame : neededThrowsPerFrame)
		{
			if (nextFrame.equalsIgnoreCase("xxx"))
			{
				total += 30;
			} else
			{
				char[] nextPoint = nextFrame.toCharArray();
				for (int i = 0; i < nextPoint.length; i++)
				{
					if (nextPoint[i] == '/')
					{
						total += (10 - Character.getNumericValue(nextPoint[i - 1]));
					} else if (nextPoint[i] == 'x' || nextPoint[i] == 'X')
					{
						total += 10;
					} else if (nextPoint[i] != '-')
					{
						total += Character.getNumericValue(nextPoint[i]);
					}
				}
			}
		}
		return total;
	}

	/**
	 * returns the values of each frame and total score
	 */
	public String toString()
	{
		String summary = "Match:\n";
		for (String nextFrame : frames)
			summary += nextFrame + "|";
		summary += "\nTotal:" + getScore();

		return summary;
	}
}
