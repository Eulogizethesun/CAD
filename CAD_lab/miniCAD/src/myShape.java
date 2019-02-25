import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.Polygon;

public abstract class myShape
{
    int x1,y1;
    int thickness=1;
    Color c =Color.BLACK;
    boolean is_selected=false;
    Stroke dash = new BasicStroke(2.5f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,3.5f,new float[]{15,10,},0f);
    abstract void draw(Graphics g);
    abstract void drawdash(Graphics g);
    abstract void move(int xn,int yn);
    abstract boolean is_select(int xm,int ym);
    abstract void bigger();
    abstract void smaller();
}

class myLine extends myShape
{
    //int x1,y1,
    int x2,y2;
    myLine(){}
    myLine(int xt1,int yt1,int xt2,int yt2)
    {
        x1=xt1;y1=yt1;x2=xt2;y2=yt2;
    }

    void draw(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(thickness));
        g.setColor(c);
        g.drawLine(x1,y1,x2,y2);
    }
    boolean is_select(int xm,int ym)
    {
        int[] xt={x1,x2,x2,x1};
        int[] yt={y1-10,y2-10,y2+10,y1+10};
        Polygon p=new Polygon(xt,yt,4);
        if (p.contains(xm,ym))
        {
            is_selected=true;
            return true;
        }
        else
        {
            is_selected=false;
            return false;
        }
    }
    void drawdash(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        Stroke t=g2.getStroke();
        g2.setStroke(dash);
        g.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
        g2.setStroke(t);
    }
    void move(int xn,int yn)
    {
        x2=x2-x1+xn;x1=xn;
        y2=y2-y1+yn;y1=yn;
    }
    void bigger()
    {
        double times=1.1;
        x2=(int)((x2-x1)*times+x1);
        y2=(int)((y2-y1)*times+y1);
    }
    void smaller()
    {
        double times=0.9;
        x2=(int)((x2-x1)*times+x1);
        y2=(int)((y2-y1)*times+y1);
    }

}
class myRectangle extends myShape
{
    //int x1,y1;
    int width,height;
    myRectangle(){}
    myRectangle(int xt1,int yt1,int widtht,int heightt)
    {
        x1=xt1;y1=yt1;width=widtht;height=heightt;
    }
    void draw(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(thickness));
        g.setColor(c);
        g.drawRect(x1,y1,width,height);
    }
    boolean is_select(int xm,int ym)
    {
        int[] xt={x1,x1+width,x1+width,x1};
        int[] yt={y1,y1,y1+height,y1+height};
        Polygon p=new Polygon(xt,yt,4);
        if (p.contains(xm,ym))
        {
            is_selected=true;
            return true;
        }
        else
        {
            is_selected=false;
            return false;
        }
    }
    void drawdash(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        Stroke t=g2.getStroke();
        g2.setStroke(dash);
        g.drawRect(x1,y1,width,height);
        g2.setStroke(t);
    }
    void move(int xn,int yn)
    {
        x1=xn;y1=yn;
    }
    void bigger()
    {
        double times=1.1;
        width=(int)(width*times);
        height=(int)(height*times);
    }
    void smaller()
    {
        double times=0.9;
        width=(int)(width*times);
        height=(int)(height*times);
    }
}
class myEliipse extends myShape
{
    //int x1,y1;
    int width,height;
    myEliipse(){}
    myEliipse(int xt1,int yt1,int widtht,int heightt)
    {
        x1=xt1;y1=yt1;width=widtht;height=heightt;
    }
    void draw(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(thickness));
        g.setColor(c);
        g.drawOval(x1,y1,width,height);
    }
    boolean is_select(int xm,int ym)
    {
        int a=width/2;
        int b=height/2;
        Point centerPoint=new Point(x1+a,y1+b);
        double v = Math.pow(centerPoint.x-xm, 2) / Math.pow(a, 2) + Math.pow(centerPoint.y-ym, 2) / Math.pow(b, 2);
        if (v<1)
        {
            is_selected=true;
            return true;
        }
        else
        {
            is_selected=false;
            return false;
        }
    }
    void drawdash(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        Stroke t=g2.getStroke();
        g2.setStroke(dash);
        g.drawRect(x1,y1,width,height);
        g2.setStroke(t);
    }
    void move(int xn,int yn)
    {
        x1=xn;y1=yn;
    }
    void bigger()
    {
        double times=1.1;
        width=(int)(width*times);
        height=(int)(height*times);
    }
    void smaller()
    {
        double times=0.9;
        width=(int)(width*times);
        height=(int)(height*times);
    }

}
class Rectangle_filled extends myShape
{
    //int x1,y1;
    int width,height;
    Rectangle_filled(){}
    Rectangle_filled(int xt1,int yt1,int widtht,int heightt)
    {
        x1=xt1;y1=yt1;width=widtht;height=heightt;
    }
    void draw(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(thickness));
        g.setColor(c);
        g.fillRect(x1,y1,width,height);
    }
    boolean is_select(int xm,int ym)
    {
        int[] xt={x1,x1+width,x1+width,x1};
        int[] yt={y1,y1,y1+height,y1+height};
        Polygon p=new Polygon(xt,yt,4);
        if (p.contains(xm,ym))
        {
            is_selected=true;
            return true;
        }
        else
        {
            is_selected=false;
            return false;
        }
    }
    void drawdash(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        Stroke t=g2.getStroke();
        g2.setStroke(dash);
        g.drawRect(x1,y1,width,height);
        g2.setStroke(t);
    }
    void move(int xn,int yn)
    {
        x1=xn;y1=yn;
    }
    void bigger()
    {
        double times=1.1;
        width=(int)(width*times);
        height=(int)(height*times);
    }
    void smaller()
    {
        double times=0.9;
        width=(int)(width*times);
        height=(int)(height*times);
    }
}
class Eliipse_filled extends myShape
{
    //int x1,y1;
    int width,height;
    Eliipse_filled(){}
    Eliipse_filled(int xt1,int yt1,int widtht,int heightt)
    {
        x1=xt1;y1=yt1;width=widtht;height=heightt;
    }
    void draw(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(thickness));
        g.setColor(c);
        g.fillOval(x1,y1,width,height);
    }
    boolean is_select(int xm,int ym)
    {
        int a=width/2;
        int b=height/2;
        Point centerPoint=new Point(x1+a,y1+b);
        double v = Math.pow(centerPoint.x-xm, 2) / Math.pow(a, 2) + Math.pow(centerPoint.y-ym, 2) / Math.pow(b, 2);
        if (v<1)
        {
            is_selected=true;
            return true;
        }
        else
        {
            is_selected=false;
            return false;
        }
    }
    void drawdash(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        Stroke t=g2.getStroke();
        g2.setStroke(dash);
        g.drawRect(x1,y1,width,height);
        g2.setStroke(t);
    }
    void move(int xn,int yn)
    {
        x1=xn;y1=yn;
    }
    void bigger()
    {
        double times=1.1;
        width=(int)(width*times);
        height=(int)(height*times);
    }
    void smaller()
    {
        double times=0.9;
        width=(int)(width*times);
        height=(int)(height*times);
    }
}
class myPolygon extends myShape
{
    List<Integer> x=new ArrayList<Integer>();
    List<Integer> y=new ArrayList<Integer>();
    myPolygon(){}
    void draw(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(thickness));
        x1=x.get(0);y1=y.get(0);
        g.setColor(c);
        int i=0;
        int xp[]=new int[x.size()];int yp[]=new int[y.size()];
        for(i=0;i<x.size();i++)
        {
            xp[i]=x.get(i);
            yp[i]=y.get(i);
        }
        g.drawPolyline(xp, yp, x.size());
    }
    boolean is_select(int xm,int ym)
    {
        x1=x.get(0);y1=y.get(0);
        int i=0;
        int xp[]=new int[x.size()];int yp[]=new int[y.size()];
        for(i=0;i<x.size();i++)
        {
            xp[i]=x.get(i);
            yp[i]=y.get(i);
        }
        Polygon p=new Polygon(xp,yp,x.size());
        if (p.contains(xm,ym))
        {
            is_selected=true;
            return true;
        }
        else
        {
            is_selected=false;
            return false;
        }
    }
    void drawdash(Graphics g)
    {
        int minx = (int) Collections.min(x);
        int maxx = (int) Collections.max(x);
        int miny = (int) Collections.min(y);
        int maxy = (int) Collections.max(y);
        Graphics2D g2=(Graphics2D)g;
        Stroke t=g2.getStroke();
        g2.setStroke(dash);
        g.drawRect(minx,miny,maxx-minx,maxy-miny);
        g2.setStroke(t);
    }
    void move(int xn,int yn)
    {
        int xt=x.get(0);int yt=y.get(0);
        int dx=xn-xt;int dy=yn-yt;
        int i=0;
        for(i=0;i<x.size();i++)
        {
            x.set(i,x.get(i)+dx);
            y.set(i,y.get(i)+dy);
        }
    }
    void bigger()
    {
        double times=1.1;
        int i=0;
        for(i=0;i<x.size();i++)
        {
            x.set(i,((int)((x.get(i)-x.get(0))*times+x.get(0))));
            y.set(i,((int)((y.get(i)-y.get(0))*times+y.get(0))));
        }
    }
    void smaller()
    {
        double times=0.9;
        int i=0;
        for(i=0;i<x.size();i++)
        {
            x.set(i,((int)((x.get(i)-x.get(0))*times+x.get(0))));
            y.set(i,((int)((y.get(i)-y.get(0))*times+y.get(0))));
        }
    }
}
class Broken_line extends myShape
{
    List<Integer> x=new ArrayList<Integer>();
    List<Integer> y=new ArrayList<Integer>();
    Broken_line(){}
    void draw(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(thickness));
        x1=x.get(0);y1=y.get(0);
        g.setColor(c);
        int i=0;
        int xp[]=new int[x.size()];int yp[]=new int[y.size()];
        for(i=0;i<x.size();i++)
        {
            xp[i]=x.get(i);
            yp[i]=y.get(i);
        }
        g.drawPolyline(xp, yp, x.size());
    }
    boolean is_select(int xm,int ym)
    {
        x1=x.get(0);y1=y.get(0);
        int i=0;
        int xp[]=new int[x.size()];int yp[]=new int[y.size()];
        for(i=0;i<x.size();i++)
        {
            xp[i]=x.get(i);
            yp[i]=y.get(i);
            if (i>=1)
            {
                myLine l=new myLine(xp[i-1],yp[i-1],xp[i],yp[i]);
                if(l.is_select(xm,ym))
                {
                    is_selected=true;
                    return true;
                }
            }
        }
        is_selected=false;
        return false;
    }
    void drawdash(Graphics g)
    {
        int minx = (int) Collections.min(x);
        int maxx = (int) Collections.max(x);
        int miny = (int) Collections.min(y);
        int maxy = (int) Collections.max(y);
        Graphics2D g2=(Graphics2D)g;
        Stroke t=g2.getStroke();
        g2.setStroke(dash);
        g.drawRect(minx,miny,maxx-minx,maxy-miny);
        g2.setStroke(t);
    }
    void move(int xn,int yn)
    {
        int xt=x.get(0);int yt=y.get(0);
        int dx=xn-xt;int dy=yn-yt;
        int i=0;
        for(i=0;i<x.size();i++)
        {
            x.set(i,x.get(i)+dx);
            y.set(i,y.get(i)+dy);
        }
    }
    void bigger()
    {
        double times=1.1;
        int i=0;
        for(i=0;i<x.size();i++)
        {
            x.set(i,((int)((x.get(i)-x.get(0))*times+x.get(0))));
            y.set(i,((int)((y.get(i)-y.get(0))*times+y.get(0))));
        }
    }
    void smaller()
    {
        double times=0.9;
        int i=0;
        for(i=0;i<x.size();i++)
        {
            x.set(i,((int)((x.get(i)-x.get(0))*times+x.get(0))));
            y.set(i,((int)((y.get(i)-y.get(0))*times+y.get(0))));
        }
    }
}
class myText extends myShape
{
    Font f=new Font("Dialog",Font.PLAIN,12);
    int size=12;
    String content;
    Graphics gg;
    myText(){}
    myText(String con,int xt,int yt)
    {
        x1=xt;y1=yt;content=con;
    }
    void draw(Graphics g)
    {
        gg=g;
        g.setColor(c);
        f=new Font("Dialog",Font.PLAIN,size);
        g.setFont(f);
        g.drawString(content,x1,y1);
    }
    boolean is_select(int xm,int ym)
    {
        int width=0;int height=0;
        FontMetrics fm=gg.getFontMetrics(f);
        width=fm.stringWidth( content );
        height=fm.getHeight();
        myRectangle r=new myRectangle(x1,y1-height,width,height);
        if (r.is_select(xm,ym))
        {
            is_selected=true;
            return true;
        }
        else
        {
            is_selected=false;
            return false;
        }
    }
    void drawdash(Graphics g)
    {
        FontMetrics fm=gg.getFontMetrics(f);
        int width=fm.stringWidth( content );
        int height=fm.getHeight();
        Graphics2D g2=(Graphics2D)g;
        Stroke t=g2.getStroke();
        Stroke temp = new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,3.5f,new float[]{3,2,},0f);
        g2.setStroke(temp);
        g.drawRect(x1,y1-height,width,height);
        g2.setStroke(t);
    }
    void move(int xn,int yn)
    {
        x1=xn;
        y1=yn;
    }
    void bigger()
    {
        size=size+2;
    }
    void smaller()
    {
        if(size>6)
            size=size-2;
    }
}



