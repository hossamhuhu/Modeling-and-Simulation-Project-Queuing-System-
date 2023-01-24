package modelingproject;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class statistics extends JFrame {

  JPanel p, p1, p2, p3, p4, p5, p6, p7, p8;
  JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16;
  JTextField txt1, txt2;
  ArrayList<JPanel> panel = new ArrayList();

  public statistics() {
    p = new JPanel();
    p.setLayout(new GridLayout(MultiServer.numberOfServers * 2 + 2, 1));

    for (int i = 0; i < MultiServer.numberOfServers * 2; i++) {
      p5 = new JPanel();
      panel.add(p5);
    }
    l1 = new JLabel("Average Waiting ");
    l2 = new JLabel(Double.toString(MultiServerTable.waitingaverage));
    p1 = new JPanel();
    p1.add(l1);
    p1.add(l2);
    p.add(p1);
    for (int j = 0; j < MultiServer.numberOfServers; j++) {
      for (int i = 0; i < MultiServer.numberOfServers; i++) {
        l3 = new JLabel("Server " + Integer.toString(i + 1) + " is kept idle ");
        l6 =
          new JLabel(Double.toString(MultiServerTable.avergaeTimeIdle.get(i)));
        p2 = new JPanel();
        p2.add(l3);
        p2.add(l6);
        panel.get(j).add(p2);
        j++;
        l11 =
          new JLabel("Server " + Integer.toString(i + 1) + " is kept Busy ");
        l12 =
          new JLabel(Double.toString(MultiServerTable.avergaeTimeBusy.get(i)));
        p4 = new JPanel();
        p4.add(l11);
        p4.add(l12);

        panel.get(j).add(p4);
      }
    }
    for (int i = 0; i < panel.size(); i++) {
      p.add(panel.get(i));
    }
    l9 = new JLabel("Average of total service time ");
    l10 = new JLabel(Double.toString(MultiServerTable.serviceaverage));
    p8 = new JPanel();
    p8.add(l9);
    p8.add(l10);
    p.add(p8);
    this.add(p);
  }
}
