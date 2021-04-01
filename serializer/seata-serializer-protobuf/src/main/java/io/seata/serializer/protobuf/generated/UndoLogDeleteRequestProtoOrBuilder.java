// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: undoLogDeleteRequest.proto

package io.seata.serializer.protobuf.generated;

public interface UndoLogDeleteRequestProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.seata.protocol.protobuf.UndoLogDeleteRequestProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.io.seata.protocol.protobuf.AbstractTransactionRequestProto abstractTransactionRequest = 1;</code>
   * @return Whether the abstractTransactionRequest field is set.
   */
  boolean hasAbstractTransactionRequest();
  /**
   * <code>.io.seata.protocol.protobuf.AbstractTransactionRequestProto abstractTransactionRequest = 1;</code>
   * @return The abstractTransactionRequest.
   */
  io.seata.serializer.protobuf.generated.AbstractTransactionRequestProto getAbstractTransactionRequest();
  /**
   * <code>.io.seata.protocol.protobuf.AbstractTransactionRequestProto abstractTransactionRequest = 1;</code>
   */
  io.seata.serializer.protobuf.generated.AbstractTransactionRequestProtoOrBuilder getAbstractTransactionRequestOrBuilder();

  /**
   * <pre>
   **
   * The Resource id.
   * </pre>
   *
   * <code>string resourceId = 2;</code>
   * @return The resourceId.
   */
  java.lang.String getResourceId();
  /**
   * <pre>
   **
   * The Resource id.
   * </pre>
   *
   * <code>string resourceId = 2;</code>
   * @return The bytes for resourceId.
   */
  com.google.protobuf.ByteString
      getResourceIdBytes();

  /**
   * <pre>
   **
   * The SaveDays data.
   * </pre>
   *
   * <code>int32 saveDays = 3;</code>
   * @return The saveDays.
   */
  int getSaveDays();

  /**
   * <pre>
   **
   * The Branch type.
   * </pre>
   *
   * <code>.io.seata.protocol.protobuf.BranchTypeProto branchType = 4;</code>
   * @return The enum numeric value on the wire for branchType.
   */
  int getBranchTypeValue();
  /**
   * <pre>
   **
   * The Branch type.
   * </pre>
   *
   * <code>.io.seata.protocol.protobuf.BranchTypeProto branchType = 4;</code>
   * @return The branchType.
   */
  io.seata.serializer.protobuf.generated.BranchTypeProto getBranchType();
}
