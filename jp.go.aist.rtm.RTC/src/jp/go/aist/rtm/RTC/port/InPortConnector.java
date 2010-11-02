package jp.go.aist.rtm.RTC.port;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

import jp.go.aist.rtm.RTC.buffer.BufferBase;
import jp.go.aist.rtm.RTC.log.Logbuf;
import jp.go.aist.rtm.RTC.util.DataRef;
import jp.go.aist.rtm.RTC.util.ORBUtil;

/**
 * <p> InPortConnector </p>
 * <p> InPortConnector base class </p>
 *
 * <p> The base class to derive subclasses for InPort's Push/Pull Connectors </p>
 */
public abstract class InPortConnector extends ConnectorBase {
    /**
     * <p> Constructor </p>
     */
    public InPortConnector(ConnectorBase.ConnectorInfo profile,
                    BufferBase<OutputStream> buffer) {
        rtcout = new Logbuf("InPortConnector");
        m_profile = profile;
        m_buffer = buffer;
        m_isLittleEndian = true;
        m_spi_orb = (com.sun.corba.se.spi.orb.ORB)ORBUtil.getOrb();
        m_orb = ORBUtil.getOrb();
    }


    /**
     * <p> Getting Profile </p>
     *
     * <p> This operation returns Connector Profile </p>
     *
     */
    public final ConnectorInfo profile() {
        rtcout.println(Logbuf.TRACE, "profile()");
        return m_profile;
    }
    /**
     * <p> Getting Connector ID </p>
     *
     * <p> This operation returns Connector ID </p>
     *
     */
    public final String id() {
        rtcout.println(Logbuf.TRACE, "id() = "+profile().id);
        return profile().id;
    }

    /**
     * <p> Getting Connector name </p>
     *
     * <p> This operation returns Connector name </p>
     *
     */
    public final String name() {
        rtcout.println(Logbuf.TRACE, "name() = "+profile().name);
        return profile().name;
    }

    /**
     * <p> Disconnect connection </p>
     *
     * <p> This operation disconnect this connection </p>
     *
     */
    public abstract ReturnCode disconnect();

    /**
     *
     * <p> Getting Buffer </p>
     *
     * <p> This operation returns this connector's buffer </p>
     *
     */
    public BufferBase<OutputStream> getBuffer() {
        return m_buffer;
    }
    /**
     *
     * <p> Setting an endian type </p>
     *
     * <p> This operation set this connector's endian type </p>
     *
     */
    public void setEndian(boolean isLittleEndian){
        m_isLittleEndian = isLittleEndian;
    }
    /**
     * <p> This value is true if the architecture is little-endian; false if it is big-endian.  </p>
     * 
     */
    public boolean isLittleEndian(){
        return m_isLittleEndian;
    }
    public abstract void setListener(ConnectorInfo profile, 
                            ConnectorListeners listeners);
    

    /**
     *
     * <p> Destructor </p>
     *
     * <p> The read function to read data from buffer to InPort </p>
     *
     */
    public abstract ReturnCode read(DataRef<InputStream> data);

    protected Logbuf rtcout;
    protected ConnectorInfo m_profile;
    protected BufferBase<OutputStream> m_buffer;
    protected boolean m_isLittleEndian;
    protected com.sun.corba.se.spi.orb.ORB m_spi_orb;
    protected org.omg.CORBA.ORB m_orb;
}

