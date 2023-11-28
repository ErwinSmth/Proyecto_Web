package dao;

import java.util.List;

/**
 *
 * @author DAVID
 */
public interface IDAO <k>{
    
    public int add(k objeto);
    public int update(k objeto);
    public int delete(k objeto);
    public List<k> getListado();
    
}
