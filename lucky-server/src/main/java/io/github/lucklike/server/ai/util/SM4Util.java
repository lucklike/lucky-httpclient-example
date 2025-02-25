package io.github.lucklike.server.ai.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

public class SM4Util {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODING = "UTF-8";
    public static final String ALGORITHM_NAME = "SM4";
    // 加密算法/分组加密模式/分组填充方式
    // PKCS5Padding-以8个字节为一组进行分组加密
    // 定义分组加密模式使用：PKCS5Padding
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    // 128-32位16进制；256-64位16进制
    public static final int DEFAULT_KEY_SIZE = 128;

    private static final String key = "7e2b04ac0f1c0c2933cb1f8d4a11cf35";

    private static final String SM4_STR_PREFIX = "sm4:";

    /**
     * 生成ECB暗号
     *
     * @param algorithmName 算法名称
     * @param mode          模式
     * @param key
     * @return
     * @throws Exception
     * @explain ECB模式（电子密码本模式：Electronic codebook）
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    /**
     * 自动生成密钥
     *
     * @return
     * @throws Exception
     */
    public static String generateKey() throws Exception {
        return new String(Hex.encode(generateKey(DEFAULT_KEY_SIZE)));
    }

    /**
     * @param keySize
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] generateKey(int keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    /**
     * sm4加密
     *
     * @param hexKey   16进制密钥（忽略大小写）
     * @param paramStr 待加密字符串
     * @return 返回16进制的加密字符串
     * @throws Exception
     * @explain 加密模式：ECB
     * 密文长度不固定，会随着被加密字符串长度的变化而变化
     */
    public static String encryptEcb(String hexKey, String paramStr) throws Exception {
        String cipherText = "";
        // 16进制字符串-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // String-->byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 加密后的数组
        byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
        // byte[]-->hexString
        cipherText = ByteUtils.toHexString(cipherArray);
        return cipherText;
    }

    /**
     * 加密模式之Ecb
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     * @explain
     */
    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * sm4解密
     *
     * @param hexKey     16进制密钥
     * @param cipherText 16进制的加密字符串（忽略大小写）
     * @return 解密后的字符串
     * @throws Exception
     * @explain 解密模式：采用ECB
     */
    public static String decryptEcb(String hexKey, String cipherText) throws Exception {
        // 用于接收解密后的字符串
        String decryptStr = "";
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // hexString-->byte[]
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
        // byte[]-->String
        decryptStr = new String(srcData, ENCODING);
        return decryptStr;
    }

    /**
     * 解密
     *
     * @param key
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    /**
     * 校验加密前后的字符串是否为同一数据
     *
     * @param hexKey     16进制密钥（忽略大小写）
     * @param cipherText 16进制加密后的字符串
     * @param paramStr   加密前的字符串
     * @return 是否为同一数据
     * @throws Exception
     */
    public static boolean verifyEcb(String hexKey, String cipherText, String paramStr) throws Exception {
        // 用于接收校验结果
        boolean flag = false;
        // hexString-->byte[]
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        // 将16进制字符串转换成数组
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        // 解密
        byte[] decryptData = decrypt_Ecb_Padding(keyData, cipherData);
        // 将原字符串转换成byte[]
        byte[] srcData = paramStr.getBytes(ENCODING);
        // 判断2个数组是否一致
        flag = Arrays.equals(decryptData, srcData);
        return flag;
    }

    /**
     * sm4加密
     *
     * @param data 数据
     * @return 加密后的数据
     * @throws Exception 加密失败抛出的异常
     */
    public static String encryptEcb(String data) throws Exception {
        return encryptEcb(key, data);
    }

    /**
     * sm4解密
     *
     * @param cipher 秘文
     * @return 明文
     * @throws Exception 解密失败抛出的异常
     */
    public static String decryptEcb(String cipher) throws Exception {
        return decryptEcb(key, cipher);
    }

    /**
     * 解码带有{@link #SM4_STR_PREFIX}固定前缀的SM4密文
     * <pre>
     * 1.null -> null
     * 2.不带有{@link #SM4_STR_PREFIX}固定前缀 -> 返回原文
     * 3.带{@link #SM4_STR_PREFIX}固定前缀 -> 去掉{@link #SM4_STR_PREFIX}固定前缀，将剩余部分进行SM4解码
     * </pre>
     *
     * @param ciphertext 密文
     * @return 明文
     * @throws Exception 解密失败抛出的异常
     */
    public static String decodeIfSM4Prefix(String ciphertext) throws Exception {
        if (ciphertext == null) {
            return null;
        }
        if (!isSM4PrefixCiphertext(ciphertext)) {
            return ciphertext;
        }
        return decryptEcb(ciphertext.substring(SM4_STR_PREFIX.length()));
    }

    /**
     * 加密成带有固定前缀{@link #SM4_STR_PREFIX}的密文
     *
     * @param data 明文
     * @return 带有固定前缀{@link #SM4_STR_PREFIX}的密文
     * @throws Exception 解密失败抛出的异常
     */
    public static String encryptToSM4PrefixStr(String data) throws Exception {
        if (data == null) {
            return null;
        }
        String ciphertext = encryptEcb(data);
        return SM4_STR_PREFIX + ciphertext;
    }

    public static boolean isSM4PrefixCiphertext(String ciphertext) {
        return ciphertext != null && ciphertext.startsWith(SM4_STR_PREFIX);
    }


    public static void main(String[] args) throws Exception {
        //test1();
    }

    private static void test2() throws Exception {
        String data = "2023-07-12,下午三点";
        String s = encryptToSM4PrefixStr(data);
        System.out.println(s);

        String s1 = decodeIfSM4Prefix(s);
        System.out.println(s1);
    }

    private static void test1() {
        try {
            String data = "2023-07-12,下午三点";
            //生成key
            String key = generateKey();
            System.out.println("key:" + key);
            //加密
            String cipher = SM4Util.encryptEcb(key, data);
            System.out.println("加密后：" + cipher);
            //判断是否正确
            System.out.println(SM4Util.verifyEcb(key, cipher, data));// true
            //解密
            String res = SM4Util.decryptEcb(key, cipher + 123);
            System.out.println("解密后：" + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
