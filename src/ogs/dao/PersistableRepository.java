package ogs.dao;

public interface PersistableRepository<K, V extends Identifiable<K>> extends Repository<K,V>, Persistable {
}
