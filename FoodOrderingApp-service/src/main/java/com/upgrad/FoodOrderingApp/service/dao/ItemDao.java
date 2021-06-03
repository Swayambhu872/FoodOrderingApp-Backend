package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ItemEntity> getItemsByPopularity(final long restaurantId) {
        return entityManager.createNamedQuery("topFiveItemsByRestaurant", ItemEntity.class)
                .setParameter(0, restaurantId)
                .getResultList();
    }

    //To get ItemEntity by its UUID if no result then null is returned.
    public ItemEntity getItemByUuid(String uuid) {
        try {
            ItemEntity itemEntity = entityManager.createNamedQuery("getItemByUUID", ItemEntity.class).setParameter("uuid",uuid).getSingleResult();
            return itemEntity;
        }catch (NoResultException nre) {
            return null;
        }
    }

}