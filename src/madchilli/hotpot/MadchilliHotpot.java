//right click close table
package madchilli.hotpot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @author Jiaming Zhang
 */
public class MadchilliHotpot extends JFrame implements Runnable {
    private Image dbI;
    private Graphics dbg;
    int screen_size_x = 1080, screen_size_y = 700;
    int addr = 0;
    boolean main = true;
    //======================================================================PRICE$
    int basicfee = 600; //jim4
    int weekend = 2499;
    int all_eat = 250;
    int softbeverage = 299;
    int drink = 499;
    int others = 100;
    int price = 0;//change to system time
    //【0】锅底 
    //【0】人均 + 【1】饮料1 【2】饮料2 【3】饮料3 【4】饮料4 *数量 【5-8】费用*
    int[][] A1 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][] A2 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][] A3 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][] A4 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][] A5 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][] A6 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][] B1 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][] B2 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][] B3 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][] B4 = {{0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}, {0,0}};
    int[][][] tables = {A1, B1, A2, B2, A3, B3, A4, B4, A5, A6};
    int cur = 0;//back to Mainmenu; which is to close the current open table
    int cursit = 0;
    int ppx = 0, ppy = 0;
    boolean unset=false;
    int[] bills = {0,0,0,0,0,0,0,0};
    int[][] extra = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou0 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou1 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou2 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou3 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou4 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou5 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou6 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou7 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou8 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][] cou9 = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    int[][][] count = {cou0, cou1, cou2, cou3, cou4, cou5, cou6, cou7, cou8, cou9};
    int[] drinkfee = {125, 125, 125, 125, 125, 125, 125, 125};//jim1
    boolean[][] table_open = {{false,false},{false,false},{false,false},
    {false,false},{false,false},{false,false},{false,false},{false,false},
    {false,false},{false,false},};//first for open table second for open frame
    //sit_open: [0] closed = false;
    //          [1] opened = true;
    //          [2] paid   = 3;
    int[][] sit_open = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
    {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
    //======================================================================TABLE
    int tabx = 300, taby = screen_size_y/8, ini = tabx/6, gap = screen_size_y/35;
    int hence_x = 0, hence_y = 0, hence_l = 0, hence_h = 0;
    int hence_x1 = 0, hence_y1 = 0, hence_l1 = 0, hence_h1 = 0;
    int hence_x2 = 0, hence_y2 = 0, hence_l2 = 0, hence_h2 = 0;
    String[] state = {"CLOSE","CLOSE","CLOSE","CLOSE","CLOSE","CLOSE","CLOSE",
                      "CLOSE","CLOSE","CLOSE"};
    int[][] table_size = {{ini,0}, {ini*3 + tabx,0}, 
                          {ini,0}, {ini*3 + tabx,0}, 
                          {ini,0}, {ini*3 + tabx,0},
                          {ini,0}, {ini*3 + tabx,0},
                          {ini,0},
                          {ini,0},};
    //==========================================================================SET SIT
    int sit_size_x = screen_size_x/6, sit_size_y = screen_size_y/4;
    int sit_gap_x = screen_size_x/42, sit_gap_y = screen_size_y/20;
    int[][] sit_size = {{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
    //==========================================================================SET DRINK_BAR
    int drink_size_x = sit_size_x*2/3, drink_size_y = screen_size_y/10;
    String[] drinks = {"任食","蘸料","可乐","雪碧","冰红茶","王老吉","ARIZONA","EXTRA"};//jim3
    int drink_gap_y = drink_size_y/8;
    int[][] drink_size = {{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
    //==========================================================================total select
    boolean[] in = {false,false,false,false,false,false,false,false};
    int tot[] = {240, 320};
    int tot_x = 210, tot_y = 70;
    int totsel = 0;
    //==========================================================================BUTTONS
    int back_size[] = {screen_size_x/5*3, screen_size_y/2+50};
    int reset_size[] = {945, 640};
    int add_size[] = {screen_size_x/5*3, screen_size_y/2+10};
    int back_x = 105, back_y = 35;
    int reset_x = 105, reset_y = 35;
    int add_x = 105, add_y = 35;
    int added = 0;
    //==========================================================================HELP
    String[] special_str = {"主界面下：",
                            "       左键： 选择桌号",
                            "DISCOUNT： ",
                            "       左键折扣下降",
                            "       右键折扣上升",
                            "",
                            "单桌界面：", 
                            "       左键： 增加/选择座位",
                            "       右键： 减少/改变座位状态",
                            "       右键两次： 删除座位",
                            "ADD： ",
                            "       计算选中单位总和",
                            "PAID： ",
                            "       已付款（状态）",
                            "       不会改变单桌总价",
                            "",
                            "", "", ""};
    //==========================================================================DISCOUNT
    String[] discount_display = {"免单","三折","五折","六折","六六折","七折",
        "七五折","八折","八五折","八八折","九折","九五折","九八折","九九折","无"};
    int[] discount_percentage = {0, 30, 50, 60, 66, 70, 75, 80, 
        85,88, 90, 95, 98, 99, 100};
    int discount_select = discount_display.length - 1;
    int[] discount_hence = {820, 550};
    int discount_x = 95, discount_y = 35;
    boolean discount_if_hence = false;
    public MadchilliHotpot() {
        //======================================================================SET SCREEN
        this.setTitle("Madchilli Hotpot");
        this.setSize(screen_size_x, screen_size_y);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        this.addMouseListener(new ML());
        this.addMouseMotionListener(new ML());
    }
    
    public static void main(String[] args) {
        MadchilliHotpot screen = new MadchilliHotpot();
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void paint(Graphics g) {
        dbI=createImage(getWidth(), getHeight());
        dbg=dbI.getGraphics();
        if(main){
            Mainmenu(dbg);
        } else {
            Checkout(dbg);
        }
        g.drawImage(dbI, 0, 0, this);
    }
    
    private void Mainmenu(Graphics g) {
        //g.drawString(ppx+" "+ppy, ppx, ppy);
        addr = 1;
        added = 0;
        int str_x = 450, str_y = screen_size_y-125;
        g.setColor(Color.gray);
        g.drawRoundRect(str_x-20, str_y-50, 350, 75, 100, 100);
        g.setFont(new Font("Ariel", Font.BOLD, 40));
        g.setColor(Color.red);
        g.drawString("Madchilli Hotpot", str_x, str_y);
        for (int i = 0; i < 8; i++) {
            for (int j = 1; j < 9; j++) {
                tables[i][j][0] = drinkfee[j-1];
            }
        }
        
        //set week of day -jim2
        Calendar today = Calendar.getInstance();
        int week = today.get(Calendar.DAY_OF_WEEK);
        if (week == 1 || week >= 6) {
            price = 2499;
        } else {
            price = 2299;
        }
        for (int i = 0; i < tables.length; i++) {
            tables[i][0][0] = price;
        }
        g.setColor(Color.red);
        g.fillRect(hence_x, hence_y, hence_l, hence_h);
        
        for (int i = 0, count = 0; i < 6; ++i) {
            if(table_open[count][0]){
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.gray);
            }
            table_size[count][1] = 50 + 20*i + taby*i;
            g.fillRect(table_size[count][0], table_size[count][1], tabx, taby);
            g.setColor(Color.LIGHT_GRAY);
            g.drawString(state[count], 115, 105 + 20*i + taby *i);
            count++;
            if (i < 4) {
                if(table_open[count][0]){
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.gray);
            }
                table_size[count][1] = 50 + 20*i + taby*i;
                g.fillRect(table_size[count][0],table_size[count][1],tabx,taby);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString(state[count], 215 + tabx, 105 + 20*i + taby *i);
                count++;
            }
        }
        g.setColor(Color.gray);
        g.setFont(new Font("Ariel", Font.ITALIC, 40));
        g.drawString("HELP", 50*4 + tabx*2, 100);
        g.setColor(Color.gray);
        g.setFont(new Font("Ariel", Font.BOLD, 16));
        for (int i = 0; i <= 15; i++) {
            g.drawString(special_str[i], 50*4 + 20 + tabx*2, 130+20*i);
        }
        
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(reset_size[0], reset_size[1], reset_x, reset_y, 10, 10);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ariel", Font.BOLD, 20));
        g.drawString("reset", reset_size[0]+25, reset_size[1]+25);
        
        g.setColor(Color.ORANGE);
        g.drawRoundRect(50*4 + tabx*2, 100, 250, 350, 75, 75);
        
        //discount for all
        
        g.setColor(Color.black);
        g.setFont(new Font("Ariel", Font.BOLD, 20));
        g.drawString("全天折扣：", 825, 575);
        g.drawString(discount_display[discount_select], 975, 575);
        if (discount_if_hence) {
            g.drawRect(discount_hence[0], discount_hence[1], discount_x, discount_y);
        }
        repaint();
    }
    
    private void Checkout (Graphics g) {
        addr = 2;
        int seats = tables[cur][0][1];
        if (seats != 0) {
            for (int i = 0; i < bills.length; i++) {
                int base_fee = basicfee/seats;
                if (sit_open[cur][i]==1) {
                    bills[i]=base_fee+price+extra[cur][i];
                }
            }
        }
        
        //close table 
        if (seats == 0) {
            table_open[cur][0] = false;
        }
        
        if (table_open[cur][0]) {
            state[cur] = "OPEN";//change place state open if table people > 0
        } else {
            state[cur] = "CLOSE";
        }

        //g.drawString(ppx+" "+ppy, ppx, ppy);
        g.setColor(Color.red);
        g.fillRect(hence_x1, hence_y1, hence_l1, hence_h1);
        g.fillRect(hence_x2, hence_y2, hence_l2, hence_h2);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ariel", Font.BOLD, 20));
        int total = (Bill(tables[cur],false)*100-Bill(tables[cur], false)*100%1)/100;
        g.drawString("TOTAL:"+total/100+"."+total%100, screen_size_x/5*3, screen_size_y/2);
        
        for (int i = 0; i < sit_size.length/2; i++) {
            sit_size[i][0] = sit_gap_x*(i+1) + sit_size_x*i;
            sit_size[i][1] = sit_gap_y;
            if (sit_open[cur][i]==1) {
                g.setColor(Color.white);
            } else if (sit_open[cur][i]==2) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(Color.gray);
            }
            g.fillRect(sit_size[i][0], sit_size[i][1], sit_size_x, sit_size_y);
            g.setColor(Color.LIGHT_GRAY);
            
            
            int j = i+sit_size.length/2;
            sit_size[j][0] = sit_gap_x*(i+1) + sit_size_x*i;
            sit_size[j][1] = screen_size_y - sit_gap_y - sit_size_y;
            if (sit_open[cur][j]==1) {
                g.setColor(Color.white);
            } else if (sit_open[cur][j]==2) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(Color.gray);
            }
            g.fillRect(sit_size[j][0], sit_size[j][1], sit_size_x, sit_size_y);
            g.setColor(Color.LIGHT_GRAY);
            
            
            
            
            if (sit_open[cur][i]==1) {
                g.setColor(Color.red);
                int result = bills[i]*113/100*discount_percentage[discount_select]/100;
                g.setFont(new Font("Ariel", Font.CENTER_BASELINE, 20));
                g.drawString("$"+result/100+"."+result%100, sit_size[i][0]+sit_size_x/3, sit_size[i][1]+sit_size_y/2);
                if (in[i]) {
                    g.setColor(Color.gray);
                    g.setFont(new Font("Ariel", Font.CENTER_BASELINE, 20));
                    g.drawString("added", sit_size[i][0]+sit_size_x/3, sit_size[i][1]+sit_size_y/2+50);
                }
            } else if (sit_open[cur][i]==2) {
                g.setColor(Color.black);
                g.setFont(new Font("Ariel", Font.CENTER_BASELINE, 20));
                g.drawString("PAID", sit_size[i][0]+sit_size_x/3, sit_size[i][1]+sit_size_y/2);
            }
            
            if (sit_open[cur][j]==1) {
                g.setColor(Color.red);
                int result = bills[j]*113/100*discount_percentage[discount_select]/100;
                g.setFont(new Font("Ariel", Font.CENTER_BASELINE, 20));
                g.drawString("$"+result/100+"."+result%100, sit_size[j][0]+sit_size_x/3, sit_size[j][1]+sit_size_y/2);
                if (in[j]) {
                    g.setColor(Color.gray);
                    g.setFont(new Font("Ariel", Font.CENTER_BASELINE, 20));
                    g.drawString("added", sit_size[j][0]+sit_size_x/3, sit_size[j][1]+sit_size_y/2+50);
                }
            } else if (sit_open[cur][j]==2) {
                g.setColor(Color.black);
                g.setFont(new Font("Ariel", Font.CENTER_BASELINE, 20));
                g.drawString("PAID", sit_size[j][0]+sit_size_x/3, sit_size[j][1]+sit_size_y/2);
            }
        }
        
        for (int i = 0; i < drink_size.length; i++) {
            drink_size[i][0] = screen_size_x - sit_gap_x - drink_size_x;
            drink_size[i][1] = drink_size_y*2/3 + drink_gap_y*i + drink_size_y*i;
            if(count[cur][cursit][i]==0){
                g.setColor(Color.white);
            } else {
                g.setColor(Color.pink);
            }
            g.fillRect(drink_size[i][0], drink_size[i][1], drink_size_x, drink_size_y);
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Ariel", Font.BOLD, 20));
            g.drawString(" ("+count[cur][cursit][i]+")"+drinks[i], 932, 93 + 7*i + drink_size_y *i);
        }
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(back_size[0], back_size[1], back_x, back_y, 10, 10);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ariel", Font.BOLD, 20));
        g.drawString("BACK", back_size[0]+25, back_size[1]+25);
        
        if (added==1) {
            g.setColor(Color.BLACK);
            g.drawRect(tot[0], tot[1], tot_x, tot_y);
            g.setColor(Color.white);
            g.fillRect(tot[0], tot[1], tot_x, tot_y);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Ariel", Font.BOLD, 20));
            int result = totsel/100;
            g.drawString("CURRENT: $:"+result/100+"."+result%100, screen_size_x/4-10, screen_size_y/2+10);
            g.setColor(Color.red);
        } else {
            totsel=0;
            for (int i = 0; i < in.length; i++) {
                in[i] = false;
            }
            g.setColor(Color.DARK_GRAY);
        }
        g.fillRoundRect(add_size[0], add_size[1], add_x, add_y, 10, 10);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ariel", Font.BOLD, 20));
        g.drawString("ADD", add_size[0]+25, add_size[1]+25);
        
        repaint();
    }
    
        
    private void Reservation(Graphics g) {
        
    }
    
    private void ReadFile() {
        
    }
    
    private int Bill(int[][] f, boolean ifsep) {
        //change to graphics
        int sum = 0;
        if (f[0][1]!=0){
            sum = basicfee;
        }
        for (int i = 0; i < f.length; i++) {
            sum += f[i][0]*f[i][1];
        }
        return sum*113/100*discount_percentage[discount_select]/100;
    }
    
    
    public class KL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            
        }
        @Override
        public void keyReleased(KeyEvent e){
            
        }
    }
    
    public class ML extends MouseAdapter {
        public void mouseRightClick(MouseEvent e) {
            
        }
        @Override
        public void mouseMoved(MouseEvent e){
            int mx = e.getX();
            int my = e.getY();
            ppx = mx;
            ppy = my;
            if(addr == 1) {
                int[] hence = {0,0,0,0,0,0,0,0,0,0};//sit 8 + drink 8
                for (int i = 0; i < table_size.length; i++){//hence for seats
                    if (mx > table_size[i][0] && mx < table_size[i][0]+tabx
                     && my > table_size[i][1] && my < table_size[i][1]+taby) {
                        hence[i] = 1;
                        hence_x = table_size[i][0]-2;
                        hence_y = table_size[i][1]-2;
                        hence_l = tabx+4;
                        hence_h = taby+4;
                    } else{
                        hence[i] = 0;
                    }
                }
                
                int sum=0;
                for (int i = 0; i < hence.length; i++) {
                    if(hence[i] == 1) {
                        sum=1;
                    }
                }
                if(sum==0){
                    hence_x = 0;
                    hence_y = 0;
                    hence_l = 0;
                    hence_h = 0;
                }
                if (mx > discount_hence[0] && mx < discount_hence[0] + discount_x
                  &&my > discount_hence[1] && my < discount_hence[1] + discount_y) {
                    discount_if_hence = true;
                } else {
                    discount_if_hence = false;
                }
            } else if (addr == 2) {
                int[] hence = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//sit 8 + drink 8
                for (int i = 0; i < sit_size.length; i++){//hence for seats
                    if (mx > sit_size[i][0] && mx < sit_size[i][0]+sit_size_x
                     && my > sit_size[i][1] && my < sit_size[i][1]+sit_size_y) {
                        hence[i] = 1;
                        hence_x1 = sit_size[i][0]-2;
                        hence_y1 = sit_size[i][1]-2;
                        hence_l1 = sit_size_x+4;
                        hence_h1 = sit_size_y+4;
                    }
                }
                for (int i = 0; i < drink_size.length; i++){//hence for seats
                    if (mx > drink_size[i][0] && mx < drink_size[i][0]+drink_size_x
                     && my > drink_size[i][1] && my < drink_size[i][1]+drink_size_y) {
                        hence[i] = 1;
                        hence_x1 = drink_size[i][0]-2;
                        hence_y1 = drink_size[i][1]-2;
                        hence_l1 = drink_size_x+4;
                        hence_h1 = drink_size_y+4;
                    }
                }
                int sum=0;
                for (int i = 0; i < hence.length; i++) {
                    if(hence[i] == 1) {
                        sum=1;
                    }
                }
                if(sum==0){
                    hence_x1 = 0;
                    hence_y1 = 0;
                    hence_l1 = 0;
                    hence_h1 = 0;
                }
            }
        }
        @Override
        public void mouseReleased(MouseEvent e){
            
        }
        @Override
        public void mousePressed(MouseEvent e){
            int mx = e.getX();
            int my = e.getY();
            if(e.getButton()==1){
                if(addr == 1) {
                    for(int i = 0; i < table_size.length; i++) {
                        if (mx > table_size[i][0] && mx < table_size[i][0]+tabx
                         && my > table_size[i][1] && my < table_size[i][1]+taby) {
                            table_open[i][1] = true;
                            main = false;
                            cur = i;
                        }
                    }
                    if (mx > reset_size[0] && mx < reset_size[0]+reset_x
                     && my > reset_size[1] && my < reset_size[1]+reset_y) {
                        discount_select = discount_display.length - 1;
                        for (int i = 0; i < tables.length; i++) {
                            table_open[i][0]=false;
                            table_open[i][1]=false;
                            state[i]="CLOSE";
                            for (int j = 0; j < sit_open[0].length; j++) {
                                sit_open[i][j]=0;
                                sit_open[i][j]=0;
                                extra[i][j]=0;
                                for(int k = 0; k < count[0][0].length; k++) {
                                    count[i][j][k]=0;
                                }
                            }
                            for (int j = 0; j < tables[0].length; j++) {
                                tables[i][j][0]=0;
                                tables[i][j][1]=0;
                            }
                            tables[i][0][0]=price;
                        }
                    }
                    if (mx > discount_hence[0] && mx < discount_hence[0] + discount_x
                      &&my > discount_hence[1] && my < discount_hence[1] + discount_y) {
                        discount_select++;
                        if (discount_select == discount_display.length) {
                            discount_select -= discount_display.length;
                        }
                    }
                } else if (addr == 2) {
                    if (added==0) {
                        for (int i = 0; i < sit_size.length; i++){//hence for seats
                            if (mx > sit_size[i][0] && mx < sit_size[i][0]+sit_size_x
                             && my > sit_size[i][1] && my < sit_size[i][1]+sit_size_y) {
                                hence_x2 = sit_size[i][0]-2;
                                hence_y2 = sit_size[i][1]-2;
                                hence_l2 = sit_size_x+4;
                                hence_h2 = sit_size_y+4;
                            }
                        }
                        for(int i = 0; i < sit_size.length; i++){
                            if (mx > sit_size[i][0] && mx < sit_size[i][0]+sit_size_x
                             && my > sit_size[i][1] && my < sit_size[i][1]+sit_size_y) {
                                if(sit_open[cur][i]==0) {
                                    tables[cur][0][1]++;
                                }
                                sit_open[cur][i] = 1;
                                table_open[cur][0] = true;
                                cursit = i;
                            }
                        }
                        for(int i = 0; i < drink_size.length; i++){
                            if (mx > drink_size[i][0] && mx < drink_size[i][0]+drink_size_x
                             && my > drink_size[i][1] && my < drink_size[i][1]+drink_size_y) {
                                if (table_open[cur][0]) {
                                    extra[cur][cursit]+=drinkfee[i];
                                    tables[cur][i+1][1]++;
                                    count[cur][cursit][i]++;
                                }
                            }
                        }
                    } else {
                        for(int i = 0; i < sit_size.length; i++){
                            if (mx > sit_size[i][0] && mx < sit_size[i][0]+sit_size_x
                             && my > sit_size[i][1] && my < sit_size[i][1]+sit_size_y) {
                                if (!in[i]&&sit_open[cur][i]==1) {
                                    totsel+=bills[i]*113*discount_percentage[discount_select]/100;
                                    in[i]=true;
                                }
                            }
                        }
                    }
                    if (mx > back_size[0] && mx < back_size[0]+back_x
                     && my > back_size[1] && my < back_size[1]+back_y) {
                        main = true;
                        table_open[cur][1] = false;
                    }
                    if (mx > add_size[0] && mx < add_size[0]+add_x
                     && my > add_size[1] && my < add_size[1]+add_y) {
                        if (table_open[cur][0]&&tables[cur][0][1]>1) {
                            added = (added+1)%2;
                        }
                    }
                }
            } else if (e.getButton() == 3) {
                if (addr == 1) {
                    if (mx > discount_hence[0] && mx < discount_hence[0] + discount_x
                      &&my > discount_hence[1] && my < discount_hence[1] + discount_y) {
                        discount_select--;
                        if (discount_select == -1) {
                            discount_select += discount_display.length;
                        }
                    }
                } else if (addr == 2) {
                    if (added==0){
                        for(int i = 0; i < sit_size.length; i++){
                            if (mx > sit_size[i][0] && mx < sit_size[i][0]+sit_size_x
                             && my > sit_size[i][1] && my < sit_size[i][1]+sit_size_y) {
                                if (sit_open[cur][i]>0){
                                    sit_open[cur][i]++;
                                    if (sit_open[cur][i]==3) {
                                        sit_open[cur][i]=0;
                                        tables[cur][0][1]--;
                                        extra[cur][i]=0;
                                        for (int j = 0; j < drink_size.length; j++) {
                                            tables[cur][j+1][1] -= count[cur][i][j];
                                            count[cur][i][j] = 0;
                                        }
                                    }
                                }
                            }
                        }
                        for(int i = 0; i < drink_size.length; i++){
                            if (mx > drink_size[i][0] && mx < drink_size[i][0]+drink_size_x
                             && my > drink_size[i][1] && my < drink_size[i][1]+drink_size_y) {
                                if (table_open[cur][0]&&count[cur][cursit][i]>0) {
                                    extra[cur][cursit]-=drinkfee[i];
                                    tables[cur][i][1]--;
                                    count[cur][cursit][i]--;
                                }
                            }
                        }
                    } else {
                        for(int i = 0; i < sit_size.length; i++){
                            if (mx > sit_size[i][0] && mx < sit_size[i][0]+sit_size_x
                             && my > sit_size[i][1] && my < sit_size[i][1]+sit_size_y) {
                                if (in[i]) {
                                    totsel-=bills[i]*113*discount_percentage[discount_select]/100;
                                    in[i]=false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
}
