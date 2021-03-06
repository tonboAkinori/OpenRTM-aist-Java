package jp.go.aist.rtm.RTC.port;

import junit.framework.TestCase;

import java.util.Vector;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

import org.omg.CORBA.ORB;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;
import org.omg.PortableServer.POA;

import _SDOPackage.NVListHolder;
import RTC.PortService;
import RTC.ReturnCode_t;

import jp.go.aist.rtm.RTC.BufferFactory;
import jp.go.aist.rtm.RTC.PublisherBaseFactory;
import jp.go.aist.rtm.RTC.PeriodicTaskFactory;
import jp.go.aist.rtm.RTC.ObjectCreator;
import jp.go.aist.rtm.RTC.ObjectDestructor;
import jp.go.aist.rtm.RTC.PeriodicTask;
import jp.go.aist.rtm.RTC.PeriodicTaskBase;
import jp.go.aist.rtm.RTC.util.DataRef;
import jp.go.aist.rtm.RTC.util.Properties;
import jp.go.aist.rtm.RTC.util.ORBUtil;
import jp.go.aist.rtm.RTC.util.CORBA_SeqUtil;
import jp.go.aist.rtm.RTC.util.NVUtil;
import jp.go.aist.rtm.RTC.buffer.BufferBase;
import jp.go.aist.rtm.RTC.buffer.RingBuffer;
import jp.go.aist.rtm.RTC.buffer.CdrRingBuffer;
import jp.go.aist.rtm.RTC.log.Logbuf;
import jp.go.aist.rtm.RTC.port.publisher.PublisherBase;
import jp.go.aist.rtm.RTC.log.Logbuf;
/**
 * @class OutPortPushConnectorTests class
 * @brief OutPortPushConnector test
 */
public class OutPortPushConnectorTest extends TestCase {
    /**
     * * 
     *
     */
    class Logger {
        public void log(final String msg) {
            m_log.add(msg);
        }

        public int countLog(final String msg) {
            int count = 0;
            for (int i = 0; i < (int) m_log.size(); ++i) {
                if (m_log.elementAt(i).equals(msg)) {
                    ++count;
                }
            }
            return count;
        }
		
       private Vector<String> m_log = new Vector<String>();
    }
    /**
     * 
     * 
     *
     */
    class InPortCorbaCdrConsumerMock extends InPortCorbaCdrConsumer {

        public InPortCorbaCdrConsumerMock() {
            m_logger = null;
        }
        /**
         *
         *
         */
        public void init(Properties prop) {
          if (m_logger != null) {
              m_logger.log("InPortCorbaCdrConsumerMock.init");
          }
          // ここで指定したバッファが使用されることを確認する
          //prop.setProperty("buffer_type", "ring_buffer_mock2");
        }
        /**
         *
         *
         */
        public ReturnCode put(final byte[] data) {
            return ReturnCode.PORT_OK;
        }
        /**
         *
         *
         */
        public void publishInterfaceProfile(_SDOPackage.NVListHolder properties) {
            return;
        }

        /**
         *
         *
         */
        public boolean subscribeInterface(final _SDOPackage.NVListHolder properties) {
            return true;
        }
  
        /**
         *
         *
         */
        public void unsubscribeInterface(final _SDOPackage.NVListHolder properties) {
        }
  
  
        /**
         *
         *
         */
        public void setLogger(Logger logger) {
            m_logger = logger;
        }
        private Logger m_logger;

    }
    /**
     * 
     * 
     *
     */
    class PublisherFlushMock extends PublisherBase implements Runnable, ObjectCreator<PublisherBase>, ObjectDestructor{
        public PublisherFlushMock() {
            m_logger = null;
        }
        public PublisherBase creator_() {
            return new PublisherFlushMock();
        }
        public void destructor_(Object obj) {
            obj = null;
        }
        public void run() {
        }
        /**
         *
         *
         */
        public ReturnCode write(final OutputStream data){
            return this.write(data, -1, 0);
        }
        public ReturnCode write(final OutputStream data,
                                           int sec,
                                           int usec) {
            RTC.TimedLong tl = new RTC.TimedLong();
            RTC.TimedLongHolder tlh 
                = new RTC.TimedLongHolder(tl);
            tlh._read(data.create_input_stream());
            if (m_logger != null) {
                m_logger.log("PublisherFlushMock.write");
                m_logger.log(Integer.toString(tlh.value.data));
            }
            m_mock_logger.log("PublisherFlushMock.write");
            m_mock_logger.log(Integer.toString(tlh.value.data));
            return ReturnCode.PORT_OK;
        }
        /**
         *
         *
         */
        public boolean isActive() {
            return true;
        }

