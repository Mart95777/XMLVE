/**
 * 
 */
package com.mart95777.xmlve;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;






import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	JPopupMenu menuPopUp;
	private JTree tree;
	private Document document;
    private DefaultTreeModel treeModel;
    File fileToOpen;

	//= new JTree();
	
	static final int windowHeight = 460;
	static final int leftWidth = 300;
	static final int rightWidth = 340;
	static final int windowWidth = leftWidth + rightWidth;
	
	
	
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
	    
	    JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
	    openMenuItem.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//JOptionPane.showMessageDialog(null, "Testing open ...");
	    		JFileChooser c = new JFileChooser();
	    	    // Demonstrate "Open" dialog:
	    		int rVal = c.showOpenDialog(Xmlve.this);
	    	    if (rVal == JFileChooser.APPROVE_OPTION) {
	    	    fileToOpen = c.getSelectedFile();
	    	    // parsing file
	    	    document = parseDomFromXml(fileToOpen);
	    	    treeModel = new DefaultTreeModel(buildTreeNode(document));
	    	    tree.setModel(treeModel);
	    	    ((DefaultTreeModel)tree.getModel()).reload();
	    	    }
	    	    if (rVal == JFileChooser.CANCEL_OPTION) {
	    	    //filename.setText("You pressed cancel");
	    	    //dir.setText("");
	    	    }
	        }
	    });
	    fileMenu.add(openMenuItem);

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
		
		
		
		// Set up the tree
		tree = new JTree();
		
//		 MouseListener ml = new MouseAdapter() {
//		     public void mousePressed(MouseEvent e) {
//		         int selRow = tree.getRowForLocation(e.getX(), e.getY());
//		         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
//		         if(selRow != -1) {
//		             if(e.getClickCount() == 1) {
//		                 //mySingleClick(selRow, selPath);
//		            	 JOptionPane.showMessageDialog(null, "Testing click mouse");
//		             }
//		             else if(e.getClickCount() == 2) {
//		                 //myDoubleClick(selRow, selPath);
//		             }
//		         }
//		     }
		     // another version
		     MouseListener ml = new MouseAdapter() {
		         public void mousePressed(MouseEvent mouseEvent) {
		           int modifiers = mouseEvent.getModifiers();
		           if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
		             //System.out.println("Left button pressed.");
		           }
		           if ((modifiers & InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK) {
		             //System.out.println("Middle button pressed.");
		           }
		           if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
		             //System.out.println("Right button pressed.");
		        	   
		           }
		         }
		         public void mouseReleased(MouseEvent e) {
		           if (SwingUtilities.isLeftMouseButton(e)) {
		             //System.out.println("Left button released.");
		           }
		           if (SwingUtilities.isMiddleMouseButton(e)) {
		             //System.out.println("Middle button released.");
		           }
		           if (SwingUtilities.isRightMouseButton(e)) {
		             //System.out.println("Right button released.");
		        	   int selRow = tree.getRowForLocation(e.getX(), e.getY());
				         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				         if(selRow != -1) {
				             if(e.getClickCount() == 1) {
				                 //mySingleClick(selRow, selPath);
				            	 //JOptionPane.showMessageDialog(null, "Testing click mouse, right released");
				            	 if (e.isPopupTrigger()) {
				                     menuPopUp.show(e.getComponent(), e.getX(), e.getY());
				                   }
				             }
				             else if(e.getClickCount() == 2) {
				                 //myDoubleClick(selRow, selPath);
				             }
				         }
		           }
		           //System.out.println();
		         }
		 };
		 tree.addMouseListener(ml);
		 tree.addMouseListener(new PopupTriggerListener());

		// Build left-side view
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setPreferredSize(new Dimension( leftWidth, windowHeight ));

		// Build right-side view
		JEditorPane htmlPane = new JEditorPane("text/html","");
		htmlPane.setEditable(false);
		JScrollPane htmlView = new JScrollPane(htmlPane);
		htmlView.setPreferredSize(new Dimension( rightWidth, windowHeight ));

		// Build split-pane view
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treeView, htmlView );
		splitPane.setContinuousLayout( true );
		splitPane.setDividerLocation( leftWidth );
		//splitPane.setPreferredSize(new Dimension( windowWidth + 10, windowHeight+10 ));
		splitPane.setMinimumSize(new Dimension(600,500));
		splitPane.setMaximumSize(new Dimension(3000,1500));
		
		// Add GUI components
		addcomponent(mainPanel, splitPane, 0,0,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		
		testText = new JTextArea();
		testText.setText("Test text");
		testText.setEditable(false);
		testText.setOpaque(false);
		addcomponent(mainPanel, testText, 0,1,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH);
		
		// Popup menu --> here?
		menuPopUp = new JPopupMenu("Popup");
		JMenuItem item = new JMenuItem("Clear All");
	    item.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Object o = tree.getModel().getRoot();
	    		//JOptionPane.showMessageDialog(null, o.toString());
	    		DefaultMutableTreeNode  dmn = (DefaultMutableTreeNode )o;
	    		dmn.removeAllChildren();
	    		((DefaultTreeModel)tree.getModel()).reload();
	    		//treeModel.reload();
	    		//tree.getModel().reload();
	    	}
		});
		menuPopUp.add(item);
		
		
