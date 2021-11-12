package tuvarna.sit.busservices.presentation.models;

public class UserTypeListView {
    private String type;


    public UserTypeListView(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s", type);
    }
}
