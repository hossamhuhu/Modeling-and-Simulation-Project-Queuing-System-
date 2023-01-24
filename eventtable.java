package modelingproject;

import static modelingproject.event.S;
import static modelingproject.event.customerSize;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class eventtable extends JFrame {

  String data[][] = new String[event.customerSize * 2][9];
  String column[] = {
    "Event Type",
    "clock",
    "LQ",
    "LS",
    "FEL",
    "Checkout Line",
    "B",
    "MQ",
    "S",
  };
  String clock[] = new String[event.customerSize * 2];
  String total[] = new String[event.customerSize * 2];
  String LQ[] = new String[event.customerSize * 2];
  String LS[] = new String[event.customerSize * 2];

  String B[] = new String[event.customerSize * 2];
  String MQ[] = new String[event.customerSize * 2];
  String[] S = new String[customerSize * 2];
  String checkout[] = new String[event.customerSize * 2];
  JPanel p1;
  JButton b1;
  JTable jt;

  public eventtable() {
    b1 = new JButton("omar");
    p1 = new JPanel();
    p1.setLayout(new GridLayout(1, 1));
    p1.setBackground(Color.yellow);
    creat_table();
    jt = new JTable(data, column);
    jt.setBounds(30, 40, 200, 300);
    jt.getColumnModel().getColumn(0).setPreferredWidth(300);
    jt.getColumnModel().getColumn(1).setPreferredWidth(300);
    jt.getColumnModel().getColumn(2).setPreferredWidth(300);
    jt.getColumnModel().getColumn(3).setPreferredWidth(300);
    jt.getColumnModel().getColumn(4).setPreferredWidth(800);
    jt.getColumnModel().getColumn(5).setPreferredWidth(300);
    jt.getColumnModel().getColumn(6).setPreferredWidth(300);
    jt.getColumnModel().getColumn(7).setPreferredWidth(300);
    jt.getColumnModel().getColumn(8).setPreferredWidth(300);
    JScrollPane sp = new JScrollPane(jt);
    p1.add(sp);
    this.add(p1);
  }

  public void creat_table() {
    for (int i = 0; i < event.customerSize * 2; i++) {
      clock[i] = Integer.toString(event.clock[i]);

      LQ[i] = Integer.toString(event.LQ[i]);
      LS[i] = Integer.toString(event.LS[i]);
      B[i] = Integer.toString(event.B[i]);
      MQ[i] = Integer.toString(event.MQ[i]);
      checkout[i] = Integer.toString(event.checkout[i]);
      S[i] = Integer.toString(event.S[i]);
    }
    for (int i = 0; i < event.customerSize * 2; i++) {
      for (int j = 0; j < 1; j++) {
        if (event.checkout[i] == 0) {
          if (i == (customerSize * 2) - 2) {
            data[i][j] = event.total[i];

            data[i][j + 1] = clock[i];
            data[i][j + 2] = LQ[i];
            data[i][j + 3] = LS[i];
            data[i][j + 4] =
              "(" + event.total[i + 1] + "," + clock[i + 1] + ")";
            data[i][j + 5] = " ";
            data[i][j + 6] = B[i];
            data[i][j + 7] = MQ[i];
            data[i][j + 8] = S[i];
          } else if (i == (customerSize * 2) - 1) {
            data[i][j] = event.total[i];

            data[i][j + 1] = clock[i];
            data[i][j + 2] = LQ[i];
            data[i][j + 3] = LS[i];
            data[i][j + 4] = " ";
            data[i][j + 5] = " ";
            data[i][j + 6] = B[i];
            data[i][j + 7] = MQ[i];
            data[i][j + 8] = S[i];
          } else {
            data[i][j] = event.total[i];

            data[i][j + 1] = clock[i];
            data[i][j + 2] = LQ[i];
            data[i][j + 3] = LS[i];
            data[i][j + 4] =
              "(" + event.total[i + 1] + "," + clock[i + 1] + ")";
            data[i][j + 5] = " ";
            data[i][j + 6] = B[i];
            data[i][j + 7] = MQ[i];
            data[i][j + 8] = S[i];
          }
        } else if (i == (customerSize * 2) - 2) {
          data[i][j] = event.total[i];

          data[i][j + 1] = clock[i];
          data[i][j + 2] = LQ[i];
          data[i][j + 3] = LS[i];
          data[i][j + 4] = "(" + event.total[i + 1] + "," + clock[i + 1] + ")";
          data[i][j + 5] = "C" + checkout[i];
          data[i][j + 6] = B[i];
          data[i][j + 7] = MQ[i];
          data[i][j + 8] = S[i];
        } else if (i == (customerSize * 2) - 1) {
          data[i][j] = event.total[i];

          data[i][j + 1] = clock[i];
          data[i][j + 2] = LQ[i];
          data[i][j + 3] = LS[i];
          data[i][j + 4] = " ";
          data[i][j + 5] = "C" + checkout[i];
          data[i][j + 6] = B[i];
          data[i][j + 7] = MQ[i];
          data[i][j + 8] = S[i];
        } else {
          data[i][j] = event.total[i];

          data[i][j + 1] = clock[i];
          data[i][j + 2] = LQ[i];
          data[i][j + 3] = LS[i];

          data[i][j + 4] =
            "(" +
            event.total[i + 1] +
            "," +
            clock[i + 1] +
            ")" +
            "(" +
            event.total[i + 2] +
            "," +
            clock[i + 2] +
            ")";
          data[i][j + 5] = "C" + checkout[i];
          data[i][j + 6] = B[i];
          data[i][j + 7] = MQ[i];
          data[i][j + 8] = S[i];
        }
      }
    }
  }
}
