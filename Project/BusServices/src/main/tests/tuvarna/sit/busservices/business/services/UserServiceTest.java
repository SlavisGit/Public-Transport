package tuvarna.sit.busservices.business.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.data.entities.UserType;

class UserServiceTest {
    private static UserService userService;
    @BeforeEach
    void setUp() {
        userService = UserService.getInstance();
    }

    @Test
    void getUserLogin() {
        User user = new User();
        UserType userType = UserTypeService.getInstance().getById(1L);
        user.setUserType(userType);
        user.setAdmin(AdministratorService.getInstance().getById(1L));
        user.setUsername("aaa");
        user.setID(1L);
        user.setPassword("123");


        User admin = userService.getUserLogin("123", "aaa", "Admin");
        Assertions.assertEquals(user.getID(), admin.getID());
        Assertions.assertEquals(user.getUsername(), admin.getUsername());
        Assertions.assertEquals(user.getPassword(), admin.getPassword());
    }

    @Test
    void getByIdStation() {
        User byIdStation = userService.getByIdStation(1L);

        Assertions.assertEquals(3, byIdStation.getID());
        Assertions.assertEquals("sta", byIdStation.getUsername());
        Assertions.assertEquals("321", byIdStation.getPassword());
    }

    @Test
    void getByIdCashier() {

        User byIdStation = userService.getByIdCashier(1L);

        Assertions.assertEquals(4, byIdStation.getID());
        Assertions.assertEquals("ty", byIdStation.getUsername());
        Assertions.assertEquals("321", byIdStation.getPassword());
    }

    @Test
    void getByIdCompany() {

        User byIdStation = userService.getByIdCompany(1L);

        Assertions.assertEquals(2, byIdStation.getID());
        Assertions.assertEquals("com", byIdStation.getUsername());
        Assertions.assertEquals("321", byIdStation.getPassword());
    }
}