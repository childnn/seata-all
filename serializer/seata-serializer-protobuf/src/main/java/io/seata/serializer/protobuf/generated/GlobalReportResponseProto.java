// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: globalReportResponse.proto

package io.seata.serializer.protobuf.generated;

/**
 * Protobuf type {@code io.seata.protocol.protobuf.GlobalReportResponseProto}
 */
public final class GlobalReportResponseProto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.seata.protocol.protobuf.GlobalReportResponseProto)
    GlobalReportResponseProtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GlobalReportResponseProto.newBuilder() to construct.
  private GlobalReportResponseProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GlobalReportResponseProto() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GlobalReportResponseProto();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GlobalReportResponseProto(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.Builder subBuilder = null;
            if (abstractGlobalEndResponse_ != null) {
              subBuilder = abstractGlobalEndResponse_.toBuilder();
            }
            abstractGlobalEndResponse_ = input.readMessage(io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(abstractGlobalEndResponse_);
              abstractGlobalEndResponse_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.seata.serializer.protobuf.generated.GlobalReportResponse.internal_static_io_seata_protocol_protobuf_GlobalReportResponseProto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.seata.serializer.protobuf.generated.GlobalReportResponse.internal_static_io_seata_protocol_protobuf_GlobalReportResponseProto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.seata.serializer.protobuf.generated.GlobalReportResponseProto.class, io.seata.serializer.protobuf.generated.GlobalReportResponseProto.Builder.class);
  }

  public static final int ABSTRACTGLOBALENDRESPONSE_FIELD_NUMBER = 1;
  private io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto abstractGlobalEndResponse_;
  /**
   * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
   * @return Whether the abstractGlobalEndResponse field is set.
   */
  @java.lang.Override
  public boolean hasAbstractGlobalEndResponse() {
    return abstractGlobalEndResponse_ != null;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
   * @return The abstractGlobalEndResponse.
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto getAbstractGlobalEndResponse() {
    return abstractGlobalEndResponse_ == null ? io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.getDefaultInstance() : abstractGlobalEndResponse_;
  }
  /**
   * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
   */
  @java.lang.Override
  public io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProtoOrBuilder getAbstractGlobalEndResponseOrBuilder() {
    return getAbstractGlobalEndResponse();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (abstractGlobalEndResponse_ != null) {
      output.writeMessage(1, getAbstractGlobalEndResponse());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (abstractGlobalEndResponse_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getAbstractGlobalEndResponse());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.seata.serializer.protobuf.generated.GlobalReportResponseProto)) {
      return super.equals(obj);
    }
    io.seata.serializer.protobuf.generated.GlobalReportResponseProto other = (io.seata.serializer.protobuf.generated.GlobalReportResponseProto) obj;

    if (hasAbstractGlobalEndResponse() != other.hasAbstractGlobalEndResponse()) return false;
    if (hasAbstractGlobalEndResponse()) {
      if (!getAbstractGlobalEndResponse()
          .equals(other.getAbstractGlobalEndResponse())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasAbstractGlobalEndResponse()) {
      hash = (37 * hash) + ABSTRACTGLOBALENDRESPONSE_FIELD_NUMBER;
      hash = (53 * hash) + getAbstractGlobalEndResponse().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.seata.serializer.protobuf.generated.GlobalReportResponseProto prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code io.seata.protocol.protobuf.GlobalReportResponseProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.seata.protocol.protobuf.GlobalReportResponseProto)
      io.seata.serializer.protobuf.generated.GlobalReportResponseProtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.seata.serializer.protobuf.generated.GlobalReportResponse.internal_static_io_seata_protocol_protobuf_GlobalReportResponseProto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.seata.serializer.protobuf.generated.GlobalReportResponse.internal_static_io_seata_protocol_protobuf_GlobalReportResponseProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.seata.serializer.protobuf.generated.GlobalReportResponseProto.class, io.seata.serializer.protobuf.generated.GlobalReportResponseProto.Builder.class);
    }

    // Construct using io.seata.serializer.protobuf.generated.GlobalReportResponseProto.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (abstractGlobalEndResponseBuilder_ == null) {
        abstractGlobalEndResponse_ = null;
      } else {
        abstractGlobalEndResponse_ = null;
        abstractGlobalEndResponseBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.seata.serializer.protobuf.generated.GlobalReportResponse.internal_static_io_seata_protocol_protobuf_GlobalReportResponseProto_descriptor;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.GlobalReportResponseProto getDefaultInstanceForType() {
      return io.seata.serializer.protobuf.generated.GlobalReportResponseProto.getDefaultInstance();
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.GlobalReportResponseProto build() {
      io.seata.serializer.protobuf.generated.GlobalReportResponseProto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.seata.serializer.protobuf.generated.GlobalReportResponseProto buildPartial() {
      io.seata.serializer.protobuf.generated.GlobalReportResponseProto result = new io.seata.serializer.protobuf.generated.GlobalReportResponseProto(this);
      if (abstractGlobalEndResponseBuilder_ == null) {
        result.abstractGlobalEndResponse_ = abstractGlobalEndResponse_;
      } else {
        result.abstractGlobalEndResponse_ = abstractGlobalEndResponseBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.seata.serializer.protobuf.generated.GlobalReportResponseProto) {
        return mergeFrom((io.seata.serializer.protobuf.generated.GlobalReportResponseProto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.seata.serializer.protobuf.generated.GlobalReportResponseProto other) {
      if (other == io.seata.serializer.protobuf.generated.GlobalReportResponseProto.getDefaultInstance()) return this;
      if (other.hasAbstractGlobalEndResponse()) {
        mergeAbstractGlobalEndResponse(other.getAbstractGlobalEndResponse());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.seata.serializer.protobuf.generated.GlobalReportResponseProto parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.seata.serializer.protobuf.generated.GlobalReportResponseProto) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto abstractGlobalEndResponse_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto, io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.Builder, io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProtoOrBuilder> abstractGlobalEndResponseBuilder_;
    /**
     * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
     * @return Whether the abstractGlobalEndResponse field is set.
     */
    public boolean hasAbstractGlobalEndResponse() {
      return abstractGlobalEndResponseBuilder_ != null || abstractGlobalEndResponse_ != null;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
     * @return The abstractGlobalEndResponse.
     */
    public io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto getAbstractGlobalEndResponse() {
      if (abstractGlobalEndResponseBuilder_ == null) {
        return abstractGlobalEndResponse_ == null ? io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.getDefaultInstance() : abstractGlobalEndResponse_;
      } else {
        return abstractGlobalEndResponseBuilder_.getMessage();
      }
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
     */
    public Builder setAbstractGlobalEndResponse(io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto value) {
      if (abstractGlobalEndResponseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        abstractGlobalEndResponse_ = value;
        onChanged();
      } else {
        abstractGlobalEndResponseBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
     */
    public Builder setAbstractGlobalEndResponse(
        io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.Builder builderForValue) {
      if (abstractGlobalEndResponseBuilder_ == null) {
        abstractGlobalEndResponse_ = builderForValue.build();
        onChanged();
      } else {
        abstractGlobalEndResponseBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
     */
    public Builder mergeAbstractGlobalEndResponse(io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto value) {
      if (abstractGlobalEndResponseBuilder_ == null) {
        if (abstractGlobalEndResponse_ != null) {
          abstractGlobalEndResponse_ =
            io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.newBuilder(abstractGlobalEndResponse_).mergeFrom(value).buildPartial();
        } else {
          abstractGlobalEndResponse_ = value;
        }
        onChanged();
      } else {
        abstractGlobalEndResponseBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
     */
    public Builder clearAbstractGlobalEndResponse() {
      if (abstractGlobalEndResponseBuilder_ == null) {
        abstractGlobalEndResponse_ = null;
        onChanged();
      } else {
        abstractGlobalEndResponse_ = null;
        abstractGlobalEndResponseBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
     */
    public io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.Builder getAbstractGlobalEndResponseBuilder() {
      
      onChanged();
      return getAbstractGlobalEndResponseFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
     */
    public io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProtoOrBuilder getAbstractGlobalEndResponseOrBuilder() {
      if (abstractGlobalEndResponseBuilder_ != null) {
        return abstractGlobalEndResponseBuilder_.getMessageOrBuilder();
      } else {
        return abstractGlobalEndResponse_ == null ?
            io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.getDefaultInstance() : abstractGlobalEndResponse_;
      }
    }
    /**
     * <code>.io.seata.protocol.protobuf.AbstractGlobalEndResponseProto abstractGlobalEndResponse = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto, io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.Builder, io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProtoOrBuilder> 
        getAbstractGlobalEndResponseFieldBuilder() {
      if (abstractGlobalEndResponseBuilder_ == null) {
        abstractGlobalEndResponseBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto, io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProto.Builder, io.seata.serializer.protobuf.generated.AbstractGlobalEndResponseProtoOrBuilder>(
                getAbstractGlobalEndResponse(),
                getParentForChildren(),
                isClean());
        abstractGlobalEndResponse_ = null;
      }
      return abstractGlobalEndResponseBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:io.seata.protocol.protobuf.GlobalReportResponseProto)
  }

  // @@protoc_insertion_point(class_scope:io.seata.protocol.protobuf.GlobalReportResponseProto)
  private static final io.seata.serializer.protobuf.generated.GlobalReportResponseProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.seata.serializer.protobuf.generated.GlobalReportResponseProto();
  }

  public static io.seata.serializer.protobuf.generated.GlobalReportResponseProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GlobalReportResponseProto>
      PARSER = new com.google.protobuf.AbstractParser<GlobalReportResponseProto>() {
    @java.lang.Override
    public GlobalReportResponseProto parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GlobalReportResponseProto(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GlobalReportResponseProto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GlobalReportResponseProto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.seata.serializer.protobuf.generated.GlobalReportResponseProto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

