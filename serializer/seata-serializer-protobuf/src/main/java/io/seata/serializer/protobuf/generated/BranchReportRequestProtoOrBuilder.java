// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: branchReportRequest.proto

package io.seata.serializer.protobuf.generated;

public interface BranchReportRequestProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.seata.protocol.protobuf.BranchReportRequestProto)
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
   * <code>string xid = 2;</code>
   * @return The xid.
   */
  java.lang.String getXid();
  /**
   * <code>string xid = 2;</code>
   * @return The bytes for xid.
   */
  com.google.protobuf.ByteString
      getXidBytes();

  /**
   * <code>int64 branchId = 3;</code>
   * @return The branchId.
   */
  long getBranchId();

  /**
   * <code>string resourceId = 4;</code>
   * @return The resourceId.
   */
  java.lang.String getResourceId();
  /**
   * <code>string resourceId = 4;</code>
   * @return The bytes for resourceId.
   */
  com.google.protobuf.ByteString
      getResourceIdBytes();

  /**
   * <code>.io.seata.protocol.protobuf.BranchStatusProto status = 5;</code>
   * @return The enum numeric value on the wire for status.
   */
  int getStatusValue();
  /**
   * <code>.io.seata.protocol.protobuf.BranchStatusProto status = 5;</code>
   * @return The status.
   */
  io.seata.serializer.protobuf.generated.BranchStatusProto getStatus();

  /**
   * <code>string applicationData = 6;</code>
   * @return The applicationData.
   */
  java.lang.String getApplicationData();
  /**
   * <code>string applicationData = 6;</code>
   * @return The bytes for applicationData.
   */
  com.google.protobuf.ByteString
      getApplicationDataBytes();

  /**
   * <code>.io.seata.protocol.protobuf.BranchTypeProto branchType = 7;</code>
   * @return The enum numeric value on the wire for branchType.
   */
  int getBranchTypeValue();
  /**
   * <code>.io.seata.protocol.protobuf.BranchTypeProto branchType = 7;</code>
   * @return The branchType.
   */
  io.seata.serializer.protobuf.generated.BranchTypeProto getBranchType();
}