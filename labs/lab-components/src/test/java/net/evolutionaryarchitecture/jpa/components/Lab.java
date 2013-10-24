package net.evolutionaryarchitecture.jpa.components;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class Lab {

    @Autowired
    DataSource dataSource;

	@PersistenceContext
	private EntityManager em;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    @Repeat(2)
    public void createPerson() throws Exception {
        Assert.assertEquals(0, count("T_PERSON"));

        // TODO: Create and store a person.
        // A suggestion is to create a constructor in Person accepting a Name
        // object.
        // Hint: Note the field 'em' above. Use that as Entity Manager.

        printTable("T_PERSON");
        Assert.assertEquals(1, count("T_PERSON"));

    }

    @Test
    public void createPersonWithAddresses() throws Exception {
        Assert.assertEquals(0, count("T_PERSON"));
        Assert.assertEquals(0, count("T_ADDRESS"));

        // TODO: Create and store a person with two addresses

        Assert.assertEquals(1, count("T_PERSON"));
        Assert.assertEquals(2, count("T_ADDRESS"));

        printTable("T_PERSON");
        printTable("T_ADDRESS");

    }

    private long count(String table) throws SQLException {
        return jdbcTemplate.queryForInt("select count(*) from " + table);
    }

    private void printTable(String table) throws SQLException {
        System.out.println(jdbcTemplate.queryForList("select * from " + table));
    }

}
