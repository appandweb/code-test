
package es.voghdev.prjdagger2.ui.presenter;

public abstract class Presenter<T> {
    public abstract void initialize();

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();

    protected T view;

    public void setView(T v) {
        view = v;
    }
}
