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

import com.google.common.annotations.Beta;
import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import java.beans.ConstructorProperties;
import java.net.URI;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Base class for Google Cloud Storage resources.
 *
 * @author Andrea Turli
 */
@Beta
public class Resource {

   public enum Kind {
      BUCKET, BUCKETS, OBJECT, OBJECTS, BUCKET_ACCESS_CONTROL, BUCKET_ACCESS_CONTROLS, OBJECT_ACCESS_CONTROL,
      OBJECT_ACCESS_CONTROLS;

      public String value() {
         return Joiner.on("#").join("storage", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name()));
      }

      @Override
      public String toString() {
         return value();
      }

      public static Kind fromValue(String kind) {
         return valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat
                 .UPPER_UNDERSCORE,
                 Iterables.getLast(Splitter.on("#").split(checkNotNull(kind,
                         "kind")))));
      }
   }

   protected final Kind kind;
   protected final String id;
   protected final URI selfLink;


   @ConstructorProperties({"kind", "id", "selfLink"})
   protected Resource(Kind kind, String id, URI selfLink) {
      this.kind = checkNotNull(kind, "kind");
      this.id = id;
      this.selfLink = selfLink;
   }

   /**
    * @return the Type of the resource
    */
   public Kind getKind() {
      return kind;
   }

   /**
    * @return unique identifier for the resource; defined by the server.
    */
   public String getId() {
      return id;
   }

   /**
    * @return server defined URL for the resource.
    */
   public URI getSelfLink() {
      return selfLink;
   }


   /**
    * {@inheritDoc}
    */
   @Override
   public int hashCode() {
      return Objects.hashCode(kind, id);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Resource that = Resource.class.cast(obj);
      return equal(this.kind, that.kind)
              && equal(this.id, that.id);
   }

   /**
    * {@inheritDoc}
    */
   protected Objects.ToStringHelper string() {
      return toStringHelper(this)
              .omitNullValues()
              .add("kind", kind)
              .add("id", id)
              .add("selfLink", selfLink);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String toString() {
      return string().toString();
   }

   public static Builder<?> builder() {
      return new ConcreteBuilder();
   }

   public Builder<?> toBuilder() {
      return new ConcreteBuilder().fromResource(this);
   }

   public abstract static class Builder<T extends Builder<T>> {

      protected abstract T self();

      protected Kind kind;
      protected String id;
      protected URI selfLink;

      /**
       * @see Resource#getKind()
       */
      protected T kind(Kind kind) {
         this.kind = kind;
         return self();
      }

      /**
       * @see Resource#getId()
       */
      public T id(String id) {
         this.id = id;
         return self();
      }

      /**
       * @see Resource#getSelfLink()
       */
      public T selfLink(URI selfLink) {
         this.selfLink = selfLink;
         return self();
      }

      public Resource build() {
         return new Resource(kind, id, selfLink);
      }

      public T fromResource(Resource in) {
         return this
                 .kind(in.getKind())
                 .id(in.getId())
                 .selfLink(in.getSelfLink());
      }
   }

   private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
      @Override
      protected ConcreteBuilder self() {
         return this;
      }
   }
}
