	package wfp;
	
	import java.util.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.io.*;
	
	public class window extends Frame {
		Panel pnl1 = new Panel(), pnl2 = new Panel(), pnl3 = new Panel(), pnl4 = new Panel(), pnl5 = new Panel();
		static String[] chs, eng;
		static String ans = "";
		static int pnl = 1, random[], num, wrong, turn;
		RandomTree rd = new RandomTree();
		// Frame frm = new Frame();
		Label pnl3lab0 = new Label("", 1), pnl3lab1 = new Label("", 1), labRate = new Label("",1), labWrong = new Label("",1);
	
		public window() {
			// over pnl_start
			// over pnl_end
			// pnl1_start (first window)
			Label labTitle = new Label("英文打字練習", 1);
			labTitle.setBounds(100, 100, 400, 100);
			labTitle.setFont(new Font("標楷體", Font.BOLD, 32));
			pnl1.add(labTitle);
			
			Label labRule=new Label("選擇Practice或Test模式後，請直接輸入英文單字並以空白件作為輸入依據",1);
			labRule.setFont(new Font("標楷體", Font.BOLD, 12));
			labRule.setBounds(50,200,500,100);
			pnl1.add(labRule);	
	
			Button btnDinary = new Button("Dictionary");
			btnDinary.setBounds(50, 300, 100, 50);
			btnDinary.addActionListener(new Dinary());
			pnl1.add(btnDinary);
	
			Button btnPractice = new Button("Practice");
			btnPractice.setBounds(250, 300, 100, 50);
			btnPractice.addActionListener(new Practice());
			pnl1.add(btnPractice);
	
			Button btnTest = new Button("Test");
			btnTest.setBounds(450, 300, 100, 50);
			btnTest.addActionListener(new Test());
			pnl1.add(btnTest);
	
			pnl1.setLayout(null);
			pnl1.setBounds(0, 0, 600, 400);
			pnl1.setBackground(Color.GREEN);
			pnl1.setVisible(true);
			this.add(pnl1);
			// pnl1_end (first window)
	
			// pnl2_start (dinary_window)
			TextArea txaPnl2;
			txaPnl2 = new TextArea();
			txaPnl2.setBounds(100, 50, 400, 200);
			for (int i = 0; i < eng.length; i++) {
				txaPnl2.append(chs[i] + " " + eng[i] + "\n");
			}
			pnl2.add(txaPnl2);
	
			Button btnBack2 = new Button("Back");
			btnBack2.setBounds(250, 300, 100, 50);
			btnBack2.addActionListener(new Back());
			pnl2.add(btnBack2);
	
			pnl2.setLayout(null);
			pnl2.setBounds(0, 0, 600, 400);
			pnl2.setBackground(Color.RED);
			pnl2.setVisible(false);
			this.add(pnl2);
			// pnl2_end (dinary_window)
	
			// pnl3_start (practice_window)
	
			pnl3lab0.setBounds(100, 200, 400, 100);
			pnl3lab0.setForeground(Color.white);
			pnl3lab0.addKeyListener(new CMyListener1());
			pnl3lab0.setFont(new Font("標楷體", Font.ITALIC, 64));
			pnl3.add(pnl3lab0);
	
			pnl3lab1.setBounds(100, 100, 400, 100);
			pnl3lab1.setForeground(Color.white);
			pnl3lab1.setFont(new Font("標楷體", Font.ITALIC, 64));
			pnl3.add(pnl3lab1);
	
			Button btnBack3 = new Button("Back");
			btnBack3.setBounds(250, 300, 100, 50);
			btnBack3.addActionListener(new Back());
			pnl3.add(btnBack3);
	
			pnl3.setLayout(null);
			pnl3.setBounds(0, 0, 600, 400);
	
			pnl3.setBackground(Color.BLUE);
			pnl3.setVisible(false);
			this.add(pnl3);
			// pnl3_end (practice_window)
	
			// pnl4_start (test_window)
			labWrong.setBounds(100, 100, 400, 100);
			labWrong.setFont(new Font("標楷體", Font.ITALIC, 64));
			pnl4.add(labWrong);
	
			labRate.setBounds(0, 200, 600, 100);
			labRate.setFont(new Font("標楷體", Font.ITALIC, 64));
			pnl4.add(labRate);
	
			Button btnBack4 = new Button("Back");
			btnBack4.setBounds(250, 300, 100, 50);
			btnBack4.addActionListener(new Back());
			pnl4.add(btnBack4);
	
			pnl4.setLayout(null);
			pnl4.setBounds(0, 0, 600, 400);
			pnl4.setBackground(Color.ORANGE);
			pnl4.setVisible(false);
			this.add(pnl4);
			// pnl4_end (test_window)
	
			// pnl5_start (last_window)
			Button btnBack5 = new Button("Back");
			btnBack5.setBounds(250, 300, 100, 50);
			btnBack5.addActionListener(new Back());
			pnl5.add(btnBack5);
	
			pnl5.setLayout(null);
			pnl5.setBounds(0, 0, 600, 400);
			pnl5.setBackground(Color.YELLOW);
			pnl5.setVisible(false);
			this.add(pnl5);
			// pnl5_end (last_window)
	
			// this_start
			this.setTitle("英文打字練習");
			this.setLayout(null);
			this.setSize(600, 400);
			this.setVisible(true);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			// frm_end
		}
	
		public static void main(String args[]) throws FileNotFoundException {
			// read file_start
			Scanner scn = new Scanner(new File("01.txt"));
			int line = 0;
			while (scn.hasNextLine()) {// dinary line number
				line++;
				scn.nextLine();
			}
			scn.close();
			scn = new Scanner(new File("01.txt"));
			chs = new String[line];
			eng = new String[line];
			for (int i = 0; i < line; i++) {
				String temp = scn.nextLine();
				String[] words = new String(temp).split(" ");
				chs[i] = words[0];
				eng[i] = words[1];
			}
			scn.close();
			// read file_end
	
			random = new int[line];// 隨機單字順序
	
			new window();
		}
	
		// btn_event
		class Dinary implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				pnl1.setVisible(false);
				pnl2.setVisible(true);
				pnl = 2;
			}
		}
	
		class Practice implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				pnl1.setVisible(false);
				pnl3.setVisible(true);
				pnl = 3;
				rd.RT(random);
				num = 0;
				wrong = 0;
				turn = 0;
				ans = "";
				pnl3lab1.setText(eng[random[num]] + " " + chs[random[num]]);
				pnl3lab0.setText(ans);
				pnl3lab0.requestFocus();
			}
		}
	
		class Test implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				pnl1.setVisible(false);
				pnl3.setVisible(true);
				pnl = 4;
				rd.RT(random);
				num = 0;
				wrong = 0;
				turn = 0;
				ans = "";
				pnl3lab1.setText(chs[random[num]]);
				pnl3lab0.setText(ans);
				pnl3lab0.requestFocus();
			}
		}
	
		class Back implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				pnl1.setVisible(true);
				pnl2.setVisible(false);
				pnl3.setVisible(false);
				pnl4.setVisible(false);
				pnl5.setVisible(false);
				pnl = 1;
			}
		}
	
		// 按鍵
		class CMyListener1 implements KeyListener {
			public void keyPressed(KeyEvent e) {
				String temp = "";
				switch (e.getKeyCode()) {
				case 65:
					temp = "a";
					break;
				case 66:
					temp = "b";
					break;
				case 67:
					temp = "c";
					break;
				case 68:
					temp = "d";
					break;
				case 69:
					temp = "e";
					break;
				case 70:
					temp = "f";
					break;
				case 71:
					temp = "g";
					break;
				case 72:
					temp = "h";
					break;
				case 73:
					temp = "i";
					break;
				case 74:
					temp = "j";
					break;
				case 75:
					temp = "k";
					break;
				case 76:
					temp = "l";
					break;
				case 77:
					temp = "m";
					break;
				case 78:
					temp = "n";
					break;
				case 79:
					temp = "o";
					break;
				case 80:
					temp = "p";
					break;
				case 81:
					temp = "q";
					break;
				case 82:
					temp = "r";
					break;
				case 83:
					temp = "s";
					break;
				case 84:
					temp = "t";
					break;
				case 85:
					temp = "u";
					break;
				case 86:
					temp = "v";
					break;
				case 87:
					temp = "w";
					break;
				case 88:
					temp = "x";
					break;
				case 89:
					temp = "y";
					break;
				case 90:
					temp = "z";
					break;
				case 8:// back space
					ans = ans.substring(0, ans.length() - 1);
					temp = "";
					break;
				}
				ans = ans + temp;
				pnl3lab0.setText(ans);
	
				switch (e.getKeyCode()) {
				case 32:// space
					if (eng[random[num]].equals(ans)) {
						num = num + 1;
						turn = turn + 1;
						ans = "";
						pnl3lab0.setText("");
						if (num == random.length) {
							pnl3.setVisible(false);
							pnl4.setVisible(true);
							labWrong.setText("錯誤次數：" + wrong);
							labRate.setText("正確率：" + ((float) ((float) (turn - wrong) / (float) turn))*100+"%");
						} else {
							if (pnl == 3)
								pnl3lab1.setText(eng[random[num]] + " " + chs[random[num]]);
							else if (pnl == 4)
								pnl3lab1.setText(chs[random[num]]);
						}
					} else {
						wrong=wrong+1;
						turn=turn+1;
					}
					break;
				}
			}
	
			@Override
			public void keyTyped(KeyEvent e) {
	
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
			}
		}
	
		// 隨機亂數
		class RandomTree {
			public int[] RT(int a[]) {
				int b[] = new int[a.length];
				for (int i = 0; i < a.length; i++) {
					b[i] = i;
				}
				for (int i = 0; i < a.length; i++) {
					int j = (int) (Math.random() * (a.length - i));
					a[i] = b[j];
					for (int k = j; k < b.length - 1; k++) {
						b[k] = b[k + 1];
					}
	
				}
				return a;
			}
		}
	}
