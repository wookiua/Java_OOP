package dilib;

import javax.inject.Inject;

public class B {
    private C c;
    @Inject
    public B(C c) {
        this.c = c;
    }
}
