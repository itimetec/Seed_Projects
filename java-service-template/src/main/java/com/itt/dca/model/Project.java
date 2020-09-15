package com.itt.dca.model;

import lombok.Data;
import org.springframework.data.annotation.Id;


/**
 * project class.
 * @author triveni.k
 */
@Data
public class Project {

    /**
     * id of project.
     */
    @Id
    private String id;

    /**
     * dcaSpecificProperty of project.
     */
    private String dcaSpecificProperty;

    /**
     * dcaAgentType of project.
     */
    private String dcaAgentType;

    /**
     * uniqueIds of project.
     */
    private String[] uniqueIds;
}
