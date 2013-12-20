/**
 * Copyright 2010 Karthik Kumar
 * 
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hbasene.index.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.LockObtainFailedException;
import org.hbasene.index.HBaseTermEnum;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unused")
public class TestHBaseTermEnum extends AbstractHBaseneTest {

  @SuppressWarnings("unused")
  private static final Logger LOGGER = Logger.getLogger(TestHBaseTermEnum.class
      .getName());

  private HBaseTermEnum termEnum;

 
  /**
   * @throws java.lang.Exception
   */
  protected void doSetupDerived() throws CorruptIndexException, IOException {
    termEnum = new HBaseTermEnum(indexReader);

  }

  /**
   * @throws java.lang.Exception
   */
  
  public void tearDown() throws Exception {
    termEnum.close();
  }

  @Test
  public void testTermEnum() throws CorruptIndexException,
      LockObtainFailedException, IOException {
    while (termEnum.next()) {
      Term term = termEnum.term();
      String field = term.field();
      Assert.assertTrue(field.contains("content") || field.contains("id"));
      Assert.assertTrue(termEnum.docFreq() > 0);
          //"At least one document present with the given term");
    }

  }
}