        /**
         *
         *
         */
        public ReturnCode activate() {
            if (m_logger != null) {
                m_logger.log("PublisherFlushMock.activate");
            }
            m_mock_logger.log("PublisherFlushMock.activate");
            return ReturnCode.PORT_OK;
        }

        /**
         *
         *
         */
        public ReturnCode deactivate() {
            if (m_logger != null) {
                m_logger.log("PublisherFlushMock.deactivate");
            }
            m_mock_logger.log("PublisherFlushMock.deactivate");
            return ReturnCode.PORT_OK;
        }
        /**
         *
         *
         */
        public ReturnCode init(Properties prop) {
            return ReturnCode.PORT_OK;
        }
        /**
         *
         *
         */
        public ReturnCode setConsumer(InPortConsumer consumer) {
            if (m_logger != null) {
                m_logger.log("PublisherFlushMock.setConsumer");
            }
            m_mock_logger.log("PublisherFlushMock.setConsumer");
            return ReturnCode.PORT_OK;
        }
  
        /**
         *
         *
         */
        public ReturnCode setBuffer(BufferBase<OutputStream> buffer) {
            if (m_logger != null) {
                m_logger.log("PublisherFlushMock.setBuffer");
                if(buffer == null) {
                    m_logger.log("buffer NG");
                }
                else {
                    m_logger.log("buffer OK");
                }
            }
            //System.out.println("bufferName:"+buffer.toString());
            m_mock_logger.log("PublisherFlushMock.setBuffer");
            if(buffer == null) {
                m_mock_logger.log("buffer NG");
            }
            else {
                m_mock_logger.log("buffer OK");
            }
            return ReturnCode.PORT_OK;
        }
        /**
         *
         *
         */
        public void setLogger(Logger logger) {
            m_logger = logger;
        }
        private Logger m_logger;
        public String getName(){
            return "flush";
        }
        public ReturnCode setListener(ConnectorBase.ConnectorInfo info,
                              ConnectorListeners listeners) {

            return ReturnCode.PORT_OK;
        }
    }
    /**
     * 
     * 
     *
     */
    class PublisherNewMock extends PublisherBase implements Runnable, ObjectCreator<PublisherBase>, ObjectDestructor{
        public PublisherNewMock() {
            m_logger = null;
        }
        public PublisherBase creator_() {
            return new PublisherNewMock();
        }
        public void destructor_(Object obj) {
            obj = null;
        }  
        public void run() {
        }

        /**
         *
         *
         */
        public ReturnCode write(final OutputStream data){
            return this.write(data, -1, 0);
        }
        public ReturnCode write(final OutputStream data,
                                             int sec,
                                             int usec) {
            RTC.TimedLong tl = new RTC.TimedLong();
            RTC.TimedLongHolder tlh 
                = new RTC.TimedLongHolder(tl);

            tlh._read(data.create_input_stream());
            
            if (m_logger != null) {
                m_logger.log("PublisherNewMock.write");
                m_logger.log(Integer.toString(tlh.value.data));
            }
            m_mock_logger.log("PublisherNewMock.write");
            m_mock_logger.log(Integer.toString(tlh.value.data));
            return ReturnCode.PORT_OK;
        }
        /**
         *
         *
         */
        public boolean isActive() {
            return true;
        }
  
        /**
         *
         *
         */
        public ReturnCode activate() {
            if (m_logger != null) {
                m_logger.log("PublisherNewMock.activate");
            }
            m_mock_logger.log("PublisherNewMock.activate");
            return ReturnCode.PORT_OK;
        }
  
