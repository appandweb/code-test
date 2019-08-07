
package es.voghdev.prjdagger2.datasource.api.model;

import es.voghdev.prjdagger2.global.model.User;

public class UserApiEntry {

    private static final String SPACE = " ";

    IdApiEntry id = new IdApiEntry();
    UserNameApiEntry name = new UserNameApiEntry();
    String email = "";
    String gender = "";
    UserPictureApiEntry picture = new UserPictureApiEntry();
    UserLocationApiEntry location = new UserLocationApiEntry();
    String md5 = "";
    UserDateOfBirthApiEntry dob;

    public User parseUser() {
        User u = new User();
        u.setId(id.parseId());
        u.setEmail(email);
        u.setAddress(location.getStreet());
        u.setName(String.format("%s %s %s", name.getTitle(), name.getFirst(), name.getLast()));
        u.setThumbnail(picture.getThumbnail());
        return u;
    }
}
