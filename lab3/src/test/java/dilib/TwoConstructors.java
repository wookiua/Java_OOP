package dilib;

import javax.inject.Inject;


public class TwoConstructors {
    private SingleCircle scircle;
    private FigureCircle circle;


    @Inject
    public TwoConstructors (SingleCircle sc) {
        this.scircle = sc;
    }


    @Inject
    public TwoConstructors (FigureCircle c) {
        this.circle = c;
    }
}
