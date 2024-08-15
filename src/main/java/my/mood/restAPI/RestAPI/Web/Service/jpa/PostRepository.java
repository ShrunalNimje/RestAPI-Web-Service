package my.mood.restAPI.RestAPI.Web.Service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import my.mood.restAPI.RestAPI.Web.Service.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
