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

import com.google.common.collect.ImmutableSet;
import org.jclouds.googlecloudstorage.domain.BucketAccessControl;
import org.jclouds.googlecloudstorage.domain.ListPage;
import org.jclouds.googlecloudstorage.domain.Resource;
import org.jclouds.googlecloudstorage.domain.Role;
import org.jclouds.googlecloudstorage.internal.BaseGoogleCloudStorageParseTest;
import org.testng.annotations.Test;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.net.URI;

/**
 * @author Andrea Turli
 */
@Test(groups = "unit")
public class ParseBucketAccessControlListTest extends BaseGoogleCloudStorageParseTest<ListPage<BucketAccessControl>> {

   @Override
   public String resource() {
      return "/bucketAccessControl_list.json";
   }

   @Override
   @Consumes(MediaType.APPLICATION_JSON)
   public ListPage<BucketAccessControl> expected() {
      return ListPage.<BucketAccessControl>builder()
              .kind(Resource.Kind.BUCKET_ACCESS_CONTROLS)
              .items(ImmutableSet.of(
                      BucketAccessControl.builder()
                              .id("bucket-api-live-test-24/group-00b4903a97a979b8761ab4f612d1a56c02e2562692c1f7d196691f00d84970b3")
                              .selfLink(URI.create("https://www.googleapis.com/storage/v1beta1/b/bucket-api-live-test-24/acl/group-00b4903a97a979b8761ab4f612d1a56c02e2562692c1f7d196691f00d84970b3"))
                              .bucket("bucket-api-live-test-24")
                              .entity("group-00b4903a97a979b8761ab4f612d1a56c02e2562692c1f7d196691f00d84970b3")
                              .role(Role.OWNER)
                              .entityId("00b4903a97a979b8761ab4f612d1a56c02e2562692c1f7d196691f00d84970b3")
                              .build(),
                      BucketAccessControl.builder()
                              .id("bucket-api-live-test-24/group-00b4903a970fd0a647b7983c5755d0ca9ffa268e476bcacad97bb90f2cd2c7cc")
                              .selfLink(URI.create("https://www.googleapis.com/storage/v1beta1/b/bucket-api-live-test-24/acl/group-00b4903a97a979b8761ab4f612d1a56c02e2562692c1f7d196691f00d84970b3"))
                              .bucket("bucket-api-live-test-24")
                              .entity("group-00b4903a970fd0a647b7983c5755d0ca9ffa268e476bcacad97bb90f2cd2c7cc")
                              .role(Role.OWNER)
                              .entityId("00b4903a970fd0a647b7983c5755d0ca9ffa268e476bcacad97bb90f2cd2c7cc")
                              .build(),
                      BucketAccessControl.builder()
                              .id("bucket-api-live-test-24/group-00b4903a9730f1b1ea29ae03903cf68ff9ced56a1a57eb1124e23045ba9c6c9e")
                              .selfLink(URI.create("https://www.googleapis.com/storage/v1beta1/b/bucket-api-live-test-24/acl/group-00b4903a9730f1b1ea29ae03903cf68ff9ced56a1a57eb1124e23045ba9c6c9e"))
                              .bucket("bucket-api-live-test-24")
                              .entity("group-00b4903a9730f1b1ea29ae03903cf68ff9ced56a1a57eb1124e23045ba9c6c9e")
                              .role(Role.READER)
                              .entityId("00b4903a9730f1b1ea29ae03903cf68ff9ced56a1a57eb1124e23045ba9c6c9e")
                              .build())
              ).build();
   }
}
