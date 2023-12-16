package bsu.rfe.java.group5.lab2.Shaban.varB5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
@SuppressWarnings("serial")

public class MainFrame extends JFrame {

	private static final int WIDTH = 650;
	private static final int HEIGHT = 320;
	Double mem1 = 0.0;
	Double mem2 = 0.0;
	Double mem3 = 0.0;
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	private JTextField textFieldResult;
	private JTextField textFieldMem;
	private ButtonGroup radioButtons = new ButtonGroup();
	private ButtonGroup radioVars = new ButtonGroup();
	private Box hboxFormulaType = Box.createHorizontalBox();
	private int formulaId = 1;
	public Double calculate1(Double x, Double y, Double z) {
	if(x==0){
		throw new IllegalArgumentException("Error 0"); 
	}
	else
		return Math.pow(Math.cos(Math.PI*x*x*x)+Math.log((1+y)*(1+y)), 0.25)*(Math.pow(Math.E,z*z)+Math.sqrt(1/x)+Math.cos(Math.pow(Math.E,y)));
	
		}

	public Double calculate2(Double x, Double y, Double z) {
		if((z==0 && y==0)||(x<0))
		{
			throw new IllegalArgumentException("Error 0"); 
		}
		else
		return Math.pow(Math.E, 0.5*x)/(Math.sqrt(z+y)*z*Math.log(x));
	}
	
