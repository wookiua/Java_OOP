package dilib;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SingleCircle {
    private final FigureCircle circle;

    @Inject
    public SingleCircle (FigureCircle fc) {
        this.circle = fc;
    }

    public FigureCircle getCircle() {
        return circle;
    }
}
