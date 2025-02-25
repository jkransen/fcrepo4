
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
package org.fcrepo.kernel.impl.services;

import static org.fcrepo.kernel.api.rdf.DefaultRdfStream.fromModel;

import javax.inject.Inject;
import org.apache.jena.rdf.model.Model;
import org.fcrepo.kernel.api.exception.MalformedRdfException;
import org.fcrepo.kernel.api.exception.RepositoryRuntimeException;
import org.fcrepo.kernel.api.operations.RdfSourceOperationFactory;
import org.fcrepo.kernel.api.operations.ResourceOperation;
import org.fcrepo.kernel.api.services.ReplacePropertiesService;
import org.fcrepo.persistence.api.PersistentStorageSession;
import org.fcrepo.persistence.api.PersistentStorageSessionManager;
import org.fcrepo.persistence.api.exceptions.PersistentStorageException;

/**
 * This class mediates update operations between the kernel and persistent storage layers
 * @author bseeger
 */

public class ReplacePropertiesServiceImpl extends AbstractService implements ReplacePropertiesService {

    @Inject
    private PersistentStorageSessionManager psManager;

    @Inject
    private RdfSourceOperationFactory factory;

    @Override
    public void perform(final String txId,
                        final String fedoraId,
                        final String contentType,
                        final Model inputModel) throws MalformedRdfException {
        try {
            final PersistentStorageSession pSession = this.psManager.getSession(txId);

            hasRestrictedPath(fedoraId);

            ensureValidMemberRelation(inputModel);

            ensureValidACLAuthorization(fedoraId, inputModel);

            checkForSmtsLdpTypes(inputModel);

            final ResourceOperation updateOp = factory.updateBuilder(fedoraId)
                .triples(fromModel(inputModel.createResource(fedoraId).asNode(), inputModel))
                .build();

            pSession.persist(updateOp);
        } catch (final PersistentStorageException ex) {
            throw new RepositoryRuntimeException(String.format("failed to replace resource %s",
                  fedoraId), ex);
        }
    }
}