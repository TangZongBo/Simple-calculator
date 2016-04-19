
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
	private String [] texts={"7","8","9","/","%","4","5","6","*","1/x","1","2","3","-","±","√","CE","0","+","="};
	private JButton text[] = new JButton[texts.length]; 
	private boolean firstDigit = true; 
	private double resultNum = 0; //结果
	private String operator = "="; 
	private boolean operateValidFlag = true; 
	public calculator(){
	    	initialize();//初始化界面
	    	pressbutton();//按钮设置
	    }
	private void pressbutton() {
	    for(int i=0;i<texts.length;i++){
	         text[i]=new JButton(texts[i]);
	         text[i].setSize(30,30);
	         text[i].setMargin(new Insets(0,0,0,0));//四个焦距设置
	         text[i].setLocation(10+(i%5)*40,100+(i/5)*40);
	    	this.add(text[i]);
	    }
	    for(int i=0;i<texts.length;i++){//规定为一个机制
	    	  text[i].addActionListener(this); 
	    }
	}
	private void initialize() {
		JMenuBar bar=createMenu();
		this.setJMenuBar(bar);
        Toolkit tk=Toolkit.getDefaultToolkit();
        Image i=tk.getImage("src\\calculator\\cal.jpg");//换图标
	  this.setIconImage(i);
        this.setTitle("简易版·计算器");
		this.setSize(218,313);
		this.setLayout(null);//不调用布局管理器
		this.setLocationRelativeTo(null);//居中
			this.setResizable(false);////禁止放大
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//随着关闭而关闭运行
		jf.setBounds(8,8,195,40);
       jf.setCaretColor(Color.WHITE);//把光标变成背景颜色 让用户无法看到
      jf.setFont(new Font("宋体",Font.ITALIC,25));//改变字体
		this.add(jf);
 }
	private JMenuBar createMenu() {
	       JMenuBar bar=new JMenuBar();//创建菜单栏
	       JMenu  menuView=new JMenu("查看(V)");//创建菜单
	       menuView.setMnemonic('V');//创建快捷键
	       JMenu menuHelp=new JMenu("帮助(H)");
	       menuHelp.setMnemonic('H');
	       JMenuItem itemStard=new JMenuItem("退出(X)");
	       itemStard.setMnemonic('X');
	       JMenuItem itemAbout=new JMenuItem("关于计算器(A)");
	       itemAbout.setMnemonic('A');
	      itemAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//定制事件
				about at=new about();
			}
		});
	       itemStard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.ALT_MASK ));//创建
	       itemStard.addActionListener(new ActionListener() {//定制事件
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
	public void actionPerformed(ActionEvent e) {//判断所按的键的类型
		//获取事件源的标签
		String str=e.getActionCommand();
		//按下CE键的反应
		if(str.equals(texts[16])){
		      jf.setText("0");
		}
		//按数字键
		else if("0123456789.".indexOf(str)>=0){
			handleNumber(str);
		}
		//按运算符键
		else{
			  handleOperator(str); 
		}
	}
	private void handleOperator(String str) {//判断所按的运算符键
		if (operator.equals("/")) { 
            // 除法运算 
            // 如果当前结果文本框中的值等于0 
            if (getNumberFromText() == 0.0) { 
                // 操作不合法 
                operateValidFlag = false; 
               JOptionPane.showConfirmDialog(this,"除数不能为零");//弹出提示机智
            } else { 
                resultNum /= getNumberFromText(); 
            } 
        } else if (operator.equals("1/x")) { 
            // 倒数运算 
            if (resultNum == 0.0) { 
                // 操作不合法 
                operateValidFlag = false; 
                JOptionPane.showConfirmDialog(this,"零没有倒数"); //弹出提示机智
            } else { 
                resultNum = 1 / resultNum; 
            } 
        } else if (operator.equals("+")) { 
            // 加法运算 
            resultNum += getNumberFromText(); 
        } else if (operator.equals("-")) { 
            // 减法运算           
            resultNum -= getNumberFromText(); 
        } else if (operator.equals("*")) { 
            // 乘法运算 
            resultNum *= getNumberFromText(); 
        } else if (operator.equals("sqrt")) { 
            // 平方根运算 
            resultNum = Math.sqrt(resultNum); 
        } else if (operator.equals("%")) { 
            // 百分号运算，除以100 
            resultNum = resultNum / 100; 
        } else if (operator.equals("±")) { 
            // 正数负数运算 
            resultNum = resultNum * (-1); 
        } else if (operator.equals("=")) { 
            // 赋值运算 
            resultNum = getNumberFromText(); 
        } 
        if (operateValidFlag) { 
            // 双精度浮点数的运算 
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
        // 运算符等于用户按的按钮 
        operator = str; 
        firstDigit = true; 
        operateValidFlag = true; 
		
	}
	private double getNumberFromText() { 
        double result = 0; 
        try { 
            result = Double.valueOf(jf.getText()).doubleValue(); //把字符串转成double
        } catch (NumberFormatException e) { 
        } 
        return result; 
    } 
	private void handleNumber(String str) {//判断按下的数字
		  if (firstDigit) { 
	            // 输入的第一个数字 
	            jf.setText(str); 
	        } else if ((str.equals(".")) && (jf.getText().indexOf(".") < 0)) { 
	            // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面 
	           jf.setText(jf.getText() + "."); 
	        } else if (!str.equals(".")) { 
	            // 如果输入的不是小数点，则将数字附在结果文本框的后面 
	           jf.setText(jf.getText() + str);
	        }
	        // 以后输入的肯定不是第一个数字了 
	        firstDigit = false; 
		
	}
}
