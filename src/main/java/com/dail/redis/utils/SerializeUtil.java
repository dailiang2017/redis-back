package com.dail.redis.utils;

import java.io.*;

/**
 * @Auther: dailiang
 * @Date: 2018/12/26 16:44
 * @Description:
 */
public class SerializeUtil {

    public static byte[] serialize2(Object object) {
        if(object == null) return null;
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
//            log.error("SerializeUtil.serialize error",e);
        }finally {
            try {
                if(oos != null) {
                    oos.close();
                }
                if(baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
//                log.error("SerializeUtil.serialize error", e);
            }
        }
        return null;
    }

    /**
     * 序列化
     * @param obj
     * @return
     */
    public static String serialize(Object obj) {
        if (obj == null) return null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            String str = baos.toString("ISO-8859-1");
            return str;
        } catch (Exception e) {
//            log.error(e.getMessage());
        } finally {
            try {
                if(oos != null) {
                    oos.close();
                }
                if(baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
//                log.error("SerializeUtil.serialize error", e);
            }
        }
        return null;
    }

    public static Object unserialize(String str) {
        if (StringUtil.isBlankOrEmpty(str)) return null;
        ByteArrayInputStream bais = null;
        try {
            //反序列化
            bais = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
//            log.error("SerializeUtil.unserialize error",e);
        }finally {
            try {
                if(bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
//                log.error("SerializeUtil.serialize error",e);
            }
        }
        return null;
    }
}
