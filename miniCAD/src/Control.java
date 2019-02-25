//import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Control
{
    static View view;
    static Model model;

    static int select=1;
    static int line=0;
    static int rectangle=0;
    static int ellipse=0;
    static int rec_fill=0;
    static int ell_fill=0;
    static int poly=0;
    static int bro_line=0;
    static int text=0;

    public void create(View vi,Model m)
    {
        view=vi;
        model=m;

        action act=new action();
        vi.addMouseListener(act);
        vi.addMouseMotionListener(act);
        vi.addKeyListener(act);
    }
    void input()
    {

    }
    void read_file()
    {
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        if (file!=null) {
            if (file.isDirectory()) {
                System.out.println("不能是文件夹");
            } else if (file.isFile())
            {
                model.read(file);
            }
        }
    }
    void save_file()
    {
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        if (file!=null) {
            model.save(file);
        }
    }
    void clear_map()
    {
        model.clear_model();
    }
    void getbig()
    {
        myShape s=model.get_selectshape();
        model.shape_big(s);
    }
    void getsmall()
    {
        myShape s=model.get_selectshape();
        model.shape_small(s);
    }
    void thick_less()
    {
        myShape s=model.get_selectshape();
        model.shape_thin(s);
    }
    void thick_more()
    {
        myShape s=model.get_selectshape();
        model.shape_thick(s);
    }

    void set_black()
    {
        myShape s=model.get_selectshape();
        model.color_change(s,Color.BLACK);
    }
    void set_blue()
    {
        myShape s=model.get_selectshape();
        model.color_change(s,Color.BLUE);
    }
    void set_green()
    {
        myShape s=model.get_selectshape();
        model.color_change(s,Color.GREEN);
    }
    void set_orange()
    {
        myShape s=model.get_selectshape();
        model.color_change(s,Color.ORANGE);
    }
    void set_pink()
    {
        myShape s=model.get_selectshape();
        model.color_change(s,Color.PINK);
    }
    void set_purple()
    {
        myShape s=model.get_selectshape();
        model.color_change(s,Color.MAGENTA);
    }
    void set_red()
    {
        myShape s=model.get_selectshape();
        model.color_change(s,Color.RED);
    }
    void set_yellow()
    {
        myShape s=model.get_selectshape();
        model.color_change(s,Color.YELLOW);
    }

    void setnormal(){ select=1;line=0;rectangle=0;ellipse=0;rec_fill=0;ell_fill=0;poly=0;bro_line=0;text=0;}
    void setline(){ select=0;line=1;rectangle=0;ellipse=0;rec_fill=0;ell_fill=0;poly=0;bro_line=0;text=0;}
    void setrec(){ select=0;line=0;rectangle=1;ellipse=0;rec_fill=0;ell_fill=0;poly=0;bro_line=0;text=0;}
    void setell(){ select=0;line=0;rectangle=0;ellipse=1;rec_fill=0;ell_fill=0;poly=0;bro_line=0;text=0;}
    void setrec_filled(){ select=0;line=0;rectangle=0;ellipse=0;rec_fill=1;ell_fill=0;poly=0;bro_line=0;text=0;}
    void setell_filled(){ select=0;line=0;rectangle=0;ellipse=0;rec_fill=0;ell_fill=1;poly=0;bro_line=0;text=0;}
    void setpoly(){ select=0;line=0;rectangle=0;ellipse=0;rec_fill=0;ell_fill=0;poly=1;bro_line=0;text=0;}
    void setbro(){ select=0;line=0;rectangle=0;ellipse=0;rec_fill=0;ell_fill=0;poly=0;bro_line=1;text=0;}
    void settext(){ select=0;line=0;rectangle=0;ellipse=0;rec_fill=0;ell_fill=0;poly=0;bro_line=0;text=1;}

}

class action implements MouseListener, MouseMotionListener, KeyListener
{
    int state=0;
    int x1,y1,x2,y2;//x1,y1,x2,y2 for line
    int width,height;//x1,y1,width,height for rectangle
                        //x1,y1,width,height for ellipse
    List<Integer> x=new ArrayList<Integer>();
    List<Integer> y=new ArrayList<Integer>();//x[],y[],for polygong and broken line
    int dxm;int dym;

