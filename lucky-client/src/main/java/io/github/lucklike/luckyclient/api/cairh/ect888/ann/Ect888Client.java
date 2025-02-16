package io.github.lucklike.luckyclient.api.cairh.ect888.ann;

import com.luckyframework.httpclient.core.meta.Request;
import com.luckyframework.httpclient.proxy.context.MethodContext;
import com.luckyframework.httpclient.proxy.context.ParameterContext;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import com.luckyframework.httpclient.proxy.spel.hook.Lifecycle;
import com.luckyframework.httpclient.proxy.spel.hook.callback.Callback;
import com.luckyframework.reflect.AnnotationUtils;
import com.luckyframework.reflect.ClassUtils;
import com.luckyframework.reflect.FieldUtils;
import io.github.lucklike.httpclient.annotation.HttpClient;
import io.github.lucklike.luckyclient.api.cairh.BizException;
import io.github.lucklike.luckyclient.api.cairh.ect888.config.Ect888Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpELImport(Ect888Client.Ect888CallbackAndFunction.class)
@HttpClient("#{@ect888Config.url}")
public @interface Ect888Client {

    @SuppressWarnings("all")
    class Ect888CallbackAndFunction {


        @Callback(lifecycle = Lifecycle.REQUEST_INIT)
        public static void createRequestParam(MethodContext mc, Request request, Ect888Config config) throws UnsupportedEncodingException {
            String ptyKey = config.getPtyKey();
            AES aes = new AES(ptyKey);


            Map<String, Object> signMap = new LinkedHashMap<>();
            Map<String, Object> formMap = new LinkedHashMap<>();
            for (ParameterContext pc : mc.getParameterContexts()) {
                Object paramValue = pc.getValue();
                if (paramValue == null) {
                    throw new BizException("");
                }

                for (Field field : ClassUtils.getAllFields(paramValue.getClass())) {
                    String name = field.getName();
                    Object value = FieldUtils.getValue(paramValue, field);

                    // 处理ID
                    boolean isEctId = AnnotationUtils.isAnnotated(field, EctId.class);
                    if (isEctId) {
                        value = aes.encrypt(String.valueOf(value), "utf-8");
                    }

                    // 处理时间字段
                    if (AnnotationUtils.isAnnotated(field, EctTime.class)) {
                        value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    }

                    // 处理机构号字段
                    if (AnnotationUtils.isAnnotated(field, EctCd.class)) {
                        value = config.getPtyCd();
                    }

                    // 处理机构帐号字段
                    if (AnnotationUtils.isAnnotated(field, EctAcct.class)) {
                        value = config.getPtyAcct();
                    }

                    formMap.put(name, value);
                    if (AnnotationUtils.isAnnotated(field, EctSign.class)) {
                        signMap.put(name, value);
                        if (isEctId) {
                            value = Base64.encodeBytes((URLEncoder.encode(String.valueOf(value), "utf-8")).getBytes(), Base64.DONT_BREAK_LINES);
                            formMap.put(name, value);
                        }
                    }
                }

            }

            //加签
            String sign = SignatureUtil.signature(signMap, ptyKey);
            formMap.put("sign", sign);

            request.setFormParameter(formMap);
        }

    }


    /**
     * AES 加解密工具类
     */
    class AES {

        private String key = "B49A86FA425D439dB510A234A3E25A3E";
        private final BASE64Decoder decoder = new BASE64Decoder();
        private final BASE64Encoder encoder = new BASE64Encoder();
        private static final String AES = "AES";
        private static final String ALGORITHM = "SHA1PRNG";

        public AES() {
        }

        public AES(String key) {
            this.key = key;
        }

        public static byte[] encrypt(byte[] byteContent, byte[] password) throws Exception {
            KeyGenerator kgen = KeyGenerator.getInstance(AES);
            SecureRandom secureRandom = SecureRandom.getInstance(ALGORITHM);
            secureRandom.setSeed(password);
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(1, key);
            return cipher.doFinal(byteContent);
        }

