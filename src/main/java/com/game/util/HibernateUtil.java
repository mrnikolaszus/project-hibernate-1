package com.game.util;

import com.game.entity.Player;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    public static SessionFactory buildSessionFactory(){
        Configuration configuration = buildConfiguration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }

    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Player.class);
        return configuration;
    }
}
