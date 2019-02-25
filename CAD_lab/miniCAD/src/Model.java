import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    static Control control;
    static View view;

    public List<myShape> shapelist=new ArrayList<myShape>();

    public void create(Control con,View vi)
    {
        control=con;
        view=vi;
    }

    void add_shape(myShape x)
    {
        shapelist.add(x);
        view.up(shapelist);
    }

    void replace_shape(myShape x)
    {
        shapelist.set(shapelist.size()-1,x);
        view.up(shapelist);
    }

    void quit_shape()
    {
        shapelist.remove(shapelist.size()-1);
        view.up(shapelist);
    }

    void remove_shape(myShape x)
    {
        shapelist.remove(x);
        view.up(shapelist);
    }

    boolean select_shape(int xm,int ym)
    {
        boolean flag=false;
        for(myShape i:shapelist)
        {
            i.is_selected=false;
        }
        for(myShape i:shapelist)
        {
            flag=i.is_select(xm,ym);
            if (flag)
            {
                break;
            }
        }
        view.up(shapelist);
        return flag;
    }

    void move_shape(int xn,int yn)
    {
        for(myShape i:shapelist)
        {
            if (i.is_selected)
            {
                i.move(xn,yn);
                view.up(shapelist);
                break;
            }
        }

    }

    myShape get_selectshape()
    {
        for(myShape i:shapelist)
        {
            if (i.is_selected)
            {
                return i;
            }
        }
        return null;
    }

    void color_change(myShape s, Color co)
    {
        if (s!=null)
        {
            s.c=co;
            view.up(shapelist);
        }
    }
    void shape_thick(myShape s)
    {
        s.thickness++;
        view.up(shapelist);
    }
    void shape_thin(myShape s)
    {
        if (s.thickness>1)
            s.thickness--;
        view.up(shapelist);
    }
    void shape_big(myShape s)
    {
        if (s!=null)
        {
            s.bigger();
        }
        view.up(shapelist);
    }
    void shape_small(myShape s)
    {
        if (s!=null)
        {
            s.smaller();
        }
        view.up(shapelist);
    }
    void add_text(myShape s,char t)
    {
        if (s==null)
            return;
        if (s.getClass().getName()=="myText")
        {
            s=(myText)s;
            ((myText) s).content=((myText) s).content+t;
        }
        view.up(shapelist);
    }
    void delete_text(myShape s)
    {
        if (s==null)
            return;
        if (s.getClass().getName()=="myText")
        {
            s=(myText)s;
            if (((myText) s).content.length()<=0)
                return;
            ((myText) s).content=((myText) s).content.substring(0,((myText) s).content.length()-1);
        }
        view.up(shapelist);
    }
    void delete_shape(myShape s)
    {
        shapelist.remove(s);
        view.up(shapelist);
    }
    void read(File file)
    {
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            shapelist.clear();
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                String[] t=s.split(" ");
                if (t[0].equals("myLine"))
                {
                    myLine l=new myLine();
                    l.x1=Integer.parseInt(t[1]);l.y1=Integer.parseInt(t[2]);l.x2=Integer.parseInt(t[3]);l.y2=Integer.parseInt(t[4]);
                    l.c=new Color(Integer.parseInt(t[5]));l.thickness=Integer.parseInt(t[6]);
                    shapelist.add(l);
                }
                else if(t[0].equals("myRectangle"))
                {
                    myRectangle r=new myRectangle();
                    r.x1=Integer.parseInt(t[1]);r.y1=Integer.parseInt(t[2]);r.width=Integer.parseInt(t[3]);r.height=Integer.parseInt(t[4]);
                    r.c=new Color(Integer.parseInt(t[5]));r.thickness=Integer.parseInt(t[6]);
                    shapelist.add(r);
                }
                else if(t[0].equals("myEliipse"))
                {
                    myEliipse r=new myEliipse();
                    r.x1=Integer.parseInt(t[1]);r.y1=Integer.parseInt(t[2]);r.width=Integer.parseInt(t[3]);r.height=Integer.parseInt(t[4]);
                    r.c=new Color(Integer.parseInt(t[5]));r.thickness=Integer.parseInt(t[6]);
                    shapelist.add(r);
                }
                else if(t[0].equals("Rectangle_filled"))
                {
                    Rectangle_filled r=new Rectangle_filled();
                    r.x1=Integer.parseInt(t[1]);r.y1=Integer.parseInt(t[2]);r.width=Integer.parseInt(t[3]);r.height=Integer.parseInt(t[4]);
                    r.c=new Color(Integer.parseInt(t[5]));r.thickness=Integer.parseInt(t[6]);
                    shapelist.add(r);
                }
                else if(t[0].equals("Eliipse_filled"))
                {
                    Eliipse_filled r=new Eliipse_filled();
                    r.x1=Integer.parseInt(t[1]);r.y1=Integer.parseInt(t[2]);r.width=Integer.parseInt(t[3]);r.height=Integer.parseInt(t[4]);
                    r.c=new Color(Integer.parseInt(t[5]));r.thickness=Integer.parseInt(t[6]);
                    shapelist.add(r);
                }
                else if(t[0].equals("myPolygon"))
                {
                    myPolygon p=new myPolygon();
                    int size=Integer.parseInt(t[1]);int k=0;
                    for (k=2;k<size*2+2;k=k+2)
                    {
                        p.x.add(Integer.parseInt(t[k]));p.y.add(Integer.parseInt(t[k+1]));
                    }
                    p.c=new Color(Integer.parseInt(t[size*2+2]));p.thickness=Integer.parseInt(t[size*2+3]);
                    shapelist.add(p);
                }
                else if(t[0].equals("Broken_line"))
                {
                    Broken_line p=new Broken_line();
                    int size=Integer.parseInt(t[1]);int k=0;
                    for (k=2;k<size*2+2;k=k+2)
                    {
                        p.x.add(Integer.parseInt(t[k]));p.y.add(Integer.parseInt(t[k+1]));
                    }
                    p.c=new Color(Integer.parseInt(t[size*2+2]));p.thickness=Integer.parseInt(t[size*2+3]);
                    shapelist.add(p);
                }
                else if(t[0].equals("myText"))
                {
                    myText p=new myText();
                    p.x1=Integer.parseInt(t[1]);p.y1=Integer.parseInt(t[2]);
                    String k=br.readLine();
                    p.content=new String(k);
                    String[] temp =br.readLine().split(" ");
                    p.c=new Color(Integer.parseInt(temp[0]));p.size=Integer.parseInt(temp[1]);
                    shapelist.add(p);
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        view.up(shapelist);
    }
    void save(File file)
    {
        //File writename = new File(".\\result\\en\\output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
        try {
            file.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            int i=0;
            for (i=0;i<shapelist.size();i++)
            {
                myShape s=shapelist.get(i);
                if(s.getClass().getName().equals("myLine"))
                {
                    out.write("myLine"+" "+s.x1+" "+s.y1+" "+((myLine)s).x2+" "+((myLine)s).y2+" "+s.c.getRGB()+" "+s.thickness+"\r\n");
                }
                else if (s.getClass().getName().equals("myRectangle"))
                {
                    out.write("myRectangle"+" "+s.x1+" "+s.y1+" "+((myRectangle)s).width+" "+((myRectangle)s).height+" "+s.c.getRGB()+" "+s.thickness+"\r\n");
                }
                else if (s.getClass().getName().equals("myEliipse"))
                {
                    out.write("myEliipse"+" "+s.x1+" "+s.y1+" "+((myEliipse)s).width+" "+((myEliipse)s).height+" "+s.c.getRGB()+" "+s.thickness+"\r\n");
                }
                else if (s.getClass().getName().equals("Rectangle_filled"))
                {
                    out.write("Rectangle_filled"+" "+s.x1+" "+s.y1+" "+((Rectangle_filled)s).width+" "+((Rectangle_filled)s).height+" "+s.c.getRGB()+" "+s.thickness+"\r\n");
                }
                else if (s.getClass().getName().equals("Eliipse_filled"))
                {
                    out.write("Eliipse_filled"+" "+s.x1+" "+s.y1+" "+((Eliipse_filled)s).width+" "+((Eliipse_filled)s).height+" "+s.c.getRGB()+" "+s.thickness+"\r\n");
                }
                else if (s.getClass().getName().equals("myPolygon"))
                {
                    out.write("myPolygon"+" "+((myPolygon)s).x.size());
                    int k=0;
                    for (k=0;k<((myPolygon)s).x.size();k++)
                    {
                        out.write(" "+((myPolygon)s).x.get(k)+" "+((myPolygon)s).y.get(k));
                    }
                    out.write(" "+s.c.getRGB()+" "+s.thickness+"\r\n");

                }
                else if (s.getClass().getName().equals("Broken_line"))
                {
                    out.write("Broken_line"+" "+((Broken_line)s).x.size());
                    int k=0;
                    for (k=0;k<((Broken_line)s).x.size();k++)
                    {
                        out.write(" "+((Broken_line)s).x.get(k)+" "+((Broken_line)s).y.get(k));
                    }
                    out.write(" "+s.c.getRGB()+" "+s.thickness+"\r\n");
                }
                else if (s.getClass().getName().equals("myText"))
                {
                    out.write("myText"+" "+s.x1+" "+s.y1+"\r\n");
                    out.write(((myText)s).content+"\r\n");
                    out.write(s.c.getRGB()+" "+((myText)s).size+"\r\n");
                }
            }
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    void clear_model()
    {
        shapelist.clear();
        view.up(shapelist);
    }




}




