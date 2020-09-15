package com.itt.dca;


import com.itt.dca.pharos.PharosDcaAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * DcaAgentFactory, returns different dca-instance
 * get device status from various tenants.
 */
@Component
public class DcaAgentFactory {
    /**
     * Spring will initialize pharosDcaAgent object.
     */
    @Autowired
    private PharosDcaAgent pharosDcaAgent;

    /**
     * Function to provide dca agent.
     * returns IDCA agent
     * @return IDcaAgent
     * @param tenant gives the tenant details
     */
    IDcaAgent getDcaAgent(final String tenant) {

        switch (tenant) {
            case "pharos":
                return pharosDcaAgent;
            default:
                return null;
        }
    }
}
