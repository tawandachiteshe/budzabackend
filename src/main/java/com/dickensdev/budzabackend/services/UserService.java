package com.dickensdev.budzabackend.services;

import com.dickensdev.budzabackend.models.UserInfo;
import com.dickensdev.budzabackend.models.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
//implements UserDetailsService
@Repository
public class UserService {
    private UserRoles userRoles;
    private Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("secret", 10000, 128);
    private final JdbcTemplate jdbcTemplate;
    private List<UserInfo> userList;
    private Date date;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        date = Date.from(Instant.now());
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<UserInfo> getAllpeople(){
        //        String username, String email, String password, UUID userId

        return jdbcTemplate.query("select * from userdb",(resultSet, i) -> {
            String name = resultSet.getString("username");
            UUID userid =  UUID.fromString(resultSet.getString("userid"));
            String uemail = resultSet.getString("useremail");
            String password = resultSet.getString("userpassword");
            boolean enabled = resultSet.getBoolean("enabled");
            String role = resultSet.getString("user_role");
            String date = resultSet.getString("usertime");
            return new UserInfo(name,uemail,password,userid,enabled,role,date);
        });
    }

    public int createUser(UserInfo userInfo){


        String sql = "insert into userdb(username,userid,useremail,userpassword,enabled,user_role,usertime) values (?,uuid_generate_v4(),?,?,?,?,\'"+date.toLocaleString() +"\')";
        jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setString(1,userInfo.getUsername());
                preparedStatement.setString(2,userInfo.getEmail());
                preparedStatement.setString(3,encoder.encode(userInfo.getPassword()));
                preparedStatement.setBoolean(4,userInfo.getEnabled());
                preparedStatement.setString(5,userInfo.getRoles());
                return preparedStatement.execute();
            }
        });

        return 1;
    }

    public boolean validateLogin(UserInfo userInfo){
        Boolean isValid = false;
        List<UserInfo> validate = getAllpeople();

        for (UserInfo user: validate) {
            if(user.getEmail().equals(userInfo.getEmail())&&encoder.matches(userInfo.getPassword(),user.getPassword())){
                isValid = true;
            }
        }
        return isValid;
    }

    public boolean confirmationVerifier(UserInfo info){
        if (info.getEnabled()){
            return info.getEnabled();
        }else {
            return info.getEnabled();
        }
    }

    public boolean validateRegister(UserInfo info){
        String sql = "select * from userdb where username=" +"\'"+ info.getUsername() +"\'";
        List<UserInfo> users = jdbcTemplate.query(sql,(resultSet, i) -> {
            String name = resultSet.getString("username");
            UUID userid =  UUID.fromString(resultSet.getString("userid"));
            String uemail = resultSet.getString("useremail");
            String password = resultSet.getString("userpassword");
            boolean enabled = resultSet.getBoolean("enabled");
            String role = resultSet.getString("user_role");
            String date = resultSet.getString("usertime");
            return new UserInfo(name,uemail,password,userid,enabled,role,date);
        });

        if (users.isEmpty()){
            return true;
        }
        else {
            return false;
        }

    }


/*
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        List<userInfo> users = jdbcTemplate.query("select * from userdb where useremail=" + "\'" + s + "\'",(resultSet, i) -> {
            String name = resultSet.getString("username");
            UUID userid =  UUID.fromString(resultSet.getString("userid"));
            String uemail = resultSet.getString("useremail");
            String password = resultSet.getString("userpassword");
            boolean enabled = resultSet.getBoolean("enabled");
            String role = resultSet.getString("user_role");
            String date = resultSet.getString("usertime");
            return new userInfo(name,uemail,password,userid,enabled,role,date);
        });
        userInfo user = users.get(0);
        if(user.getEnabled()){
            return new SecurityProperties.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(Arrays.asList(new Role(user.getRoles()))));
        }else {
            throw new UsernameNotFoundException("Please confirm your email");
        }

    }

 */
    public UserInfo findByEmail(String email){
        UserInfo userInfo = null;
        for (UserInfo user:userList) {
            if(user.getEmail().equals(email)){
                userInfo = new UserInfo(user.getUsername(),user.getEmail(),user.getPassword(),user.getUserId(),user.getEnabled(),userInfo.getRoles(),userInfo.getDate());
            }
        }
        return userInfo;
    }
    public void enableUser(UserInfo user){
        String sql = "update userdb set enabled="+user.getEnabled()+" where userid="+"\'"+user.getUserId()+"\'";
        jdbcTemplate.execute(sql);

    }
    public List<UserInfo> findById(String token){
        String sql = "select * from userdb where userid=" + "\'" + token + "\'";
        return jdbcTemplate.query(sql,(resultSet, i) -> {
            String name = resultSet.getString("username");
            UUID userid =  UUID.fromString(resultSet.getString("userid"));
            String uemail = resultSet.getString("useremail");
            String password = resultSet.getString("userpassword");
            boolean enabled = resultSet.getBoolean("enabled");
            String role = resultSet.getString("user_role");
            String date = resultSet.getString("usertime");
            return new UserInfo(name,uemail,password,userid,enabled,role,date);
        });
    }

    public void deleteFindById(String token){
        String sql = "delete from userdb where userid=" + "\'" + token + "\'";
        jdbcTemplate.execute(sql);
    }
/*
    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

 */
}