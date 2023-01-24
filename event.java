package modelingproject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import static modelingproject.MultiServer.customerSize;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;
import static modelingproject.MultiServer.customerSize;
public class event extends JFrame implements ActionListener{
     Scanner sc=new Scanner(System.in);
     static float sum1=0;
     static float utilization=0;
     static int busy=0;
     static int totaltime=0;
   static int customers[];
    static int customerSize;
     static int interarrival[];
    static int servicetime[];
    static int arrivaltime[];
    static int departialtime[];
     static int servicebegin[];
    static int serviceend[];
    static int clock[];
    static String a[];
  static   String d[];
     static String total[];
    static int LQ[];
     static int LS[];
     static int endtime;
    static int B[];
    static int MQ[];
      static int S[];
     static int checkout[];
     static int lq;
Queue <Integer> q = new PriorityQueue();
  
    JLabel enter;
    JTextField txt1,txt2;
    ArrayList<JTextField> txt3 ,txt4 ;
    JPanel p,p1,p2 , p3;
    JTable table;
    JButton b1;
    JLabel l1,l2,l3;
    public event() {
        
         p1 = new JPanel();
      p = new JPanel();
      p2 = new JPanel();
      
      l1 = new JLabel();
      this.add(p1);
      p1.setLayout(new GridLayout(3,1));
      txt1 = new JTextField();
      txt1.setColumns(2);
      
      customerSize = (int) Integer.parseInt(EventInput.txt.getText());
        txt3 = new ArrayList();
         customers=new int [customerSize];
        interarrival=new int[customerSize];
        servicetime=new int [customerSize];
        arrivaltime=new int[customerSize];
        departialtime=new int[customerSize];
        servicebegin=new int[customerSize];
        serviceend=new int[customerSize];
        clock=new int[customerSize*2];
        a=new String[customerSize];
        d=new String[customerSize];
        total=new String[customerSize*2];
        LQ=new int[customerSize*2];
        LS=new int[customerSize*2];
        checkout = new int[customerSize*2];
    B=new int[customerSize*2];
    MQ=new int[customerSize*2];
    S=new int[customerSize*2];   
      p.setLayout(new GridLayout((customerSize),1));
      
      for(int i =0; i <customerSize;i++){
      l2 = new JLabel("please enter time between arrival between customer "+(i+1)+" and customer "+(i)+" :");
      l2.setFont(new Font("Arial", Font.BOLD, 16));
      l2.setForeground(new Color(0,97,169));
      p.add(l2);
      p.setLayout(new FlowLayout());
      p.setBackground(Color.white);   
      txt2 = new JTextField();
      txt2.setColumns(5);
      txt3.add(txt2);
      p.add(txt3.get(i));
      }
      
      p2.setLayout(new GridLayout((customerSize)+1,1));
      txt4 = new ArrayList();
      for(int i =0; i <customerSize;i++){
      l2 = new JLabel("please enter service time of customer :"+(i+1));
      l2.setFont(new Font("Arial", Font.BOLD, 16));
      l2.setForeground(new Color(0,97,169));
      p2.add(l2);
      p2.setBackground(Color.white);
      p2.setLayout(new FlowLayout());
      txt2 = new JTextField();
      txt2.setColumns(5);
      txt4.add(txt2);
      p2.add(txt4.get(i));
      }
     p3 = new JPanel();
      
      b1 = new JButton("Next");
      b1.setPreferredSize(new Dimension(150, 40));
      p3.add(b1);
      p3.setBackground(Color.white);
      b1.addActionListener(this);
      p1.add(p);
      p1.add(p2);
      p1.add(p3);
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==b1)
        {
             for(int i =0; i<customerSize;i++){
            servicetime[i]=(Integer.parseInt(txt4.get(i).getText()));
            }
            for ( int i =0 ; i<txt3.size();i++){
//            timeBetweenArrivals.add(Integer.parseInt(txt3.get(i).getText()));
              interarrival[i] = (Integer.parseInt(txt3.get(i).getText()));
            }
            for(int i=0;i<customerSize;i++)
    {
        if(i==0)
        {
         arrivaltime[i]=0;
        }
        else
        {
          arrivaltime[i]=interarrival[i]+arrivaltime[i-1];
        }
    }
        for(int i=0;i<customerSize;i++)
        {
        if(i==0)
        {
         servicebegin[i]=0;
         
         serviceend[i]=servicebegin[i]+servicetime[i];
         arrivaltime[i]=0;
        }
         else
        {
          if(arrivaltime[i]>=serviceend[i-1]){
         servicebegin[i]=arrivaltime[i];
         }
         else if(arrivaltime[i]<serviceend[i-1]){
         servicebegin[i]=serviceend[i-1];
         }

         serviceend[i]=servicebegin[i]+servicetime[i];
          }
        }
        for(int i=0;i<customerSize;i++)
        {
            clock[i]=arrivaltime[i];
        }
 
         for(int i =0;i<customerSize;i++)
         {
         clock[i+customerSize]=serviceend[i];
         }
         Arrays.sort(clock);
 
        }
         for(int i=0 ; i<customerSize;i++)
         {
          for(int j =0;j<customerSize;j++)
         {        
         if(clock[i]==arrivaltime[j])
         {
             a[i]="A";
             total[i]=a[i];
         }
         if(clock[i]==serviceend[j])
         {
             d[i]="D";
             total[i]=d[i];
           }
         }   
         }
         for(int i =0;i<customerSize;i++)
         {  
         for(int j =0;j<customerSize;j++)
         {
         if(clock[i+customerSize]==serviceend[j])
         {
         d[i]="D";
         total[i+customerSize]=d[i];
         }
         if (clock[i+customerSize]==arrivaltime[j])
         {
         a[i]="A";
         total[i+customerSize]=a[i];
         }
         }
         }
        for(int i=0;i<(customerSize*2);i++)
          {
           if(i==0){
            LQ[i] = 0;
           
           } 
           else if(i==(customerSize*2)-1){
           LQ[i] = 0; 
           }
           
           else{
           if(total[i]=="A"){
               LQ[i]=LQ[i-1]+1;

           }
           else if(total[i]=="D"){
               LQ[i]=LQ[i-1]-1;
           }
            if (LQ[i]<0){
               LQ[i]=0;
           }
           }
          }
      int counter=0;   
 for (int i =0;i<customerSize*2;i++){
     if(i==0){
     counter++;
     LS[i]=counter;
     }
     else if(total[i]=="A"){
     counter++;
     LS[i]=counter;
     }
     else if(total[i]=="D"){
     counter--;
     LS[i]=counter;
     }
 }
 for(int i =0; i<customerSize*2;i++)
 {
 if(LS[i]>=1){
 LS[i]=1;
 }
 }
 Queue <Integer> q = new PriorityQueue();
  int D =0;
  checkout[0]=D;
  for(int i =0 ; i< customerSize*2;i++)
  {
  if(total[i]=="A"){
  D++;
  q.add(D);
  checkout[i]= q.peek();
  }
  else if(total[i]=="D")
  {
  q.remove();
  if(q.peek()==null){
  checkout[i]=0;
  }
  else
  {
  checkout[i]=q.peek();
  }
  }
 }
  
  int h = 0;//counter
  int o =0;//counter
  for(int i = 0; i< customerSize*2;i++){
      if(i==0)
      {
      B[i]=h;
      }
      if(total[i]=="A")
      {
          B[i]=h;
      }
       else if(total[i]=="D")
       {
        h+= servicetime[o];   
        B[i]=h;
        o++;
       }
  }
  int index=0;
  int w;
for(int i=0;i<(customerSize*2);i++)
{ w=LQ[i];

if(w>index)
{
index =w;
MQ[i]=index;
}
else
{
MQ[i]=index;
}
}
int n = 0;//counter
 int a =0;//counter
  for(int i = 0; i< customerSize*2;i++){
      if(i==0)
      {
      S[i]=0;
      }
      else if(total[i]=="A"&&total[i-1]=="D"){
              S[i]=S[i-1];
              a++;
              }
      
      else{
        n=h;
        h= (clock[a+1]-clock[a]);   
        S[i]=h+S[i-1];
        a++;
      }
      if(S[i]==0&&i>0){
          S[i]=S[i-1];
      }
  }
            eventtable m=new eventtable();
            m.setSize(600,600);
            m.setVisible(true);
            m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        }
      }