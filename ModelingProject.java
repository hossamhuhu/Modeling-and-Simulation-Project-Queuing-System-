package modelingproject;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ModelingProject {

  ModelingProject() {}

  public static void main(String[] args) {
    Welcome frame = new Welcome();
    frame.setSize(600, 500);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
}
