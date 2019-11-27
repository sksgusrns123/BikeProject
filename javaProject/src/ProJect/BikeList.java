package ProJect;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ch21_jdbc.ScoreDTO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BikeList extends JFrame {

	private JPanel contentPane;
	private JTextField tfNum;
	private JTextField tfBranch;
	private JTextField tfPrname;
	private JTextField tfCompany;
	private JTextField tfWhdate;
	private JTextField tfMoney;
	private JTextField tfAmount;
	private JTextField tfSearch;
	//추가
	private BikeDAO dao;
	private Vector<String> col;
	private DefaultTableModel model;
	private BikeDTO dto;
	private JTable table;
	private JButton btnUpdate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BikeList frame = new BikeList();
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
	public BikeList() {
		setTitle("자전거 매출기록표");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 244, 524, 198);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("품번");
		lblNewLabel.setBounds(12, 10, 57, 15);
		contentPane.add(lblNewLabel);
		
		//추가
		dao=new BikeDAO();
		col=new Vector<String>();
		col.add("품번");
		col.add("지점");
		col.add("제품명");
		col.add("제조사");
		col.add("입고날짜");
		col.add("가격");
		col.add("수량");
		col.add("총액");
		
		list();
		table=new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				tfNum.setText(table.getValueAt(idx, 0)+"");
				tfBranch.setText(table.getValueAt(idx, 1)+"");
				tfPrname.setText(table.getValueAt(idx, 2)+"");
				tfCompany.setText(table.getValueAt(idx, 3)+"");
				tfWhdate.setText(table.getValueAt(idx, 4)+"");
				tfMoney.setText(table.getValueAt(idx, 5)+"");
				tfAmount.setText(table.getValueAt(idx, 6)+"");
			}
		});
		scrollPane.setViewportView(table);
		
		tfNum = new JTextField();
		tfNum.setBounds(81, 7, 116, 21);
		contentPane.add(tfNum);
		tfNum.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("지점");
		lblNewLabel_1.setBounds(12, 35, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		tfBranch = new JTextField();
		tfBranch.setBounds(81, 32, 116, 21);
		contentPane.add(tfBranch);
		tfBranch.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("제품명");
		lblNewLabel_2.setBounds(12, 60, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		tfPrname = new JTextField();
		tfPrname.setBounds(81, 57, 116, 21);
		contentPane.add(tfPrname);
		tfPrname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("제조사");
		lblNewLabel_3.setBounds(12, 85, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		tfCompany = new JTextField();
		tfCompany.setBounds(81, 82, 116, 21);
		contentPane.add(tfCompany);
		tfCompany.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("입고날짜");
		lblNewLabel_4.setBounds(12, 110, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tfWhdate = new JTextField();
		tfWhdate.setBounds(81, 107, 116, 21);
		contentPane.add(tfWhdate);
		tfWhdate.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("가격");
		lblNewLabel_5.setBounds(12, 135, 57, 15);
		contentPane.add(lblNewLabel_5);
		
		tfMoney = new JTextField();
		tfMoney.setBounds(81, 132, 116, 21);
		contentPane.add(tfMoney);
		tfMoney.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("수량");
		lblNewLabel_6.setBounds(12, 160, 57, 15);
		contentPane.add(lblNewLabel_6);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(81, 157, 116, 21);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("제조사를 입력하시오.");
		lblNewLabel_8.setBounds(12, 219, 125, 15);
		contentPane.add(lblNewLabel_8);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(149, 216, 198, 21);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.insertBike(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(
							BikeList.this, "저장되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnSave.setBounds(250, 6, 125, 23);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.updateBike(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(
							BikeList.this, "수정되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnUpdate.setBounds(250, 31, 125, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=tfCompany.getText();
				int result=0;
				int response=JOptionPane.showConfirmDialog(
						BikeList.this, "삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteBike(company);
				}
				if(result==1) {
					JOptionPane.showMessageDialog(
							BikeList.this, "삭제되엇습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnDelete.setBounds(250, 56, 125, 23);
		contentPane.add(btnDelete);
		
		JButton btnSearch = new JButton("찾기");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(373, 211, 97, 23);
		contentPane.add(btnSearch);
	}//BikeList()
	public void search() {
		String company=tfSearch.getText();
		model=new DefaultTableModel(dao.searchBike(company),col) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}//search()
	public void input() {
		String num=tfNum.getText();
		String branch=tfBranch.getText();
		String prname=tfPrname.getText();
		String company=tfCompany.getText();
		String whdate=tfWhdate.getText();
		int money=Integer.parseInt(tfMoney.getText());
		int amount=Integer.parseInt(tfAmount.getText());
		dto=new BikeDTO(num, branch, prname, company, whdate, money, amount);
	}//input
	public void clear() {
		tfNum.setText("");
		tfBranch.setText("");
		tfPrname.setText("");
		tfCompany.setText("");
		tfWhdate.setText("");
		tfMoney.setText("");
		tfAmount.setText("");
		tfCompany.requestFocus();//커서를 옮김
		tfCompany.setEditable(true);
		//수정이 끝나면 입력을 활성화시킴
	}
	public void list() {
		model=new DefaultTableModel(dao.listBike(), col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}//list
}
