import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame
{
    static Model model;
    static Control control;
    static draw map=new draw();
    static JButton select=new JButton("选取");
    static JButton mode_line=new JButton("线");
    static JButton mode_rec=new JButton("矩形");
    static JButton mode_ell=new JButton("椭圆");
    static JButton mode_recfill=new JButton("填充矩形");
    static JButton mode_ellfill=new JButton("填充椭圆");
    static JButton mode_ploy=new JButton("多边形");
    static JButton mode_bro=new JButton("折线");
    static JButton mode_text=new JButton("文本");

    static JButton set_red=new JButton("");
    static JButton set_blue=new JButton("");
    static JButton set_black=new JButton("");
    static JButton set_green=new JButton("");
    static JButton set_purple=new JButton("");
    static JButton set_orange=new JButton("");
    static JButton set_yellow=new JButton("");
    static JButton set_pink=new JButton("");

    static JButton thick_plus=new JButton("粗细+");
    static JButton thick_dec=new JButton("粗细-");

    static JButton bigger=new JButton("变大");
    static JButton smaller=new JButton("变小");

    static JButton read=new JButton("读取");
    static JButton save=new JButton("保存");

    static JButton clear=new JButton("清空");

    static int leftwidth=0;
    static int topwidth=0;
    public View() {
        setTitle("Draw");
    }

    public void create(Model m,Control con)
    {
        model=m;
        control=con;
        map.setLayout(null);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        int height=this.getHeight();
        leftwidth=this.getInsets().left;
        topwidth=this.getInsets().top;
        this.add(map);

        select.setBounds(0,0,90,40);
        select.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.setnormal(); requestFocus();}});
        mode_line.setBounds(0,40,90,40);
        mode_line.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.setline();requestFocus(); }});
        mode_rec.setBounds(0,80,90,40);
        mode_rec.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.setrec();requestFocus(); }});
        mode_ell.setBounds(0,120,90,40);
        mode_ell.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.setell();requestFocus(); }});
        mode_recfill.setBounds(0,160,90,40);
        mode_recfill.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.setrec_filled();requestFocus(); }});
        mode_ellfill.setBounds(0,200,90,40);
        mode_ellfill.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.setell_filled();requestFocus(); }});
        mode_ploy.setBounds(0,240,90,40);
        mode_ploy.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.setpoly();requestFocus(); }});
        mode_bro.setBounds(0,280,90,40);
        mode_bro.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.setbro();requestFocus(); }});
        mode_text.setBounds(0,320,90,40);
        mode_text.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.settext();requestFocus(); }});
        map.add(select);map.add(mode_line);map.add(mode_rec);map.add(mode_ell);map.add(mode_recfill);map.add(mode_ellfill);map.add(mode_ploy);map.add(mode_bro);map.add(mode_text);

        set_black.setBounds(0,height-30-topwidth,30,30);set_black.setBackground(Color.BLACK);
        set_black.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.set_black();requestFocus(); }});
        set_blue.setBounds(30,height-30-topwidth,30,30);set_blue.setBackground(Color.BLUE);
        set_blue.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.set_blue();requestFocus(); }});
        set_green.setBounds(60,height-30-topwidth,30,30);set_green.setBackground(Color.GREEN);
        set_green.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.set_green();requestFocus(); }});
        set_orange.setBounds(90,height-30-topwidth,30,30);set_orange.setBackground(Color.ORANGE);
        set_orange.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.set_orange();requestFocus(); }});
        set_pink.setBounds(120,height-30-topwidth,30,30);set_pink.setBackground(Color.PINK);
        set_pink.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.set_pink();requestFocus(); }});
        set_purple.setBounds(150,height-30-topwidth,30,30);set_purple.setBackground(Color.MAGENTA);
        set_purple.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.set_purple();requestFocus(); }});
        set_red.setBounds(180,height-30-topwidth,30,30);set_red.setBackground(Color.RED);
        set_red.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.set_red();requestFocus(); }});
        set_yellow.setBounds(210,height-30-topwidth,30,30);set_yellow.setBackground(Color.YELLOW);
        set_yellow.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.set_yellow();requestFocus(); }});
        map.add(set_black);map.add(set_blue);map.add(set_green);map.add(set_orange);map.add(set_pink);map.add(set_purple);map.add(set_red);map.add(set_yellow);


        thick_plus.setBounds(0,460,90,40);
        thick_plus.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.thick_more();requestFocus();}});
        thick_dec.setBounds(0,500,90,40);
        thick_dec.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.thick_less();requestFocus();}});
        map.add(thick_plus);map.add(thick_dec);

        bigger.setBounds(90,460,90,40);
        bigger.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.getbig();requestFocus();}});
        smaller.setBounds(90,500,90,40);
        smaller.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.getsmall();requestFocus();}});
        map.add(bigger);map.add(smaller);

        read.setBounds(0,370,90,40);
        read.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.read_file();requestFocus();}});
        save.setBounds(0,410,90,40);
        save.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.save_file();requestFocus();}});
        map.add(read);map.add(save);

        clear.setBounds(500,525,90,40);
        clear.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { control.clear_map();requestFocus();}});
        map.add(clear);


    }

    void up(List<myShape> s)
    {
        map.up(s);
    }
}



