package dilib;

import javax.inject.Inject;

public class Page {
    private final SingleCircle scircle;
    private final FigureSquare fsquare;

    @Inject
    public Page (SingleCircle sc, FigureSquare fs) {
        this.scircle = sc;
        this.fsquare = fs;        
    }

    public SingleCircle getSingleCircle() {
        return scircle;
    }

    public FigureSquare getFigureSquare() {
        return fsquare;
    }    
}
