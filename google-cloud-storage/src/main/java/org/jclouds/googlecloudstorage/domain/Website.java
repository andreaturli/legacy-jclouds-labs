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
package org.jclouds.googlecloudstorage.domain;

import com.google.common.base.Objects;

import java.beans.ConstructorProperties;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;

/**
 * The website associated to the bucket
 *
 * @see <a href="https://developers.google.com/storage/docs/json_api/v1/buckets"/>
 *
 * @author Andrea Turli
 *
 */
public final class Website {

   private final String mainPageSuffix;
   private final String notFoundPage;

   @ConstructorProperties({"mainPageSuffix", "notFoundPage" })
   public Website(String mainPageSuffix, String notFoundPage) {
      this.mainPageSuffix = mainPageSuffix;
      this.notFoundPage = notFoundPage;
   }

   /**
    *
    * @return the bucket's directory index where missing objects are treated as potential directories.
    */
   public String getMainPageSuffix() {
      return mainPageSuffix;
   }

   /**
    *
    * @return the custom object to return when a requested resource is not found.
    */
   public String getNotFoundPage() {
      return notFoundPage;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public int hashCode() {
      return Objects.hashCode(mainPageSuffix, notFoundPage);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Website that = Website.class.cast(obj);
      return equal(this.mainPageSuffix, that.mainPageSuffix)
              && equal(this.notFoundPage, that.notFoundPage);
   }

   /**
    * {@inheritDoc}
    */
   protected Objects.ToStringHelper string() {
      return toStringHelper(this)
              .add("mainPageSuffix", mainPageSuffix).add("notFoundPage", notFoundPage);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String toString() {
      return string().toString();
   }

   public static Builder builder() {
      return new Builder();
   }

   public Builder toBuilder() {
      return builder().fromWebsite(this);
   }

   public static final class Builder {

      private String mainPageSuffix;
      private String notFoundPage;

      /**
       *
       * @see Website#mainPageSuffix
       */
      public Builder mainPageSuffix(String mainPageSuffix) {
         this.mainPageSuffix = mainPageSuffix;
         return this;
      }

      /**
       *
       * @see Website#notFoundPage
       */
      public Builder notFoundPage(String notFoundPage) {
         this.notFoundPage = notFoundPage;
         return this;
      }

      public Website build() {
         return new Website(mainPageSuffix, notFoundPage);
      }

      public Builder fromWebsite(Website in) {
         return this.mainPageSuffix(in.getMainPageSuffix())
                 .notFoundPage(in.getNotFoundPage());
      }
   }
}
