package dk.pejomi.orderPaymentGRPC;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: orderPayment.proto")
public final class OrderPaymentServiceGrpc {

  private OrderPaymentServiceGrpc() {}

  public static final String SERVICE_NAME = "dk.pejomi.orderPaymentGRPC.OrderPaymentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto,
      dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse> getProcessPaymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProcessPayment",
      requestType = dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto.class,
      responseType = dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto,
      dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse> getProcessPaymentMethod() {
    io.grpc.MethodDescriptor<dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto, dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse> getProcessPaymentMethod;
    if ((getProcessPaymentMethod = OrderPaymentServiceGrpc.getProcessPaymentMethod) == null) {
      synchronized (OrderPaymentServiceGrpc.class) {
        if ((getProcessPaymentMethod = OrderPaymentServiceGrpc.getProcessPaymentMethod) == null) {
          OrderPaymentServiceGrpc.getProcessPaymentMethod = getProcessPaymentMethod = 
              io.grpc.MethodDescriptor.<dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto, dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "dk.pejomi.orderPaymentGRPC.OrderPaymentService", "ProcessPayment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new OrderPaymentServiceMethodDescriptorSupplier("ProcessPayment"))
                  .build();
          }
        }
     }
     return getProcessPaymentMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OrderPaymentServiceStub newStub(io.grpc.Channel channel) {
    return new OrderPaymentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OrderPaymentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new OrderPaymentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OrderPaymentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new OrderPaymentServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class OrderPaymentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void processPayment(dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto request,
        io.grpc.stub.StreamObserver<dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProcessPaymentMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getProcessPaymentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto,
                dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse>(
                  this, METHODID_PROCESS_PAYMENT)))
          .build();
    }
  }

  /**
   */
  public static final class OrderPaymentServiceStub extends io.grpc.stub.AbstractStub<OrderPaymentServiceStub> {
    private OrderPaymentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OrderPaymentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderPaymentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OrderPaymentServiceStub(channel, callOptions);
    }

    /**
     */
    public void processPayment(dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto request,
        io.grpc.stub.StreamObserver<dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProcessPaymentMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class OrderPaymentServiceBlockingStub extends io.grpc.stub.AbstractStub<OrderPaymentServiceBlockingStub> {
    private OrderPaymentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OrderPaymentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderPaymentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OrderPaymentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse processPayment(dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto request) {
      return blockingUnaryCall(
          getChannel(), getProcessPaymentMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class OrderPaymentServiceFutureStub extends io.grpc.stub.AbstractStub<OrderPaymentServiceFutureStub> {
    private OrderPaymentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OrderPaymentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderPaymentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OrderPaymentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse> processPayment(
        dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto request) {
      return futureUnaryCall(
          getChannel().newCall(getProcessPaymentMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROCESS_PAYMENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OrderPaymentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OrderPaymentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PROCESS_PAYMENT:
          serviceImpl.processPayment((dk.pejomi.orderPaymentGRPC.OrderPayment.OrderDto) request,
              (io.grpc.stub.StreamObserver<dk.pejomi.orderPaymentGRPC.OrderPayment.PaymentResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class OrderPaymentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OrderPaymentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dk.pejomi.orderPaymentGRPC.OrderPayment.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OrderPaymentService");
    }
  }

  private static final class OrderPaymentServiceFileDescriptorSupplier
      extends OrderPaymentServiceBaseDescriptorSupplier {
    OrderPaymentServiceFileDescriptorSupplier() {}
  }

  private static final class OrderPaymentServiceMethodDescriptorSupplier
      extends OrderPaymentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OrderPaymentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (OrderPaymentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OrderPaymentServiceFileDescriptorSupplier())
              .addMethod(getProcessPaymentMethod())
              .build();
        }
      }
    }
    return result;
  }
}