        /**
         *
         *
         */
        public ReturnCode deactivate() {
            if (m_logger != null) {
                m_logger.log("PublisherNewMock.deactivate");
            }
            m_mock_logger.log("PublisherNewMock.deactivate");
            return ReturnCode.PORT_OK;
        }
  
        /**
         *
         *
         */
        public ReturnCode init(Properties prop) {
            return ReturnCode.PORT_OK;
        }
  
        /**
         *
         *
         */
        public ReturnCode setConsumer(InPortConsumer consumer) {
            if (m_logger != null) {
                m_logger.log("PublisherNewMock.setConsumer");
            }
            m_mock_logger.log("PublisherNewMock.setConsumer");
            return ReturnCode.PORT_OK;
        }
  
        /**
         *
         *
         */
        public ReturnCode setBuffer(BufferBase<OutputStream> buffer) {
            if (m_logger != null) {
                m_logger.log("PublisherNewMock.setBuffer");
                if(buffer == null) {
                    m_logger.log("buffer NG");
                }
                else {
                    m_logger.log("buffer OK");
                }
            }
            m_mock_logger.log("PublisherNewMock.setBuffer");
            if(buffer == null) {
                m_mock_logger.log("buffer NG");
            }
            else {
                m_mock_logger.log("buffer OK");
            }
            return ReturnCode.PORT_OK;
        }
        /**
         *
         *
         */
        public void setLogger(Logger logger) {
            m_logger = logger;
        }
        private Logger m_logger;
        public String getName(){
            return "new";
        }
        public ReturnCode setListener(ConnectorBase.ConnectorInfo info,
                              ConnectorListeners listeners) {

            return ReturnCode.PORT_OK;
        }
    };
    public static Logger m_mock_logger = null;
  
    private ORB m_orb;
    private POA m_poa;
    protected Logbuf rtcout;
    protected ConsoleHandler m_stdout;
    protected FileHandler m_fh;
      /**
       * <p> Test initialization </p>
       */
      protected void setUp() throws Exception {
          super.setUp();
          if (m_mock_logger == null){
              m_mock_logger = new Logger();
          }

          java.util.Properties props = new java.util.Properties();
          this.m_orb = ORB.init(new String[0], props);
          this.m_poa = org.omg.PortableServer.POAHelper.narrow(
                  this.m_orb.resolve_initial_references("RootPOA"));
          this.m_poa.the_POAManager().activate();

          rtcout = new Logbuf("Manager");
          m_fh = null; 
          rtcout.setLevel("SILENT");

          rtcout.println(rtcout.TRACE, "IN  test_OutPortPushConnector.log");
          final PublisherBaseFactory<PublisherBase,String> factory0 
              = PublisherBaseFactory.instance();

          factory0.addFactory("flush",
                      new PublisherFlushMock(),
                      new PublisherFlushMock());

          final PublisherBaseFactory<PublisherBase,String> factory1
              = PublisherBaseFactory.instance();

          factory1.addFactory("new",
                      new PublisherNewMock(),
                      new PublisherNewMock());

          final BufferFactory<RingBuffer<OutputStream>,String> factoryB 
              = BufferFactory.instance();
          factoryB.addFactory("ring_buffer",
                      new CdrRingBuffer(),
                      new CdrRingBuffer());

          PeriodicTaskFactory<PeriodicTaskBase,String> factoryP
              = PeriodicTaskFactory.instance();
          factoryP.addFactory("default",
                      new PeriodicTask(),
                      new PeriodicTask());

          CdrRingBufferMock2.CdrRingBufferMock2Init();
      }
  		
      /**
       * <p> Test finalization </p>
       */
      protected void tearDown() throws Exception {
          super.tearDown();
          if( m_orb != null) {
              m_orb.destroy();
              m_orb = null;
          }
          rtcout.removeStream(m_fh);
      }
  		