	//Formulas radio buttons
	private void addRadioButton(String buttonName, final int formulaId) {
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MainFrame.this.formulaId = formulaId;
			}
		});
		radioButtons.add(button);
		hboxFormulaType.add(button);
	}
	
	//Variables radio buttons
	Box hboxVars = Box.createHorizontalBox();
	private int VarNum = 1;
	private void addRadioVar(String buttonName, final int VarNum) {
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MainFrame.this.VarNum = VarNum;
				switch (VarNum) {
				case 1:
				textFieldMem.setText(mem1.toString());
				break;
				case 2:
					textFieldMem.setText(mem2.toString());
					break;
				case 3:
					textFieldMem.setText(mem3.toString());
					break;
				}
			}
		});
		radioVars.add(button);
		hboxVars.add(button);
	}
	public MainFrame() {
	super("Formula calculating");
	setSize(WIDTH, HEIGHT);
	Toolkit kit = Toolkit.getDefaultToolkit();
	setLocation((kit.getScreenSize().width - WIDTH)/2,
	(kit.getScreenSize().height - HEIGHT)/2);
	
	//Box with formulas radio buttons 
	hboxFormulaType.add(Box.createHorizontalGlue());
	addRadioButton("Formula 1", 1);
	addRadioButton("Formula 2", 2);
	radioButtons.setSelected(
	radioButtons.getElements().nextElement().getModel(), true);
	hboxFormulaType.add(Box.createHorizontalGlue());
	hboxFormulaType.setBorder(
	BorderFactory.createLineBorder(Color.YELLOW));
	JLabel labelForX = new JLabel("X:");
	textFieldX = new JTextField("0", 10);
	textFieldX.setMaximumSize(textFieldX.getPreferredSize());
	JLabel labelForY = new JLabel("Y:");
	textFieldY = new JTextField("0", 10);
	textFieldY.setMaximumSize(textFieldY.getPreferredSize());
	JLabel labelForZ = new JLabel("Z:");
	textFieldZ = new JTextField("0", 10);
	textFieldZ.setMaximumSize(textFieldX.getPreferredSize());
	
	//Box with Variables values
	Box hboxVariables = Box.createHorizontalBox();
	hboxVariables.setBorder(
	BorderFactory.createLineBorder(Color.RED));
	hboxVariables.add(labelForX);
	hboxVariables.add(Box.createHorizontalStrut(10));
	hboxVariables.add(textFieldX);
	hboxVariables.add(Box.createHorizontalGlue());
	hboxVariables.add(labelForY);
	hboxVariables.add(Box.createHorizontalStrut(10));
	hboxVariables.add(textFieldY);
	hboxVariables.add(Box.createHorizontalGlue());
	hboxVariables.add(labelForZ);
	hboxVariables.add(Box.createHorizontalStrut(10));
	hboxVariables.add(textFieldZ);
	JLabel labelForResult = new JLabel("Result:");
	textFieldResult = new JTextField("0", 20);
	textFieldResult.setMaximumSize(
	textFieldResult.getPreferredSize());
	JLabel labelForMem = new JLabel("Memory:");
	textFieldMem = new JTextField("0", 20);
	textFieldMem.setMaximumSize(
	textFieldMem.getPreferredSize());
	
	//Result box
	Box hboxResult = Box.createHorizontalBox();
	hboxResult.add(Box.createHorizontalGlue());
	hboxResult.add(labelForResult);
	hboxResult.add(Box.createHorizontalStrut(10));
	hboxResult.add(textFieldResult);
	hboxResult.add(Box.createHorizontalStrut(10));
	hboxResult.add(labelForMem);
	hboxResult.add(Box.createHorizontalStrut(10));
	hboxResult.add(textFieldMem);
	hboxResult.add(Box.createHorizontalGlue());
	hboxResult.setBorder(BorderFactory.createLineBorder(Color.RED));

	//Box with variables radio buttons
	hboxVars.add(Box.createHorizontalGlue());
	addRadioVar("Variable 1", 1);
	addRadioVar("Variable 2", 2);
	addRadioVar("Variable 3", 3);
	radioVars.setSelected(
	radioVars.getElements().nextElement().getModel(), true);
	hboxVars.add(Box.createHorizontalGlue());
	hboxVars.setBorder(
	BorderFactory.createLineBorder(Color.ORANGE));
	JButton buttonCalc = new JButton("Calculate");
	buttonCalc.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
			try {
				 Double x = Double.parseDouble(textFieldX.getText());
				 Double y = Double.parseDouble(textFieldY.getText());
				 Double z = Double.parseDouble(textFieldZ.getText());
				 Double result;
				 if (formulaId==1)
					 result = calculate1(x, y, z);
				 else
					 result = calculate2(x, y, z);
				 textFieldResult.setText(result.toString());
				} 
			catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(MainFrame.this,
				"Error in the format of writing a floating-point number", "Wrong number format",
				JOptionPane.WARNING_MESSAGE);
				}
			catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(MainFrame.this,
				"X cannot be equal to 0", "Wrong number format",
				JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JButton buttonReset = new JButton("Clear");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				textFieldX.setText("0");
				textFieldY.setText("0");
				textFieldZ.setText("0");
				textFieldResult.setText("0");
			}
		});
		JButton buttonMC = new JButton("MC");
		buttonMC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				switch (VarNum) {
				case 1:
					mem1 = 0.0;
					textFieldMem.setText("0");
					break;
				case 2:
					mem2 = 0.0;
					textFieldMem.setText("0");
					break;
				case 3:
					mem3 = 0.0;
					textFieldMem.setText("0");
					break;
				}
			}
		});
		JButton buttonMplus = new JButton("M+");
		buttonMplus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Double Result = Double.parseDouble(textFieldResult.getText());
				switch (VarNum) {
				case 1:
					mem1 += Result;
					textFieldMem.setText(mem1.toString());
					break;
				case 2:
					mem2 += Result;
					textFieldMem.setText(mem2.toString());
					break;
				case 3:
					mem3 += Result;
					textFieldMem.setText(mem3.toString());
					break;
				}				 
			}
		});
		
		//Buttons box
		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.add(buttonCalc);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonReset);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonMC);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonMplus);
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.setBorder(
		BorderFactory.createLineBorder(Color.GREEN));
		
		//Vertical box
		Box contentBox = Box.createVerticalBox();
		contentBox.add(Box.createVerticalGlue());
		contentBox.add(hboxFormulaType);
		contentBox.add(hboxVariables);
		contentBox.add(hboxVars);
		contentBox.add(hboxButtons);
		contentBox.add(hboxResult);
		contentBox.add(Box.createVerticalGlue());
		getContentPane().add(contentBox, BorderLayout.CENTER);
		}
		public static void main(String[] args) {
			MainFrame frame = new MainFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
			
}
