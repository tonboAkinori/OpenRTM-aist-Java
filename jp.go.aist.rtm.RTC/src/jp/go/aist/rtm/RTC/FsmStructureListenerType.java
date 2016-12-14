package jp.go.aist.rtm.RTC;
  /**
   * {@.ja FsmStructureListener のタイプ}
   * {@.en The types of FsmStructureListener}
   * <p>
   * {@.ja <ul> 
   * <li> SET_FSM_STRUCTURE: FSM構造の設定
   * <li> GET_FSM_STRUCTURE: FSM構造の取得
   * </ul>}
   * {@.en <ul> 
   * <li> SET_FSM_STRUCTURE: Setting FSM structure
   * <li> GET_FSM_STRUCTURE: Getting FSM structure
   * </ul>}
   * </p>
   */
public class FsmStructureListenerType {

    public static final int SET_FSM_STRUCTURE = 0;
    public static final int GET_FSM_STRUCTURE = 1;
    public static final int FSM_STRUCTURE_LISTENER_NUM = 2; 

    private static final String[] TypeString = {
        "SET_FSM_STRUCTURE",
        "GET_FSM_STRUCTURE",
        "FSM_STRUCTURE_LISTENER_NUM",
    };

    /**
     * {@.ja FsmStructureListenerType を文字列に変換}
     * {@.en Convert FsmStructureListenerType into the string.}
     * <p>
     * {@.ja FsmStructureListenerType を文字列に変換する}
     * {@.en Convert FsmStructureListenerType into the string.}
     * </p>
     *
     * @param type 
     *   {@.ja 変換対象 FsmStructureListenerType}
     *   {@.en The target FsmStructureListenerType for transformation}
     *
     * @return
     *   {@.ja 文字列変換結果}
     *   {@.en Trnasformation result of string representation}
     *
     */
    public static String toString(final int type){
        if (type < FSM_STRUCTURE_LISTENER_NUM) { 
            return TypeString[type]; 
        }
        return "";
    }
}
