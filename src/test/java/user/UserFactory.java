package user;

import utils.PropertyReader;

public class UserFactory {

    public static User withStandartPermission() {
        return new User(PropertyReader.getProperty("sauce.standard_user"),
                PropertyReader.getProperty("sauce.password"));
    }

    public static User withLockedPermission() {
        return new User(PropertyReader.getProperty("sauce.locked_user"),
                PropertyReader.getProperty("sauce.password"));
    }

    public static User withEmptyLogin() {
        return new User("", PropertyReader.getProperty("sauce.password"));
    }

    public static User withEmptyPassword() {
        return new User(PropertyReader.getProperty("sauce.standard_user"), "");
    }

    public static User withNotExistUser() {
        return new User(PropertyReader.getProperty("sauce.not_exist_user"),
                PropertyReader.getProperty("sauce.password"));
    }
}
