package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class UserServiceJPA implements UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserFromLogin(String login) throws UserException {
        try {
            return (User) entityManager.createNamedQuery("User.getUserFromLogin")
                    .setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getUserFromLoginAndPass(String login, String password) throws UserException {
        try {
            return (User) entityManager.createNamedQuery("User.getUserFromLoginAndPass")
                    .setParameter("login", login).setParameter("password", password).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addUser(User user) throws UserException {
        entityManager.persist(user);
    }
}
