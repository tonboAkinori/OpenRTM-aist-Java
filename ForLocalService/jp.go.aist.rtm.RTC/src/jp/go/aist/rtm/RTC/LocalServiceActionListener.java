package jp.go.aist.rtm.RTC;

import jp.go.aist.rtm.RTC.util.Properties;

  /**
   * {@.ja LocalServiceActionListener クラス}
   * {@.en LocalServiceActionListener class}
   * <p>
   * {@.ja 各アクションに対応するユーザーコードが呼ばれる直前のタイミング
   * でコールされるリスなクラスの基底クラス。
   *
   * - ADD_PORT:
   * - REMOVE_PORT:}
   * {@.en This class is abstract base class for listener classes that
   * provides callbacks for various events in rtobject.}
   */
public class LocalServiceActionListener {
    /**
     * {@.ja preServiceRegister コールバック関数}
     * {@.en preServiceRegister callback function}
     */
    public void preServiceRegister(String service_name) {
    }
    
    /**
     * {@.ja postServiceRegister コールバック関数}
     * {@.en postServiceRegister callback function}
     */
    public void postServiceRegister(String service_name,
                                     LocalServiceBase service){
    }

    /**
     * {@.ja preServiceInit コールバック関数}
     * {@.en preServiceInit callback function}
     */
    public void preServiceInit(Properties prop,
                                LocalServiceBase service){
    }
    
    /**
     * {@.ja postServiceInit コールバック関数}
     * {@.en postServiceInit callback function}
     */
    public void postServiceInit(Properties prop,
                                 LocalServiceBase service){
    }
    
    /**
     * {@.ja preServiceReinit コールバック関数}
     * {@.en preServiceReinit callback function}
     */
    public void preServiceReinit(Properties prop,
                                  LocalServiceBase service){
    }
    
    /**
     * {@.ja postServiceReinit コールバック関数}
     * {@.en postServiceReinit callback function}
     */
    public void postServiceReinit(Properties prop,
                                   LocalServiceBase service){
    }
    
    /**
     * {@.ja postServiceFinalize コールバック関数}
     * {@.en postServiceFinalize callback function}
     */
    public void postServiceFinalize(String service_name,
                                     LocalServiceBase service){
    }
    
    /**
     * {@.ja preServiceFinalize コールバック関数}
     * {@.en preServiceFinalize callback function}
     */
    public void preServiceFinalize(String service_name,
                                    LocalServiceBase service){
    }
  };
