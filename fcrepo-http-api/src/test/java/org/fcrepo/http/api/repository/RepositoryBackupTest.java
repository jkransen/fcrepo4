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
package org.fcrepo.http.api.repository;

import static java.lang.System.getProperty;
import static org.fcrepo.http.commons.test.util.TestHelpers.getUriInfoImpl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.fcrepo.kernel.api.Transaction;
import org.fcrepo.kernel.api.services.RepositoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * @author Andrew Woods
 *         Date: 9/4/13
 */
@Deprecated
public class RepositoryBackupTest {

    private FedoraRepositoryBackup repoBackup;

    @Mock
    private RepositoryService mockService;

    @Mock
    private Transaction mockTransaction;

    @Before
    public void setUp() {
        initMocks(this);

        repoBackup = new FedoraRepositoryBackup();
        setField(repoBackup, "transaction", mockTransaction);
        setField(repoBackup, "repositoryService", mockService);
        setField(repoBackup, "uriInfo", getUriInfoImpl());
    }

    @Test
    public void testRunBackup() throws Exception {
        final Collection<Throwable> problems = new ArrayList<>();
        when(mockService.backupRepository(any(Transaction.class),
                                        any(File.class))).thenReturn(
                problems);

        final String backupPath = (String) repoBackup.runBackup(null).getEntity();
        assertNotNull(backupPath);
    }

    @Test
    public void testRunBackupWithDir() throws Exception {
        final Collection<Throwable> problems = new ArrayList<>();
        when(mockService.backupRepository(any(Transaction.class),
                                        any(File.class))).thenReturn(
                problems);

        final String tmpDir = getProperty("java.io.tmpdir");
        final String tmpDirPath = new File(tmpDir).getCanonicalPath();
        final InputStream inputStream = new ByteArrayInputStream(tmpDir.getBytes());

        final String backupPath = (String) repoBackup.runBackup(inputStream).getEntity();
        assertNotNull(backupPath);
        assertEquals(tmpDirPath, backupPath);
    }

}