        public static byte[] decrypt(byte[] content, byte[] password) throws Exception {
            KeyGenerator kgen = KeyGenerator.getInstance(AES);
            SecureRandom secureRandom = SecureRandom.getInstance(ALGORITHM);
            secureRandom.setSeed(password);
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(2, key);
            return cipher.doFinal(content);
        }

        /**
         * @deprecated
         */
        public final String decrypt(String data) {
            try {
                return new String(decrypt(this.hex2byte(data), this.key.getBytes()));
            } catch (Exception var3) {
                return null;
            }
        }

        public final String decrypt(String data, String charsetName) {
            try {
                return new String(decrypt(this.hex2byte(data), this.key.getBytes()), charsetName);
            } catch (Exception var4) {
                return null;
            }
        }

        /**
         * @deprecated
         */
        public final String encrypt(String data) {
            try {
                return this.byte2hex(encrypt(data.getBytes(), this.key.getBytes()));
            } catch (Exception var3) {
                return null;
            }
        }

        public final String encrypt(String data, String charsetName) {
            try {
                return this.byte2hex(encrypt(data.getBytes(charsetName), this.key.getBytes()));
            } catch (Exception var4) {
                return null;
            }
        }

        private String byte2hex(byte[] b) {
            return this.encoder.encode(b);
        }