//		public void fillUserList(){    
//		    List<User> userFriends = ClientController.getInstance().getPrieteniiUserului(user);
//		    root.removeAllChildren(); //this removes all nodes
//		    model.reload(); //this notifies the listeners and changes the GUI
//		    for(int i=0;i<userFriends.size();i++){
//		        User user = userFriends.get(i);
//		        model.insertNodeInto(new DefaultMutableTreeNode(user.getNume()), root, i);        
//		    }
//		}

		item = new JMenuItem("Add \"new\" node");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Menu item Test2");
				//creating a new node
				DefaultMutableTreeNode root, child, chosen;
				Object o = tree.getModel().getRoot();
				root = (DefaultMutableTreeNode )o;
				child = new DefaultMutableTreeNode("new");
		          // What's the last one you clicked?
		          chosen = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		          if (chosen == null)
		            chosen = root;
		          // The model will create the appropriate event.
		          // In response, the tree will update itself:
		          ((DefaultTreeModel) tree.getModel()).insertNodeInto(child, chosen, 0);
				
				
				
			}
		});
		menuPopUp.add(item);
		
		item = new JMenuItem("Expand All");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expandAll(tree);
			}
		});
		menuPopUp.add(item);
		
		item = new JMenuItem("Collapse All");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				collapseAll(tree);
			}
		});
		menuPopUp.add(item);
		
		this.add(mainPanel);
		//this.pack();
		this.setVisible(true);
	}// end of Constructor
	
	/**
     * Expand all tree nodes.
     */
    public void expandAllNodes() {
        if (tree != null) {
            for (int i = 0; i < tree.getRowCount(); i++) {
                tree.expandRow(i);
            }
        }
    }

/**
 * Recursively build tree node from document node. Add attributes as leaves
 *
 * @param docNode
 * @return A default mutable tree node.
 */
private DefaultMutableTreeNode buildTreeNode(Node docNode) {
    DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(
            docNode.getNodeName());

    if (docNode.hasAttributes()) {
        NamedNodeMap attributes = docNode.getAttributes();

        for (int j = 0; j < attributes.getLength(); j++) {
            String attr = attributes.item(j).toString();
            treeNode.add(new DefaultMutableTreeNode(attr));
        }
    }

    if (docNode.hasChildNodes()) {
        NodeList childNodes = docNode.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            short childNodeType = childNode.getNodeType();

            if (childNodeType == Node.ELEMENT_NODE) {
                treeNode.add(buildTreeNode(childNode));
            } else if (childNodeType == Node.TEXT_NODE) {
                String text = childNode.getTextContent().trim();
                if (!text.equals("")) {
                    treeNode.add(new DefaultMutableTreeNode(text));
                }
            } else if (childNodeType == Node.COMMENT_NODE) {
                String comment = childNode.getNodeValue().trim();
                treeNode.add(new DefaultMutableTreeNode(comment));
            }
        }
    }
    return treeNode;
}// end of buildTreeNode(Node docNode) 

/**
 * Parse an XML file into document object model.
 *
 * @param filePath
 * @return A DOM document.
 */
private Document parseDomFromXml(File file) {
    Document document = null;
    try {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        document = dbf.newDocumentBuilder().parse(file);
        document.normalizeDocument();
    } catch (IOException | ParserConfigurationException | SAXException e) {
        e.printStackTrace();
    }
    return document;
}//end of parseDomFromXml(File file)

/**
 * Inner class
 */

class PopupTriggerListener extends MouseAdapter {
    public void mousePressed(MouseEvent ev) {
      if (ev.isPopupTrigger()) {
    	  menuPopUp.show(ev.getComponent(), ev.getX(), ev.getY());
      }
    }
}// end of inner class PopupTriggerListener extends MouseAdapter

public void expandAll(JTree tree) {
    int row = 0;
    while (row < tree.getRowCount()) {
      tree.expandRow(row);
      row++;
      }
}//end of public void expandAll(JTree tree)

public void collapseAll(JTree tree) {
    int row = tree.getRowCount() - 1;
    while (row >= 0) {
      tree.collapseRow(row);
      row--;
      }
}//end of public void collapseAll(JTree tree)

}// end of Xmlve class
