package modelingproject;

import static modelingproject.MultiServer.avergaeTimeBusy;
import static modelingproject.MultiServer.avergaeTimeIdle;
import static modelingproject.MultiServer.customerSize;
import static modelingproject.MultiServer.totalServiceTime;
import static modelingproject.MultiServer.waitingaverage;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MultiServerTable extends JFrame implements ActionListener {

  String data[][] = new String[MultiServer.customerSize][3 +
  MultiServer.numberOfServers *
  3 +
  1];
  //String column[]={"CUSTOMER","TIME BETWEEN ARRIVAL","ARRIVAL TIME","TIME SERVICE BEGIN","SERVICE TIME" ,"TIME SERVICE END" ,"TIME SERVICE BEGIN","SERVICE TIME" ,"TIME SERVICE END" ,"TIME SERVICE BEGIN","SERVICE TIME" ,"TIME SERVICE END","TIME IN QUEUE"};
  String column[] = new String[3 + MultiServer.numberOfServers * 3 + 1];
  //String data1[][]={ {"101","Amit","670000"},
  //                      {"102","Jai","780000"},
  //                      {"101","Sachin","700000"}};
  String[] customer1 = new String[MultiServer.customerSize];
  String[] timeBetweenArrivals1 = new String[MultiServer.customerSize];
  String[] arrivalTime1 = new String[MultiServer.customerSize];
  String[] AtimeServicebegin1 = new String[MultiServer.customerSize];
  String[] AserviceTime1 = new String[MultiServer.customerSize];
  String[] AtimeServiceEnd1 = new String[MultiServer.customerSize];
  String[] BtimeServicebegin1 = new String[MultiServer.customerSize];
  String[] BserviceTime1 = new String[MultiServer.customerSize];
  String[] serviceTime1 = new String[MultiServer.customerSize];
  String[] BtimeServiceEnd1 = new String[MultiServer.customerSize];
  String[] serviceEnd1 = new String[MultiServer.customerSize];
  String[] CtimeServicebegin1 = new String[MultiServer.customerSize];
  String[] CserviceTime1 = new String[MultiServer.customerSize];
  String[] CtimeServiceEnd1 = new String[MultiServer.customerSize];
  String[] timeInQueue1 = new String[MultiServer.customerSize];
  static double waitingaverage = 0.0;
  static double serviceaverage = 0.0;
  static int sum2 = 0;
  static double avA, avB, avC = 0;
  static double idle1, idle2, idle3 = 0;
  static int max = 0;

  static ArrayList<Double> avergaeTimeBusy = new ArrayList();
  static ArrayList<Double> avergaeTimeIdle = new ArrayList();

  JPanel p1, p2;
  JButton b1;
  JTable jt;

  public MultiServerTable() {
    b1 = new JButton("mm");
    p1 = new JPanel();
    p1.setLayout(new GridLayout(2, 1));
    p1.setBackground(Color.yellow);

    creat_table();
    jt = new JTable(data, column);
    jt.setBounds(30, 40, 200, 300);

    JScrollPane sp = new JScrollPane(jt);
    b1 = new JButton("NEXT");
    b1.addActionListener(this);
    p2 = new JPanel();

    p1.add(sp);
    p2.add(b1);
    p1.add(p2);
    this.add(p1);
  }

  public void omar() {
    for (int i = 0; i < 13; i++) {
      jt.getColumnModel().getColumn(i).setPreferredWidth(500);
    }
  }

  public void creat_table() {
    for (int i = 0; i < MultiServer.customerSize; i++) {
      column[0] = "C";
      column[1] = "Time Between Arrivals";
      column[2] = "Arrival Time";
      column[column.length - 1] = "Time in Queue";
      customer1[i] = Integer.toString(MultiServer.customer[i]);
      timeBetweenArrivals1[i] =
        Integer.toString(MultiServer.timeBetweenArrivals[i]);
      arrivalTime1[i] = Integer.toString(MultiServer.arrivalTime[i]);
      serviceTime1[i] = Integer.toString(MultiServer.serviceTime[i]);
      timeInQueue1[i] = Integer.toString(MultiServer.timeInQueue[i]);
    }

    for (int i = 3; i < (MultiServer.numberOfServers * 3) + 3; i++) {
      column[i] = "service " + Integer.toString(i / 3) + " begin";
      i++;

      i++;
    }

    for (int i = 4; i < (MultiServer.numberOfServers * 3) + 3; i++) {
      column[i] = "service time";

      i++;
      i++;
    }
    for (int i = 5; i < (MultiServer.numberOfServers * 3) + 3; i++) {
      column[i] = "service end";
      i++;
      i++;
    }

    for (int i = 0; i < MultiServer.customerSize; i++) {
      for (int j = 0; j < 1; j++) {
        data[i][j] = customer1[i];
        data[i][j + 1] = timeBetweenArrivals1[i];
        data[i][j + 2] = arrivalTime1[i];
      }
    }

    for (int k = 0; k < MultiServer.numberOfServers; k++) {
      for (int i = 3; i < (MultiServer.numberOfServers * 3) + 3; i++) {
        for (int j = 0; j < MultiServer.customerSize; j++) {
          data[j][i] = Integer.toString(MultiServer.servers.get(k).get(0)[j]);
        }
        i++;
        i++;

        k++;
      }
    }
    for (int k = 0; k < MultiServer.numberOfServers; k++) {
      for (int i = 5; i < (MultiServer.numberOfServers * 3) + 3; i++) {
        for (int j = 0; j < MultiServer.customerSize; j++) {
          data[j][i] = Integer.toString(MultiServer.servers.get(k).get(1)[j]);
        }
        i++;
        i++;
        k++;
      }
    }
    for (int k = 0; k < MultiServer.numberOfServers; k++) {
      for (int i = 4; i < (MultiServer.numberOfServers * 3) + 3; i++) {
        for (int j = 0; j < MultiServer.customerSize; j++) {
          data[j][i] = Integer.toString(MultiServer.servers.get(k).get(3)[j]);
        }
        i++;
        i++;
        k++;
      }
    }
    for (int i = 0; i < customerSize; i++) {
      data[i][column.length - 1] = timeInQueue1[i];
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == b1) {
      waitingaverage =
        MultiServer.totalTimeWaitInQueue / MultiServer.customerSize;

      for (int i = 0; i < totalServiceTime.size(); i++) {
        sum2 += MultiServer.totalServiceTime.get(i);
      }
      serviceaverage = sum2 / MultiServer.customerSize;

      for (int i = 0; i < MultiServer.numberOfServers; i++) {
        if (MultiServer.servers.get(i).get(2)[0] >= max) {
          max = MultiServer.servers.get(i).get(2)[0];
        }
      }

      for (int i = 0; i < totalServiceTime.size(); i++) {
        avergaeTimeBusy.add((MultiServer.totalServiceTime.get(i) / max) * 100);
      }
      for (int i = 0; i < totalServiceTime.size(); i++) {
        avergaeTimeIdle.add(100 - avergaeTimeBusy.get(i));
      }

      //            close();
      statistics m = new statistics();

      m.setSize(1650, 1080);
      m.setVisible(true);
      m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  }
}
