package jp.go.aist.rtm.RTC.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.omg.CORBA.Any;

import RTC.Time;
import RTC.TimeHelper;
import RTC.TimedBoolean;
import RTC.TimedBooleanHelper;
import RTC.TimedBooleanSeq;
import RTC.TimedBooleanSeqHelper;
import RTC.TimedChar;
import RTC.TimedCharHelper;
import RTC.TimedCharSeq;
import RTC.TimedCharSeqHelper;
import RTC.TimedDouble;
import RTC.TimedDoubleHelper;
import RTC.TimedDoubleSeq;
import RTC.TimedDoubleSeqHelper;
import RTC.TimedFloat;
import RTC.TimedFloatHelper;
import RTC.TimedFloatSeq;
import RTC.TimedFloatSeqHelper;
import RTC.TimedLong;
import RTC.TimedLongHelper;
import RTC.TimedLongSeq;
import RTC.TimedLongSeqHelper;
import RTC.TimedOctet;
import RTC.TimedOctetHelper;
import RTC.TimedOctetSeq;
import RTC.TimedOctetSeqHelper;
import RTC.TimedShort;
import RTC.TimedShortHelper;
import RTC.TimedShortSeq;
import RTC.TimedShortSeqHelper;
import RTC.TimedState;
import RTC.TimedStateHelper;
import RTC.TimedString;
import RTC.TimedStringHelper;
import RTC.TimedStringSeq;
import RTC.TimedStringSeqHelper;
import RTC.TimedULong;
import RTC.TimedULongHelper;
import RTC.TimedULongSeq;
import RTC.TimedULongSeqHelper;
import RTC.TimedUShort;
import RTC.TimedUShortHelper;
import RTC.TimedUShortSeq;
import RTC.TimedUShortSeqHelper;

/**
 * {@.ja 各種データ型とAny型との間を型変換するユーティリティクラス}
 * {@.en It is a utility class that converts the type 
 * as for various data types/Any type. }
 *
 * @param &lt;DataType&gt; 
 *   {@.ja Any型との変換相手となるデータ型を指定}
 *   {@.en data type}
 */
public class TypeCast<T> {

    /**
     * {@.ja コンストラクタ}
     * {@.en Constructor}
     * 
     * @param klass 
     *   {@.ja 変換相手となるクラスのClassオブジェクト}
     *   {@.en class object}
     */
    public TypeCast(Class<T> klass) {
        
        assert(klass != null);
        this.klass = klass;
    }
    
