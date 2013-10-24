package net.evolutionaryarchitecture.jpa.samples;

public interface AgentRepository {

    String registerNewAgent(String description);
    
    void deleteAgent(String key);
    
}
