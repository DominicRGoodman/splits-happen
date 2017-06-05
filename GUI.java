import javax.swing.*;

/**
 * 
 * @author Dominic Goodman 
 * 
 *         Subclass of JFrame to create initialize the basic GUI I will use for the quiz
 */
class GUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUI(String name)
	{
		super(name);
	}

	public void display()
	{
		setVisible(true);
	}

	private void setFrame(int width, int height)
	{
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void setDimensions(int x, int y)
	{
		setFrame(x, y);
	}

}
