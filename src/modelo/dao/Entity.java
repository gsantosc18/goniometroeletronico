/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;

/**
 *
 * @author myhouse
 */
public interface Entity {
    public void add(Object o);
    public void update(Object o);
    public void delete(Object o);
}
