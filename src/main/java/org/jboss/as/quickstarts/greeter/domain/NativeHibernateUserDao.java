package org.jboss.as.quickstarts.greeter.domain;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class NativeHibernateUserDao implements UserDao {

    protected SessionFactory sessionFactory;

    Class<User> clazz = User.class;

    protected Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    @Override
    @Transactional
    public User getForUsername(String username) {
        return (User) getCurrentSession().createQuery("select u from User u where u.username = :username")
            .setParameter("username", username).uniqueResult();
    }

    @Override
    @Transactional
    public User createUser(User user) {
        User mergedUser = (User) getCurrentSession().merge(user);
        getCurrentSession().flush();
        return mergedUser;

    }

}
