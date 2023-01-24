package modelingproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EventInput extends JFrame implements ActionListener {

  JPanel base, p1, p2, p3;
  JButton alg1, alg2, cmp;
  JLabel wel, choose, l1, l2;
  static JTextField txt, txt2;

  public EventInput() {
    base = new JPanel();
    base.setLayout(new GridLayout(4, 1));
    base.setBackground(Color.WHITE);

    p1 = new JPanel();
    p1.setLayout(new FlowLayout());
    p1.setBackground(Color.WHITE);

    p3 = new JPanel();
    p3.setLayout(new FlowLayout());
    p3.setBackground(Color.WHITE);

    alg1 = new JButton(" Next ");
    alg1.setForeground(Color.blue);
    alg1.setFont(new Font("Arial", Font.BOLD, 20));

    base.add(p1);
    base.add(p3);

    choose = new JLabel("    please choose the needed algorithm");
    choose.setFont(new Font("Arial", Font.BOLD, 30));
    txt = new JTextField();
    txt.setColumns(10);
    txt2 = new JTextField();
    txt2.setColumns(10);

    l1 = new JLabel(" please enter number of customers :");
    p2 = new JPanel();

    p2.setLayout(new FlowLayout());
    p2.setBackground(Color.WHITE);
    l1.setFont(new Font("Arial", Font.BOLD, 16));
    l1.setForeground(new Color(0, 97, 169));
    p2.add(l1);
    p2.add(txt);
    base.add(p2);
    p1.add(alg1);

    base.add(p1);
    this.add(base);
    alg1.addActionListener(this);
  }

  public void close() {
    WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == alg1) {
      close();
      event m = new event();
      m.setSize(600, 600);
      m.setVisible(true);
      m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  }
}
