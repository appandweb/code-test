
package es.voghdev.prjdagger2.ui.view;

import android.content.Context;
import android.widget.Toast;

import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.model.User;
import es.voghdev.prjdagger2.usecase.ShowUserClicked;

public class ShowUserClickedToastImpl implements ShowUserClicked {
    Context context;

    public ShowUserClickedToastImpl(Context context) {
        this.context = context;
    }

    @Override
    public void show(User user) {
        Toast.makeText(context, context.getString(R.string.user_was_clicked, user.getEmail()),
                Toast.LENGTH_LONG).show();
    }
}
