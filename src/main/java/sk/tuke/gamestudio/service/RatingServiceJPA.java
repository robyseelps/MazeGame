package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Transactional
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void setRating(Rating rating) throws RatingException {
        if(rating.getRating() > 0 && rating.getRating() < 6) {
            try {
                Rating existingRating = (Rating) entityManager.createNamedQuery("Rating.getRatingColumn").setParameter("game", rating.getGame()).setParameter("player", rating.getPlayer()).getSingleResult();
                if (existingRating != null) {
                    existingRating.setRating(rating.getRating());
                    existingRating.setRatedOn(rating.getRatedOn());
                    entityManager.merge(existingRating);
                } else {
                    entityManager.persist(rating);
                }
            } catch (NoResultException e) {
                entityManager.persist(rating);
            }
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
       Double averageRating = (Double) entityManager.createNamedQuery("Rating.getAverageRating").setParameter("game",game).getSingleResult();
       if (averageRating != null) {
           return averageRating.intValue();
       }
       return  0;

    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return (int) entityManager.createNamedQuery("Rating.getRating").setParameter("game",game).
                setParameter("player",player).getSingleResult();

    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRatings").executeUpdate();
    }
}
