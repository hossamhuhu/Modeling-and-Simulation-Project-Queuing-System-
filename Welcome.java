package modelingproject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Welcome extends JFrame implements ActionListener {

  JPanel base, p1, p2, p3;
  JButton alg1, alg2, cmp, alg3;
  JLabel wel, choose, l1, l2;
  static JTextField txt, txt2;

  public Welcome() {
    base = new JPanel();
    base.setLayout(new GridLayout(4, 1));
    base.setBackground(Color.WHITE);

    p1 = new JPanel();
    p1.setLayout(new FlowLayout());
    p1.setBackground(Color.WHITE);

    p3 = new JPanel();
    p3.setLayout(new FlowLayout());
    p3.setBackground(Color.WHITE);

    alg1 = new JButton(" Multi server ");
    alg1.setForeground(Color.blue);
    alg1.setFont(new Font("Arial", Font.BOLD, 20));

    cmp = new JButton("  Classical Inventory  ");
    cmp.setForeground(Color.blue);
    cmp.setFont(new Font("Arial", Font.BOLD, 20));
    cmp.addActionListener(this);

    alg2 = new JButton("Event Scheduling");
    alg2.setForeground(Color.blue);
    alg2.setFont(new Font("Arial", Font.BOLD, 20));
    alg2.addActionListener(this);

    alg3 = new JButton("(M,N)Inventory ");
    alg3.setForeground(Color.blue);
    alg3.setFont(new Font("Arial", Font.BOLD, 20));
    alg3.addActionListener(this);

    wel =
      new JLabel(
        "                                     Welcome to our simulation system"
      );
    wel.setFont(new Font("Arial", Font.BOLD, 16));
    wel.setForeground(new Color(0, 97, 169));

    choose = new JLabel("    please choose the needed algorithm");
    choose.setFont(new Font("Arial", Font.BOLD, 30));
    txt = new JTextField();
    txt.setColumns(10);
    txt2 = new JTextField();
    txt2.setColumns(10);
    p2 = new JPanel();

    p2.setLayout(new FlowLayout());
    p2.setBackground(Color.WHITE);

    base.add(wel);
    base.add(p2);
    p1.add(alg1);
    p1.add(cmp);
    p1.add(alg2);
    p1.add(alg3);
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
      MultiServerInput m = new MultiServerInput();
      m.setSize(600, 600);
      m.setVisible(true);
      m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } else if (e.getSource() == alg2) {
      close();
      EventInput m = new EventInput();
      m.setSize(600, 600);
      m.setVisible(true);
      m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } else if (e.getSource() == cmp) {
      close();
      Classical_Inventory m = new Classical_Inventory();

      m.setSize(600, 600);
      m.setVisible(true);
      m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } else if (e.getSource() == alg3) {
      close();
      MNinventory m = new MNinventory();

      m.setSize(600, 600);
      m.setVisible(true);
      m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  }
}
