/**
 * 
 */
package com.mart95777.xmlve;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author marcin
 *
 */
public class Xmlve extends JFrame {

	/**
	 * @param args
	 */
	public static final String VERSION = "1.0.0";
	JPanel mainPanel;
	JTextArea testText;
	JMenuBar menuBar;
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Xmlve frame = new Xmlve();

	}
	/**
	 * Methods for the constructor
	 */
	
	private void addcomponent(JPanel pn, JComponent cmp, int xpos, int ypos, int w, int h, int place, int stretch){
		GridBagConstraints gridcns = new GridBagConstraints();
		gridcns.gridx = xpos;
		gridcns.gridy = ypos;
		gridcns.gridwidth = w;
		gridcns.gridheight = h;
		gridcns.weightx = 100;
		gridcns.weighty = 100;
		gridcns.insets = new Insets(5,5,5,5);
		gridcns.anchor = place;
		gridcns.fill = stretch;
		
		pn.add(cmp, gridcns);
	} // end private void addcomponent
	/**
	 * CONSTRUCTOR!
	 */
	public Xmlve(){
		super("XML VE, viewer and editor, ver. " + VERSION);
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		menuBar = new JMenuBar();
	    // File Menu, F - Mnemonic
	    JMenu fileMenu = new JMenu("File");
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    menuBar.add(fileMenu);
	    // File->New, N - Mnemonic
	    JMenuItem newMenuItem = new JMenuItem("New", KeyEvent.VK_N);
	    newMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JOptionPane.showMessageDialog(null, "Testing menu item");
	        }
	    });
	    fileMenu.add(newMenuItem);

	    //frame.setJMenuBar(menuBar);
	    setJMenuBar(menuBar);
	    
//	    menuBar.addActionListener(new ActionListener() {
//			  public void actionPerformed(ActionEvent evt) {
//				// 
//				//treeOfUser.invalidate();
//				//treeOfUser.validate();
//				//treeOfUser.repaint();
//				//treeOfUser.setModel(modelTree);
//				//modelTree.reload();
//				mainPanel.updateUI();
//				JOptionPane.showMessageDialog(null, "So?...");
//				
//			  }
//		});
		
		testText = new JTextArea();
		testText.setText("Test text");
		testText.setEditable(false);
		testText.setOpaque(false);
		addcomponent(mainPanel, testText, 0,0,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
		
		this.add(mainPanel);
		//this.pack();
		this.setVisible(true);
	}

}
