package com.example.Bank_Management_System.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Bank_Management_System.models.User;

@Repository
public interface UserRepository  extends CrudRepository<User, Integer> {

    @Query(value = "SELECT email FROM Users WHERE email = :email", nativeQuery = true)
    String getUserEmail(@Param("email")String email);

    @Query(value = "SELECT password FROM Users WHERE email = :email", nativeQuery = true)
    String getUserPassword(@Param("email")String email);

    @Query(value = "SELECT verified FROM Users WHERE email = :email", nativeQuery = true)
    int isVerified(@Param("email")String email);

    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    User getUserDetails(@Param("email")String email);

    @Modifying
    @Query(value = "INSERT INTO Users (first_name, last_name, email, id_num, password, token, code) VALUES" +
            "(:first_name, :last_name, :email, :id_num, :password, :token, :code)", nativeQuery = true )
    @Transactional
    void registerUser(@Param("first_name")String first_name,
                      @Param("last_name") String last_name,
                      @Param("email")String email,
                      @Param("id_num")String id_num,
                      @Param("password")String password,
                      @Param("token") String token,
                      @Param("code")int code);

    @Modifying
    @Query(value = "UPDATE Users SET token=null, code=null, verified=1, verified_at=NOW(), updated_at=NOW() WHERE " +
            "token= :token AND code= :code", nativeQuery = true)
    @Transactional
    void verifyAccount(@Param("token")String token, @Param("code") String code);

    @Query(value = "SELECT token FROM Users WHERE token = :token" , nativeQuery = true)
    String checkToken(@Param("token")String token);

}
