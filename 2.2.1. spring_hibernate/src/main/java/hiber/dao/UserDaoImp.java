package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession()
              .createQuery("select u from User u join u.car c where c.model=:model and c.series=:series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      List<User> user = query.getResultList();
      if (!user.isEmpty()) {
         return user.get(0);
      }
      return null;
   }

   @Override
   public int clean(){
      String hql = "delete from User";
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      return query.executeUpdate();
   }

}
