package org.massonus.repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.massonus.SessionCreator;
import org.massonus.entity.AdditionalMaterial;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdditionalMaterialRepo implements UniversalRepository {

    public AdditionalMaterialRepo() {
    }

    public void addMaterial(final AdditionalMaterial material) {

        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {

            session.save(material);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            transaction.rollback();

        } finally {
            session.close();
        }
    }

    public List<AdditionalMaterial> getMaterialList() {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            final Query<AdditionalMaterial> fromChild = session.createQuery("from AdditionalMaterial", AdditionalMaterial.class);
            return fromChild.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public AdditionalMaterial getMaterialById(int id) {
        final SessionFactory sessionFactory = SessionCreator.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            final Query<AdditionalMaterial> materialQuery = session.createQuery("from AdditionalMaterial where material_id = :id", AdditionalMaterial.class);
            materialQuery.setParameter("id", id);
            List<AdditionalMaterial> list = materialQuery.list();
            return list.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean updateMaterial(final AdditionalMaterial material) {
        try (final Session session = SessionCreator.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(material);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean deleteMaterial(final AdditionalMaterial material) {
        try (final Session session = SessionCreator.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(material);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
