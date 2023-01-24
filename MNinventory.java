package modelingproject;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MNinventory extends JFrame implements ActionListener {

  JTextField cycleTF, daysTF, beginTF, orderQuantityTF;
  JLabel cycleLA, daysLA, beginLA, orderQuantityLA;
  JButton show, Back;
  JPanel p1, p2, p3, p4, p5, p6, pAll;
  JTextArea txarea1, txarea2;

  public MNinventory() {
    p1 = new JPanel();
    p2 = new JPanel();
    p3 = new JPanel();
    p4 = new JPanel();
    p5 = new JPanel();
    p6 = new JPanel();
    pAll = new JPanel();

    cycleLA = new JLabel("Cycles count");
    cycleTF = new JTextField(15);
    p1.add(cycleLA);
    p1.add(cycleTF);

    daysLA = new JLabel("Days count");
    daysTF = new JTextField(15);
    p2.add(daysLA);
    p2.add(daysTF);

    beginLA = new JLabel("Order begin");
    beginTF = new JTextField(15);
    p3.add(beginLA);
    p3.add(beginTF);

    orderQuantityLA = new JLabel("Order Quantity");
    orderQuantityTF = new JTextField(15);
    p4.add(orderQuantityLA);
    p4.add(orderQuantityTF);

    show = new JButton("Show");
    show.setActionCommand("show");
    show.addActionListener(this);
    Back = new JButton("Back");
    Back.setActionCommand("back");
    Back.addActionListener(this);
    p5.add(show);
    p5.add(Back);

    pAll.add(p1);
    pAll.add(p2);
    pAll.add(p3);
    pAll.add(p4);
    pAll.add(p5);

    txarea1 = new JTextArea();
    p6.add(txarea1);
    pAll.add(p6);
    add(pAll);
  }

  class Inventory {

    public int begin;
    public int random_widget;
    public int widget;
    public int ending;
    public int Back_ordering;
    public int Order_Quantity;
    public int Random_leadTime;
    public int daysUntilOrder;
  }

  public int numberOFcycle;
  public int numberOFdays;
  public int inventoryBegin;
  public int OrderQuantity;
  public int lead_day = 0;
  public int sumOFending = 0;
  public int c = 0, c2, back;
  public Inventory[][] in;

  class MN {

    public void simulate() {
      in = new Inventory[numberOFcycle][numberOFdays];
      Random random = new Random();
      for (int i = 0; i < numberOFcycle; i++) {
        for (int j = 0; j < numberOFdays; j++) {
          in[i][j] = new Inventory();
          int r = random.nextInt(100);
          int r2 = random.nextInt(100);
          in[i][j].random_widget = r;

          //--------------------------widget
          if (r >= 1 && r <= 33) {
            in[i][j].widget = 0;
          } else if (r >= 34 && r <= 58) {
            in[i][j].widget = 1;
          } else if (r >= 59 && r <= 78) {
            in[i][j].widget = 2;
          } else if (r >= 79 && r <= 90) {
            in[i][j].widget = 3;
          } else {
            in[i][j].widget = 4;
          }

          //-------------------begin
          if (i == 0 && j == 0) {
            in[i][j].begin = inventoryBegin;
          } else if (i != 0 && j == 0) {
            in[i][j].begin = in[i - 1][numberOFdays - 1].ending;
          } else {
            in[i][j].begin = in[i][j - 1].ending;
          }

          //------------------------leadTime & OrderQuantity
          if (j == numberOFdays - 1 && in[i][j].begin <= 6) {
            in[i][j].Order_Quantity = OrderQuantity;
            in[i][j].Random_leadTime = r2;
            if (r2 >= 1 && r2 <= 30) {
              in[i][j].daysUntilOrder = 1;
              lead_day = 1;
              c2 = 1;
              c = 0;
            } else if (r2 >= 31 && r2 <= 80) {
              in[i][j].daysUntilOrder = 2;
              lead_day = 2;
              c2 = 2;
              c = 0;
            } else if (r2 >= 81 && r2 <= 100) {
              in[i][j].daysUntilOrder = 3;
              lead_day = 3;
              c2 = 3;
              c = 0;
            }
          } else {
            in[i][j].daysUntilOrder = 0;
            in[i][j].Order_Quantity = 0;
          }

          if (lead_day != 0 && j >= 0) {
            in[i][j].daysUntilOrder = lead_day;
            lead_day--;
            c++;
          }

          if (c2 == 1) {
            if (c == c2 + 2) {
              in[i][j].begin = in[i][j].begin + OrderQuantity;
              in[i][j].ending = in[i][j].begin - in[i][j].widget;
              sumOFending += in[i][j].ending;
              c = 0;
              c2 = 0;
            } else {
              c++;
            }
          } else if (c2 == 2) {
            if (c == c2 + 3) {
              in[i][j].begin = in[i][j].begin + OrderQuantity;
              in[i][j].ending = in[i][j].begin - in[i][j].widget;
              sumOFending += in[i][j].ending;
              c = 0;
            } else {
              c++;
            }
          } else if (c2 == 3) {
            if (c == c2 + 4) {
              in[i][j].begin = in[i][j].begin + OrderQuantity;
              in[i][j].ending = in[i][j].begin - in[i][j].widget;
              sumOFending += in[i][j].ending;
              c = 0;
            } else {
              c++;
            }
          }

          //-----------------------------ending
          if (in[i][j].begin - in[i][j].widget >= 0 && in[i][j].begin != 0) {
            if (j == 0) {
              in[i][j].ending = in[i][j].begin - in[i][j].widget;
              sumOFending += in[i][j].ending;
              //                    in[i][j].Back_ordering = 0;
            } else {
              if (in[i][j - 1].Back_ordering != 0) {
                in[i][j].ending = in[i][j].begin - in[i][j].widget - back;
                sumOFending += in[i][j].ending;
              } else {
                in[i][j].ending = in[i][j].begin - in[i][j].widget;
                sumOFending += in[i][j].ending;
                in[i][j].Back_ordering = 0;
                back = 0;
              }
            }
          } else {
            if (i == 0 && j != 0) {
              in[i][j].Back_ordering =
                in[i][j - 1].Back_ordering + in[i][j].widget;
              back = in[i][j].Back_ordering;
            } else if (i != 0 && j == 0) {
              in[i][j].Back_ordering =
                in[i - 1][numberOFdays - 1].Back_ordering + in[i][j].widget;
              back = in[i][j].Back_ordering;
            } else if (i != 0 && j != 0) {
              in[i][j].Back_ordering =
                in[i][j - 1].Back_ordering + in[i][j].widget;
              back = in[i][j].Back_ordering;
            }
          }
        }
      }
    }

    public void print() {
      txarea1.setForeground(java.awt.Color.red);
      txarea1.setFont(new Font("", Font.BOLD, 20));
      //        txarea1.setEditable(false);
      txarea1.setText(
        "Cycle  " +
        "days  " +
        "RandomWidget  " +
        "Begin  " +
        "Widget  " +
        "Ending  " +
        "BackOrder  " +
        "OrderQuantity  " +
        "RandomTime  " +
        "DaysArrive" +
        '\n'
      );

      for (int i = 0; i < numberOFcycle; i++) {
        for (int j = 0; j < numberOFdays; j++) {
          txarea2 = new JTextArea();
          //                txarea2.setEditable(false);
          txarea2.setSize(200, 200);
          txarea2.setFont(new Font("", Font.ITALIC, 17));
          txarea2.setText(
            (i + 1) +
            "              " +
            (j + 1) +
            "                 " +
            in[i][j].random_widget +
            "                  " +
            in[i][j].begin +
            "             " +
            in[i][j].widget +
            "               " +
            in[i][j].ending +
            "                    " +
            in[i][j].Back_ordering +
            "                    " +
            in[i][j].Order_Quantity +
            "                    " +
            in[i][j].Random_leadTime +
            "                          " +
            in[i][j].daysUntilOrder +
            "          " +
            '\n'
          );
          pAll.add(txarea2);
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("show")) {
      numberOFcycle = Integer.parseInt(cycleTF.getText());
      numberOFdays = Integer.parseInt(daysTF.getText());
      inventoryBegin = Integer.parseInt(beginTF.getText());
      OrderQuantity = Integer.parseInt(orderQuantityTF.getText());
      System.out.println();
      MN m = new MN();
      m.simulate();
      m.print();
    } else if (e.getActionCommand().equals("back")) {
      this.setVisible(false);
      //        Actions a=new Actions();
      //        a.setVisible(true);
    }
  }
}
