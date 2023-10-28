package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import geometry.Rectangle;
import geometry.Point;

import stack.DlgStack;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	DefaultListModel<Rectangle> dlm=new DefaultListModel<Rectangle> ();
	ArrayList <Rectangle> list = new ArrayList<Rectangle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmSort() {
		setTitle("Sanja Tica IT80/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.PINK);
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblStack = new JLabel("Stack");
		pnlNorth.add(lblStack);
		
		JPanel pnlCentar = new JPanel();
		contentPane.add(pnlCentar, BorderLayout.CENTER);
		pnlCentar.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlCentar.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setViewportView(scrollPane_1);
		
		JList lstRectangleSort = new JList();
		scrollPane_1.setColumnHeaderView(lstRectangleSort);
		lstRectangleSort.setModel(dlm);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(Color.PINK);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(Color.YELLOW);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgStack dlgStack=new DlgStack();
				dlgStack.setVisible(true);
				if(dlgStack.isOk) {
					try {
						int x = Integer.parseInt(dlgStack.getTxtX().getText()); 
						int y = Integer.parseInt(dlgStack.getTxtY().getText());
						int width = Integer.parseInt(dlgStack.getTxtWidth().getText());
						int height = Integer.parseInt(dlgStack.getTxtHeight().getText());
						
						Rectangle rct = new Rectangle(new Point(x,y), height, width); 
						
						dlm.add(0, rct);
						list.add(rct);
						
					}
					 catch(Exception NumberFormatException) {
						 
						 JOptionPane.showMessageDialog(null,"Fill in all the fields!");
						 
					 }
					
				}
			}
		});
		pnlSouth.add(btnAdd);
		
		JButton btnSort = new JButton("Sort");
		btnSort.setBackground(Color.YELLOW);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dlm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "List is empty! ", "ERROR", JOptionPane.WARNING_MESSAGE);
				} else {
					list.sort(null);
					dlm.clear();
					for(int i=0; i<list.size();i++) {
						dlm.addElement(list.get(i));
					
					}
				}
			}
		});
		pnlSouth.add(btnSort);
		
		
		
		
		
	}

}
