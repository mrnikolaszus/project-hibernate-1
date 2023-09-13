package com.game.repository;

import com.game.entity.Player;
import com.game.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import javax.sql.rowset.serial.SerialStruct;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Repository(value = "db")
public class PlayerRepositoryDB implements IPlayerRepository {

    private final SessionFactory sessionFactory;
    public PlayerRepositoryDB() {
        sessionFactory = HibernateUtil.buildSessionFactory();
    }

    @Override
    public List<Player> getAll(int pageNumber, int pageSize) {
       try(Session session = sessionFactory.openSession()){
           NativeQuery<Player> nativeQuery = session.createNativeQuery("select * from rpg.player", Player.class);
           nativeQuery.setFirstResult(pageNumber * pageSize);
           nativeQuery.setMaxResults(pageSize);
           return nativeQuery.list();

       }
    }

    @Override
    public int getAllCount() {
        try(Session session = sessionFactory.openSession()){
            Query<Long> query = session.createNamedQuery("player_getAllCount", Long.class);
            Long result = query.uniqueResult();
            return result.intValue();

        }
    }

    @Override
    public Player save(Player player) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();

            session.save(player);

            transaction.commit();
            return player;

        }
    }

    @Override
    public Player update(Player player) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(player);

            transaction.commit();
            return player;

        }
    }

    @Override
    public Optional<Player> findById(long id) {
        try(Session session = sessionFactory.openSession()){
            Player player = session.find(Player.class, id);
            return Optional.of(player);
        }

    }

    @Override
    public void delete(Player player) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(player);
            transaction.commit();
        }

    }

    @PreDestroy
    public void beforeStop() {
        sessionFactory.close();
    }
}