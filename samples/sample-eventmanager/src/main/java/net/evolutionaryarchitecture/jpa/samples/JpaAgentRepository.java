package net.evolutionaryarchitecture.jpa.samples;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class JpaAgentRepository implements AgentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void deleteAgent(String key) {
		Agent agent = (Agent) entityManager
				.createQuery("from Agent where key = :key")
				.setParameter("key", key).getSingleResult();
		entityManager.remove(agent);
		entityManager.flush();
	}

	@Override
	public String registerNewAgent(String description) {
		Agent newAgent = new Agent(description);
		entityManager.persist(newAgent);
		return newAgent.getKey();
	}

}
