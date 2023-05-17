package com.ashish.sample;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class CalculatorUI extends JFrame implements ActionListener {
	private JTextField num1Field;
	private JTextField num2Field;
	private JTextField resultField;

	public CalculatorUI() {
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setSize(600, 550);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);

		JLabel num1Label = new JLabel("Number 1:");
		add(num1Label, constraints);

		num1Field = new JTextField(10);
		constraints.gridx = 1;
		add(num1Field, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		JLabel num2Label = new JLabel("Number 2:");
		add(num2Label, constraints);

		num2Field = new JTextField(10);
		constraints.gridx = 1;
		add(num2Field, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		JLabel resultLabel = new JLabel("Result:");
		add(resultLabel, constraints);

		resultField = new JTextField(10);
		resultField.setEditable(false);
		constraints.gridx = 1;
		add(resultField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;

		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);
		add(addButton, constraints);

		constraints.gridy = 4;
		JButton subtractButton = new JButton("Subtract");
		subtractButton.addActionListener(this);
		add(subtractButton, constraints);

		constraints.gridy = 5;
		JButton multiplyButton = new JButton("Multiply");
		multiplyButton.addActionListener(this);
		add(multiplyButton, constraints);

		constraints.gridy = 6;
		JButton divideButton = new JButton("Divide");
		divideButton.addActionListener(this);
		add(divideButton, constraints);

		constraints.gridy = 7;
		JButton squareButton = new JButton("Square");
		squareButton.addActionListener(this);
		add(squareButton, constraints);

		constraints.gridy = 8;
		JButton squareRootButton = new JButton("Square Root");
		squareRootButton.addActionListener(this);
		add(squareRootButton, constraints);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String num1Text = num1Field.getText().trim();
		String num2Text = num2Field.getText().trim();

		if (num1Text.isEmpty()) {
			resultField.setText("Error: Invalid input");
			return;
		}

		double num1 = Double.parseDouble(num1Text);
		double num2 = 0; // Default value for num2
		double result = 0;

		if (!num2Text.isEmpty()) {
			num2 = Double.parseDouble(num2Text);
		}

		if (e.getActionCommand().equals("Add")) {
			result = num1 + num2;
		} else if (e.getActionCommand().equals("Subtract")) {
			result = num1 - num2;
		} else if (e.getActionCommand().equals("Multiply")) {
			result = num1 * num2;
		} else if (e.getActionCommand().equals("Divide")) {
			if (num2 != 0) {
				result = num1 / num2;
			} else {
				resultField.setText("Error: Division by zero");
				return;
			}
			resultField.setText(String.format("%.4f", result));
		} else if (e.getActionCommand().equals("Square")) {
			result = num1 * num1;
			resultField.setText(String.format("%.4f", result));
			num2Field.setText("");
			num2Field.setEnabled(false);
		} else if (e.getActionCommand().equals("Square Root")) {
			if (num1 >= 0) {
				result = Math.sqrt(num1);
				resultField.setText(String.format("%.4f", result));
			} else {
				resultField.setText("Error: Invalid input");
				return;
			}
			num2Field.setText("");
			num2Field.setEnabled(false);
		}
		resultField.setText(String.valueOf(result));
		num2Field.setEnabled(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			CalculatorUI calculator = new CalculatorUI();
			calculator.setVisible(true);
		});
	}
}
