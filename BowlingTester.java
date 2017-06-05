import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Dominic Goodman
 * 
 *         Tests the BowlingGame class
 */
public class BowlingTester
{

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		final JFileChooser fileChooser = new JFileChooser(".");
		// If a good file is selected it will display all the frames and total
		//  scores for the games
		// else it will allow for manual input
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				scan.close();
				scan = new Scanner(fileChooser.getSelectedFile());
				GUI ui = new GUI("Bowling");
				ui.setLayout(new BorderLayout(3, 1));
				ui.setDimensions(300, 400);
				String output = "";
				while (scan.hasNext())
				{
					BowlingGame nextGame = new BowlingGame(scan.nextLine());
					output += nextGame.toString() + "\n\n";
				}
				final JTextArea outputText = new JTextArea(output);
				outputText.setEditable(false);
				final JScrollPane scrollBar = new JScrollPane(outputText);
				scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				ui.add(scrollBar, BorderLayout.CENTER);
				ui.display();
			} catch (FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(new JPanel(),"Invalid file selected, allowing for manual input.");
				createManualInput();
			}
		} else
		{
			JOptionPane.showMessageDialog(new JPanel(),"No file selected, allowing for manual input.");
			createManualInput();
		}
		scan.close();

	}

	/**
	 * 
	 * 
	 * creates a UI that allows for the user to specify the game manually then
	 * display the score of the game
	 */
	public static void createManualInput()
	{
		GUI ui = new GUI("Bowling");
		ui.setLayout(new GridLayout(3, 1));
		ui.setDimensions(400, 200);
		TitledBorder inputTitle = new TitledBorder("Input");
		TitledBorder outputTitle = new TitledBorder("Output");
		final JTextField inputField = new JTextField();
		final JTextField outputField = new JTextField();
		final JButton pointsButton = new JButton("Get Total");
		outputField.setEditable(false);
		outputField.setBackground(Color.LIGHT_GRAY);
		pointsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				BowlingGame nextGame = new BowlingGame(inputField.getText());
				outputField.setText(nextGame.getScore() + "");
			}
		});
		inputField.setBorder(inputTitle);
		outputField.setBorder(outputTitle);
		ui.add(inputField);
		ui.add(pointsButton);
		ui.add(outputField);
		ui.display();
	}

}
