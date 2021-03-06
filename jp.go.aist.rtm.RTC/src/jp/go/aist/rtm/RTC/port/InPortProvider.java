package jp.go.aist.rtm.RTC.port;

import jp.go.aist.rtm.RTC.buffer.BufferBase;
import jp.go.aist.rtm.RTC.util.Properties;

import org.omg.CORBA.portable.OutputStream;

import _SDOPackage.NVListHolder;

/**
 * <p>InPortに対して何を提供しているかを宣言するインタフェースです。</p>
 */
public interface InPortProvider {

    /**
     * <p>Interface情報を公開します。</p>
     * 
     * @param properties Interface情報を受け取るホルダオブジェクト
     * @return boolean
     */
    public boolean publishInterface(NVListHolder properties);

    /**
     * <p>InterfaceProfile情報を公開します。</p>
     * 
     * @param properties InterfaceProfile情報を受け取るホルダオブジェクト
     */
    public void publishInterfaceProfile(NVListHolder properties);
    /**
     * <p> Initializing configuration </p>
     *
     * <p> This operation would be called to configure this consumer </p>
     * <p> in initialization. </p>
     *
     */
    public void init(Properties prop);

    /**
     * <p> Initializing configuration </p>
     *
     * <p> This operation would be called to configure this consumer </p>
     * <p> in initialization. </p>
     *
     */
    public void setBuffer(BufferBase<OutputStream> buffer);

    /**
     * <p> setting Connector </p>
     * 
     * @param connector
     */
    public void setConnector(InPortConnector connector);

    public void setListener(ConnectorBase.ConnectorInfo info, 
                            ConnectorListeners listeners);

}
