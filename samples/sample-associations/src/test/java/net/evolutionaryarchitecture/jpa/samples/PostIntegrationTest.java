package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PostIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createPost() {

        Post post = new Post();
        post.postText = "JPA 2.0 rocks!";
        em.persist(post);
        
        post.userComments.add("Word");
        post.userComments.add("Right on!");

        em.flush();
    }

}