        private byte[] hex2byte(String hex) throws IOException {
            return this.decoder.decodeBuffer(hex);
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

    /**
     * SHA512 加解密工具类
     */
    class SHA512 {
        private static Logger logger = LoggerFactory.getLogger(SHA512.class);

        private final static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        private static String getSHA512(byte[] source) {
            String s = null;
            try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-512");
                md.update(source);
                byte tmp[] = md.digest(); // SHA-512 的计算结果是一个 64 位的长整数，
                // 用字节表示就是 16 个字节
                char str[] = new char[64 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
                // 所以表示成 16 进制需要 32 个字符
                int k = 0; // 表示转换结果中对应的字符位置
                for (int i = 0; i < 64; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                    // 转换成 16 进制字符的转换
                    byte byte0 = tmp[i]; // 取第 i 个字节
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                    // >>> 为逻辑右移，将符号位一起右移
                    str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
                }
                s = new String(str); // 换后的结果转换为字符串

            } catch (Exception e) {
                logger.error("获取sha出错", e);
            }
            return s;
        }

        /**
         * getMD5ofStr是类MD5最主要的公共方法，入口参数是你想要进行MD5变换的字符串
         * 返回的是变换完的结果，这个结果是从公共成员digestHexStr取得的．
         */
        public static String getSHA512ofStr(String inbuf) {
            String s = "";
            try {
                s = getSHA512(inbuf.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                logger.error("上传数据转换异常" + e);
            }
            return s;
        }

        public static void main(String[] args) {
            String toEncryptStr = "123456789";
            System.out.println(SHA512.getSHA512ofStr(toEncryptStr));
        }
    }

    /**
     * Base64编解码
     */
    class Base64 {
        public static final int NO_OPTIONS = 0;
        public static final int ENCODE = 1;
        public static final int DECODE = 0;
        public static final int GZIP = 2;
        public static final int DONT_BREAK_LINES = 8;
        public static final int URL_SAFE = 16;
        public static final int ORDERED = 32;
        private static final int MAX_LINE_LENGTH = 76;
        private static final byte EQUALS_SIGN = 61;
        private static final byte NEW_LINE = 10;
        private static final String PREFERRED_ENCODING = "UTF-8";
        private static final byte WHITE_SPACE_ENC = -5;
        private static final byte EQUALS_SIGN_ENC = -1;
        private static final byte[] _STANDARD_ALPHABET = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] _STANDARD_DECODABET = new byte[]{-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9};
        private static final byte[] _URL_SAFE_ALPHABET = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        private static final byte[] _URL_SAFE_DECODABET = new byte[]{-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9};
        private static final byte[] _ORDERED_ALPHABET = new byte[]{45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
        private static final byte[] _ORDERED_DECODABET = new byte[]{-9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9};

        private static final byte[] getAlphabet(int options) {
            if ((options & 16) == 16) {
                return _URL_SAFE_ALPHABET;
            } else {
                return (options & 32) == 32 ? _ORDERED_ALPHABET : _STANDARD_ALPHABET;
            }
        }

        private static final byte[] getDecodabet(int options) {
            if ((options & 16) == 16) {
                return _URL_SAFE_DECODABET;
            } else {
                return (options & 32) == 32 ? _ORDERED_DECODABET : _STANDARD_DECODABET;
            }
        }

        private Base64() {
        }

        public static final void main(String[] args) {
            if (args.length < 3) {
                usage("Not enough arguments.");
            } else {
                String flag = args[0];
                String infile = args[1];
                String outfile = args[2];
                if (flag.equals("-e")) {
                    encodeFileToFile(infile, outfile);
                } else if (flag.equals("-d")) {
                    decodeFileToFile(infile, outfile);
                } else {
                    usage("Unknown flag: " + flag);
                }
            }

        }

        private static final void usage(String msg) {
            System.err.println(msg);
            System.err.println("Usage: java Base64 -e|-d inputfile outputfile");
        }

        private static byte[] encode3to4(byte[] b4, byte[] threeBytes, int numSigBytes, int options) {
            encode3to4(threeBytes, 0, numSigBytes, b4, 0, options);
            return b4;
        }

        private static byte[] encode3to4(byte[] source, int srcOffset, int numSigBytes, byte[] destination, int destOffset, int options) {
            byte[] ALPHABET = getAlphabet(options);
            int inBuff = (numSigBytes > 0 ? source[srcOffset] << 24 >>> 8 : 0) | (numSigBytes > 1 ? source[srcOffset + 1] << 24 >>> 16 : 0) | (numSigBytes > 2 ? source[srcOffset + 2] << 24 >>> 24 : 0);
            switch (numSigBytes) {
                case 1:
                    destination[destOffset] = ALPHABET[inBuff >>> 18];
                    destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 63];
                    destination[destOffset + 2] = 61;
                    destination[destOffset + 3] = 61;
                    return destination;
                case 2:
                    destination[destOffset] = ALPHABET[inBuff >>> 18];
                    destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 63];
                    destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 63];
                    destination[destOffset + 3] = 61;
                    return destination;
                case 3:
                    destination[destOffset] = ALPHABET[inBuff >>> 18];
                    destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 63];
                    destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 63];
                    destination[destOffset + 3] = ALPHABET[inBuff & 63];
                    return destination;
                default:
                    return destination;
            }
        }

        public static String encodeObject(Serializable serializableObject) {
            return encodeObject(serializableObject, 0);
        }

        public static String encodeObject(Serializable serializableObject, int options) {
            ByteArrayOutputStream baos = null;
            java.io.OutputStream b64os = null;
            ObjectOutputStream oos = null;
            GZIPOutputStream gzos = null;
            int gzip = options & 2;
            int dontBreakLines = options & 8;

            label178:
            {
                Object var9;
                try {
                    baos = new ByteArrayOutputStream();
                    b64os = new OutputStream(baos, 1 | options);
                    if (gzip == 2) {
                        gzos = new GZIPOutputStream(b64os);
                        oos = new ObjectOutputStream(gzos);
                    } else {
                        oos = new ObjectOutputStream(b64os);
                    }

                    oos.writeObject(serializableObject);
                    break label178;
                } catch (IOException e) {
                    e.printStackTrace();
                    var9 = null;
                } finally {
                    if (oos != null) {
                        try {
                            oos.close();
                        } catch (Exception var31) {
                        }
                    }

                    if (gzos != null) {
                        try {
                            gzos.close();
                        } catch (Exception var30) {
                        }
                    }

                    if (b64os != null) {
                        try {
                            b64os.close();
                        } catch (Exception var29) {
                        }
                    }

                    if (baos != null) {
                        try {
                            baos.close();
                        } catch (Exception var28) {
                        }
                    }

                }

                return (String) var9;
            }

            try {
                return new String(baos.toByteArray(), "UTF-8");
            } catch (UnsupportedEncodingException var32) {
                return new String(baos.toByteArray());
            }
        }

        public static String encodeBytes(byte[] source) {
            return encodeBytes(source, 0, source.length, 0);
        }

        public static String encodeBytes(byte[] source, int options) {
            return encodeBytes(source, 0, source.length, options);
        }

        public static String encodeBytes(byte[] source, int off, int len) {
            return encodeBytes(source, off, len, 0);
        }

        public static String encodeBytes(byte[] source, int off, int len, int options) {
            int dontBreakLines = options & 8;
            int gzip = options & 2;
            if (gzip == 2) {
                ByteArrayOutputStream baos = null;
                GZIPOutputStream gzos = null;
                OutputStream b64os = null;

                label216:
                {
                    Object var37;
                    try {
                        baos = new ByteArrayOutputStream();
                        b64os = new OutputStream(baos, 1 | options);
                        gzos = new GZIPOutputStream(b64os);
                        gzos.write(source, off, len);
                        gzos.close();
                        break label216;
                    } catch (IOException e) {
                        e.printStackTrace();
                        var37 = null;
                    } finally {
                        if (gzos != null) {
                            try {
                                gzos.close();
                            } catch (Exception var29) {
                            }
                        }

                        if (b64os != null) {
                            try {
                                b64os.close();
                            } catch (Exception var28) {
                            }
                        }

                        if (baos != null) {
                            try {
                                baos.close();
                            } catch (Exception var27) {
                            }
                        }

                    }

                    return (String) var37;
                }

                try {
                    return new String(baos.toByteArray(), "UTF-8");
                } catch (UnsupportedEncodingException var30) {
                    return new String(baos.toByteArray());
                }
            } else {
                boolean breakLines = dontBreakLines == 0;
                int len43 = len * 4 / 3;
                byte[] outBuff = new byte[len43 + (len % 3 > 0 ? 4 : 0) + (breakLines ? len43 / 76 : 0)];
                int d = 0;
                int e = 0;
                int len2 = len - 2;

                for (int lineLength = 0; d < len2; e += 4) {
                    encode3to4(source, d + off, 3, outBuff, e, options);
                    lineLength += 4;
                    if (breakLines && lineLength == 76) {
                        outBuff[e + 4] = 10;
                        ++e;
                        lineLength = 0;
                    }

                    d += 3;
                }

                if (d < len) {
                    encode3to4(source, d + off, len - d, outBuff, e, options);
                    e += 4;
                }

                try {
                    return new String(outBuff, 0, e, "UTF-8");
                } catch (UnsupportedEncodingException var31) {
                    return new String(outBuff, 0, e);
                }
            }
        }

        private static int decode4to3(byte[] source, int srcOffset, byte[] destination, int destOffset, int options) {
            byte[] DECODABET = getDecodabet(options);
            if (source[srcOffset + 2] == 61) {
                int outBuff = (DECODABET[source[srcOffset]] & 255) << 18 | (DECODABET[source[srcOffset + 1]] & 255) << 12;
                destination[destOffset] = (byte) (outBuff >>> 16);
                return 1;
            } else if (source[srcOffset + 3] == 61) {
                int outBuff = (DECODABET[source[srcOffset]] & 255) << 18 | (DECODABET[source[srcOffset + 1]] & 255) << 12 | (DECODABET[source[srcOffset + 2]] & 255) << 6;
                destination[destOffset] = (byte) (outBuff >>> 16);
                destination[destOffset + 1] = (byte) (outBuff >>> 8);
                return 2;
            } else {
                try {
                    int outBuff = (DECODABET[source[srcOffset]] & 255) << 18 | (DECODABET[source[srcOffset + 1]] & 255) << 12 | (DECODABET[source[srcOffset + 2]] & 255) << 6 | DECODABET[source[srcOffset + 3]] & 255;
                    destination[destOffset] = (byte) (outBuff >> 16);
                    destination[destOffset + 1] = (byte) (outBuff >> 8);
                    destination[destOffset + 2] = (byte) outBuff;
                    return 3;
                } catch (Exception var7) {
                    System.out.println("" + source[srcOffset] + ": " + DECODABET[source[srcOffset]]);
                    System.out.println("" + source[srcOffset + 1] + ": " + DECODABET[source[srcOffset + 1]]);
                    System.out.println("" + source[srcOffset + 2] + ": " + DECODABET[source[srcOffset + 2]]);
                    System.out.println("" + source[srcOffset + 3] + ": " + DECODABET[source[srcOffset + 3]]);
                    return -1;
                }
            }
        }

        public static byte[] decode(byte[] source, int off, int len, int options) {
            byte[] DECODABET = getDecodabet(options);
            int len34 = len * 3 / 4;
            byte[] outBuff = new byte[len34];
            int outBuffPosn = 0;
            byte[] b4 = new byte[4];
            int b4Posn = 0;
            int i = 0;
            byte sbiCrop = 0;
            byte sbiDecode = 0;

            for (int var14 = off; var14 < off + len; ++var14) {
                sbiCrop = (byte) (source[var14] & 127);
                sbiDecode = DECODABET[sbiCrop];
                if (sbiDecode < -5) {
                    System.err.println("Bad Base64 input character at " + var14 + ": " + source[var14] + "(decimal)");
                    return null;
                }

                if (sbiDecode >= -1) {
                    b4[b4Posn++] = sbiCrop;
                    if (b4Posn > 3) {
                        outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn, options);
                        b4Posn = 0;
                        if (sbiCrop == 61) {
                            break;
                        }
                    }
                }
            }

            byte[] out = new byte[outBuffPosn];
            System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
            return out;
        }

        public static byte[] decode(String s) {
            return decode(s, 0);
        }

        public static byte[] decode(String s, int options) {
            byte[] bytes;
            try {
                bytes = s.getBytes("UTF-8");
            } catch (UnsupportedEncodingException var27) {
                bytes = s.getBytes();
            }

            bytes = decode(bytes, 0, bytes.length, options);
            if (bytes != null && bytes.length >= 4) {
                int head = bytes[0] & 255 | bytes[1] << 8 & '\uff00';
                if (35615 == head) {
                    ByteArrayInputStream bais = null;
                    GZIPInputStream gzis = null;
                    ByteArrayOutputStream baos = null;
                    byte[] buffer = new byte[2048];
                    int length = 0;

                    try {
                        baos = new ByteArrayOutputStream();
                        bais = new ByteArrayInputStream(bytes);
                        gzis = new GZIPInputStream(bais);

                        while ((length = gzis.read(buffer)) >= 0) {
                            baos.write(buffer, 0, length);
                        }

                        bytes = baos.toByteArray();
                    } catch (IOException var28) {
                    } finally {
                        if (baos != null) {
                            try {
                                baos.close();
                            } catch (Exception var26) {
                            }
                        }

                        if (gzis != null) {
                            try {
                                gzis.close();
                            } catch (Exception var25) {
                            }
                        }

                        if (bais != null) {
                            try {
                                bais.close();
                            } catch (Exception var24) {
                            }
                        }

                    }
                }
            }

            return bytes;
        }

        public static Object decodeToObject(String encodedObject) {
            byte[] objBytes = decode(encodedObject);
            ByteArrayInputStream bais = null;
            ObjectInputStream ois = null;
            Object obj = null;

            try {
                bais = new ByteArrayInputStream(objBytes);
                ois = new ObjectInputStream(bais);
                obj = ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
                obj = null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                obj = null;
            } finally {
                try {
                    bais.close();
                } catch (Exception var20) {
                }

                try {
                    ois.close();
                } catch (Exception var19) {
                }

            }

            return obj;
        }

        public static boolean encodeToFile(byte[] dataToEncode, String filename) {
            boolean success = false;
            OutputStream bos = null;

            try {
                bos = new OutputStream(new FileOutputStream(filename), 1);
                bos.write(dataToEncode);
                success = true;
            } catch (IOException var13) {
                success = false;
            } finally {
                try {
                    bos.close();
                } catch (Exception var12) {
                }

            }

            return success;
        }

        public static boolean decodeToFile(String dataToDecode, String filename) {
            boolean success = false;
            OutputStream bos = null;

            try {
                bos = new OutputStream(new FileOutputStream(filename), 0);
                bos.write(dataToDecode.getBytes("UTF-8"));
                success = true;
            } catch (IOException var13) {
                success = false;
            } finally {
                try {
                    bos.close();
                } catch (Exception var12) {
                }

            }

            return success;
        }

        public static byte[] decodeFromFile(String filename) {
            byte[] decodedData = null;
            InputStream bis = null;

            Object var7;
            try {
                File file = new File(filename);
                byte[] buffer = null;
                int length = 0;
                int numBytes = 0;
                if (file.length() <= 2147483647L) {
                    buffer = new byte[(int) file.length()];

                    for (bis = new InputStream(new BufferedInputStream(new FileInputStream(file)), 0); (numBytes = bis.read(buffer, length, 4096)) >= 0; length += numBytes) {
                    }

                    decodedData = new byte[length];
                    System.arraycopy(buffer, 0, decodedData, 0, length);
                    return decodedData;
                }

                System.err.println("File is too big for this convenience method (" + file.length() + " bytes).");
                var7 = null;
            } catch (IOException var18) {
                System.err.println("Error decoding from file " + filename);
                return decodedData;
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception var17) {
                    }
                }

            }

            return (byte[]) var7;
        }

        public static String encodeFromFile(String filename) {
            String encodedData = null;
            InputStream bis = null;

            try {
                File file = new File(filename);
                byte[] buffer = new byte[Math.max((int) ((double) file.length() * 1.4), 40)];
                int length = 0;
                int numBytes = 0;

                for (bis = new InputStream(new BufferedInputStream(new FileInputStream(file)), 1); (numBytes = bis.read(buffer, length, 4096)) >= 0; length += numBytes) {
                }

                encodedData = new String(buffer, 0, length, "UTF-8");
            } catch (IOException var15) {
                System.err.println("Error encoding from file " + filename);
            } finally {
                try {
                    bis.close();
                } catch (Exception var14) {
                }

            }

            return encodedData;
        }

        public static boolean encodeFileToFile(String infile, String outfile) {
            boolean success = false;
            java.io.InputStream in = null;
            java.io.OutputStream out = null;

            try {
                in = new InputStream(new BufferedInputStream(new FileInputStream(infile)), 1);
                out = new BufferedOutputStream(new FileOutputStream(outfile));
                byte[] buffer = new byte[65536];
                int read = -1;

                while ((read = in.read(buffer)) >= 0) {
                    out.write(buffer, 0, read);
                }

                success = true;
            } catch (IOException exc) {
                exc.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (Exception var18) {
                }

                try {
                    out.close();
                } catch (Exception var17) {
                }

            }

            return success;
        }

        public static boolean decodeFileToFile(String infile, String outfile) {
            boolean success = false;
            java.io.InputStream in = null;
            java.io.OutputStream out = null;

            try {
                in = new InputStream(new BufferedInputStream(new FileInputStream(infile)), 0);
                out = new BufferedOutputStream(new FileOutputStream(outfile));
                byte[] buffer = new byte[65536];
                int read = -1;

                while ((read = in.read(buffer)) >= 0) {
                    out.write(buffer, 0, read);
                }

                success = true;
            } catch (IOException exc) {
                exc.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (Exception var18) {
                }

                try {
                    out.close();
                } catch (Exception var17) {
                }

            }

            return success;
        }

        public static class InputStream extends FilterInputStream {
            private boolean encode;
            private int position;
            private byte[] buffer;
            private int bufferLength;
            private int numSigBytes;
            private int lineLength;
            private boolean breakLines;
            private int options;
            private byte[] alphabet;
            private byte[] decodabet;

            public InputStream(java.io.InputStream in) {
                this(in, 0);
            }

            public InputStream(java.io.InputStream in, int options) {
                super(in);
                this.breakLines = (options & 8) != 8;
                this.encode = (options & 1) == 1;
                this.bufferLength = this.encode ? 4 : 3;
                this.buffer = new byte[this.bufferLength];
                this.position = -1;
                this.lineLength = 0;
                this.options = options;
                this.alphabet = Base64.getAlphabet(options);
                this.decodabet = Base64.getDecodabet(options);
            }

            public int read() throws IOException {
                if (this.position < 0) {
                    if (!this.encode) {
                        byte[] b4 = new byte[4];
                        int i = 0;

                        for (i = 0; i < 4; ++i) {
                            int b = 0;

                            do {
                                b = this.in.read();
                            } while (b >= 0 && this.decodabet[b & 127] <= -5);

                            if (b < 0) {
                                break;
                            }

                            b4[i] = (byte) b;
                        }

                        if (i != 4) {
                            if (i == 0) {
                                return -1;
                            }

                            throw new IOException("Improperly padded Base64 input.");
                        }

                        this.numSigBytes = Base64.decode4to3(b4, 0, this.buffer, 0, this.options);
                        this.position = 0;
                    } else {
                        byte[] b3 = new byte[3];
                        int numBinaryBytes = 0;

                        for (int i = 0; i < 3; ++i) {
                            try {
                                int b = this.in.read();
                                if (b >= 0) {
                                    b3[i] = (byte) b;
                                    ++numBinaryBytes;
                                }
                            } catch (IOException e) {
                                if (i == 0) {
                                    throw e;
                                }
                            }
                        }

                        if (numBinaryBytes <= 0) {
                            return -1;
                        }

                        Base64.encode3to4(b3, 0, numBinaryBytes, this.buffer, 0, this.options);
                        this.position = 0;
                        this.numSigBytes = 4;
                    }
                }

                if (this.position >= 0) {
                    if (this.position >= this.numSigBytes) {
                        return -1;
                    } else if (this.encode && this.breakLines && this.lineLength >= 76) {
                        this.lineLength = 0;
                        return 10;
                    } else {
                        ++this.lineLength;
                        int b = this.buffer[this.position++];
                        if (this.position >= this.bufferLength) {
                            this.position = -1;
                        }

                        return b & 255;
                    }
                } else {
                    throw new IOException("Error in Base64 code reading stream.");
                }
            }

            public int read(byte[] dest, int off, int len) throws IOException {
                int i;
                for (i = 0; i < len; ++i) {
                    int b = this.read();
                    if (b < 0) {
                        if (i == 0) {
                            return -1;
                        }
                        break;
                    }

                    dest[off + i] = (byte) b;
                }

                return i;
            }
        }

        public static class OutputStream extends FilterOutputStream {
            private boolean encode;
            private int position;
            private byte[] buffer;
            private int bufferLength;
            private int lineLength;
            private boolean breakLines;
            private byte[] b4;
            private boolean suspendEncoding;
            private int options;
            private byte[] alphabet;
            private byte[] decodabet;

            public OutputStream(java.io.OutputStream out) {
                this(out, 1);
            }

            public OutputStream(java.io.OutputStream out, int options) {
                super(out);
                this.breakLines = (options & 8) != 8;
                this.encode = (options & 1) == 1;
                this.bufferLength = this.encode ? 3 : 4;
                this.buffer = new byte[this.bufferLength];
                this.position = 0;
                this.lineLength = 0;
                this.suspendEncoding = false;
                this.b4 = new byte[4];
                this.options = options;
                this.alphabet = Base64.getAlphabet(options);
                this.decodabet = Base64.getDecodabet(options);
            }

            public void write(int theByte) throws IOException {
                if (this.suspendEncoding) {
                    super.out.write(theByte);
                } else {
                    if (this.encode) {
                        this.buffer[this.position++] = (byte) theByte;
                        if (this.position >= this.bufferLength) {
                            this.out.write(Base64.encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                            this.lineLength += 4;
                            if (this.breakLines && this.lineLength >= 76) {
                                this.out.write(10);
                                this.lineLength = 0;
                            }

                            this.position = 0;
                        }
                    } else if (this.decodabet[theByte & 127] > -5) {
                        this.buffer[this.position++] = (byte) theByte;
                        if (this.position >= this.bufferLength) {
                            int len = Base64.decode4to3(this.buffer, 0, this.b4, 0, this.options);
                            this.out.write(this.b4, 0, len);
                            this.position = 0;
                        }
                    } else if (this.decodabet[theByte & 127] != -5) {
                        throw new IOException("Invalid character in Base64 data.");
                    }

                }
            }

            public void write(byte[] theBytes, int off, int len) throws IOException {
                if (this.suspendEncoding) {
                    super.out.write(theBytes, off, len);
                } else {
                    for (int i = 0; i < len; ++i) {
                        this.write(theBytes[off + i]);
                    }

                }
            }

            public void flushBase64() throws IOException {
                if (this.position > 0) {
                    if (!this.encode) {
                        throw new IOException("Base64 input not properly padded.");
                    }

                    this.out.write(Base64.encode3to4(this.b4, this.buffer, this.position, this.options));
                    this.position = 0;
                }

            }

            public void close() throws IOException {
                this.flushBase64();
                super.close();
                this.buffer = null;
                this.out = null;
            }

            public void suspendEncoding() throws IOException {
                this.flushBase64();
                this.suspendEncoding = true;
            }

            public void resumeEncoding() {
                this.suspendEncoding = false;
            }
        }
    }

    /**
     * 签名工具类
     */
    class SignatureUtil {

        /**
         * SHA-512摘要
         *
         * @param secret 会话密钥
         */
        public static String signature(Map<String, Object> params, String secret) {
            String result = null;
            StringBuffer orgin = getSignParam(params);
            if (orgin == null)
                return result;
            orgin.append(secret);
            try {
                System.out.println("orgin=" + orgin);
                result = SHA512.getSHA512ofStr(orgin.toString());
            } catch (Exception e) {
                throw new RuntimeException("sign error !");
            }
            return result;
        }

        /**
         * 添加参数的封装方法
         */
        private static StringBuffer getSignParam(Map<String, Object> params) {
            StringBuffer sb = new StringBuffer();
            if (params == null)
                return null;

            Set<String> keySet = params.keySet();
            keySet.stream().sorted(String::compareTo).forEach(key -> {
                sb.append(key).append(params.get(key));
            });
//
//            Map<String, Object> treeMap = new TreeMap<String, Object>();
//            treeMap.putAll(params);
//            Iterator<String> iter = treeMap.keySet().iterator();
//            while (iter.hasNext())
//            {
//                String name = (String) iter.next();
//                sb.append(name).append(params.get(name));
//            }
            return sb;
        }
    }
}
