
package calculator;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Entry {
        public static void main(String[] args) {
        	try {
				UIManager.setLookAndFeel(MyLookAndFeel.JTATTOO_BERNSTEIN);//»»Æ¤·ô
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
        	calculator cl=new calculator();
        	cl.setVisible(true);
		}
}
