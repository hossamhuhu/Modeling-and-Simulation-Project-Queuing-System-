package modelingproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MultiServer extends JFrame implements ActionListener {

  Scanner sc = new Scanner(System.in);
  static int customerSize;
  JLabel enter;
  JTextField txt1, txt2;
  ArrayList<JTextField> txt3, txt4;
  JPanel p, p1, p2, p3;
  JTable table;
  JButton b1;
  static int numberOfServers = 0;

  static int max = 0;

  static double waitingaverage = 0;
  static float serviceaverage = 0;

  static int[] customer;
  static int[] timeBetweenArrivals;
  static int[] arrivalTime;
  static int[] serviceTime;

  static int[] timeInQueue;
  int counter = 0;
  int min = 10000;
  int index = 0;
  static double totalServiceTime1 = 0;
  static ArrayList<ArrayList<int[]>> servers = new ArrayList();
  static ArrayList<Double> totalServiceTime = new ArrayList();
  static ArrayList<Double> avergaeTimeBusy = new ArrayList();
  static ArrayList<Double> avergaeTimeIdle = new ArrayList();

  static int endOfServerA, endOfServerB, endOfServerC;
  static int totalServiceTimeA, totalServiceTimeB, totalServiceTimeC;
  static double totalTimeWaitInQueue;
  static int avWaiting;
  JLabel l1, l2, l3, l4;

  public MultiServer() {
    numberOfServers = Integer.parseInt(MultiServerInput.txt2.getText());

    p1 = new JPanel();
    p = new JPanel();
    p2 = new JPanel();
    l1 = new JLabel();
    l1.setFont(new Font("Arial", Font.BOLD, 16));
    l1.setForeground(new Color(0, 97, 169));
    this.add(p1);
    p1.setLayout(new GridLayout(3, 1));
    txt1 = new JTextField();
    txt1.setColumns(2);
    customerSize = (int) Integer.parseInt(MultiServerInput.txt.getText());
    customer = new int[customerSize];
    timeBetweenArrivals = new int[customerSize];
    arrivalTime = new int[customerSize];
    serviceTime = new int[customerSize];
    timeInQueue = new int[customerSize];
    txt3 = new ArrayList();
    p.setLayout(new GridLayout((customerSize), 1));
    for (int i = 0; i < customerSize; i++) {
      l2 =
        new JLabel(
          "please enter time between arrival between customer " +
          (i + 1) +
          " and customer " +
          (i) +
          " :"
        );
      l2.setFont(new Font("Arial", Font.BOLD, 16));
      l2.setForeground(new Color(0, 97, 169));
      p.add(l2);
      p.setLayout(new FlowLayout());
      p.setBackground(Color.white);
      txt2 = new JTextField();
      txt2.setColumns(5);
      txt3.add(txt2);
      p.add(txt3.get(i));
    }
    p2.setLayout(new GridLayout((customerSize) + 1, 1));
    txt4 = new ArrayList();
    for (int i = 0; i < customerSize; i++) {
      l2 = new JLabel("please enter service time of customer :" + (i + 1));
      l2.setFont(new Font("Arial", Font.BOLD, 16));
      l2.setForeground(new Color(0, 97, 169));
      p2.add(l2);
      p2.setBackground(Color.white);
      p2.setLayout(new FlowLayout());
      txt2 = new JTextField();
      txt2.setColumns(5);
      txt4.add(txt2);
      p2.add(txt4.get(i));
    }

    p3 = new JPanel();
    p3.setBackground(Color.white);
    b1 = new JButton("Next");
    b1.setPreferredSize(new Dimension(150, 40));
    p3.add(b1);
    b1.addActionListener(this);
    JPanel p4 = new JPanel();
    l4 = new JLabel("average waiting time:" + MultiServer.waitingaverage);
    p4.add(l4);
    p1.add(p);
    p1.add(p2);
    p1.add(p3);
  }

  public void close() {
    WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
  }

  public void customer() {
    for (int i = 0; i < customerSize; i++) {
      customer[i] = i + 1;
    }
  }

  public void timeBetweenArrivals() {
    for (int i = 0; i < txt3.size(); i++) {
      //            timeBetweenArrivals.add(Integer.parseInt(txt3.get(i).getText()));
      timeBetweenArrivals[i] = (Integer.parseInt(txt3.get(i).getText()));
    }
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    Object o = ae.getSource();
    if (o == b1) {
      //customer column
      customer();

      //Time between arrivals column
      timeBetweenArrivals();

      //Service time
      for (int i = 0; i < customerSize; i++) {
        serviceTime[i] = (Integer.parseInt(txt4.get(i).getText()));
      }
      //arrival time coloumn
      for (int i = 0; i < arrivalTime.length; i++) {
        if (i - 1 == -1) {
          arrivalTime[i] = 0;
        } else {
          arrivalTime[i] = timeBetweenArrivals[i] + arrivalTime[i - 1];
        }
      }

      for (int i = 0; i < numberOfServers; i++) {
        ArrayList<int[]> s = new ArrayList();
        int[] serviceBegin = new int[customerSize];
        int[] serviceEnd1 = new int[customerSize];
        int[] serviceEnd = new int[numberOfServers];
        int[] serviceTimes = new int[customerSize];

        s.add(serviceBegin);
        s.add(serviceEnd1);
        s.add(serviceEnd);
        s.add(serviceTimes);
        servers.add(s);
      }

      //servers

      for (int i = 0; i < customerSize; i++) {
        if (i == 0) {
          servers.get(0).get(0)[0] = arrivalTime[i];
          servers.get(0).get(1)[0] = servers.get(i).get(0)[0] + serviceTime[i];
          servers.get(0).get(2)[0] = servers.get(i).get(1)[i];
          servers.get(0).get(3)[0] = servers.get(0).get(2)[0];
          timeInQueue[i] = servers.get(0).get(0)[0] - arrivalTime[i];
        } else {
          for (int j = 0; j < numberOfServers; j++) {
            if (arrivalTime[i] >= servers.get(j).get(2)[0]) {
              servers.get(j).get(0)[i] = arrivalTime[i];
              servers.get(j).get(1)[i] =
                servers.get(j).get(0)[i] + serviceTime[i];
              servers.get(j).get(2)[0] = servers.get(j).get(1)[i];
              servers.get(j).get(3)[i] = serviceTime[i];
              timeInQueue[i] = servers.get(j).get(0)[i] - arrivalTime[i];

              break;
            }
            counter++;
          }
          if (counter == numberOfServers) {
            for (int k = 0; k < numberOfServers; k++) {
              if (min > servers.get(k).get(2)[0]) {
                min = servers.get(k).get(2)[0];
                index = k;
              }
            }

            servers.get(index).get(0)[i] = servers.get(index).get(2)[0];
            servers.get(index).get(1)[i] =
              servers.get(index).get(0)[i] + serviceTime[i];
            servers.get(index).get(2)[0] = servers.get(index).get(1)[i];
            servers.get(0).get(3)[i] = serviceTime[i];
            timeInQueue[i] = servers.get(index).get(0)[i] - arrivalTime[i];
          }
          counter = 0;
        }
      }

      for (int i = 0; i < customerSize; i++) {
        totalTimeWaitInQueue += timeInQueue[i];
      }
      for (int k = 0; k < numberOfServers; k++) {
        for (int i = 0; i < customerSize; i++) {
          totalServiceTime1 += servers.get(k).get(3)[i];
        }
        totalServiceTime.add(totalServiceTime1);
        totalServiceTime1 = 0;
      }
      close();
      MultiServerTable m = new MultiServerTable();

      m.setSize(1650, 1080);
      m.setVisible(true);
      m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  }
}
