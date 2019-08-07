
package es.voghdev.prjdagger2.ui.renderer;

import android.content.Context;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.voghdev.prjdagger2.global.model.User;

public class UserRendererBuilder extends RendererBuilder<User> {

    public UserRendererBuilder(Context context, UserRenderer.OnUserClicked onUserClicked) {
        Collection<Renderer<User>> prototypes = getPrototypes(context, onUserClicked);
        setPrototypes(prototypes);
    }

    @Override
    protected Class getPrototypeClass(User content) {
        return UserRenderer.class;
    }

    private List<Renderer<User>> getPrototypes(Context context, UserRenderer.OnUserClicked onUserClicked) {
        List<Renderer<User>> prototypes = new LinkedList<Renderer<User>>();

        UserRenderer userRenderer = new UserRenderer(context, onUserClicked);
        prototypes.add(userRenderer);

        return prototypes;
    }
}