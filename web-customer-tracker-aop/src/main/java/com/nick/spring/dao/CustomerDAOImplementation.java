package com.nick.spring.dao;

import com.nick.spring.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

    private final SessionFactory sessionFactory;

    public CustomerDAOImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers() {

        Session session = sessionFactory.getCurrentSession();


        //sort by last name with HQL by adding "order by..." If you don't want to have sorting just delete "order by"
        Query<Customer> query =
                session.createQuery("from Customer order by lastName", Customer.class);

        List<Customer> customerList = query.getResultList();

        return customerList;
    }

    @Override
    public void saveCustomer(Customer customer) {

        Session session = sessionFactory.getCurrentSession();

//      save OR update

        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {

        Session session = sessionFactory.getCurrentSession();

        Customer customer = session.get(Customer.class, id);

        return customer;
    }

    @Override
    public void deleteCustomer(int id) {

        Session session = sessionFactory.getCurrentSession();

        //create query and customerId then set it as a parameter from the method

        Query query = session.createQuery("delete from Customer where id=:customerId");

        //here set as a parameter
        query.setParameter("customerId", id);

        query.executeUpdate();

    }
}
