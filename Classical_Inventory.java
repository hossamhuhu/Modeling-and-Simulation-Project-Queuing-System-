package modelingproject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Classical_Inventory extends JFrame implements ActionListener {

  JTextField costTF, sellTF, LengthTF, numberOfNewsPapersTF, scrapTF;
  JButton show, back;
  JPanel p1, p2, p3, p4, p5, pAll;
  JLabel costLA, sellLA, LengthLA, numberOfNewsPapersLA, scrapLA;
  JTextArea txArea1, txArea2, txArea3;
  static int simulationLength, cost, sell, numberOfNewsPapers, scrap, lostProfit;

  public Classical_Inventory() {
    p1 = new JPanel();
    p2 = new JPanel();
    p3 = new JPanel();
    p4 = new JPanel();
    p5 = new JPanel();
    pAll = new JPanel();

    costLA = new JLabel("Cost");
    costTF = new JTextField(15);
    p1.add(costLA);
    p1.add(costTF);

    sellLA = new JLabel("Sell");
    sellTF = new JTextField(15);
    p2.add(sellLA);
    p2.add(sellTF);

    LengthLA = new JLabel("Length");
    LengthTF = new JTextField(15);
    p3.add(LengthLA);
    p3.add(LengthTF);

    numberOfNewsPapersLA = new JLabel("number Of goods");
    numberOfNewsPapersTF = new JTextField(15);
    p4.add(numberOfNewsPapersLA);
    p4.add(numberOfNewsPapersTF);

    scrapLA = new JLabel("scrap");
    scrapTF = new JTextField(15);
    p5.add(scrapLA);
    p5.add(scrapTF);

    show = new JButton("Show");
    show.setActionCommand("show");
    show.addActionListener(this);

    back = new JButton("Back");
    back.setActionCommand("back");
    back.addActionListener(this);

    txArea1 = new JTextArea();

    pAll.add(p1);
    pAll.add(p2);
    pAll.add(p3);
    pAll.add(p4);
    pAll.add(p5);
    pAll.add(show);
    pAll.add(back);
    pAll.add(txArea1);
    add(pAll);
  }

  class Days {

    public String dayType;
    public int demand;
    public int rDemand;
    public int rType;
    public double revenue;
    public double myLost;
    public double myScrap;
    public double dayProfit;
  }

  public class MSAssignment3 {

    public Random random = new Random();
    public Days[] day;
    public double totalProfit = 0;
    public double totalScrap = 0;
    public double totalLost = 0;
    public double totalRevenue = 0;

    private void createRandomDigits() {
      for (int i = 0; i < simulationLength; i++) {
        day[i] = new Days();
        day[i].rDemand = random.nextInt(100) + 1;
        day[i].rType = random.nextInt(100) + 1;
      }
    }

    private void setDemand() {
      for (int i = 0; i < simulationLength; i++) {
        switch (day[i].dayType) {
          case "Good":
            if (day[i].rDemand >= 1 && day[i].rDemand <= 3) {
              day[i].demand = 40;
            } else if (day[i].rDemand >= 4 && day[i].rDemand <= 8) {
              day[i].demand = 50;
            } else if (day[i].rDemand >= 9 && day[i].rDemand <= 23) {
              day[i].demand = 60;
            } else if (day[i].rDemand >= 24 && day[i].rDemand <= 43) {
              day[i].demand = 70;
            } else if (day[i].rDemand >= 44 && day[i].rDemand <= 78) {
              day[i].demand = 80;
            } else if (day[i].rDemand >= 79 && day[i].rDemand <= 93) {
              day[i].demand = 90;
            } else {
              day[i].demand = 100;
            }
            break;
          case "Fair":
            if (day[i].rDemand >= 1 && day[i].rDemand <= 10) {
              day[i].demand = 40;
            } else if (day[i].rDemand >= 11 && day[i].rDemand <= 28) {
              day[i].demand = 50;
            } else if (day[i].rDemand >= 29 && day[i].rDemand <= 68) {
              day[i].demand = 60;
            } else if (day[i].rDemand >= 69 && day[i].rDemand <= 88) {
              day[i].demand = 70;
            } else if (day[i].rDemand >= 89 && day[i].rDemand <= 96) {
              day[i].demand = 80;
            } else {
              day[i].demand = 90;
            }
            break;
          case "Poor":
            if (day[i].rDemand >= 1 && day[i].rDemand <= 44) {
              day[i].demand = 40;
            } else if (day[i].rDemand >= 45 && day[i].rDemand <= 66) {
              day[i].demand = 50;
            } else if (day[i].rDemand >= 67 && day[i].rDemand <= 82) {
              day[i].demand = 60;
            } else if (day[i].rDemand >= 83 && day[i].rDemand <= 94) {
              day[i].demand = 70;
            } else {
              day[i].demand = 80;
            }
            break;
          default:
            throw new AssertionError();
        }
      }
    }

    private void setType() {
      for (int i = 0; i < simulationLength; i++) {
        if (day[i].rType >= 1 && day[i].rType <= 35) {
          day[i].dayType = "Good";
        } else if (day[i].rType >= 36 && day[i].rType <= 80) {
          day[i].dayType = "Fair";
        } else {
          day[i].dayType = "Poor";
        }
      }
    }

    private void simulate() {
      day = new Days[simulationLength];
      double costOfNewsPaper = (numberOfNewsPapers * cost) / 100;
      createRandomDigits();
      setType();
      setDemand();
      for (int i = 0; i < simulationLength; i++) {
        if (day[i].demand <= numberOfNewsPapers) {
          day[i].myLost = 0;
          day[i].revenue = (day[i].demand * sell) / 100.0;
          day[i].myScrap =
            ((numberOfNewsPapers - day[i].demand) * scrap) / 100.0;
        } else {
          day[i].myLost =
            (day[i].demand - numberOfNewsPapers) * lostProfit / 100.0;
          day[i].revenue = (numberOfNewsPapers * sell) / 100.0;
          day[i].myScrap = 0;
        }
        day[i].dayProfit =
          day[i].revenue - costOfNewsPaper - day[i].myLost + day[i].myScrap;
        totalProfit += day[i].dayProfit;
        totalLost += day[i].myLost;
        totalRevenue += day[i].revenue;
        totalScrap += day[i].myScrap;
      }
    }

    private void printTable() {
      txArea1.setForeground(java.awt.Color.red);
      txArea1.setFont(new Font("", Font.BOLD, 13));
      //        txArea1.setEditable(false);
      txArea1.setText(
        "DAY#" +
        "\t" +
        "RDT" +
        "\t" +
        "TYPE" +
        "\t" +
        "RDD" +
        "\t" +
        "DEMAND" +
        "\t" +
        "REVENUE" +
        "\t" +
        "LOST" +
        "\t" +
        "SCRAP" +
        "\t" +
        "PROFIT" +
        "\t"
      );

      for (int i = 0; i < simulationLength; i++) {
        txArea2 = new JTextArea();
        txArea2.setForeground(java.awt.Color.red);
        txArea2.setFont(new Font("", Font.ITALIC, 15));
        txArea2.setSize(200, 200);
        //        txArea2.setEditable(false);
        txArea2.setText(
          "DAY" +
          (i + 1) +
          "\t" +
          day[i].rType +
          "\t" +
          day[i].dayType +
          "\t" +
          day[i].rDemand +
          "\t" +
          day[i].demand +
          "\t" +
          day[i].revenue +
          "\t" +
          day[i].myLost +
          "\t" +
          day[i].myScrap +
          "\t" +
          day[i].dayProfit +
          "\t"
        );

        pAll.add(txArea2);
      }
      txArea3 = new JTextArea();
      txArea3.setText("\n" + "Total Profit = " + totalProfit + "\n");
      pAll.add(txArea3);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("show")) {
      simulationLength = Integer.parseInt(LengthTF.getText());
      scrap = Integer.parseInt(scrapTF.getText());
      cost = Integer.parseInt(costTF.getText());
      sell = Integer.parseInt(sellTF.getText());
      numberOfNewsPapers = Integer.parseInt(numberOfNewsPapersTF.getText());
      lostProfit = sell - cost;
      MSAssignment3 m = new MSAssignment3();
      m.simulate();
      m.printTable();
    } else if (e.getActionCommand().equals("back")) {
      this.setVisible(false);
    }
  }
}
