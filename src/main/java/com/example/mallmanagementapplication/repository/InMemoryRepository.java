package com.example.mallmanagementapplication.repository;

import com.example.mallmanagementapplication.model.Identifiable;
import java.util.*;

/*
 Implementare generică în memorie, folosește un HashMap pentru stocare.
 @param <T> tipul entității care implementează Identifiable (adică are getId()).

<T extends Identifiable> este o clasă generică, care funcționează pentru
orice tip de obiect (Customer, Shop, Purchase, etc.), dar:
- doar dacă acel tip (T) implementează interfața Identifiable.
- adică trebuie să aibă o metodă getId() (altfel compilatorul nu ar ști cum să obțină id-ul).

*/
public class InMemoryRepository<T extends Identifiable> implements Repository<T> {

    protected Map<String, T> store = new HashMap<>();

    @Override
    public void save(T entity) {
        store.put(entity.getId(), entity);
    }

    @Override
    public T findById(String id) {
        return store.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(String id) {
        store.remove(id);
    }
}
