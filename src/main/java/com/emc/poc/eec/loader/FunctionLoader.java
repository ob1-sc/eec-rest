package com.emc.poc.eec.loader;

import com.emc.poc.eec.model.Function;
import com.emc.poc.eec.service.function.FunctionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Function Loader
 *
 * @author Simon O'Brien
 */
@Component
public class FunctionLoader {

    @Autowired
    private FunctionService functionService;

    /**
     * Loads functions from JSON file into the DB
     *
     * @param functionsJson JSON file containing array of functions
     * @throws Exception
     */
    public void loadFunctions(Resource functionsJson) throws Exception {

        // load the functions
        ObjectMapper mapper = new ObjectMapper();
        List<Function> functions = mapper.readValue(functionsJson.getInputStream(), new TypeReference<List<Function>>(){});

        // iterate through the functions
        for(Function function : functions) {

            // check if the function already exists
            Function existingFunction = functionService.getFunctionByName(function.getName());

            // only create the function if it doesn't already exist
            if(existingFunction == null) {

                // create the new function
                functionService.createFunction(function);
            }
        }
    }
}
