package dilib;


import static org.junit.Assert.assertSame;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.junit.Test;

public class LibTest {
    
    private final Environment environment = new DiEnvironment();
 
    // Step 2
    @Test(expected = CircularInjectException.class)
    public void CircularInjectException() {

        environment.configure((binder) -> {
            binder.bind(A.class);
            //binder.bind(B.class);
            //binder.bind(C.class);
        });
    }

    // Step 3    
    @Test(expected = BindException.class)
    public void RebindWithClassException() {
        environment.configure((binder) -> {
            binder.bind(Shape.class, FigureCircle.class);
            binder.bind(Shape.class, FigureSquare.class);
        });
    }    

    @Test(expected = BindException.class)
    public void RebindWithInstanceException() {
        environment.configure((binder) -> {
            binder.bind(Shape.class, FigureCircle.class);
            binder.bind(Shape.class, new FigureSquare());
        });
    }   

    // Step 4
    @Test(expected = BindException.class)
    public void BindingAbstractClass() {
        environment.configure((binder) -> {
            binder.bind(Draw.class);
        });
    }

    @Test(expected = BindException.class)
    public void BindingNotRegistered() {
        environment.configure((binder) -> {
            binder.bind(Shape.class);
        });
    }    

    @Test(expected = BindException.class)
    public void BindingNull() {
        environment.configure((binder) -> {
            binder.bind(null);
        });
    }

    // Step 5
    @Test(expected = UnregisteredComponentException.class)
    public void UnregisteredComponent() {
        Container container = environment.configure((binder) -> {});
        container.getComponent(FigureCircle.class);
    }

    // Step 6
    @Test
    public void shouldResolveSingletonWithInjection() {
        Container container = environment.configure((binder) -> {
            binder.bind(FigureCircle.class);
            binder.bind(SingleCircle.class);
        });

        // Singleton has to return only one object
        SingleCircle c1 = container.getComponent(SingleCircle.class);
        SingleCircle c2 = container.getComponent(SingleCircle.class);
        assertSame(c1, c2);
        assertSame(c1.getCircle(), c2.getCircle());
    }

    // Step 7    
    @Test(expected = BindException.class)
    public void TwoInjectionConstructors() {
        environment.configure((binder) -> {
            binder.bind(TwoConstructors.class);
        });
    }

    // Step 8
    @Test
    public void NestedDependency() {
        Container container = environment.configure((binder) -> {
            binder.bind(FigureCircle.class);  
            binder.bind(FigureSquare.class);              
            binder.bind(SingleCircle.class);
            binder.bind(Page.class);
        });
        Page p = container.getComponent(Page.class);
        assertSame(p.getSingleCircle(), container.getComponent(SingleCircle.class));
    }

}
