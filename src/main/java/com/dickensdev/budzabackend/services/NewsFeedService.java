package com.dickensdev.budzabackend.services;


import com.dickensdev.budzabackend.models.NewsFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service("NewsFeedService")
@Repository
public class NewsFeedService {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public NewsFeedService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addStory(NewsFeed story){
        String sql = "insert into stories(storyid,heading,body,category) values (uuid_generate_v4(),?,?,?)";
        jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setString(1,story.getHeading());
                preparedStatement.setString(2,story.getBody());
                preparedStatement.setString(3,story.getCategory());
                return preparedStatement.execute();
            }
        });
    }

    public List<NewsFeed> getAllStories(){
        return jdbcTemplate.query("select * from stories",(resultSet, i) -> {
            UUID storyid = UUID.fromString(resultSet.getString("storyId"));
            String tech = resultSet.getString("heading");
            String current = resultSet.getString("body");
            String news = resultSet.getString("category");

            return  new NewsFeed(storyid,tech,current,news);
        });
    }
    public void addImage(byte bytes[]){

    }
    public void deleteAll(){
        String query = "DELETE FROM stories";
        jdbcTemplate.execute(query);
    }

    public void deleteById(String id){
        String query = "DELETE FROM stories where storyid=" + "\'" + id + "\'";
        jdbcTemplate.execute(query);
    }

}