package com.luzzetti.cruddemo.dao;

import com.luzzetti.cruddemo.entity.Employee;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    //  Define field for entityManager

    private EntityManager entityManager;

    //  Setup constructor injection

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        //  get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //  create a query
        Query<Employee> theQuery =
                currentSession.createQuery("from Employee", Employee.class);

        //  execute the query and get result list
        List<Employee> employees = theQuery.getResultList();

        //  return results
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Employee theEmployee = currentSession.get(Employee.class, theId);

        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {

        Session currentSession = entityManager.unwrap(Session.class);

        //se id == 0, lo crea
        //se if != 0, update
        currentSession.saveOrUpdate(theEmployee);

    }

    @Override
    public void deleteById(int theID) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery =
                currentSession.createQuery("delete from Employee where id=:employeeId");

        theQuery.setParameter("employeeId", theID);

        theQuery.executeUpdate();

    }

}
