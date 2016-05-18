package com.emc.poc.eec.service.function.impl;

import com.emc.poc.eec.model.Function;
import com.emc.poc.eec.service.function.FunctionService;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Function Service Implementation
 *
 * @author Simon O'Brien
 */
@Component
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private OrientGraphFactory tennantDbConnectionFactory;

    /**
     * @see com.emc.poc.eec.service.function.FunctionService#createFunction(Function)
     */
    public Function createFunction(Function function) {

        Function newFunction = null;
        OrientGraph txGraph = null;

        try {

            txGraph = tennantDbConnectionFactory.getTx();
            Vertex functionVertex = txGraph.addVertex("class:Function");
            functionVertex.setProperty("name", function.getName());

            txGraph.commit();

            newFunction = new Function();
            newFunction.setId(functionVertex.getId().toString());
            newFunction.setName((String)functionVertex.getProperty("name"));

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

        return newFunction;
    }

    /**
     * @see com.emc.poc.eec.service.function.FunctionService#getFunctionByName(String)
     */
    public Function getFunctionByName(String name) {

        Function function = null;
        OrientGraph txGraph = null;

        try {

            txGraph = tennantDbConnectionFactory.getTx();
            Iterable<Vertex> functionVertices = txGraph.getVertices("Function", new String[]{"name"}, new String[]{name});

            for(Vertex functionVertex : functionVertices) {
                function = new Function();
                function.setId(functionVertex.getId().toString());
                function.setName((String)functionVertex.getProperty("name"));
            }

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

        return function;

    }

    /**
     * @see com.emc.poc.eec.service.function.FunctionService#getFunctionById(String)
     */
    public Function getFunctionById(String id) {

        Function function = null;
        OrientGraph txGraph = null;

        try {

            txGraph = tennantDbConnectionFactory.getTx();
            Vertex functionVertex = txGraph.getVertex(id);

            function = new Function();
            function.setId(functionVertex.getId().toString());
            function.setName((String)functionVertex.getProperty("name"));

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }

        return function;
    }

    /**
     * @see com.emc.poc.eec.service.function.FunctionService#deleteFunction(Function)
     */
    public void deleteFunction(Function function) {

        OrientGraph txGraph = null;

        try {

            txGraph = tennantDbConnectionFactory.getTx();
            // todo catch exception and retry
            txGraph.begin();
            Vertex functionVertex = txGraph.getVertex(function.getId());
            txGraph.removeVertex(functionVertex);
            txGraph.commit();

        } finally {

            if(txGraph != null) {
                txGraph.shutdown();
            }
        }
    }
}
