// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: branchReportRequest.proto

package io.seata.serializer.protobuf.generated;

public final class BranchReportRequest {
  private BranchReportRequest() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_seata_protocol_protobuf_BranchReportRequestProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_seata_protocol_protobuf_BranchReportRequestProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031branchReportRequest.proto\022\032io.seata.pr" +
      "otocol.protobuf\032\022branchStatus.proto\032\020bra" +
      "nchType.proto\032 abstractTransactionReques" +
      "t.proto\"\307\002\n\030BranchReportRequestProto\022_\n\032" +
      "abstractTransactionRequest\030\001 \001(\0132;.io.se" +
      "ata.protocol.protobuf.AbstractTransactio" +
      "nRequestProto\022\013\n\003xid\030\002 \001(\t\022\020\n\010branchId\030\003" +
      " \001(\003\022\022\n\nresourceId\030\004 \001(\t\022=\n\006status\030\005 \001(\016" +
      "2-.io.seata.protocol.protobuf.BranchStat" +
      "usProto\022\027\n\017applicationData\030\006 \001(\t\022?\n\nbran" +
      "chType\030\007 \001(\0162+.io.seata.protocol.protobu" +
      "f.BranchTypeProtoB?\n&io.seata.serializer" +
      ".protobuf.generatedB\023BranchReportRequest" +
      "P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          io.seata.serializer.protobuf.generated.BranchStatus.getDescriptor(),
          io.seata.serializer.protobuf.generated.BranchType.getDescriptor(),
          io.seata.serializer.protobuf.generated.AbstractTransactionRequest.getDescriptor(),
        });
    internal_static_io_seata_protocol_protobuf_BranchReportRequestProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_io_seata_protocol_protobuf_BranchReportRequestProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_seata_protocol_protobuf_BranchReportRequestProto_descriptor,
        new java.lang.String[] { "AbstractTransactionRequest", "Xid", "BranchId", "ResourceId", "Status", "ApplicationData", "BranchType", });
    io.seata.serializer.protobuf.generated.BranchStatus.getDescriptor();
    io.seata.serializer.protobuf.generated.BranchType.getDescriptor();
    io.seata.serializer.protobuf.generated.AbstractTransactionRequest.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