      /**
       * <p> Constructor </p>    
       * 
       */
      public void test_OutPortPushConnector()  throws Exception {

          String logfile = "test_OutPortPushConnector.log";
          m_fh = new FileHandler(logfile);
          rtcout.addStream(m_fh);
          rtcout.setLevel("TRACE");

          RTC.ConnectorProfile prof = new RTC.ConnectorProfile();
          NVListHolder prof_holder = new NVListHolder(prof.properties);
          CORBA_SeqUtil.push_back(prof_holder,
  			       NVUtil.newNV("dataport.interface_type",
  					     "corba_cdr"));
          prof.properties = prof_holder.value;
          CORBA_SeqUtil.push_back(prof_holder,
  			       NVUtil.newNV("dataport.dataflow_type",
  					     "push"));
          prof.properties = prof_holder.value;
          CORBA_SeqUtil.push_back(prof_holder,
  	  		       NVUtil.newNV("dataport.subscription_type",
  					     "new"));
          prof.properties = prof_holder.value;
          prof.ports = new PortService[2];
          // prop: [port.outport].
          Properties prop = new Properties();
          {
              Properties conn_prop = new Properties();
              NVListHolder holder = new NVListHolder(prof.properties);
              NVUtil.copyToProperties(conn_prop, holder);
              prop.merge(conn_prop.getNode("dataport")); // merge ConnectorProfile
          }
          InPortCorbaCdrConsumerMock consumer = new InPortCorbaCdrConsumerMock();
          Logger logger = new Logger();
          consumer.setLogger(logger);
          //ConnectorBase.Profile profile_new = new ConnectorBase.Profile(
          ConnectorBase.ConnectorInfo profile_new = new ConnectorBase.ConnectorInfo(

                                     prof.name,
                                     prof.connector_id,
                                     CORBA_SeqUtil.refToVstring(prof.ports),
                                     prop); 
          OutPortConnector connector = null;
          ConnectorListeners listeners = new ConnectorListeners();
          int init_counter = logger.countLog("InPortCorbaCdrConsumerMock.init");
          int setBuffer_counter = m_mock_logger.countLog("PublisherNewMock.setBuffer");
          int buffOk_counter = m_mock_logger.countLog("buffer OK");
          int buffNg_counter = m_mock_logger.countLog("buffer NG");
          int setConsumer_counter = m_mock_logger.countLog("PublisherNewMock.setConsumer");
          try {
              //connector = new OutPortPushConnector(profile_new, consumer);
              connector = new OutPortPushConnector(profile_new, listeners, consumer);
              assertEquals("1:",init_counter+1, 
                  logger.countLog("InPortCorbaCdrConsumerMock.init"));
              assertEquals("2:",setBuffer_counter+1, 
                  m_mock_logger.countLog("PublisherNewMock.setBuffer"));
              assertEquals("3:",buffOk_counter+1, 
                  m_mock_logger.countLog("buffer OK"));
              assertEquals("4:",buffNg_counter, 
                  m_mock_logger.countLog("buffer NG"));
              assertEquals("5:",setConsumer_counter+1, 
                  m_mock_logger.countLog("PublisherNewMock.setConsumer"));
          }
          catch(Exception e) {
              fail("The exception not intended was thrown .");
          }

  
  
          //Flush starts when subscription_type is not set. 
          CORBA_SeqUtil.push_back(prof_holder,
  	  		       NVUtil.newNV("dataport.subscription_type",
  					     ""));
          prof.properties = prof_holder.value;
          // prop: [port.outport].
          {
              Properties conn_prop = new Properties();
              NVListHolder holder = new NVListHolder(prof.properties);
              NVUtil.copyToProperties(conn_prop, holder);
              prop.merge(conn_prop.getNode("dataport"));
          }
          //ConnectorBase.Profile profile_flush = new ConnectorBase.Profile(
          ConnectorBase.ConnectorInfo profile_flush = new ConnectorBase.ConnectorInfo(
                                     prof.name,
                                     prof.connector_id,
                                     CORBA_SeqUtil.refToVstring(prof.ports),
                                     prop); 
          init_counter = logger.countLog("InPortCorbaCdrConsumerMock.init");
          setBuffer_counter = m_mock_logger.countLog("PublisherFlushMock.setBuffer");
          buffOk_counter = m_mock_logger.countLog("buffer OK");
          buffNg_counter = m_mock_logger.countLog("buffer NG");
          setConsumer_counter = m_mock_logger.countLog("PublisherFlushMock.setConsumer");
          try{
              //connector = new OutPortPushConnector(profile_flush, consumer);
              connector = new OutPortPushConnector(profile_flush, listeners, consumer);
              assertEquals("6:",init_counter+1, 
              logger.countLog("InPortCorbaCdrConsumerMock.init"));
              assertEquals("7:",setBuffer_counter+1, 
              m_mock_logger.countLog("PublisherFlushMock.setBuffer"));
              assertEquals("8:",buffOk_counter+1, 
              m_mock_logger.countLog("buffer OK"));
              assertEquals("9:",setConsumer_counter+1, 
              m_mock_logger.countLog("PublisherFlushMock.setConsumer"));
          }
          catch(Exception e) {
              fail("The exception not intended was thrown .");
          }
  
          
  
          //When consumer is not set, the exception is thrown out. 
          OutPortConnector connector_err = null;
          RTC.ConnectorProfile prof_err = new RTC.ConnectorProfile();
          NVListHolder prof_err_holder = new NVListHolder(prof_err.properties);
          CORBA_SeqUtil.push_back(prof_err_holder,
  			       NVUtil.newNV("",
  					     ""));
          prof_err.properties = prof_err_holder.value;
          prof_err.ports = new PortService[2];
          try {
              // prop: [port.outport].
              {
                  Properties conn_prop = new Properties();
                  NVListHolder holder = new NVListHolder(prof_err.properties);
                  NVUtil.copyToProperties(conn_prop, holder);
                  prop.merge(conn_prop.getNode("dataport"));
              }
              //ConnectorBase.Profile profile_err = new ConnectorBase.Profile(
              ConnectorBase.ConnectorInfo profile_err = new ConnectorBase.ConnectorInfo(
                                      prof_err.name,
                                      prof_err.connector_id,
                                      CORBA_SeqUtil.refToVstring(prof_err.ports),
                                      prop); 
              //connector_err = new OutPortPushConnector(profile_err, null);
              connector_err = new OutPortPushConnector(profile_err, listeners, null);
              fail("The exception was not thrown. ");
          }
          catch(Exception e) {
              assertTrue(e.getMessage().equals("bad_alloc()"));
          }
      }
      /**
       * <p> write </p>
       * 
       */
      public void test_write()   throws Exception {
          String logfile = "test_write.log";
          m_fh = new FileHandler(logfile);
          rtcout.addStream(m_fh);
          rtcout.setLevel("TRACE");
          RTC.ConnectorProfile prof = new RTC.ConnectorProfile();
          NVListHolder prof_holder = new NVListHolder(prof.properties);
          CORBA_SeqUtil.push_back(prof_holder,
  			       NVUtil.newNV("dataport.interface_type",
  					     "corba_cdr"));
          prof.properties = prof_holder.value;
          CORBA_SeqUtil.push_back(prof_holder,
  			       NVUtil.newNV("dataport.dataflow_type",
  					     "push"));
          prof.properties = prof_holder.value;
          CORBA_SeqUtil.push_back(prof_holder,
  	  		       NVUtil.newNV("dataport.subscription_type",
  					     "new"));
          prof.properties = prof_holder.value;
          prof.ports = new PortService[2];
          // prop: [port.outport].
          Properties prop = new Properties();
          {
              Properties conn_prop = new Properties();
              NVListHolder holder = new NVListHolder(prof.properties);
              NVUtil.copyToProperties(conn_prop, holder);
              prop.merge(conn_prop.getNode("dataport"));
          }
          InPortCorbaCdrConsumerMock consumer = new InPortCorbaCdrConsumerMock();
          Logger logger = new Logger();
          consumer.setLogger(logger);
          //ConnectorBase.Profile profile_new = new ConnectorBase.Profile (
          ConnectorBase.ConnectorInfo profile_new = new ConnectorBase.ConnectorInfo(
                                     prof.name,
                                     prof.connector_id,
                                     CORBA_SeqUtil.refToVstring(prof.ports),
                                     prop); 
          OutPortConnector connector = null;
          ConnectorListeners listeners = new ConnectorListeners();
          //try {
              //connector = new OutPortPushConnector(profile_new, consumer);
              connector = new OutPortPushConnector(profile_new, listeners, consumer);
              int write_counter = m_mock_logger.countLog("PublisherNewMock.write");
              int num_counter =  m_mock_logger.countLog("12345");
              OutputStream cdr = toStream(12345,0,0);
              RTC.TimedLong out_val = new RTC.TimedLong(new RTC.Time(0,0),12345);
              DataRef<RTC.TimedLong> out = new DataRef<RTC.TimedLong>(out_val);
              OutPort<RTC.TimedLong> outPort = new OutPort<RTC.TimedLong>("out", out);
              connector.setOutPortBase(outPort);
              connector.write(out.v);
              //connector.write(cdr);
              assertEquals("1:",write_counter+1, 
                     m_mock_logger.countLog("PublisherNewMock.write"));
              assertEquals("2:",num_counter+1, 
                     m_mock_logger.countLog("12345"));
          //}
          //catch(Exception e) {
          //    fail("The exception not intended was thrown .");
          //}
  
  
      }
      /**
       * <p> disconnect </p>
       * 
       */
      public void test_disconnect_getBuffer() throws Exception {
          String logfile = "test_disconnect_getBuffer.log";
          m_fh = new FileHandler(logfile);
          rtcout.addStream(m_fh);
          rtcout.setLevel("TRACE");
          rtcout.println(rtcout.TRACE, "test_disconnect_getBuffer");

          RTC.ConnectorProfile prof = new RTC.ConnectorProfile();
          NVListHolder prof_holder = new NVListHolder(prof.properties);
          CORBA_SeqUtil.push_back(prof_holder,
  			       NVUtil.newNV("dataport.interface_type",
  					     "corba_cdr"));
          prof.properties = prof_holder.value;
          CORBA_SeqUtil.push_back(prof_holder,
  			       NVUtil.newNV("dataport.dataflow_type",
  					     "push"));
          prof.properties = prof_holder.value;
          CORBA_SeqUtil.push_back(prof_holder,
  	  		       NVUtil.newNV("dataport.subscription_type",
  					     "new"));
          prof.properties = prof_holder.value;
          prof.ports = new PortService[2];
          // prop: [port.outport].
          Properties prop = new Properties();
          {
              Properties conn_prop = new Properties();
              NVListHolder holder = new NVListHolder(prof.properties);
              NVUtil.copyToProperties(conn_prop, holder);
              prop.merge(conn_prop.getNode("dataport"));
          }
          InPortCorbaCdrConsumerMock consumer = new InPortCorbaCdrConsumerMock();
          Logger logger = new Logger();
          consumer.setLogger(logger);
          //ConnectorBase.Profile profile_new = new ConnectorBase.Profile(
          ConnectorBase.ConnectorInfo profile_new = new ConnectorBase.ConnectorInfo(
                                     prof.name,
                                     prof.connector_id,
                                     CORBA_SeqUtil.refToVstring(prof.ports),
                                     prop); 
          OutPortConnector connector=null;
          ConnectorListeners listeners = new ConnectorListeners();
          try {
              //connector = new OutPortPushConnector(profile_new, consumer);
              connector = new OutPortPushConnector(profile_new, listeners, consumer);
              BufferBase<OutputStream> buffer = connector.getBuffer();
              assertTrue("1",buffer!=null);
              connector.disconnect();
              buffer =connector.getBuffer();
              assertTrue("2",buffer==null);
          }
          catch(Exception e) {
              fail("The exception not intended was thrown .");
          }
  
      }
      /**
       * <p> activate </p>
       * 
       */
      public void test_activate_deactivate() {
	  RTC.ConnectorProfile prof = new RTC.ConnectorProfile();
          NVListHolder prof_holder = new NVListHolder(prof.properties);
          CORBA_SeqUtil.push_back(prof_holder,
  			       NVUtil.newNV("dataport.interface_type",
  					     "corba_cdr"));
          prof.properties = prof_holder.value;
          CORBA_SeqUtil.push_back(prof_holder,
  			       NVUtil.newNV("dataport.dataflow_type",
  					     "push"));
          prof.properties = prof_holder.value;
          CORBA_SeqUtil.push_back(prof_holder,
  	  		       NVUtil.newNV("dataport.subscription_type",
  					     "new"));
          prof.properties = prof_holder.value;
          prof.ports = new PortService[2];
          // prop: [port.outport].
          Properties prop = new Properties();
          {
              Properties conn_prop = new Properties();
              NVListHolder holder = new NVListHolder(prof.properties);
              NVUtil.copyToProperties(conn_prop, holder);
              prop.merge(conn_prop.getNode("dataport"));
          }
          InPortCorbaCdrConsumerMock consumer = new InPortCorbaCdrConsumerMock();
          Logger logger = new Logger();
          consumer.setLogger(logger);
          //ConnectorBase.Profile profile_new = new ConnectorBase.Profile(
          ConnectorBase.ConnectorInfo profile_new = new ConnectorBase.ConnectorInfo(
                                     prof.name,
                                     prof.connector_id,
                                     CORBA_SeqUtil.refToVstring(prof.ports),
                                     prop); 
          OutPortConnector connector = null;
          ConnectorListeners listeners = new ConnectorListeners();
          try {
              //connector = new OutPortPushConnector(profile_new, consumer);
              connector = new OutPortPushConnector(profile_new, listeners, consumer);
              assertEquals(0, 
                     m_mock_logger.countLog("PublisherNewMock.activate"));
              connector.activate();
              assertEquals(1, 
                     m_mock_logger.countLog("PublisherNewMock.activate"));
  
          }
          catch(Exception e) {
              fail("The exception not intended was thrown .");
          }
          try{
              //connector = new OutPortPushConnector(profile_new, consumer);
              connector = new OutPortPushConnector(profile_new, listeners, consumer);
              assertEquals(0, 
                   m_mock_logger.countLog("PublisherNewMock.deactivate"));
              connector.deactivate();
              assertEquals(1, 
                   m_mock_logger.countLog("PublisherNewMock.deactivate"));
          }
          catch(Exception e) {
              fail("The exception not intended was thrown .");
          }
      }
    protected OutputStream toStream(int data, int sec, int nsec){
            org.omg.CORBA.Any any = ORBUtil.getOrb().create_any();
            OutputStream cdr = any.create_output_stream();
            RTC.Time tm = new RTC.Time(sec,nsec);
            RTC.TimedLong tmlong = new RTC.TimedLong(tm,data);
            RTC.TimedLongHolder tmlongholder 
                = new RTC.TimedLongHolder(tmlong);
            tmlongholder._write(cdr);
            return cdr;

    }
};
   
