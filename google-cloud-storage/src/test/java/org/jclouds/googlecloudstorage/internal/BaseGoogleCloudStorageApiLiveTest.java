/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.googlecloudstorage.internal;

import com.google.common.reflect.TypeToken;
import org.jclouds.apis.BaseContextLiveTest;
import org.jclouds.googlecloudstorage.GoogleCloudStorageApi;
import org.jclouds.googlecloudstorage.GoogleCloudStorageApiMetadata;
import org.jclouds.googlecloudstorage.GoogleCloudStorageAsyncApi;
import org.jclouds.oauth.v2.OAuthTestUtils;
import org.jclouds.rest.RestContext;
import org.testng.annotations.Test;

import java.util.Properties;

/**
 * @author Andrea Turli
 */
@Test(groups = "live")
public class BaseGoogleCloudStorageApiLiveTest extends BaseContextLiveTest<RestContext<GoogleCloudStorageApi,
        GoogleCloudStorageAsyncApi>> {

   protected String projectId;

   public BaseGoogleCloudStorageApiLiveTest() {
      provider = "google-cloud-storage";
   }

   @Override
   protected Properties setupProperties() {
      Properties properties = super.setupProperties();
      projectId = setIfTestSystemPropertyPresent(properties, provider + ".projectId");
      OAuthTestUtils.setCredentialFromPemFile(properties, "google-cloud-storage.credential");
      return properties;
   }

   @Override
   protected TypeToken<RestContext<GoogleCloudStorageApi, GoogleCloudStorageAsyncApi>> contextType() {
      return GoogleCloudStorageApiMetadata.CONTEXT_TOKEN;
   }

}
