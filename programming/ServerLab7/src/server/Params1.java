package server;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class Params1 {
    private ByteBuffer byteBuffer;
    private SocketAddress socketAddress;

    public Params1(ByteBuffer byteBuffer, SocketAddress socketAddress) {
        this.byteBuffer = byteBuffer;
        this.socketAddress = socketAddress;
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }

    public void setByteBuffer(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }
}
