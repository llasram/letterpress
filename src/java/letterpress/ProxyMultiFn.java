package letterpress;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicReference;

import clojure.lang.IFn;
import clojure.lang.IPersistentMap;
import clojure.lang.IRef;
import clojure.lang.MultiFn;
import clojure.lang.PersistentHashMap;
import clojure.lang.Util;

public class ProxyMultiFn extends MultiFn {
  private final MultiFn mf;
  private final IFn f;

  public ProxyMultiFn(String name, IFn df, Object ddv, IRef h,
                      MultiFn mf, IFn f) {
    super(name, df, ddv, h);
    this.mf = mf;
    this.f = f;
  }

  public static MultiFn wrap(MultiFn mf, IFn f) throws Exception {
    Field namef = MultiFn.class.getDeclaredField("name");
    namef.setAccessible(true);
    String name = (String) namef.get(mf);
    IFn df = mf.dispatchFn;
    Object ddv = mf.defaultDispatchVal;
    IRef h = mf.hierarchy;
    return new ProxyMultiFn(name, df, ddv, h, mf, f);
  }

  public MultiFn addMethod(Object dv, IFn f) {
    mf.addMethod(dv, f);
    return this;
  }

  public MultiFn reset() {
    mf.reset();
    return this;
  }

  public MultiFn removeMethod(Object dv) {
    mf.removeMethod(dv);
    return this;
  }

  public MultiFn preferMethod(Object dv1, Object dv2) {
    mf.preferMethod(dv1, dv2);
    return this;
  }

  public IPersistentMap getMethodTable() {
    if (mf == null) return PersistentHashMap.EMPTY;
    return mf.getMethodTable();
  }

  public IPersistentMap getPreferTable() {
    return mf.getPreferTable();
  }

  public IFn getMethod(Object dv) {
    return mf.getMethod(dv);
  }

  public Object invoke() {
    return f.invoke();
  }

  public Object invoke(Object arg1) {
    return f.invoke(Util.ret1(arg1,arg1=null));
  }

  public Object invoke(Object arg1, Object arg2) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12,
                       Object arg13) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null),
                    Util.ret1(arg13,arg13=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12,
                       Object arg13, Object arg14) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null),
                    Util.ret1(arg13,arg13=null),
                    Util.ret1(arg14,arg14=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12,
                       Object arg13, Object arg14, Object arg15) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null),
                    Util.ret1(arg13,arg13=null),
                    Util.ret1(arg14,arg14=null),
                    Util.ret1(arg15,arg15=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12,
                       Object arg13, Object arg14, Object arg15, Object arg16) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null),
                    Util.ret1(arg13,arg13=null),
                    Util.ret1(arg14,arg14=null),
                    Util.ret1(arg15,arg15=null),
                    Util.ret1(arg16,arg16=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12,
                       Object arg13, Object arg14, Object arg15, Object arg16,
                       Object arg17) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null),
                    Util.ret1(arg13,arg13=null),
                    Util.ret1(arg14,arg14=null),
                    Util.ret1(arg15,arg15=null),
                    Util.ret1(arg16,arg16=null),
                    Util.ret1(arg17,arg17=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12,
                       Object arg13, Object arg14, Object arg15, Object arg16,
                       Object arg17, Object arg18) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null),
                    Util.ret1(arg13,arg13=null),
                    Util.ret1(arg14,arg14=null),
                    Util.ret1(arg15,arg15=null),
                    Util.ret1(arg16,arg16=null),
                    Util.ret1(arg17,arg17=null),
                    Util.ret1(arg18,arg18=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12,
                       Object arg13, Object arg14, Object arg15, Object arg16,
                       Object arg17, Object arg18, Object arg19) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null),
                    Util.ret1(arg13,arg13=null),
                    Util.ret1(arg14,arg14=null),
                    Util.ret1(arg15,arg15=null),
                    Util.ret1(arg16,arg16=null),
                    Util.ret1(arg17,arg17=null),
                    Util.ret1(arg18,arg18=null),
                    Util.ret1(arg19,arg19=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12,
                       Object arg13, Object arg14, Object arg15, Object arg16,
                       Object arg17, Object arg18, Object arg19, Object arg20) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null),
                    Util.ret1(arg13,arg13=null),
                    Util.ret1(arg14,arg14=null),
                    Util.ret1(arg15,arg15=null),
                    Util.ret1(arg16,arg16=null),
                    Util.ret1(arg17,arg17=null),
                    Util.ret1(arg18,arg18=null),
                    Util.ret1(arg19,arg19=null),
                    Util.ret1(arg20,arg20=null));
  }

  public Object invoke(Object arg1, Object arg2, Object arg3, Object arg4,
                       Object arg5, Object arg6, Object arg7, Object arg8,
                       Object arg9, Object arg10, Object arg11, Object arg12,
                       Object arg13, Object arg14, Object arg15, Object arg16,
                       Object arg17, Object arg18, Object arg19, Object arg20,
                       Object... args) {
    return f.invoke(Util.ret1(arg1,arg1=null),
                    Util.ret1(arg2,arg2=null),
                    Util.ret1(arg3,arg3=null),
                    Util.ret1(arg4,arg4=null),
                    Util.ret1(arg5,arg5=null),
                    Util.ret1(arg6,arg6=null),
                    Util.ret1(arg7,arg7=null),
                    Util.ret1(arg8,arg8=null),
                    Util.ret1(arg9,arg9=null),
                    Util.ret1(arg10,arg10=null),
                    Util.ret1(arg11,arg11=null),
                    Util.ret1(arg12,arg12=null),
                    Util.ret1(arg13,arg13=null),
                    Util.ret1(arg14,arg14=null),
                    Util.ret1(arg15,arg15=null),
                    Util.ret1(arg16,arg16=null),
                    Util.ret1(arg17,arg17=null),
                    Util.ret1(arg18,arg18=null),
                    Util.ret1(arg19,arg19=null),
                    Util.ret1(arg20,arg20=null),
                    args);
  }
}
