package antifraud.dto;

public enum UserRoles {
    ADMINISTRATOR("ROLE_ADMINISTRATOR"),
    MERCHANT("ROLE_MERCHANT");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