    /**
     * {@.ja Any型のデータを、&lt;DataType&gt;型に変換する}
     * {@.en Converts the data of Any type into DataType type}
     * 
     * @param data 
     *   {@.ja 変換対象のデータ}
     *   {@.en data}
     * @return 
     *   {@.ja 変換後のデータ}
     *   {@.en Converted data}
     * @throws ClassCastException 
     *   {@.ja 変換できない場合}
     *   {@.en Filed in conversion}
     */
    public T castType(Any data) {
        
        if (klass.equals(Byte.class)) {
            return klass.cast(Byte.valueOf(data.extract_octet()));

        } else if (klass.equals(Double.class)) {
            return klass.cast(Double.valueOf(data.extract_double()));

        } else if (klass.equals(Float.class)) {
            return klass.cast(Float.valueOf(data.extract_float()));

        } else if (klass.equals(Integer.class)) {
            return klass.cast(Integer.valueOf(data.extract_long()));

        } else if (klass.equals(Long.class)) {
            return klass.cast(Long.valueOf(data.extract_longlong()));

        } else if (klass.equals(Short.class)) {
            return klass.cast(Short.valueOf(data.extract_short()));

        } else if (klass.equals(String.class)) {
            return klass.cast(data.extract_wstring());

        } else if (klass.equals(Character.class)) {
            return klass.cast(Character.valueOf(data.extract_char()));

        } else if (klass.equals(Boolean.class)) {
            return klass.cast(Boolean.valueOf(data.extract_boolean()));
        } else if (klass.equals(Time.class)) {
            return klass.cast(TimeHelper.extract(data));

        } else if (klass.equals(TimedBoolean.class)) {
            return klass.cast(TimedBooleanHelper.extract(data));

        } else if (klass.equals(TimedChar.class)) {
            return klass.cast(TimedCharHelper.extract(data));

        } else if (klass.equals(TimedDouble.class)) {
            return klass.cast(TimedDoubleHelper.extract(data));

        } else if (klass.equals(TimedFloat.class)) {
            return klass.cast(TimedFloatHelper.extract(data));

        } else if (klass.equals(TimedLong.class)) {
            return klass.cast(TimedLongHelper.extract(data));

        } else if (klass.equals(TimedOctet.class)) {
            return klass.cast(TimedOctetHelper.extract(data));

        } else if (klass.equals(TimedShort.class)) {
            return klass.cast(TimedShortHelper.extract(data));

        } else if (klass.equals(TimedState.class)) {
            return klass.cast(TimedStateHelper.extract(data));

        } else if (klass.equals(TimedString.class)) {
            return klass.cast(TimedStringHelper.extract(data));

        } else if (klass.equals(TimedULong.class)) {
            return klass.cast(TimedULongHelper.extract(data));

        } else if (klass.equals(TimedUShort.class)) {
            return klass.cast(TimedUShortHelper.extract(data));

        } else if (klass.equals(TimedBooleanSeq.class)) {
            return klass.cast(TimedBooleanSeqHelper.extract(data));
            
        } else if (klass.equals(TimedCharSeq.class)) {
            return klass.cast(TimedCharSeqHelper.extract(data));

        } else if (klass.equals(TimedDoubleSeq.class)) {
            return klass.cast(TimedDoubleSeqHelper.extract(data));

        } else if (klass.equals(TimedFloatSeq.class)) {
            return klass.cast(TimedFloatSeqHelper.extract(data));

        } else if (klass.equals(TimedLongSeq.class)) {
            return klass.cast(TimedLongSeqHelper.extract(data));

        } else if (klass.equals(TimedOctetSeq.class)) {
            return klass.cast(TimedOctetSeqHelper.extract(data));

        } else if (klass.equals(TimedShortSeq.class)) {
            return klass.cast(TimedShortSeqHelper.extract(data));

        } else if (klass.equals(TimedStringSeq.class)) {
            return klass.cast(TimedStringSeqHelper.extract(data));

        } else if (klass.equals(TimedULongSeq.class)) {
            return klass.cast(TimedULongSeqHelper.extract(data));

        } else if (klass.equals(TimedUShortSeq.class)) {
            return klass.cast(TimedUShortSeqHelper.extract(data));

        } else {
            String className = klass.getCanonicalName();
            if (className == null) {
                throw new IllegalStateException("Cannot get class name.");
            }
            try {
                Class helper = Class.forName(className + "Helper", true, klass.getClassLoader()); 
                Method method = helper.getMethod("extract", org.omg.CORBA.Any.class);
                Object targetObject = method.invoke(
                        null, // invoke static method.
                        data);
                return klass.cast(targetObject);
            } catch ( Exception ex) {
                throw new ClassCastException("Unknown data type.");
            }
        }
    }
    
    /**
     * {@.ja T型に変換する}
     * {@.en Converts the data into T type}
     * 
     * @param obj 
     *   {@.ja 変換対象のデータ}
     *   {@.en data}
     * @return 
     *   {@.ja 変換後のデータ}
     *   {@.en Converted data}
     */
    public T castType(org.omg.CORBA.Object obj)
        throws ClassNotFoundException, SecurityException, NoSuchMethodException,
        IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        
        String className = klass.getCanonicalName();
        if (className == null) {
            throw new IllegalStateException("Cannot get class name.");
        }
        
        Class helper = Class.forName(className + "Helper", true, klass.getClassLoader()); 
        Method method = helper.getMethod("narrow", org.omg.CORBA.Object.class);
        
        java.lang.Object narrowedObj = method.invoke(
                null, // invoke static method.
                obj);
        
