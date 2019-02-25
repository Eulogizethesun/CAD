public class Main
{
    public static void main(String[] args)
    {
        Model model=new Model();
        View view=new View();
        Control control=new Control();

        model.create(control,view);
        view.create(model,control);
        control.create(view,model);

        control.input();
    }
}