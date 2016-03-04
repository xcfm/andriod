package fmz.example.helper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zzk on 15/11/27.
 */
public class GenericHelper {

    public static <T> Class<T> getViewClass(Class<?> klass){
        Type type = klass.getGenericSuperclass();//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
        if(type == null || !(type instanceof ParameterizedType)) return null;
        ParameterizedType parameterizedType = (ParameterizedType) type;//获得超类的泛型参数的实际类型
        Type[] types = parameterizedType.getActualTypeArguments();
        if(types == null || types.length == 0) return null;
        return (Class<T>) types[0];
    }
}
