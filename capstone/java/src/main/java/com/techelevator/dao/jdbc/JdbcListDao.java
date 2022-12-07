package com.techelevator.dao.jdbc;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.ListDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.model.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcListDao implements ListDao {
    private final JdbcTemplate jdbcTemplate;
    private final GroupDao groupDao;
    private final UserDao userDao;

    public JdbcListDao(DataSource dataSource, GroupDao groupDao, UserDao userDao) {
        this.jdbcTemplate = new JdbcTemplate((dataSource));
        this.groupDao = groupDao;
        this.userDao = userDao;
    }

    @Override
    public List getList(int groupId, int listId, String username) {
        String sql = "SELECT * FROM list WHERE group_id = ? AND list_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, groupId, listId);
        return mapRowToList(rs);
    }
    //CREATE TABLE list_item (
    //    list_item_id int NOT NULL DEFAULT nextval('seq_list_item_id'),
    //    list_id int NOT NULL,
    //    date_modified varchar(50) NULL,
    //    quantity int NOT NULL,
    //    last_modifier int NOT NULL,
    //    description varchar(500),
    //    CONSTRAINT PK_list_item PRIMARY KEY (list_item_id),
    //    CONSTRAINT FK_list_item_list FOREIGN KEY (list_id) REFERENCES list (list_id),
    //    CONSTRAINT chk_quantity CHECK (quantity > 0)
    //    );

    @Override
    public void createList(int groupId, int userId, String name) {
        String sql = "INSERT INTO list (list_id, group_id, list_title, description, claimed, date_modified) " +
                "VALUES (DEFAULT, ?, ?, NULL, NULL, 'test');";
        Integer itemId = jdbcTemplate.queryForObject(sql, Integer.class, groupId, name);
        //return false;
    }

    @Override
    public void deleteList(int groupId, String username, String name) {
        {
            int userId = userDao.findIdByUsername(username);
            int ownerId = groupDao.getGroupById(groupId).getGroupOwnerId();

            if (ownerId == userId) {
                String sql = "DELETE FROM list WHERE list_title = ?;";
                try {
                    jdbcTemplate.update(sql, name);
                } catch (DataAccessException e) {
                    throw new DeleteException(e);
                }
            }
        }
        //return false;
    }

    @Override
    public void updateList(List list, String name) {
            String sql = "UPDATE list set list_title = ?, description = ?, claimed = ?, date_modified = GETDATE() WHERE list_id = ?;";
            try {
                jdbcTemplate.update(sql, list.getListName(), list.getDescription(), list.getClaimedId(), list.getListId());
            } catch (DataAccessException e) {
                throw new GetException(e);
            }
        }

    private List mapRowToList(SqlRowSet rs) {
        List list = new List();
        list.setListId(rs.getInt("list_id"));
        list.setGroupId(rs.getInt("group_id"));
        list.setDescription(rs.getString("description"));
        list.setListName(rs.getString("list_name"));
        return list;
    }
}