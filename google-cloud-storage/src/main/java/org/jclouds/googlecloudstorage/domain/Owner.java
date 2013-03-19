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
 * An owner of a bucket
 *
 * @see <a href="https://developers.google.com/storage/docs/json_api/v1/buckets"/>
 *
 * @author Andrea Turli
 *
 */
public final class Owner {

   private final String entity;
   private final String entityId;

   @ConstructorProperties({"entity", "entityId" })
   public Owner(String entity, String entityId) {
      this.entity = entity;
      this.entityId = entityId;
   }

   /**
    *
    * @return the entity, in the form group-groupId.
    */
   public String getEntity() {
      return entity;
   }

   /**
    *
    * @return the ID for the entity.
    */
   public String getEntityId() {
      return entityId;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public int hashCode() {
      return Objects.hashCode(entity, entityId);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Owner that = Owner.class.cast(obj);
      return equal(this.entity, that.entity)
              && equal(this.entityId, that.entityId);
   }

   /**
    * {@inheritDoc}
    */
   protected Objects.ToStringHelper string() {
      return toStringHelper(this)
              .add("entity", entity).add("entityId", entityId);
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
      return builder().fromOwner(this);
   }

   public static final class Builder {

      private String entity;
      private String entityId;

      public Builder entity(String entity) {
         this.entity = entity;
         return this;
      }

      public Builder entityId(String entityId) {
         this.entityId = entityId;
         return this;
      }

      public Owner build() {
         return new Owner(entity, entityId);
      }

      public Builder fromOwner(Owner in) {
         return this.entity(in.getEntity())
                 .entityId(in.getEntityId());
      }
   }
}
