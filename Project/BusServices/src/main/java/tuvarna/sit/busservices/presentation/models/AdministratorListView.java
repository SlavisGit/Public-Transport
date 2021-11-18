package tuvarna.sit.busservices.presentation.models;

public class AdministratorListView {
    private String firstName;
    private String lastName;

    public AdministratorListView(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", firstName, lastName);
    }
}
