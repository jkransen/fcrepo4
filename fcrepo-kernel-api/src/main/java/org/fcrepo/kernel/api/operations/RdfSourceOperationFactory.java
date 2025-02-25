/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fcrepo.kernel.api.operations;


/**
 * Factory for operations on rdf sources
 *
 * @author bbpennel
 */
public interface RdfSourceOperationFactory extends ResourceOperationFactory {

    /**
     * Get a builder for an operation to create an RDF source
     *
     * @param rescId id of the resource targeted by the operation
     * @param interactionModel interaction model for the resource being created
     * @return new builder
     */
    RdfSourceOperationBuilder createBuilder(String rescId, String interactionModel);

    /**
     * Get a builder for an operation to update an RDF source
     *
     * @param rescId id of the resource targeted by the operation
     * @return new builder
     */
    RdfSourceOperationBuilder updateBuilder(String rescId);

    /**
     * Get a builder for an operation to perform a sparql update on an RDF source
     *
     * @param rescId id of the resource targeted by the operation
     * @param updateQuery sparql update query
     * @return new builder
     */
    ResourceOperationBuilder sparqlUpdateBuilder(String rescId, String updateQuery);
}
