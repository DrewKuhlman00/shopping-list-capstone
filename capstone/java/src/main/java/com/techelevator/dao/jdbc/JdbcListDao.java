package com.techelevator.dao.jdbc;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.ListDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.model.Group;
import com.techelevator.model.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;

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
    public java.util.List<List> getAllListsForGroup(int groupId) {
        java.util.List<List> lists = new ArrayList<>();
        String sql = "SELECT * FROM list where group_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, groupId);
        while (results.next()) {
            List list = mapRowToList(results);
            lists.add(list);
        }
        return lists;
    }

    @Override
    public List getList(int groupId, int listId) {
        String sql = "SELECT * FROM list WHERE group_id = ? AND list_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, groupId, listId);
        try {
            if (rs.next()) {
                return mapRowToList(rs);
            }
        }
        catch (DataAccessException e) {
            throw new GetException(e);
        }
        return new List();
    }

    @Override
    public void createList(List list) {
        String sql = "INSERT INTO list (group_id, list_title, description, claimed, date_modified) " +
                "VALUES (?, ?, ?, ?, ?);";
        try {
            jdbcTemplate.update(sql, list.getGroupId(), list.getListName(), list.getDescription(), list.getClaimedId(), list.getDateModified());
        } catch (DataAccessException e) {
            throw new GetException(e);
        }
    }

    //TODO: Who can delete a list? Move verification logic to controller with helper method?
    @Override
    public void deleteList(int groupId, int listId) {
//            int userId = userDao.findIdByUsername();
//            int ownerId = groupDao.getGroupById(groupId).getGroupOwnerId();
//
//            if (ownerId == userId) {
        String sql = "DELETE FROM list WHERE group_id = ? AND list_id = ?;";
                try {
                    jdbcTemplate.update(sql, groupId, listId);
                } catch (DataAccessException e) {
                    throw new DeleteException(e);
                }
            }

//TODO: Method returns @NotEmpty constraint error, needs fixed
    @Override
    public void updateList(List list) {
            String sql = "UPDATE list set list_title = ?, description = ?, claimed = ?, date_modified = ? WHERE group_id = ? AND list_id = ?;";
            try {
                jdbcTemplate.update(sql, list.getListName(), list.getDescription(), list.getClaimedId(), list.getDateModified(), list.getGroupId(), list.getListId());
            } catch (DataAccessException e) {
                throw new GetException(e);
            }
        }

    private List mapRowToList(SqlRowSet rs) {
        List list = new List();
        list.setListId(rs.getInt("list_id"));
        list.setGroupId(rs.getInt("group_id"));
        list.setDescription(rs.getString("description"));
        list.setListName(rs.getString("list_title"));
        list.setClaimedId(rs.getInt("claimed"));
        list.setDateModified(rs.getString("date_modified"));
        return list;
    }
}