    int is_poly=0;
    int is_bro=0;
    boolean has_select=false;
    boolean edit_mode=false;

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1)
        {
            if (state==0&&Control.select==0)
                state=1;
            else if (state==1)
            {
                if (is_poly==0&&is_bro==0)
                {
                    state = 0;
                }
                else if (is_poly==1||is_bro==1)
                {
                    state=1;
                }
            }
            if (Control.select==1)
            {
                x1 = e.getX() - View.leftwidth;
                y1 = e.getY() - View.topwidth;
                if (has_select==false)
                {
                    has_select=Control.model.select_shape(x1,y1);
                }
                else if(has_select)
                {
                    has_select=Control.model.select_shape(x1,y1);
                    edit_mode=true;
                }
                if (Control.model.get_selectshape()!=null)
                {
                    dxm=Control.model.get_selectshape().x1-x1;
                    dym=Control.model.get_selectshape().y1-y1;
                }
            }
            else if (Control.line==1)
            {
                x1 = e.getX() - View.leftwidth;
                y1 = e.getY() - View.topwidth;
                x2=x1;y2=y1;
                myLine l=new myLine(x1,y1,x2,y2);
                Control.model.add_shape(l);
            }
            else if (Control.rectangle==1)
            {
                x1 = e.getX() - View.leftwidth;
                y1 = e.getY() - View.topwidth;
                x2=x1;y2=y1;
                myRectangle r=new myRectangle(x1,y1,0,0);
                Control.model.add_shape(r);
            }
            else if (Control.ellipse==1)
            {
                x1 = e.getX() - View.leftwidth;
                y1 = e.getY() - View.topwidth;
                x2=x1;y2=y1;
                myEliipse ell=new myEliipse(x1,y1,0,0);
                Control.model.add_shape(ell);
            }
            else if(Control.rec_fill==1)
            {
                x1 = e.getX() - View.leftwidth;
                y1 = e.getY() - View.topwidth;
                x2=x1;y2=y1;
                Rectangle_filled r=new Rectangle_filled(x1,y1,0,0);
                Control.model.add_shape(r);
            }
            else if(Control.ell_fill==1)
            {
                x1 = e.getX() - View.leftwidth;
                y1 = e.getY() - View.topwidth;
                x2=x1;y2=y1;
                Eliipse_filled ell=new Eliipse_filled(x1,y1,0,0);
                Control.model.add_shape(ell);
            }
            else if(Control.poly==1)
            {
                x1 = e.getX() - View.leftwidth;
                y1 = e.getY() - View.topwidth;
                myPolygon p=new myPolygon();
                myLine l=new myLine(x1,y1,x1,y1);
                if (is_poly==0)
                {
                    is_poly = 1;
                    x=new ArrayList<Integer>();y=new ArrayList<Integer>();
                    x.add(x1);y.add(y1);
                    p.x = x;p.y = y;
                    Control.model.add_shape(p);
                    Control.model.add_shape(l);
                }
                else if (is_poly==1)
                {
                    x.add(x1);y.add(y1);
                    p.x=x;p.y=y;
                    Control.model.quit_shape();
                    Control.model.replace_shape(p);
                    Control.model.add_shape(l);
                }
            }
            else if(Control.bro_line==1)
            {
                x1 = e.getX() - View.leftwidth;
                y1 = e.getY() - View.topwidth;
                Broken_line p=new Broken_line();
                myLine l=new myLine(x1,y1,x1,y1);
                if (is_bro==0)
                {
                    is_bro = 1;
                    x=new ArrayList<Integer>();y=new ArrayList<Integer>();
                    x.add(x1);y.add(y1);
                    p.x = x;p.y = y;
                    Control.model.add_shape(p);
                    Control.model.add_shape(l);
                }
                else if (is_bro==1)
                {
                    x.add(x1);y.add(y1);
                    p.x=x;p.y=y;
                    Control.model.quit_shape();
                    Control.model.replace_shape(p);
                    Control.model.add_shape(l);
                }
            }
            else if(Control.text==1)
            {
                x1 = e.getX() - View.leftwidth;
                y1 = e.getY() - View.topwidth;
                myText t=new myText("Hello",x1,y1);
                Control.model.add_shape(t);
            }
        }
        else if (e.getButton()==MouseEvent.BUTTON3)
        {
            if (state==1)
            {
                state=0;
                if(is_poly==1)
                {
                    is_poly=0;
                    x1=x.get(0);y1=y.get(0);
                    x.add(x1);y.add(y1);
                    myPolygon p=new myPolygon();
                    myLine l=new myLine(x1,y1,x1,y1);
                    p.x=x;p.y=y;
                    Control.model.quit_shape();
                    Control.model.replace_shape(p);
                    Control.model.add_shape(l);
                }
                else if(is_bro==1)
                {
                    is_bro=0;
                }
                Control.model.quit_shape();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (Control.select==1)//有问题
        {
            int xt = e.getX() - View.leftwidth;
            int yt = e.getY() - View.topwidth;
            Control.model.move_shape(xt+dxm,yt+dym);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (Control.select==1)
        {

        }
        else if(state==1)
        {
            if (Control.line==1)
            {
                x2=e.getX()-View.leftwidth;
                y2=e.getY()-View.topwidth;
                myLine l=new myLine(x1,y1,x2,y2);
                Control.model.replace_shape(l);
            }
            else if(Control.rectangle==1)
            {
                width=e.getX()-View.leftwidth-x1;
                height=e.getY()-View.topwidth-y1;
                myRectangle r=new myRectangle(x1,y1,width,height);
                Control.model.replace_shape(r);
            }
            else if (Control.ellipse==1)
            {
                width=e.getX()-View.leftwidth-x1;
                height=e.getY()-View.topwidth-y1;
                myEliipse r=new myEliipse(x1,y1,width,height);
                Control.model.replace_shape(r);
            }
            else if(Control.rec_fill==1)
            {
                width=e.getX()-View.leftwidth-x1;
                height=e.getY()-View.topwidth-y1;
                Rectangle_filled r=new Rectangle_filled(x1,y1,width,height);
                Control.model.replace_shape(r);
            }
            else if(Control.ell_fill==1)
            {
                width=e.getX()-View.leftwidth-x1;
                height=e.getY()-View.topwidth-y1;
                Eliipse_filled r=new Eliipse_filled(x1,y1,width,height);
                Control.model.replace_shape(r);
            }
            else if(Control.poly==1)
            {
                x2 = e.getX() - View.leftwidth;
                y2 = e.getY() - View.topwidth;
                myLine l=new myLine(x1,y1,x2,y2);
                Control.model.replace_shape(l);
            }
            else if(Control.bro_line==1)
            {
                x2 = e.getX() - View.leftwidth;
                y2 = e.getY() - View.topwidth;
                myLine l=new myLine(x1,y1,x2,y2);
                Control.model.replace_shape(l);
            }
            else if(Control.text==1)
            {

            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int vk = e.getKeyCode();
        if ((vk>=KeyEvent.VK_A&&vk<=KeyEvent.VK_Z)||(vk>=KeyEvent.VK_0&&vk<=KeyEvent.VK_9)||(vk>=KeyEvent.VK_NUMPAD0&&vk<=KeyEvent.VK_NUMPAD9)||(vk==KeyEvent.VK_SPACE))
        {
            myShape s=Control.model.get_selectshape();
            char t=e.getKeyChar();
            Control.model.add_text(s,t);
        }
        else if(vk==KeyEvent.VK_BACK_SPACE)
        {
            myShape s=Control.model.get_selectshape();
            Control.model.delete_text(s);
        }
        else if (vk==KeyEvent.VK_DELETE)
        {
            myShape s=Control.model.get_selectshape();
            Control.model.delete_shape(s);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
