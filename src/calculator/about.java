
package calculator;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class about extends JFrame{
         public about(){
        	 Toolkit tk=Toolkit.getDefaultToolkit();
             Image i=tk.getImage("src\\calculator\\cal.jpg");//��ͼ��
     	  this.setIconImage(i);
        	 this.setTitle("��ʽ�桤1.0��");
        	 this.setVisible(true);
             this.setBounds(900,300,150,150);
             	this.setResizable(false);
             	this.setLayout(null);
             	JLabel jb=new JLabel("���ߣ���ǧ����");
             	jb.setBounds(10, 10,100,50);
             	this.add(jb);
             	JLabel jl=new JLabel("���ʱ��:2016/4��");
             	jl.setBounds(10,40,120,50);
             	this.add(jl);
         }
}
