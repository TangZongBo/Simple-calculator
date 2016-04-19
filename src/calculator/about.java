
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
             Image i=tk.getImage("src\\calculator\\cal.jpg");//换图标
     	  this.setIconImage(i);
        	 this.setTitle("正式版·1.0版");
        	 this.setVisible(true);
             this.setBounds(900,300,150,150);
             	this.setResizable(false);
             	this.setLayout(null);
             	JLabel jb=new JLabel("作者：上千主上");
             	jb.setBounds(10, 10,100,50);
             	this.add(jb);
             	JLabel jl=new JLabel("完成时间:2016/4月");
             	jl.setBounds(10,40,120,50);
             	this.add(jl);
         }
}
