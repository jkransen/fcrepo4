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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.apache.jena.graph.NodeFactory.createURI;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.apache.commons.io.IOUtils;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.graph.Node;
import org.fcrepo.kernel.api.RdfCollectors;
import org.fcrepo.kernel.api.RdfStream;
import org.fcrepo.kernel.api.Transaction;
import org.fcrepo.kernel.api.models.FedoraResource;
import org.fcrepo.kernel.api.operations.RdfSourceOperationFactory;
import org.fcrepo.kernel.impl.operations.RdfSourceOperationFactoryImpl;
import org.fcrepo.kernel.impl.operations.UpdateRdfSourceOperation;
import org.fcrepo.persistence.api.PersistentStorageSession;
import org.fcrepo.persistence.api.PersistentStorageSessionManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * DeleteResourceServiceTest
 *
 * @author bseeger
 */
@RunWith(MockitoJUnitRunner.Strict.class)
public class ReplacePropertiesServiceImplTest {

    @Mock
    private Transaction tx;

    @Mock
    private PersistentStorageSession pSession;

    @Mock
    private PersistentStorageSessionManager psManager;

    @Mock
    private FedoraResource resource;

    @InjectMocks
    private UpdateRdfSourceOperation operation;

    @InjectMocks
    private ReplacePropertiesServiceImpl service;

    private RdfSourceOperationFactory factory;

    @Captor
    private ArgumentCaptor<UpdateRdfSourceOperation> operationCaptor;

    private static final String FEDORA_ID = "info:fedora/resource1";
    private static final String TX_ID = "tx-1234";
    private static final String CONTENT_TYPE = "text/turtle";

    private static final String RDF =
            "<" + FEDORA_ID + "> <" + DC.getURI() + "title> 'fancy title' .\n" +
            "<" + FEDORA_ID + "> <" + DC.getURI() + "title> 'another fancy title' .";

    private final Node subject = createURI(FEDORA_ID);


    @Before
    public void setup() {
        factory = new RdfSourceOperationFactoryImpl();
        setField(service, "factory", factory);
        when(tx.getId()).thenReturn(TX_ID);
        when(psManager.getSession(anyString())).thenReturn(pSession);
        when(resource.getId()).thenReturn(FEDORA_ID);
    }

    @Test
    public void testReplaceProperties() throws Exception {
        final Model model = ModelFactory.createDefaultModel();
        RDFDataMgr.read(model, IOUtils.toInputStream(RDF, "UTF-8"), Lang.NTRIPLES);

        service.perform(tx.getId(), resource.getId(), CONTENT_TYPE, model);
        verify(pSession).persist(operationCaptor.capture());
        assertEquals(FEDORA_ID, operationCaptor.getValue().getResourceId());
        final RdfStream stream = operationCaptor.getValue().getTriples();
        final Model captureModel = stream.collect(RdfCollectors.toModel());

        assertTrue(captureModel.contains(ResourceFactory.createResource(FEDORA_ID),
                ResourceFactory.createProperty("http://purl.org/dc/elements/1.1/title"),
                "another fancy title"));
        assertTrue(captureModel.contains(ResourceFactory.createResource(FEDORA_ID),
                ResourceFactory.createProperty("http://purl.org/dc/elements/1.1/title"),
                "fancy title"));
    }
}

