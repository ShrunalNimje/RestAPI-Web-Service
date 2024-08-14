package my.mood.restAPI.RestAPI.Web.Service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import my.mood.restAPI.RestAPI.Web.Service.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
