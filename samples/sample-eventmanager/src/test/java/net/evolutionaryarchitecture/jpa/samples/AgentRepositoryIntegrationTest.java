package net.evolutionaryarchitecture.jpa.samples;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AgentRepositoryIntegrationTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    AgentRepository agentRepository;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void smoke() {
        int agents = jdbcTemplate.queryForInt("select count(*) from Agent");
        agentRepository.registerNewAgent("One agent");
        String key = agentRepository.registerNewAgent("Two agents");
        agentRepository.registerNewAgent("Three agents");
        assertAgents(agents + 3);
        agentRepository.deleteAgent(key);
        assertAgents(agents + 2);
    }

    private void assertAgents(int i) {
        Assert.assertEquals("Incorrect number of agents", i,
                jdbcTemplate.queryForInt("select count(*) from Agent"));
    }

}
