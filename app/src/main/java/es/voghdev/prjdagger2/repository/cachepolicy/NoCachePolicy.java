
package es.voghdev.prjdagger2.repository.cachepolicy;

public class NoCachePolicy implements CachePolicy {
    @Override
    public boolean isCacheValid() {
        return false;
    }
}
