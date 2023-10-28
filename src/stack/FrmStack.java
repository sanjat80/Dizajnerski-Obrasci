package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import geometry.Rectangle;
import geometry.Point;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	DefaultListModel<Rectangle> dlm=new DefaultListModel<Rectangle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setTitle("Sanja Tica IT80/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
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
		
		JList lstRectangle = new JList();
		scrollPane_1.setColumnHeaderView(lstRectangle);
		lstRectangle.setModel(dlm);
		
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
					try
					{
						int x=Integer.parseInt(dlgStack.txtX.getText());
						int y=Integer.parseInt(dlgStack.txtY.getText());
						int height=Integer.parseInt(dlgStack.txtHeight.getText());
						int width=Integer.parseInt(dlgStack.txtWidth.getText());
						
						Rectangle rectangle=new Rectangle(new Point(x,y),height,width);
						
						dlm.add(0,rectangle);
					}
					catch(Exception NumberFormatException)
					{ 
						JOptionPane.showMessageDialog(null, "Fill in all the fields!");
						
					}
					
				}
			}
		});
		pnlSouth.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.YELLOW);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dlm.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "List is empty","ERROR",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					DlgStack dlg=new DlgStack();
					Point p=dlm.getElementAt(0).getUpperLeft();
					int height=dlm.getElementAt(0).getHeight();
					int width=dlm.getElementAt(0).getWidth();
					
					dlg.getTxtX().setEditable(false);
					dlg.getTxtY().setEditable(false);
					dlg.getTxtHeight().setEditable(false);
					dlg.getTxtWidth().setEditable(false);
					
					
					dlg.getTxtX().setText(Integer.toString(p.getX()));
					dlg.getTxtY().setText(Integer.toString(p.getY()));
					dlg.getTxtWidth().setText(Integer.toString(width));
					dlg.getTxtHeight().setText(Integer.toString(height));
					
					dlg.setVisible(true);
					
					if(dlg.isOk)
					{
						dlm.remove(0); 
					}
					
				}
			}
		});
		pnlSouth.add(btnDelete);
	}

}
