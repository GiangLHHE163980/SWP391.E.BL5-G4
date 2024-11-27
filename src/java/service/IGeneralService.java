/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;

/**
 *
 * @author tuana
 */
public interface IGeneralService<T> {
    public void add(T t);

    public List<T> findAll();

    public T findById(int id);

    public void update(int id,T t);

    public void delete(int id);
}
