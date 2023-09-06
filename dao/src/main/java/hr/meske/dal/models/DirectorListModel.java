/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.meske.dal.models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author 505wL
 */
public class DirectorListModel implements ListModel<Director> {

    private final List<Director> directors;
    private final List<ListDataListener> listeners;

    public DirectorListModel() {
        directors = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void addDirector(Director director) {
        directors.add(director);
        int index = directors.size() - 1;
        fireIntervalAdded(index, index);
    }

    public void removeDirector(Director director) {
        int index = directors.indexOf(director);
        if (index != -1) {
            directors.remove(index);
            fireIntervalRemoved(index, index);
        }
    }

    @Override
    public int getSize() {
        return directors.size();
    }

    @Override
    public Director getElementAt(int index) {
        return directors.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListDataListener(ListDataListener listener) {
        listeners.remove(listener);
    }

    private void fireIntervalAdded(int index0, int index1) {
        ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index0, index1);
        for (ListDataListener listener : listeners) {
            listener.intervalAdded(event);
        }
    }

    private void fireIntervalRemoved(int index0, int index1) {
        ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index0, index1);
        for (ListDataListener listener : listeners) {
            listener.intervalRemoved(event);
        }
    }
}
