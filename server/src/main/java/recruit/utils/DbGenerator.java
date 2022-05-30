/**
 * @file MyBatisGenerator.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.utils;

import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;


public class DbGenerator {
  static String prefix = "ENC(";
  static String suffix = ")";

  public static void main(String[] args) throws Exception {
    RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
    List<String> arguments = runtimeMxBean.getInputArguments();
    String secret = "";

    for (String arg : arguments) {
      if (arg.indexOf("-Djasypt.encryptor.password") != -1) {
        secret = arg.split("=")[1];
      }
    }

    // MBG执行过程中的警告信息
    List<String> warnings = new ArrayList<String>();
    // 生成的代码重复时覆盖原代码
    boolean overwrite = true;
    // 读取MBG配置文件
    InputStream is = MyBatisGenerator.class.getResourceAsStream("/generatorConfig.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(is);
    JDBCConnectionConfiguration jdbcConfig = config.getContext("RecruitDB2Tables").getJdbcConnectionConfiguration();
    jdbcConfig.setConnectionURL(decodeEnctyptCode(jdbcConfig.getConnectionURL(), secret));
    jdbcConfig.setUserId(decodeEnctyptCode(jdbcConfig.getUserId(), secret));
    jdbcConfig.setPassword(decodeEnctyptCode(jdbcConfig.getPassword(), secret));
    is.close();

    DefaultShellCallback callback = new DefaultShellCallback(overwrite);


    // 创建MBG
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    // 执行代码生成器
    myBatisGenerator.generate(null);
    // 输出警告信息
    for (String warning : warnings) {
      System.out.println(warning);
    }
  }

  private static String decodeEnctyptCode(String encCode, String secret) {
    if (!encCode.startsWith(prefix) || !encCode.endsWith(suffix)) {
      throw new Error("解密格式错误");
    }

    String encCodeContent = encCode.substring(prefix.length(), encCode.length() - suffix.length());
    StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
    textEncryptor.setPassword(secret);
    return textEncryptor.decrypt(encCodeContent);
  }
}
