package jp.go.aist.rtm.RTC;

/**
 * {@.ja OutPortConsumer用ファクトリの実装}
 * {@.en Implement of factory for OutPortConsumer} 
 */
public class OutPortConsumerFactory<ABSTRACTCLASS,IDENTIFIER> extends FactoryGlobal<ABSTRACTCLASS,IDENTIFIER> {

    /**
     * {@.ja コンストラクタ。}
     * {@.en Constructor}
     */
    private OutPortConsumerFactory() {

    }
    /**
     * {@.ja インスタンス生成。}
     * {@.en Create instance}
     *
     * <p>
     * {@.ja インスタンスを生成する。}
     *
     * @return 
     *   {@.ja インスタンス}
     *   {@.en OutPortConsumerFactory object}
     *
     */
    synchronized public static OutPortConsumerFactory instance() {
        if (factory_global == null) {
            try {
                factory_global = new OutPortConsumerFactory();
            } catch (Exception e) {
                factory_global = null;
            }
        }

        return factory_global;
    }
    /**
     *  <p> object </p>
     */
    private static OutPortConsumerFactory factory_global;
}

