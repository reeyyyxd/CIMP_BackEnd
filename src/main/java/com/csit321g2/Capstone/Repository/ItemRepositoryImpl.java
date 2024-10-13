package com.csit321g2.Capstone.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csit321g2.Capstone.Entity.ItemEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ItemEntity> findByFilters(String accountablePerson, String department, 
                                        String designation, String unitOfMeasurement, 
                                        String status, String supplier, 
                                        String building, String room, 
                                        String name, String model, String type, 
                                        LocalDate invoiceDate, String lifespan) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemEntity> cq = cb.createQuery(ItemEntity.class);
        Root<ItemEntity> item = cq.from(ItemEntity.class);
        
        List<Predicate> predicates = new ArrayList<>();

        // Filter by Accountable Person (concatenated First Name and Last Name)
        if (accountablePerson != null && !accountablePerson.isEmpty()) {
            Expression<String> fullName = cb.concat(item.get("accPerson").get("fname"), 
                                    cb.concat(cb.literal(" "), item.get("accPerson").get("lname")));
            predicates.add(cb.like(fullName, "%" + accountablePerson + "%"));
        }
        
        // Other filters
        if (department != null && !department.isEmpty()) {
            predicates.add(cb.equal(item.get("department"), department));
        }
        if (designation != null && !designation.isEmpty()) {
            predicates.add(cb.equal(item.get("designation"), designation));
        }
        if (unitOfMeasurement != null && !unitOfMeasurement.isEmpty()) {
            predicates.add(cb.equal(item.get("unitOfMeasurement"), unitOfMeasurement));
        }
        if (status != null && !status.isEmpty()) {
            predicates.add(cb.equal(item.get("status"), status));
        }
        if (supplier != null && !supplier.isEmpty()) {
            predicates.add(cb.equal(item.get("supplier"), supplier));
        }
        if (building != null && !building.isEmpty()) {
            predicates.add(cb.equal(item.get("location").get("building"), building));
        }
        if (room != null && !room.isEmpty()) {
            predicates.add(cb.equal(item.get("location").get("room"), room));
        }
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.equal(item.get("description").get("name"), name));
        }
        if (model != null && !model.isEmpty()) {
            predicates.add(cb.equal(item.get("description").get("model"), model));
        }
        if (type != null && !type.isEmpty()) {
            predicates.add(cb.equal(item.get("description").get("type"), type));
        }
        if (invoiceDate != null) {
            predicates.add(cb.equal(item.get("invoiceDate"), invoiceDate));
        }
        if (lifespan != null && !lifespan.isEmpty()) {
            predicates.add(cb.equal(item.get("lifespan"), lifespan));
        }

        // Apply predicates
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public Float sumByFilters(String accountablePerson, String department, 
                            String designation, String unitOfMeasurement, 
                            String status, String supplier, 
                            String building, String room, 
                            String name, String model, String type, 
                            LocalDate invoiceDate, String lifespan) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Float> cq = cb.createQuery(Float.class);
        Root<ItemEntity> item = cq.from(ItemEntity.class);
        
        // Create predicates based on the provided filters
        List<Predicate> predicates = new ArrayList<>();

        if (accountablePerson != null && !accountablePerson.isEmpty()) {
            Expression<String> fullName = cb.concat(item.get("accPerson").get("fname"), 
                                cb.concat(cb.literal(" "), item.get("accPerson").get("lname")));
            predicates.add(cb.like(fullName, "%" + accountablePerson + "%"));
        }
        if (department != null && !department.isEmpty()) {
            predicates.add(cb.equal(item.get("department"), department));
        }
        if (designation != null && !designation.isEmpty()) {
            predicates.add(cb.equal(item.get("designation"), designation));
        }
        if (unitOfMeasurement != null && !unitOfMeasurement.isEmpty()) {
            predicates.add(cb.equal(item.get("unitOfMeasurement"), unitOfMeasurement));
        }
        if (status != null && !status.isEmpty()) {
            predicates.add(cb.equal(item.get("status"), status));
        }
        if (supplier != null && !supplier.isEmpty()) {
            predicates.add(cb.equal(item.get("supplier"), supplier));
        }
        if (building != null && !building.isEmpty()) {
            predicates.add(cb.equal(item.get("location").get("building"), building));
        }
        if (room != null && !room.isEmpty()) {
            predicates.add(cb.equal(item.get("location").get("room"), room));
        }
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.equal(item.get("description").get("name"), name));
        }
        if (model != null && !model.isEmpty()) {
            predicates.add(cb.equal(item.get("description").get("model"), model));
        }
        if (type != null && !type.isEmpty()) {
            predicates.add(cb.equal(item.get("description").get("type"), type));
        }
        if (invoiceDate != null) {
            predicates.add(cb.equal(item.get("invoiceDate"), invoiceDate));
        }
        if (lifespan != null && !lifespan.isEmpty()) {
            predicates.add(cb.equal(item.get("lifespan"), lifespan));
        }

        // Apply predicates
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        // Sum the desired field(s), for example, totalCost
        cq.select(cb.sum(item.get("totalCost")));
        return entityManager.createQuery(cq).getSingleResult();
    }
}