/**
 * 
 * 
 *
 */
class CdrRingBufferMock2 extends CdrRingBuffer{
    
        /**
         * <p> creator_ </p>
         * 
         * @return Object Created instances
         *
         */
        public BufferBase<OutputStream> creator_() {
            return new RingBufferMock2<OutputStream>();
        }
        /**
         * <p> destructor_ </p>
         * 
         * @param obj    The target instances for destruction
         *
         */
        public void destructor_(Object obj) {
            obj = null;
        }

        /**
         * <p> CdrRingBufferInit </p>
         *
         */
        public static void CdrRingBufferMock2Init() {
            final BufferFactory<RingBufferMock2<OutputStream>,String> factory 
                = BufferFactory.instance();

            factory.addFactory("ring_buffer_mock2",
                        new CdrRingBufferMock2(),
                        new CdrRingBufferMock2());
    
        }
    }

    /**
     * 
     * 
     *
     */
    class RingBufferMock2<DataType> extends RingBuffer<DataType>{
        public RingBufferMock2() {
            //m_logger = null;
            //m_mock_logger.log("RingBufferMock.Constructor");
            //m_read_return_value = jp.go.aist.rtm.RTC.buffer.ReturnCode.BUFFER_OK;
            //m_write_return_value = jp.go.aist.rtm.RTC.buffer.ReturnCode.BUFFER_OK;
        }
        /**
         *
         *
         */
        public void set_read_return_value(jp.go.aist.rtm.RTC.buffer.ReturnCode value) {
            //m_read_return_value = value;
        }
        /**
         *
         *
         */
        public void set_write_return_value(jp.go.aist.rtm.RTC.buffer.ReturnCode value) {
            //m_write_return_value = value;
        }
        /**
         *
         *
         */
        public  void init(final Properties prop) {
        }
        /**
         *
         *
         */
        public int length() {
            return 0;
        }
        /**
         *
         *
         */
        public jp.go.aist.rtm.RTC.buffer.ReturnCode length(int n) {
            return jp.go.aist.rtm.RTC.buffer.ReturnCode.BUFFER_OK; //BUFFER_OK;
        }
        /**
         *
         *
         */
        public jp.go.aist.rtm.RTC.buffer.ReturnCode reset() {
            return jp.go.aist.rtm.RTC.buffer.ReturnCode.BUFFER_OK; //BUFFER_OK;
        }
        /**
         *
         *
         */
        public DataType wptr(int n) {
            return m_data;
        }
        /**
         *
         *
         */
        public  jp.go.aist.rtm.RTC.buffer.ReturnCode advanceWptr(int n) {
            return jp.go.aist.rtm.RTC.buffer.ReturnCode.BUFFER_OK; //BUFFER_OK;
        }
        /**
         *
         *
         */
        public jp.go.aist.rtm.RTC.buffer.ReturnCode put(final DataType value) {
            return jp.go.aist.rtm.RTC.buffer.ReturnCode.BUFFER_OK; //BUFFER_OK;
        }
        /**
         *
         *
         */
        public jp.go.aist.rtm.RTC.buffer.ReturnCode write(final DataType value,
                                 int sec, int nsec) {
            /*!
            if (m_logger != null) {
                m_logger.log("RingBufferMock.write");
            }
            m_mock_logger.log("RingBufferMock.write");
            */
            return m_write_return_value; //BUFFER_OK;
        }
        /**
         *
         *
         */
        public  int writable() {
            return 0;
        }
        /**
         *
         *
         */
        public boolean full() {
              return true;
        }
        /**
         *
         *
         */
        public DataType rptr(int n ) {
            return m_data;
        }
        /**
         *
         *
         */
        public jp.go.aist.rtm.RTC.buffer.ReturnCode advanceRptr(int n) {
            return jp.go.aist.rtm.RTC.buffer.ReturnCode.BUFFER_OK; //BUFFER_OK;
        }
        /**
         *
         *
         */
        public jp.go.aist.rtm.RTC.buffer.ReturnCode get(DataType value) {
            return jp.go.aist.rtm.RTC.buffer.ReturnCode.BUFFER_OK; //BUFFER_OK;
        }
        /**
         *
         *
         */
        public DataType  get() {
            return m_data;
        }
        /**
         *
         *
         */
        public jp.go.aist.rtm.RTC.buffer.ReturnCode read(DataRef<DataType> value,
                              int sec, int nsec) {
            /*!
            if (m_logger != null) {
                m_logger.log("RingBufferMock.read");
            }
            m_mock_logger.log("RingBufferMock.read");
            */
            return m_read_return_value; //BUFFER_OK;
        }
        /**
         *
         *
         */
        public int readable() {
            return 0;
        }
        /**
         *
         *
         */
        public boolean empty() {
            return true;
        }
        /**
         *
         *
         */
        public void setLogger(Logger logger) {
            //m_logger = logger;
        }

        private DataType m_data;
        private Vector<DataType> m_buffer;
        private Logger m_logger;
        private jp.go.aist.rtm.RTC.buffer.ReturnCode m_read_return_value;
        private jp.go.aist.rtm.RTC.buffer.ReturnCode m_write_return_value;
        //public static Logger m_mock_logger = null;
  };

                            
