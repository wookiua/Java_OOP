package dilib;


import org.fpm.di.Container;
import org.fpm.di.Environment;



public class Main {
    public static void main(String[] args) {
        // Example 1
        Environment environment = new DiEnvironment();
        Container container = environment.configure((binder) -> {  
            binder.bind(FigureCircle.class);  
        });

        System.out.println(environment); 
        System.out.println(container); 
        System.out.println(container.getComponent(FigureCircle.class));
        container.getComponent(FigureCircle.class).printIt(); 

        // Example 2
        Container container2 = environment.configure((binder) -> {  
            binder.bind(FigureCircle.class);
            binder.bind(FigureSquare.class);
            binder.bind(SingleCircle.class);
            binder.bind(Page.class);
        });

        Page p = container2.getComponent(Page.class);   
        SingleCircle sc = p.getSingleCircle();
        FigureSquare fs = p.getFigureSquare();
        
        System.out.println(p); 
        System.out.println(sc);  
        System.out.println(fs);         
    }
}
