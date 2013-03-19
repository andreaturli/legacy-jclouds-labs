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
package org.jclouds.googlecloudstorage.options;

import org.jclouds.http.options.BaseHttpRequestOptions;

import static com.google.common.base.Preconditions.checkNotNull;

public class OptionalParameters extends BaseHttpRequestOptions {

   public enum Projection {
      NO_ACL, FULL
   }

   /**
    * Sets projection.
    * Acceptable values are: "full": Include all properties. "no_acl": Omit acl and defaultObjectAcl properties.
    * Defaults to "no_acl".
    */
   public OptionalParameters projection(String projection) {
      this.queryParameters.put("projection", checkNotNull(projection, "projection") + "");
      return this;
   }

   public static class Builder {

      /**
       * @see OptionalParameters#projection(String)
       */
      public OptionalParameters projection(String projection) {
         return new OptionalParameters().projection(projection);
      }

   }
}