class draw extends JPanel
{
    Image iBuffer;
    Graphics gBuffer;
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        Graphics2D g2=(Graphics2D)g;
    }

    void up(List<myShape> s)
    {
        Graphics g=getGraphics();
        if(iBuffer==null)
        {
            iBuffer=createImage(this.getSize().width,this.getSize().height);
            gBuffer=iBuffer.getGraphics();
        }
        gBuffer.setColor(getBackground());
        gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
        gBuffer.setColor(Color.BLACK);
        Graphics2D g2=(Graphics2D)gBuffer;
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for(myShape i:s)
        {
            i.draw(gBuffer);
            if (i.is_selected)
            {
                i.drawdash(gBuffer);
            }
        }
        View.select.paint(gBuffer);
        gBuffer.translate(0,40);View.mode_line.paint(gBuffer);
        gBuffer.translate(0,40);View.mode_rec.paint(gBuffer);
        gBuffer.translate(0,40);View.mode_ell.paint(gBuffer);
        gBuffer.translate(0,40);View.mode_recfill.paint(gBuffer);
        gBuffer.translate(0,40);View.mode_ellfill.paint(gBuffer);
        gBuffer.translate(0,40);View.mode_ploy.paint(gBuffer);
        gBuffer.translate(0,40);View.mode_bro.paint(gBuffer);
        gBuffer.translate(0,40);View.mode_text.paint(gBuffer);gBuffer.translate(0,-320);

        gBuffer.translate(0,540);View.set_black.paint(gBuffer);
        gBuffer.translate(30,0);View.set_blue.paint(gBuffer);
        gBuffer.translate(30,0);View.set_green.paint(gBuffer);
        gBuffer.translate(30,0);View.set_orange.paint(gBuffer);
        gBuffer.translate(30,0);View.set_pink.paint(gBuffer);
        gBuffer.translate(30,0);View.set_purple.paint(gBuffer);
        gBuffer.translate(30,0);View.set_red.paint(gBuffer);
        gBuffer.translate(30,0);View.set_yellow.paint(gBuffer);gBuffer.translate(-210,-540);

        gBuffer.translate(0,460);View.thick_plus.paint(gBuffer);
        gBuffer.translate(0,40);View.thick_dec.paint(gBuffer);gBuffer.translate(0,-500);

        gBuffer.translate(90,460);View.bigger.paint(gBuffer);
        gBuffer.translate(0,40);View.smaller.paint(gBuffer);gBuffer.translate(-90,-500);

        gBuffer.translate(0,370);View.read.paint(gBuffer);
        gBuffer.translate(0,40);View.save.paint(gBuffer);gBuffer.translate(0,-410);

        gBuffer.translate(500,525);View.clear.paint(gBuffer);gBuffer.translate(-500,-525);


        g.drawImage(iBuffer,0,0,this);
    }
}