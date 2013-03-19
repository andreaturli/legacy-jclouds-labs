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
package org.jclouds.googlecloudstorage;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.TypeToken;
import com.google.inject.Module;
import org.jclouds.apis.ApiMetadata;
import org.jclouds.googlecloudstorage.config.GoogleCloudStorageRestClientModule;
import org.jclouds.googlecloudstorage.config.OAuthModuleWithoutTypeAdapters;
import org.jclouds.googlecloudstorage.config.GoogleCloudStorageParserModule;
import org.jclouds.oauth.v2.config.OAuthAuthenticationModule;
import org.jclouds.rest.RestContext;
import org.jclouds.rest.internal.BaseRestApiMetadata;

import java.net.URI;
import java.util.Properties;

import static org.jclouds.Constants.PROPERTY_SESSION_INTERVAL;
import static org.jclouds.oauth.v2.config.OAuthProperties.AUDIENCE;
import static org.jclouds.oauth.v2.config.OAuthProperties.SIGNATURE_OR_MAC_ALGORITHM;

/**
 * Implementation of {@link ApiMetadata} for Google Cloud Storage API
 *
 * @author Andrea Turli
 *
 */
public class GoogleCloudStorageApiMetadata extends BaseRestApiMetadata {

   public static final String OAUTH_ENDPOINT = "oauth.endpoint";
   public static final TypeToken<RestContext<GoogleCloudStorageApi, GoogleCloudStorageAsyncApi>> CONTEXT_TOKEN =
           new TypeToken<RestContext<GoogleCloudStorageApi, GoogleCloudStorageAsyncApi>>() {
                private static final long serialVersionUID = 1L;
           };

   @Override
   public Builder toBuilder() {
      return new Builder().fromApiMetadata(this);
   }

   public GoogleCloudStorageApiMetadata() {
      this(new Builder());
   }

   protected GoogleCloudStorageApiMetadata(Builder builder) {
      super(builder);
   }

   public static Properties defaultProperties() {
      Properties properties = BaseRestApiMetadata.defaultProperties();
      properties.put(OAUTH_ENDPOINT, "https://accounts.google.com/o/oauth2/token");
      properties.put(AUDIENCE, "https://accounts.google.com/o/oauth2/token");
      properties.put(SIGNATURE_OR_MAC_ALGORITHM, "RS256");
      properties.put(PROPERTY_SESSION_INTERVAL, 3600);
      return properties;
   }

   public static class Builder extends BaseRestApiMetadata.Builder<Builder> {

      private static final String GOOGLE_CLOUD_STORAGE = "google-cloud-storage";

      protected Builder() {
         super(GoogleCloudStorageApi.class, GoogleCloudStorageAsyncApi.class);
         id(GOOGLE_CLOUD_STORAGE)
                 .name("Google Cloud Storage Api")
                 .identityName("Email associated with the Google API client_id")
                 .credentialName("Private key literal associated with the Google API client_id")
                 .documentation(URI.create("https://developers.google.com/storage/docs/getting-started"))
                 .version("v1beta1")
                 .defaultEndpoint("https://www.googleapis.com/storage/v1beta1")
                 .defaultProperties(GoogleCloudStorageApiMetadata.defaultProperties())
                 .defaultModules(ImmutableSet.<Class<? extends Module>>builder()
                         .add(GoogleCloudStorageRestClientModule.class)
                         .add(GoogleCloudStorageParserModule.class)
                         .add(OAuthAuthenticationModule.class)
                         .add(OAuthModuleWithoutTypeAdapters.class)
                         .build());
      }

      @Override
      public GoogleCloudStorageApiMetadata build() {
         return new GoogleCloudStorageApiMetadata(this);
      }

      @Override
      protected Builder self() {
         return this;
      }
   }
}
