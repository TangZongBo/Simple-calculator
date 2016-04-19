
package calculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import com.jtattoo.plaf.About;
public  class calculator extends JFrame implements ActionListener{
	private JTextField jf=new JTextField("0");
	private String [] texts={"7","8","9","/","%","4","5","6","*","1/x","1","2","3","-","��","��","CE","0","+","="};
	private JButton text[] = new JButton[texts.length]; 
	private boolean firstDigit = true; 
	private double resultNum = 0; //���
	private String operator = "="; 
	private boolean operateValidFlag = true; 
	public calculator(){
	    	initialize();//��ʼ������
	    	pressbutton();//��ť����
	    }
	private void pressbutton() {
	    for(int i=0;i<texts.length;i++){
	         text[i]=new JButton(texts[i]);
	         text[i].setSize(30,30);
	         text[i].setMargin(new Insets(0,0,0,0));//�ĸ���������
	         text[i].setLocation(10+(i%5)*40,100+(i/5)*40);
	    	this.add(text[i]);
	    }
	    for(int i=0;i<texts.length;i++){//�涨Ϊһ������
	    	  text[i].addActionListener(this); 
	    }
	}
	private void initialize() {
		JMenuBar bar=createMenu();
		this.setJMenuBar(bar);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Image i=tk.getImage("src\\calculator\\cal.jpg");//��ͼ��
	  this.setIconImage(i);
        this.setTitle("���װ桤������");
		this.setSize(218,313);
		this.setLayout(null);//�����ò��ֹ�����
		this.setLocationRelativeTo(null);//����
			this.setResizable(false);////��ֹ�Ŵ�
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//���Źرն��ر�����
		jf.setBounds(8,8,195,40);
       jf.setCaretColor(Color.WHITE);//�ѹ���ɱ�����ɫ ���û��޷�����
      jf.setFont(new Font("����",Font.ITALIC,25));//�ı�����
		this.add(jf);
 }
	private JMenuBar createMenu() {
	       JMenuBar bar=new JMenuBar();//�����˵���
	       JMenu  menuView=new JMenu("�鿴(V)");//�����˵�
	       menuView.setMnemonic('V');//������ݼ�
	       JMenu menuHelp=new JMenu("����(H)");
	       menuHelp.setMnemonic('H');
	       JMenuItem itemStard=new JMenuItem("�˳�(X)");
	       itemStard.setMnemonic('X');
	       JMenuItem itemAbout=new JMenuItem("���ڼ�����(A)");
	       itemAbout.setMnemonic('A');
	      itemAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//�����¼�
				about at=new about();
			}
		});
	       itemStard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.ALT_MASK ));//����
	       itemStard.addActionListener(new ActionListener() {//�����¼�
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});   
	       menuHelp.add(itemAbout);
	       menuView.add(itemStard);
	       bar.add(menuView);
	       bar.add(menuHelp);
		return bar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {//�ж������ļ�������
		//��ȡ�¼�Դ�ı�ǩ
		String str=e.getActionCommand();
		//����CE���ķ�Ӧ
		if(str.equals(texts[16])){
		      jf.setText("0");
		}
		//�����ּ�
		else if("0123456789.".indexOf(str)>=0){
			handleNumber(str);
		}
		//���������
		else{
			  handleOperator(str); 
		}
	}
	private void handleOperator(String str) {//�ж��������������
		if (operator.equals("/")) { 
            // �������� 
            // �����ǰ����ı����е�ֵ����0 
            if (getNumberFromText() == 0.0) { 
                // �������Ϸ� 
                operateValidFlag = false; 
               JOptionPane.showConfirmDialog(this,"��������Ϊ��");//������ʾ����
            } else { 
                resultNum /= getNumberFromText(); 
            } 
        } else if (operator.equals("1/x")) { 
            // �������� 
            if (resultNum == 0.0) { 
                // �������Ϸ� 
                operateValidFlag = false; 
                JOptionPane.showConfirmDialog(this,"��û�е���"); //������ʾ����
            } else { 
                resultNum = 1 / resultNum; 
            } 
        } else if (operator.equals("+")) { 
            // �ӷ����� 
            resultNum += getNumberFromText(); 
        } else if (operator.equals("-")) { 
            // ��������           
            resultNum -= getNumberFromText(); 
        } else if (operator.equals("*")) { 
            // �˷����� 
            resultNum *= getNumberFromText(); 
        } else if (operator.equals("sqrt")) { 
            // ƽ�������� 
            resultNum = Math.sqrt(resultNum); 
        } else if (operator.equals("%")) { 
            // �ٷֺ����㣬����100 
            resultNum = resultNum / 100; 
        } else if (operator.equals("��")) { 
            // ������������ 
            resultNum = resultNum * (-1); 
        } else if (operator.equals("=")) { 
            // ��ֵ���� 
            resultNum = getNumberFromText(); 
        } 
        if (operateValidFlag) { 
            // ˫���ȸ����������� 
            long t1; 
            double t2; 
            t1 = (long) resultNum; 
            t2 = resultNum - t1; 
            if (t2 == 0) { 
                jf.setText(String.valueOf(t1)); 
            } else { 
                jf.setText(String.valueOf(resultNum)); 
            } 
        } 
        // ����������û����İ�ť 
        operator = str; 
        firstDigit = true; 
        operateValidFlag = true; 
		
	}
	private double getNumberFromText() { 
        double result = 0; 
        try { 
            result = Double.valueOf(jf.getText()).doubleValue(); //���ַ���ת��double
        } catch (NumberFormatException e) { 
        } 
        return result; 
    } 
	private void handleNumber(String str) {//�жϰ��µ�����
		  if (firstDigit) { 
	            // ����ĵ�һ������ 
	            jf.setText(str); 
	        } else if ((str.equals(".")) && (jf.getText().indexOf(".") < 0)) { 
	            // �������С���㣬����֮ǰû��С���㣬��С���㸽�ڽ���ı���ĺ��� 
	           jf.setText(jf.getText() + "."); 
	        } else if (!str.equals(".")) { 
	            // �������Ĳ���С���㣬�����ָ��ڽ���ı���ĺ��� 
	           jf.setText(jf.getText() + str);
	        }
	        // �Ժ�����Ŀ϶����ǵ�һ�������� 
	        firstDigit = false; 
		
	}
}
