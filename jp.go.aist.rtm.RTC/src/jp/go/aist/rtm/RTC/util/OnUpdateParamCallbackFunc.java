package jp.go.aist.rtm.RTC.util;

/**
 * <p>Callbackクラスのインターフェースです。</p>
 */
public interface OnUpdateParamCallbackFunc {
    
    /**
     * <p>対象のオブジェクトに対して操作を行います。</p>
     *
     * @param config_set コンフィグレーション
     */

    public void operator(String config_set, String config_param);
}