
package es.voghdev.prjdagger2.datasource.api;

import java.util.List;

import es.voghdev.prjdagger2.datasource.api.model.UserApiEntry;

public class GetUsersResponse {
    private List<UserApiEntry> results;

    public List<UserApiEntry> getResults() {
        return results;
    }
}
