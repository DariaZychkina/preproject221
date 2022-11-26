package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);
      Car car1 = new Car("Chevrolet", 5454);
      Car car2 = new Car("Buick", 5436);
      Car car3 = new Car("Volvo", 5476);
      carService.add(car1);
      carService.add(car2);
      carService.add(car3);


      User user1 = new User("Vito", "Corleone", "godfather@mail.com");
      user1.setCar(car1);
      User user2 = new User("Tom", "Hagen", "thebestconsigliori@mail.com");
      user2.setCar(car2);
      User user3 = new User("Peter", "Clemenza", "comrade@mail.com");
      user3.setCar(car3);
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }
      System.out.println(userService.getUserOnCar("Volvo", 5476));
      context.close();
   }
}
