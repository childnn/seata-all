// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resultCode.proto

package io.seata.serializer.protobuf.generated;

/**
 * <pre>
 * PublishRequest is a publish request.
 * </pre>
 *
 * Protobuf enum {@code io.seata.protocol.protobuf.ResultCodeProto}
 */
public enum ResultCodeProto
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>Failed = 0;</code>
   */
  Failed(0),
  /**
   * <code>Success = 1;</code>
   */
  Success(1),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>Failed = 0;</code>
   */
  public static final int Failed_VALUE = 0;
  /**
   * <code>Success = 1;</code>
   */
  public static final int Success_VALUE = 1;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static ResultCodeProto valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static ResultCodeProto forNumber(int value) {
    switch (value) {
      case 0: return Failed;
      case 1: return Success;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<ResultCodeProto>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      ResultCodeProto> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<ResultCodeProto>() {
          public ResultCodeProto findValueByNumber(int number) {
            return ResultCodeProto.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return io.seata.serializer.protobuf.generated.ResultCode.getDescriptor().getEnumTypes().get(0);
  }

  private static final ResultCodeProto[] VALUES = values();

  public static ResultCodeProto valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private ResultCodeProto(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:io.seata.protocol.protobuf.ResultCodeProto)
}

