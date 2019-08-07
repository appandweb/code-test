
package es.voghdev.prjdagger2.global;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.squareup.picasso.Picasso;

import java.io.File;

import es.voghdev.prjdagger2.global.di.DaggerRootComponent;
import es.voghdev.prjdagger2.global.di.MainModule;
import es.voghdev.prjdagger2.global.di.RootComponent;
import es.voghdev.prjdagger2.ui.picasso.PicassoImageCache;

public class App extends Application {
    public static final String IMAGES_DIR = "images";

    private RootComponent component;
    private PicassoImageCache cache;
    private MainModule mainModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjector();
        initializeImageCache();
    }

    private void initializeDependencyInjector() {
        mainModule = new MainModule(this);
        component = DaggerRootComponent.builder()
                .mainModule(mainModule)
                .build();
        component.inject(this);
    }

    public void initializeImageCache() {
        File cacheDir = getPicturesDir();
        if (cacheDir == null) {
            return;
        }

        cacheDir.mkdirs();
        cache = new PicassoImageCache(this, cacheDir);
        Picasso picasso = new Picasso.Builder(this)
                //        .downloader(new OkHttpDownloader(cacheDir))
                .memoryCache(cache)
                .build();
        //Picasso.setSingletonInstance(picasso);
    }

    public MainModule getMainModule() {
        return mainModule;
    }

    public File getPicturesDir() {
        File f = getExternalFilesDir(IMAGES_DIR);
        return f;
    }

    public RootComponent getComponent() {
        return component;
    }

    @VisibleForTesting
    public void setComponent(RootComponent component) {
        this.component = component;
    }
}
