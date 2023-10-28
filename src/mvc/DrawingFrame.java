package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


import adapter.HexagonAdapter;
import observer.Observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

public class DrawingFrame  extends JFrame implements Observer{
	private DrawingView view=new DrawingView();
	private DrawingController controller;
	private JPanel contentPanel;
	
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	private JToggleButton tglbtnDonut = new JToggleButton("Donut");
	private JToggleButton tglbtnHexagon=new JToggleButton("Hexagon");

	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private final JScrollPane scrollPane = new JScrollPane();
	private DefaultListModel<String> defaultList=new DefaultListModel<String>();
	private final JList list = new JList();
	private JButton innerColorBtn ;
	private JButton outlineColorBtn;
	private JButton btnModify;
	private JButton btnDelete;
	private Integer selection = null;
	
	private Color innerColor=Color.WHITE;
	private Color outlineColor=Color.BLACK;
	private final JPanel sidePanel = new JPanel();
	private final JButton btnToFront = new JButton("ToFront");
	private final JButton btnToBack = new JButton("ToBack");
	private final JButton btnBringToFront = new JButton("BringToFront");
	private final JButton btnBringToBack = new JButton("BringToBack");
	private final JButton btnRedo = new JButton("Redo");
	private final JButton btnSave = new JButton("Save");
	private final JButton btnLoad = new JButton("Load");
	private final JButton btnNext = new JButton("Next");
	private final JButton btnUndo = new JButton("Undo");

	
	public DrawingFrame()
	{
		contentPanel=new JPanel();
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.thisMouseClicked(e);
			}
		});
		getContentPane().add(contentPanel,BorderLayout.CENTER);
		setTitle("Sanja Tica IT80/2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 486);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.PINK);
		contentPanel.add(pnlNorth, BorderLayout.NORTH);
		outlineColorBtn= new JButton("Outline color");
		outlineColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outlineColor=JColorChooser.showDialog(null, "Outline color", outlineColor);
				if (outlineColor!=null) {
					outlineColorBtn.setBackground(outlineColor);
				}
			}
		});
		outlineColorBtn.setForeground(Color.WHITE);
		outlineColorBtn.setBackground(outlineColor);
		pnlNorth.add(outlineColorBtn);
		
		innerColorBtn= new JButton("Inner color");
		innerColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				innerColor=JColorChooser.showDialog(null, "Inner color", innerColor);
				if(innerColor!=null) {
					innerColorBtn.setBackground(innerColor);
				}
			}
		});
		innerColorBtn.setBackground(innerColor);
		pnlNorth.add(innerColorBtn);
		tglbtnPoint.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
		tglbtnPoint.setBackground(Color.YELLOW);

		pnlNorth.add(tglbtnPoint);
		tglbtnLine.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
		tglbtnLine.setBackground(Color.YELLOW);
		pnlNorth.add(tglbtnLine);
		
		pnlNorth.add(tglbtnHexagon);
		tglbtnHexagon.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
		tglbtnHexagon.setBackground(Color.YELLOW);
		pnlNorth.add(tglbtnLine);
		

		ButtonGroup btnGroup = new ButtonGroup();

		btnGroup.add(tglbtnPoint);
		btnGroup.add(tglbtnLine);
		tglbtnRectangle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
		tglbtnRectangle.setBackground(Color.YELLOW);
		pnlNorth.add(tglbtnRectangle);
		btnGroup.add(tglbtnRectangle);
		tglbtnCircle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
		tglbtnCircle.setBackground(Color.YELLOW);
		pnlNorth.add(tglbtnCircle);
		btnGroup.add(tglbtnCircle);
		tglbtnDonut.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
		tglbtnDonut.setBackground(Color.YELLOW);
		pnlNorth.add(tglbtnDonut);
		btnGroup.add(tglbtnDonut);
		btnGroup.add(tglbtnHexagon);
		tglbtnSelect.setForeground(new Color(0, 128, 0));
		pnlNorth.add(tglbtnSelect);
		tglbtnSelect.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
		tglbtnSelect.setBackground(Color.GREEN);
		
		btnGroup.add(tglbtnSelect);
		
		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		btnModify.setForeground(new Color(0, 100, 0));
		pnlNorth.add(btnModify);
		btnModify.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
		btnModify.setBackground(Color.GREEN);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.buttonModify(e);
			}
		});
		btnGroup.add(btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setForeground(new Color(0, 128, 0));
		pnlNorth.add(btnDelete);
		btnDelete.setFont(new Font("Lucida Handwriting", Font.PLAIN, 10));
		btnDelete.setBackground(new Color(0, 255, 0));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
				tglbtnSelect.setSelected(false);
			}
		});
		btnGroup.add(btnDelete);
		
		contentPanel.add(view, BorderLayout.CENTER);
		
		contentPanel.add(view, BorderLayout.CENTER);
		
		contentPanel.add(scrollPane, BorderLayout.SOUTH);
		
		//contentPanel.add(list, BorderLayout.WEST);
		scrollPane.setViewportView(list);
		list.setModel(defaultList);
		
		sidePanel.setBackground(Color.PINK);
		
		contentPanel.add(sidePanel, BorderLayout.EAST);
		sidePanel.setLayout(new GridLayout(0, 1));
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toFront();
			}
		});
		btnToFront.setBackground(Color.YELLOW);
		
		sidePanel.add(btnToFront);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toBack();
			}
		});
		btnToBack.setBackground(Color.YELLOW);
		
		sidePanel.add(btnToBack);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		btnBringToFront.setBackground(Color.YELLOW);
		
		sidePanel.add(btnBringToFront);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		btnBringToBack.setBackground(Color.YELLOW);
		
		sidePanel.add(btnBringToBack);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		btnUndo.setBackground(Color.GREEN);
		
		sidePanel.add(btnUndo);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		btnRedo.setBackground(Color.GREEN);
		
		sidePanel.add(btnRedo);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.next();
			}
		});
		btnNext.setBackground(Color.YELLOW);
		
		sidePanel.add(btnNext);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				selection = fileChooser.showSaveDialog(view);
				if(selection != null && selection == JFileChooser.APPROVE_OPTION) {
					controller.save(fileChooser.getSelectedFile().getPath());
				}
			}
		});
		btnSave.setBackground(Color.YELLOW);
		
		sidePanel.add(btnSave);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				selection = fileChooser.showOpenDialog(view);
				if(selection != null && selection == JFileChooser.APPROVE_OPTION) {
					controller.load(fileChooser.getSelectedFile().getPath());
				}
			}
		});
		btnLoad.setBackground(Color.YELLOW);
		
		sidePanel.add(btnLoad);

	}

	

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}



	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}



	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}
	
	
	public DefaultListModel<String> getDefaultList(){
		return this.defaultList;
	}
	
	public void setDefaultList(DefaultListModel<String> defaultList) {
		this.defaultList=defaultList;
	}


	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}



	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}



	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}



	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}



	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}



	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}



	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}



	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}



	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}



	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}
	
	public JToggleButton getTglbtnHexagon() {
		return this.tglbtnHexagon;
	}
	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon=tglbtnHexagon;
	}
	public Color getInnerColor() {
		return this.innerColor;
	}
	
	public void setInnerColor(Color innerColor) {
		this.innerColor=innerColor;
	}
	
	public Color getOutlineColor() {
		return this.outlineColor;
	}
	public void setOutlineColor(Color outlineColor) {
		this.outlineColor=outlineColor;
	}
	
	public JButton getBtnModify() {
		return this.btnModify;
	}
	public void setBtnModify(JButton btnModify) {
		this.btnModify=btnModify;
	}
	
	public JButton getBtnDelete() {
		return this.btnDelete;
	}
	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete=btnDelete;
	}


	@Override
	public void update(boolean modifyEnabled, boolean deleteEnabled) {
		btnModify.setEnabled(modifyEnabled);
		btnDelete.setEnabled(deleteEnabled);
	}
	
	
}
