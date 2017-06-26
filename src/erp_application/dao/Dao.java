package erp_application.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	int insertItem(T item)throws SQLException;
	int updateItem(T item);
	T selectByItem(int idx);
	List<T> selectByAllItems();
	void deleteItem(int idx);
	int rowCnt();
	int selectNextNo();
}