        return klass.cast(narrowedObj);
    }
    
    /**
     * {@.ja &lt;DataType&gt;型のデータを、Any型のデータに変換する}
     * {@.en Cconverts the data of DataType type into the data of Any type}
     *
     * <p>
     * {@.ja 引数にnullが指定された場合には、&lt;DataType&gt;型のデフォルト値が
     * 指定されたものとして処理する。
     * また、データ変換対象のデータが
     * Timed*クラスのオブジェクトであり、
     * そのオブジェクトのTime属性がnullの場合は、
     * 自動的に現在時刻をセットする。}
     * 
     * @param data 
     *  {@.ja 変換対象のデータ}
     *  {@.en data}
     * @return 
     *  {@.ja 変換後のデータ}
     *  {@.en Converted data}
     * @throws ClassCastException 
     *   {@.ja 変換できない場合}
     *   {@.en Filed in conversion}
     */
    public Any castAny(T data) {
    
        data = replaceNull(data);
        
        Any any = ORBUtil.getOrb().create_any();

        if (klass.equals(Byte.class)) {
            any.insert_octet(Byte.class.cast(data).byteValue());
            return any;
            
        } else if (klass.equals(Double.class)) {
            any.insert_double(Double.class.cast(data).doubleValue());
            return any;
            
        } else if (klass.equals(Float.class)) {
            any.insert_float(Float.class.cast(data).floatValue());
            return any;
            
        } else if (klass.equals(Integer.class)) {
            any.insert_long(Integer.class.cast(data).intValue());
            return any;
            
        } else if (klass.equals(Long.class)) {
            any.insert_longlong(Long.class.cast(data).longValue());
            return any;
            
        } else if (klass.equals(Short.class)) {
            any.insert_short(Short.class.cast(data).shortValue());
            return any;
            
        } else if (klass.equals(String.class)) {
            any.insert_string(String.class.cast(data));
            return any;
            
        } else if (klass.equals(Character.class)) {
            any.insert_char(Character.class.cast(data).charValue());
            return any;
            
        } else if (klass.equals(Boolean.class)) {
            any.insert_boolean(Boolean.class.cast(data).booleanValue());
            return any;
            
        } else if (klass.equals(Time.class)) {
            TimeHelper.insert(any, Time.class.cast(data));
            return any;
            
        } else if (klass.equals(TimedBoolean.class)) {
            TimedBoolean timedData = TimedBoolean.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedBooleanHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedChar.class)) {
            TimedChar timedData = TimedChar.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedCharHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedDouble.class)) {
            TimedDouble timedData = TimedDouble.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedDoubleHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedFloat.class)) {
            TimedFloat timedData = TimedFloat.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedFloatHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedLong.class)) {
            TimedLong timedData = TimedLong.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedLongHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedOctet.class)) {
            TimedOctet timedData = TimedOctet.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedOctetHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedShort.class)) {
            TimedShort timedData = TimedShort.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedShortHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedState.class)) {
            TimedState timedData = TimedState.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedStateHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedString.class)) {
            TimedString timedData = TimedString.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            timedData.data = fillString(timedData.data);
            TimedStringHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedULong.class)) {
            TimedULong timedData = TimedULong.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedULongHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedUShort.class)) {
            TimedUShort timedData = TimedUShort.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            TimedUShortHelper.insert(any, timedData);
            return any;

        } else if (klass.equals(TimedBooleanSeq.class)) {
            TimedBooleanSeq timedData = TimedBooleanSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new boolean[0];
            }
            TimedBooleanSeqHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedCharSeq.class)) {
            TimedCharSeq timedData = TimedCharSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new char[0];
            }
            TimedCharSeqHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedDoubleSeq.class)) {
            TimedDoubleSeq timedData = TimedDoubleSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new double[0];
            }
            TimedDoubleSeqHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedFloatSeq.class)) {
            TimedFloatSeq timedData = TimedFloatSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new float[0];
            }
            TimedFloatSeqHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedLongSeq.class)) {
            TimedLongSeq timedData = TimedLongSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new int[0];
            }
            TimedLongSeqHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedOctetSeq.class)) {
            TimedOctetSeq timedData = TimedOctetSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new byte[0];
            }
            TimedOctetSeqHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedShortSeq.class)) {
            TimedShortSeq timedData = TimedShortSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new short[0];
            }
            TimedShortSeqHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedStringSeq.class)) {
            TimedStringSeq timedData = TimedStringSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new String[0];
            }
            TimedStringSeqHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedULongSeq.class)) {
            TimedULongSeq timedData = TimedULongSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new int[0];
            }
            TimedULongSeqHelper.insert(any, timedData);
            return any;
            
        } else if (klass.equals(TimedUShortSeq.class)) {
            TimedUShortSeq timedData = TimedUShortSeq.class.cast(data);
            timedData.tm = fillTime(timedData.tm);
            if(timedData.data==null){
                timedData.data = new short[0];
            }
            TimedUShortSeqHelper.insert(any, timedData);
            return any;
        } else {
            String className = klass.getCanonicalName();
            if (className == null) {
                throw new IllegalStateException("Cannot get class name.");
            }
            try {
                Class targetClass = Class.forName(className, true, klass.getClassLoader()); 
                Object targetObject = targetClass.cast(data);
                //
                Class helper = Class.forName(className + "Helper", true, klass.getClassLoader()); 
                Method method = helper.getMethod("insert", org.omg.CORBA.Any.class, targetClass);
                method.invoke(
                        null, // invoke static method.
                        any, targetObject);
                return any;
            } catch ( Exception ex) {
                throw new ClassCastException("Unknown data type.");
            }
        }
    }

    /**
     * {@.ja 指定されたClassオブジェクトに対応するクラスのデータ型名称を
     * 取得する}
     * {@.en Gets the data type name of the class corresponding 
     *  to the specified Class object}
     * 
     * @param klass
     *   {@.ja データ型名称を取得したいクラスのClassオブジェクト}
     *   {@.en Class object}
     * @return 
     *   {@.ja 指定されたClassオブジェクトに対応するクラスのデータ型名称}
     *   {@.en Data type name of class}
     */
    public static String getDataTypeCodeName(Class klass) {

        return klass.getSimpleName();
    }

    /**
     * {@.ja Genericsの型パラメータで指定されたデータ型の名称を取得する}
     * {@.en Gets the name of the data type specified by the type parameter 
     * of Generics}
     * 
     * @return 
     *   {@.ja Genericsの型パラメータで指定されたデータ型の名称}
     *   {@.en Name of data type specified by type parameter of Generics}
     */
    public String getDataTypeCodeName() {
        
        return getDataTypeCodeName(klass);
    }
    
    private Time fillTime(Time time) {
        
        if (time != null) {
            return time;
        }
        
        long millis = System.currentTimeMillis();
        int sec = (int) (millis / 1000);
        int nsec = (int) ((millis % 1000) * 1000000);
        
        return new Time(sec, nsec);
    }
    
    private String fillString(String str) {
        
        if (str != null) {
            return str;
        }
        
        return new String();
    }
    
    private T replaceNull(T data) {
        
        if (data != null) {
            return data;
        }
        
        if (klass.equals(Byte.class)) {
            return (T) new Byte((byte) 0);
            
        } else if (klass.equals(Double.class)) {
            return (T) new Double(0);
            
        } else if (klass.equals(Float.class)) {
            return (T) new Float(0);
            
        } else if (klass.equals(Integer.class)) {
            return (T) new Integer(0);
            
        } else if (klass.equals(Long.class)) {
            return (T) new Long(0);
            
        } else if (klass.equals(Short.class)) {
            return (T) new Short((short) 0);
            
        } else if (klass.equals(Time.class)) {
            return (T) new Time();
            
        } else if (klass.equals(TimedBoolean.class)) {
            return (T) new TimedBoolean();
            
        } else if (klass.equals(TimedChar.class)) {
            return (T) new TimedChar();
            
        } else if (klass.equals(TimedDouble.class)) {
            return (T) new TimedDouble();
            
        } else if (klass.equals(TimedFloat.class)) {
            return (T) new TimedFloat();
            
        } else if (klass.equals(TimedLong.class)) {
            return (T) new TimedLong();
            
        } else if (klass.equals(TimedOctet.class)) {
            return (T) new TimedOctet();
            
        } else if (klass.equals(TimedShort.class)) {
            return (T) new TimedShort();
            
        } else if (klass.equals(TimedState.class)) {
            return (T) new TimedState();
            
        } else if (klass.equals(TimedString.class)) {
            return (T) new TimedString();
            
        } else if (klass.equals(TimedULong.class)) {
            return (T) new TimedULong();
            
        } else if (klass.equals(TimedUShort.class)) {
            return (T) new TimedUShort();

        } else if (klass.equals(TimedBooleanSeq.class)) {
            return (T) new TimedBooleanSeq(null, new boolean[0]);
            
        } else if (klass.equals(TimedCharSeq.class)) {
            return (T) new TimedCharSeq(null, new char[0]);
            
        } else if (klass.equals(TimedDoubleSeq.class)) {
            return (T) new TimedDoubleSeq(null, new double[0]);
            
        } else if (klass.equals(TimedFloatSeq.class)) {
            return (T) new TimedFloatSeq(null, new float[0]);
            
        } else if (klass.equals(TimedLongSeq.class)) {
            return (T) new TimedLongSeq(null, new int[0]);
            
        } else if (klass.equals(TimedOctetSeq.class)) {
            return (T) new TimedOctetSeq(null, new byte[0]);
            
        } else if (klass.equals(TimedShortSeq.class)) {
            return (T) new TimedShortSeq(null, new short[0]);
            
        } else if (klass.equals(TimedStringSeq.class)) {
            return (T) new TimedStringSeq(null, new String[0]);
            
        } else if (klass.equals(TimedULongSeq.class)) {
            return (T) new TimedULongSeq(null, new int[0]);
            
        } else if (klass.equals(TimedUShortSeq.class)) {
            return (T) new TimedUShortSeq(null, new short[0]);
        }
        
        throw new ClassCastException("Unknown data type.");
    }

    private Class<T> klass;
}
