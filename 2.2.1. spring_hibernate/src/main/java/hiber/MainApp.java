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

      userService.clean();
      carService.clean();

      Car car1 = new Car("Lada", 1);
      Car car2 = new Car("Moskvitz", 2);
      Car car3 = new Car("Volga", 3);
      Car car4 = new Car("Tavria", 4);

      User user1 = new User("Ivan", "Borov", "borov@mail.ru");
      User user2 = new User("Pyotr", "Tverdyshchenko", "peterhard@mail.ru");
      User user3 = new User("Ilya", "Staroverov", "Elijah42@mail.ru");
      User user4 = new User("Gleb", "Konkin", "canoncancrizans@mail.ru");

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {

         System.out.println(user.toString());
      }

      System.out.println(userService.getUserByCar(car1.getModel(), car1.getSeries()));
      System.out.println(userService.getUserByCar(car2.getModel(), car2.getSeries()));
      System.out.println(userService.getUserByCar(car3.getModel(), car3.getSeries()));
      System.out.println(userService.getUserByCar(car4.getModel(), car4.getSeries()));

      context.close();
   }
}
