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

package org.jclouds.googlecloudstorage.parse;

import org.jclouds.date.internal.SimpleDateFormatDateService;
import org.jclouds.googlecloudstorage.domain.Bucket;
import org.jclouds.googlecloudstorage.domain.Owner;
import org.jclouds.googlecloudstorage.domain.Website;
import org.jclouds.googlecloudstorage.internal.BaseGoogleCloudStorageParseTest;
import org.testng.annotations.Test;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.net.URI;

/**
 * @author Andrea Turli
 */
@Test(groups = "unit")
public class ParseBucketTest extends BaseGoogleCloudStorageParseTest<Bucket> {

   @Override
   public String resource() {
      return "/bucket.json";
   }

   @Override
   @Consumes(MediaType.APPLICATION_JSON)
   public Bucket expected() {
      return Bucket.builder()
              .id("test-bucket-1")
              .selfLink(URI.create("https://www.googleapis.com/storage/v1beta1/b/test-bucket-1"))
              .timeCreated(new SimpleDateFormatDateService().iso8601DateParse("2013-03-06T16:49:41.263Z"))
              .projectId("477990493411")
              .owner(Owner.builder().entity("group-00b4903a97a979b8761ab4f612d1a56c02e2562692c1f7d196691f00d84970b3")
                      .entityId("00b4903a97a979b8761ab4f612d1a56c02e2562692c1f7d196691f00d84970b3")
                      .build())
              .location("US")
              .website(Website.builder().build())
              .build();
   }
}
