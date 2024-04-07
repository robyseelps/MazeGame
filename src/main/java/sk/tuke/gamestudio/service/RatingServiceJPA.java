package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void setRating(Rating rating) throws RatingException {
        entityManager.merge(rating);
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
       Double averageRating = (Double) entityManager.createNamedQuery("Rating.getAverageRating").setParameter("game",game).getSingleResult();
        return  averageRating.intValue();
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
